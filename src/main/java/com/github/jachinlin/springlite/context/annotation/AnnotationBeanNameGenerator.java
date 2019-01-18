package com.github.jachinlin.springlite.context.annotation;

import java.beans.Introspector;
import java.util.Set;

import com.github.jachinlin.springlite.annotation.AnnotationAttributes;
import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.support.BeanDefinitionRegister;
import com.github.jachinlin.springlite.beans.factory.support.BeanNameGenerator;
import com.github.jachinlin.springlite.core.type.AnnotationMetadata;
import com.github.jachinlin.springlite.util.ClassUtils;
import com.github.jachinlin.springlite.util.StringUtils;

public class AnnotationBeanNameGenerator implements BeanNameGenerator {

	
	
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegister registry) {
		if (definition instanceof AnnotatedBeanDefinition) {
			String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
			if (StringUtils.hasText(beanName)) {
				// Explicit bean name found.
				return beanName;
			}
		}
		return buildDefaultBeanName(definition, registry);
	}
	
	/**
	 * Derive a bean name from one of the annotations on the class.
	 * @param annotatedDef the annotation-aware bean definition
	 * @return the bean name, or {@code null} if none is found
	 */
	protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotatedDef) {
		AnnotationMetadata amd = annotatedDef.getMetadata();
		Set<String> types = amd.getAnnotationTypes();
		String beanName = null;
		for (String type : types) {
			AnnotationAttributes attributes = amd.getAnnotationAttributes(type);
			if (attributes.get("value") != null) {
				Object value = attributes.get("value");
				if (value instanceof String) {
					String strVal = (String) value;
					if (StringUtils.hasLength(strVal)) {						
						beanName = strVal;
					}
				}
			}
		}
		return beanName;
	}
	
	protected String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegister registry) {
		return buildDefaultBeanName(definition);
	}
	protected String buildDefaultBeanName(BeanDefinition definition) {
		String shortClassName = ClassUtils.getShortName(definition.getClassName());
		return Introspector.decapitalize(shortClassName);
	}

}
