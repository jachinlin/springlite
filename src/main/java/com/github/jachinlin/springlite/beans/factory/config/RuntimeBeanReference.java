package com.github.jachinlin.springlite.beans.factory.config;

public class RuntimeBeanReference {
	private String beanName;
	
	public RuntimeBeanReference(String beanName) {
		this.beanName = beanName;
	}
	
	public String getBeanName() {
		return this.beanName;
	}
}
