package com.github.jachinlin.springlite.beans.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.BeanDefinitionStoreException;
import com.github.jachinlin.springlite.beans.factory.support.BeanDefinitionRegister;
import com.github.jachinlin.springlite.beans.factory.support.GenericBeanDefinition;
import com.github.jachinlin.springlite.util.ClassUtils;

public class XmlBeanDefinitionReader {
	
	private BeanDefinitionRegister register;
	private static final String ID_ATTRIBUTE = "id";
	private static final String CLASS_ATTRIBUTE = "class";
	
	public XmlBeanDefinitionReader(BeanDefinitionRegister register) {
		this.register = register;
	}
	
	
	public void loadBeanDefinition(String configFile) {
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
				this.register.registerBeanDefinition(id, bd);
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
}
