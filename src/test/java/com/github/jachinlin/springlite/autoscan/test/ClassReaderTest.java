package com.github.jachinlin.springlite.autoscan.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.objectweb.asm.ClassReader;

import com.github.jachinlin.springlite.annotation.AnnotationAttributes;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.github.jachinlin.springlite.core.type.classreading.ClassMetadataReadingVisitor;

public class ClassReaderTest {

	@Test
	public void testGetClasMetaData() throws IOException {
		ClassPathResource resource = new ClassPathResource("com/github/jachinlin/springlite/service/PetStoreServiceV4.class");
		ClassReader reader = new ClassReader(resource.getInputStream());
		
		ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
		
		reader.accept(visitor, ClassReader.SKIP_DEBUG);
		
		assertFalse(visitor.isAbstract());
		assertFalse(visitor.isInterface());
		assertFalse(visitor.isFinal());		
		assertEquals("com.github.jachinlin.springlite.service.PetStoreServiceV4", visitor.getClassName());
		assertEquals("java.lang.Object", visitor.getSuperClassName());
		assertEquals(0, visitor.getInterfaceNames().length);
	}
	
	@Test
	public void testGetAnnonation() throws Exception{
		ClassPathResource resource = new ClassPathResource("com/github/jachinlin/springlite/service/PetStoreServiceV4.class");
		ClassReader reader = new ClassReader(resource.getInputStream());
		
		AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
		
		reader.accept(visitor, ClassReader.SKIP_DEBUG);
		
		String annotation = "com.github.jachinlin.springlite.stereotype.Component";
		assertTrue(visitor.hasAnnotation(annotation));
		
		AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
		
		assertEquals("petStore", attributes.get("value"));		
		
	}

}
