package com.github.jachinlin.springlite.autoscan.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.jachinlin.springlite.annotation.AnnotationAttributes;
import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.context.annotation.ClassPathBeanDefinitionScanner;
import com.github.jachinlin.springlite.context.annotation.ScannedGenericBeanDefinition;
import com.github.jachinlin.springlite.core.type.AnnotationMetadata;
import com.github.jachinlin.springlite.stereotype.Component;

public class ClassPathBeanDefinitionScannerTest {

	@Test
	public void testParseScanedBean(){
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		
		String basePackages = "com.github.jachinlin.springlite.service";
		
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
		scanner.doScan(basePackages);
		
		
		String annotation = Component.class.getName();
		
		
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		assertTrue(bd instanceof ScannedGenericBeanDefinition);
		assertEquals("com.github.jachinlin.springlite.service.PetStoreServiceV4", bd.getClassName());
		
		ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
		AnnotationMetadata amd = sbd.getMetadata();
		assertTrue(amd.hasAnnotation(annotation));		
		AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);		
		assertEquals("petStore", attributes.get("value"));
	}
		

}
