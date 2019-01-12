package com.github.jachinlin.springlite.beans;

import java.util.List;

public interface BeanDefinition {
	
	public static final String SCOPE_SINGLETON = "singleton";
	public static final String SCOPE_PROTOTYPE = "prototype";
	
	public String getScope();
	public void setScope(String scope);
	
	String getClassName();
	
	boolean isSingleton();
	boolean isPrototype();
	public List<PropertyValue> getPropertyValues();
	public void addPropertyValue(PropertyValue pv);
	public ConstructorArgument getConstructorArgument();
	public String getBeanID();
	public boolean hasConstructorArgumentValues();

}
