package com.github.jachinlin.springlite.beans.factory.config;

public interface SingletonBeanRegistry {
	
	void registerSingleton(String beanName, Object singletonObj);
	Object getSingleton(String beanName);
}
