package com.github.jachinlin.springlite.context.support;

import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.context.ApplicationContext;
import com.github.jachinlin.springlite.core.io.ClassPathResource;

public class ClassPathXmlApplicationContext implements ApplicationContext {
	
	private DefaultBeanFactory factory = null;
	
	public ClassPathXmlApplicationContext(String configFile) {
		this.factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource(configFile));
		
	}
	public Object getBean(String beanID) {
		return this.factory.getBean(beanID);
	}

}
