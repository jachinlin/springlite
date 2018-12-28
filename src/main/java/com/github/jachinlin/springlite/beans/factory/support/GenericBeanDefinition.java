package com.github.jachinlin.springlite.beans.factory.support;

import com.github.jachinlin.springlite.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {


	private String beanID;
	private String beanClassName;

	public GenericBeanDefinition(String beanID, String beanClassName) {
		this.beanID = beanID;
		this.beanClassName = beanClassName;
	}

	public String getClassName() {

		return this.beanClassName;
	}

}
