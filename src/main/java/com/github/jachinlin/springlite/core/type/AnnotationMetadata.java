package com.github.jachinlin.springlite.core.type;

import java.util.Set;

import com.github.jachinlin.springlite.annotation.AnnotationAttributes;

public interface AnnotationMetadata extends ClassMetadata{
	
	Set<String> getAnnotationTypes();


	boolean hasAnnotation(String annotationType);
	
	public AnnotationAttributes getAnnotationAttributes(String annotationType);
}
