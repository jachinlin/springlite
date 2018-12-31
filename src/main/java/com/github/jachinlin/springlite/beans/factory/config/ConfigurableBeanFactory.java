package com.github.jachinlin.springlite.beans.factory.config;

import com.github.jachinlin.springlite.beans.factory.BeanFactory;

public interface ConfigurableBeanFactory extends BeanFactory {
	
	void setBeanClassLoader(ClassLoader beanClassLoader);
	ClassLoader getBeanClassLoader();

}
