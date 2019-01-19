package com.github.jachinlin.springlite.beans.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.ConstructorArgument;
import com.github.jachinlin.springlite.beans.PropertyValue;
import com.github.jachinlin.springlite.beans.factory.BeanDefinitionStoreException;
import com.github.jachinlin.springlite.beans.factory.config.RuntimeBeanReference;
import com.github.jachinlin.springlite.beans.factory.config.TypeStringValue;
import com.github.jachinlin.springlite.beans.factory.support.BeanDefinitionRegister;
import com.github.jachinlin.springlite.beans.factory.support.GenericBeanDefinition;
import com.github.jachinlin.springlite.context.annotation.ClassPathBeanDefinitionScanner;
import com.github.jachinlin.springlite.core.io.Resource;
import com.github.jachinlin.springlite.util.StringUtils;

public class XmlBeanDefinitionReader {
	
	private BeanDefinitionRegister register;
	private static final String ID_ATTRIBUTE = "id";
	private static final String CLASS_ATTRIBUTE = "class";
	private static final String SCOPE_ATTRIBUTE = "scope";
	private static final String PROPERTY_ELEMENT = "property";
	private static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";
	private static final String NAME_ATTRIBUTE = "name";
	private static final String REF_ATTRIBUTE = "ref";
	private static final String VALUE_ATTRIBUTE = "value";
	
	public static final String BEANS_NAMESPACE_URI = "http://www.springframework.org/schema/beans";
	public static final String CONTEXT_NAMESPACE_URI = "http://www.springframework.org/schema/context";
	private static final String BASE_PACKAGE_ATTRIBUTE = "base-package";
	
	protected final Log logger = LogFactory.getLog(getClass());
	
	public XmlBeanDefinitionReader(BeanDefinitionRegister register) {
		this.register = register;
	}
	
	
	public void loadBeanDefinition(Resource resource) {
		InputStream is = null;
		try{
			is = resource.getInputStream();
			
			SAXReader reader = new SAXReader();
			Document doc = reader.read(is);
			
			Element root = doc.getRootElement(); //<beans>
			Iterator<?> iter = root.elementIterator();
			while(iter.hasNext()){
				Element ele = (Element)iter.next();
				
				String namespaceUri = ele.getNamespaceURI();
				if(this.isDefaultNamespace(namespaceUri)){
					parseDefaultElement(ele); //普通的bean
				} else if(this.isContextNamespace(namespaceUri)){
					parseComponentElement(ele); //<context:component-scan>
				} 
			}
		} catch (DocumentException e) {
			throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(), e);
		} catch (Exception e) {
			throw new BeanDefinitionStoreException("parse XML document from " + resource.getDescription() + "failed", e);
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
	public boolean isDefaultNamespace(String namespaceUri) {
		return (!StringUtils.hasLength(namespaceUri) || BEANS_NAMESPACE_URI.equals(namespaceUri));
	}
	public boolean isContextNamespace(String namespaceUri){
		return (!StringUtils.hasLength(namespaceUri) || CONTEXT_NAMESPACE_URI.equals(namespaceUri));
	}
	private void parseComponentElement(Element ele) {
		String basePackages = ele.attributeValue(BASE_PACKAGE_ATTRIBUTE);
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(register);
		scanner.doScan(basePackages);		
		
	}
	private void parseDefaultElement(Element ele) {
		String id = ele.attributeValue(ID_ATTRIBUTE);
		String beanClassName = ele.attributeValue(CLASS_ATTRIBUTE);
		BeanDefinition bd = new GenericBeanDefinition(id,beanClassName);
		if (ele.attribute(SCOPE_ATTRIBUTE)!=null) {					
			bd.setScope(ele.attributeValue(SCOPE_ATTRIBUTE));					
		}
		parseConstructorArgElements(ele,bd);
		parsePropertyElement(ele,bd); 
		this.register.registerBeanDefinition(id, bd);
		
	}
	private void parseConstructorArgElements(Element beanEle, BeanDefinition bd) {
		Iterator<?> iter = beanEle.elementIterator(CONSTRUCTOR_ARG_ELEMENT);
		while(iter.hasNext()){
			Element ele = (Element)iter.next();
			parseConstructorArgElement(ele, bd);			
		}
		
	}
	
	public void parseConstructorArgElement(Element ele, BeanDefinition bd) {
		
		
		Object value = parsePropertyValue(ele, bd, null);
		ConstructorArgument.ValueHolder valueHolder = new ConstructorArgument.ValueHolder(value);
		bd.getConstructorArgument().addArgumentValue(valueHolder);		
	}

	private void parsePropertyElement(Element beanElem, BeanDefinition bd) {
		Iterator<?> iter= beanElem.elementIterator(PROPERTY_ELEMENT);
		while(iter.hasNext()){
			Element propElem = (Element)iter.next();
			String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
			if (!StringUtils.hasLength(propertyName)) {
				logger.fatal("Tag 'property' must have a 'name' attribute");
				break;
			}
	
			Object val = parsePropertyValue(propElem, bd, propertyName);
			PropertyValue pv = new PropertyValue(propertyName, val);
			
			bd.addPropertyValue(pv);
		}
		
	}
	
	private Object parsePropertyValue(Element ele, BeanDefinition bd, String propertyName) {
		String elementName = (propertyName != null) ?
				"<property> element for property '" + propertyName + "'" :
				"<constructor-arg> element";

		
		boolean hasRefAttribute = (ele.attribute(REF_ATTRIBUTE)!=null);
		boolean hasValueAttribute = (ele.attribute(VALUE_ATTRIBUTE)!=null);
		
		
		if (hasRefAttribute) {
			String refName = ele.attributeValue(REF_ATTRIBUTE);
			if (!StringUtils.hasText(refName)) {
				logger.error(elementName + " contains empty 'ref' attribute");
			}
			RuntimeBeanReference ref = new RuntimeBeanReference(refName);			
			return ref;
		} else if (hasValueAttribute) {
			String v = ele.attributeValue(VALUE_ATTRIBUTE);
			TypeStringValue valueHolder = new TypeStringValue(v);
			return valueHolder;
		}
		else {
			
			throw new RuntimeException(elementName + " must specify a ref or value");
		}
	}
}
