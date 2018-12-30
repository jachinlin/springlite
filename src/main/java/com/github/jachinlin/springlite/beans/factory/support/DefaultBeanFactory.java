package com.github.jachinlin.springlite.beans.factory.support;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.BeanCreationException;
import com.github.jachinlin.springlite.beans.factory.BeanDefinitionStoreException;
import com.github.jachinlin.springlite.beans.factory.BeanFactory;
import com.github.jachinlin.springlite.util.ClassUtils;;

public class DefaultBeanFactory implements BeanFactory {
	
	private static final String ID_ATTRIBUTE = "id";
	private static final String CLASS_ATTRIBUTE = "class";
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);

	
	public DefaultBeanFactory(String configFile) {
		loadBeanDefinitions(configFile);
	}
	
	private void loadBeanDefinitions(String configFile) {
		InputStream is = null;
		try{
			ClassLoader cl = ClassUtils.getDefaultClassLoader();
			is = cl.getResourceAsStream(configFile);
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			
			Element root = doc.getRootElement(); //<beans>
			Iterator<?> iter = root.elementIterator();
			while(iter.hasNext()){
				Element ele = (Element)iter.next();
				String id = ele.attributeValue(ID_ATTRIBUTE);
				String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
				BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
				this.beanDefinitionMap.put(id, bd);
			}
		} catch (DocumentException e) {
			throw new BeanDefinitionStoreException("IOException parsing XML document from " + configFile, e);
		} catch (Exception e) {
			throw new BeanDefinitionStoreException("parse XML document from " + configFile + "failed", e);
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
		
		
	}
	public BeanDefinition getBeanDefinition(String beanID) {

		return this.beanDefinitionMap.get(beanID);
	}

	public Object getBean(String beanID) {
		BeanDefinition bd = this.getBeanDefinition(beanID);
		if(bd == null) {
			throw new BeanCreationException("Bean definition does not exist");
		}
		
		ClassLoader cl = ClassUtils.getDefaultClassLoader();
		String beanClassName = bd.getClassName();
		try {
			Class<?> cls = cl.loadClass(beanClassName);
			return cls.newInstance(); // 缺省无参构造函数
		 } catch (Exception e) {
			throw new BeanCreationException("Create bean for " + beanClassName + "failed", e);
		} 		
	}

}
