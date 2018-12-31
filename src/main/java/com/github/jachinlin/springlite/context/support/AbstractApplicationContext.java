package com.github.jachinlin.springlite.context.support;

import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.context.ApplicationContext;
import com.github.jachinlin.springlite.core.io.Resource;
import com.github.jachinlin.springlite.util.ClassUtils;

public abstract class AbstractApplicationContext implements ApplicationContext{
	private DefaultBeanFactory factory = null;
	private ClassLoader beanClassLoader;
	
	public AbstractApplicationContext(String configFile) {
		this.factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(this.getResource(configFile));
	}

	protected abstract Resource getResource(String configFile);

	public Object getBean(String beanID) {
		return this.factory.getBean(beanID);
	}
	
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
		this.factory.setBeanClassLoader(beanClassLoader);
		
	}

	public ClassLoader getBeanClassLoader() {
		return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
	}
}
