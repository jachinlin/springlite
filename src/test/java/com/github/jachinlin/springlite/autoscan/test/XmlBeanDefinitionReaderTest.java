package com.github.jachinlin.springlite.autoscan.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.jachinlin.springlite.annotation.AnnotationAttributes;
import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.context.annotation.ScannedGenericBeanDefinition;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.core.io.Resource;
import com.github.jachinlin.springlite.core.type.AnnotationMetadata;
import com.github.jachinlin.springlite.stereotype.Component;

public class XmlBeanDefinitionReaderTest {


	@Test
	public void testParseScanedBean(){
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("petstore-v4.xml");
		reader.loadBeanDefinition(resource);
		String annotation = Component.class.getName();
		
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		assertTrue(bd instanceof ScannedGenericBeanDefinition);
		
		ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
		AnnotationMetadata amd = sbd.getMetadata();
		assertTrue(amd.hasAnnotation(annotation));		
		AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);		
		assertEquals("petStore", attributes.get("value"));
		
	
	}

}
