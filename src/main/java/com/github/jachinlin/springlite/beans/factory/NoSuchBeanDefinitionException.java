package com.github.jachinlin.springlite.beans.factory;

import com.github.jachinlin.springlite.beans.BeansException;

public class NoSuchBeanDefinitionException extends BeansException {

	public NoSuchBeanDefinitionException(String name) {
		super("No bean named '" + name + "' is defined");
	}
}
