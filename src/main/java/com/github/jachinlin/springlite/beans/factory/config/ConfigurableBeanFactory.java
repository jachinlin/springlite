package com.github.jachinlin.springlite.beans.factory.config;

public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {
	
	void setBeanClassLoader(ClassLoader beanClassLoader);
	ClassLoader getBeanClassLoader();

}
