package com.github.jachinlin.springlite.autoscan.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.github.jachinlin.springlite.beans.factory.annotation.AutowiredAnnotationProcessor;
import com.github.jachinlin.springlite.beans.factory.annotation.AutowiredFieldElement;
import com.github.jachinlin.springlite.beans.factory.annotation.InjectionElement;
import com.github.jachinlin.springlite.beans.factory.annotation.InjectionMetadata;
import com.github.jachinlin.springlite.service.PetStoreServiceV4;

public class AutowiredAnnotationProcessorTest {

	@Test
	public void testGetInjectionMetadata(){
		
		AutowiredAnnotationProcessor processor = new AutowiredAnnotationProcessor();
		InjectionMetadata injectionMetadata = processor.buildAutowiringMetadata(PetStoreServiceV4.class);
		List<InjectionElement> elements = injectionMetadata.getInjectionElements();
		
		assertEquals(1, elements.size());
		
		assertEquals("account", ((AutowiredFieldElement)elements.get(0)).getField().getName());
		
		
	}


}
