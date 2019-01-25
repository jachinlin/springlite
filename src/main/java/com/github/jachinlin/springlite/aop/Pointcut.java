package com.github.jachinlin.springlite.aop;

import java.lang.reflect.Method;

public interface Pointcut {
	String getExpression();
	void setExpression(String expression);
	boolean matches(Method method);
}
