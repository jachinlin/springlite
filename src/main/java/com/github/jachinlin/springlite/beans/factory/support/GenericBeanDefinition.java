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
	private Class<?> beanClass;
	
	public GenericBeanDefinition(String beanID, String beanClassName) {
		this.beanID = beanID;
		this.beanClassName = beanClassName;
	}

	public GenericBeanDefinition() {
	}

	public String getClassName() {

		return this.beanClassName;
	}

	public String getScope() {
		return this.scope;
	}
	
	public void setBeanClassName(String className){
		this.beanClassName = className;
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
	public void setBeanId(String id){
		this.beanID = id;
	}
	public boolean hasConstructorArgumentValues() {
		return !this.constructorArgument.isEmpty();
	}

	public boolean hasBeanClass(){
		return this.beanClass != null;
	}

	public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException{
		String className = getClassName();
		if (className == null) {
			return null;
		}
		Class<?> resolvedClass = classLoader.loadClass(className);
		this.beanClass = resolvedClass;
		return resolvedClass;
	}

	public Class<?> getBeanClass() throws IllegalStateException {
		if(this.beanClass == null){
			throw new IllegalStateException(
					"Bean class name [" + this.getClassName() + "] has not been resolved into an actual Class");
		}		
		return this.beanClass;
	}

}
