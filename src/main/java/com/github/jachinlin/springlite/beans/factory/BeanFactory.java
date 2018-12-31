package com.github.jachinlin.springlite.beans.factory;

import com.github.jachinlin.springlite.beans.BeanDefinition;


public interface BeanFactory {

	BeanDefinition getBeanDefinition(String beanID);
	void registerBeanDefinition(String beanID, BeanDefinition bd);

	Object getBean(String beanID);

}
