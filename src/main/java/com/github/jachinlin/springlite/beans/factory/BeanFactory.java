package com.github.jachinlin.springlite.beans.factory;

import com.github.jachinlin.springlite.beans.BeanDefinition;


public interface BeanFactory {

	BeanDefinition getBeanDefinition(String beanID);

	Object getBean(String beanID);

}
