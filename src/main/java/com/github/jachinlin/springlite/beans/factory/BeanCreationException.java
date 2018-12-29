package com.github.jachinlin.springlite.beans.factory;

import com.github.jachinlin.springlite.beans.BeansException;

public class BeanCreationException extends BeansException {
	
	private String beanName;
	
	public BeanCreationException(String msg) {
		super(msg);
	}
	
	public BeanCreationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
