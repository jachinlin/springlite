package com.github.jachinlin.springlite.beans.factory.support;

import com.github.jachinlin.springlite.beans.factory.BeanFactory;
import com.github.jachinlin.springlite.beans.factory.config.RuntimeBeanReference;

public class BeanDefinitionValueResolver {
	private BeanFactory beanFactory;
	
	public BeanDefinitionValueResolver(BeanFactory factory) {
		this.beanFactory = factory;
	}

	public Object resolveValueIfNecessary(Object value) {
		if (value instanceof RuntimeBeanReference) {
			RuntimeBeanReference ref = (RuntimeBeanReference) value;			
			String refName = ref.getBeanName();			
			Object bean = this.beanFactory.getBean(refName);				
			return bean;
			
		} else {
			//TODO
			throw new RuntimeException("the value " + value +" has not implemented");
		}
	}

}
