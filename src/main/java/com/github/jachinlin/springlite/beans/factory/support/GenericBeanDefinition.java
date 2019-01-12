package com.github.jachinlin.springlite.beans.factory.support;

import java.util.ArrayList;
import java.util.List;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.ConstructorArgument;
import com.github.jachinlin.springlite.beans.PropertyValue;

public class GenericBeanDefinition implements BeanDefinition {


	private String beanID;
	private String beanClassName;
	private boolean singleton = true;
	private boolean prototype = false;
	private String scope;
	
	List<PropertyValue> propertyValues = new ArrayList<PropertyValue>();
	private ConstructorArgument constructorArgument = new ConstructorArgument();
	
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

	public List<PropertyValue> getPropertyValues() {
		return this.propertyValues;
	}

	public void addPropertyValue(PropertyValue pv) {
		this.propertyValues.add(pv);
		
	}

	public ConstructorArgument getConstructorArgument() {
		return this.constructorArgument ;
	}

	public String getBeanID() {
		return beanID;
	}

}
