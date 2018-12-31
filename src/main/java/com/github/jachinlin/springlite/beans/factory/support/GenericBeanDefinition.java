package com.github.jachinlin.springlite.beans.factory.support;

import com.github.jachinlin.springlite.beans.BeanDefinition;

public class GenericBeanDefinition implements BeanDefinition {


	private String beanID;
	private String beanClassName;
	private boolean singleton = false;
	private boolean prototype = false;
	private String scope;

	public GenericBeanDefinition(String beanID, String beanClassName) {
		this.beanID = beanID;
		this.beanClassName = beanClassName;
	}

	public String getClassName() {

		return this.beanClassName;
	}

	public String getScope() {
		return this.scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
		this.singleton = SCOPE_SINGLETON.equals(scope);
		this.prototype = SCOPE_PROTOTYPE.equals(scope);
	}

	public boolean isSingleton() {
		
		return this.singleton ;
	}

	public boolean isPrototype() {
		
		return this.prototype ;
	}

}
