package com.github.jachinlin.springlite.context.annotation;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.core.type.AnnotationMetadata;

public interface AnnotatedBeanDefinition extends BeanDefinition {
	AnnotationMetadata getMetadata();
}
