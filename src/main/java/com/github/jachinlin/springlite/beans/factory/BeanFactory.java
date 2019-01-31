package com.github.jachinlin.springlite.beans.factory;


public interface BeanFactory {

	Object getBean(String beanID);

	Class<?> getType(String beanName) throws NoSuchBeanDefinitionException;

}
