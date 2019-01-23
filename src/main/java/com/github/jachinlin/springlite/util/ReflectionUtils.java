package com.github.jachinlin.springlite.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public abstract class ReflectionUtils {
	
	public static void makeAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()) ||
				!Modifier.isPublic(field.getDeclaringClass().getModifiers()) ||
				Modifier.isFinal(field.getModifiers())) && !field.isAccessible()) {
			field.setAccessible(true);
		}
	}
			
}
