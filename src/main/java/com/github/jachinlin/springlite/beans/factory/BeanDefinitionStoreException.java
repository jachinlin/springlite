package com.github.jachinlin.springlite.beans.factory;

import com.github.jachinlin.springlite.beans.BeansException;

public class BeanDefinitionStoreException extends BeansException {
	
	public BeanDefinitionStoreException(String msg) {
		super(msg);
	}
	
	public BeanDefinitionStoreException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
