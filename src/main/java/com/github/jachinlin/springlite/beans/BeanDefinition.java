package com.github.jachinlin.springlite.beans;

public interface BeanDefinition {
	
	public static final String SCOPE_SINGLETON = "singleton";
	public static final String SCOPE_PROTOTYPE = "prototype";
	
	public String getScope();
	public void setScope(String scope);
	
	String getClassName();
	
	boolean isSingleton();
	boolean isPrototype();

}
