package com.github.jachinlin.springlite.context.annotation;

import com.github.jachinlin.springlite.beans.factory.support.GenericBeanDefinition;
import com.github.jachinlin.springlite.core.type.AnnotationMetadata;

public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

	private final AnnotationMetadata metadata;


	public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
		super();
		
		this.metadata = metadata;
		
		setBeanClassName(this.metadata.getClassName());
	}


	public final AnnotationMetadata getMetadata() {
		return this.metadata;
	}

}
