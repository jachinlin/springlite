package com.github.jachinlin.springlite.beans.factory.support;

import com.github.jachinlin.springlite.beans.BeanDefinition;

public interface BeanDefinitionRegister {
	BeanDefinition getBeanDefinition(String beanID);
	void registerBeanDefinition(String beanID, BeanDefinition bd);
}
