package com.github.jachinlin.springlite.autoscan.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.github.jachinlin.springlite.annotation.AnnotationAttributes;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.core.type.AnnotationMetadata;
import com.github.jachinlin.springlite.core.type.classreading.MetadataReader;
import com.github.jachinlin.springlite.core.type.classreading.SimpleMetadataReader;
import com.github.jachinlin.springlite.stereotype.Component;

public class MetadataReaderTest {

	@Test
	public void testGetMetadata() throws IOException{
		ClassPathResource resource = new ClassPathResource("com/github/jachinlin/springlite/service/PetStoreServiceV4.class");
		
		MetadataReader reader = new SimpleMetadataReader(resource);
		AnnotationMetadata amd = reader.getAnnotationMetadata();
		
		String annotation = Component.class.getName();
		
		// test annotation meta
		assertTrue(amd.hasAnnotation(annotation));		
		AnnotationAttributes attributes = amd.getAnnotationAttributes(annotation);		
		assertEquals("petStore", attributes.get("value"));
		
		// test class meta
		assertFalse(amd.isAbstract());		
		assertFalse(amd.isFinal());
		assertEquals("com.github.jachinlin.springlite.service.PetStoreServiceV4", amd.getClassName());
		
	}

}
