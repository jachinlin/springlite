package com.github.jachinlin.springlite.constructorinjection.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.support.ConstructorResolver;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.service.PetStoreServiceV3;

public class ConstructorResolverTest {
	
	private DefaultBeanFactory factory;
	private XmlBeanDefinitionReader reader;
	
	@Before
	public void setUp() {
		factory = new DefaultBeanFactory();
		reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));
	}
	
	@Test
	public void testAutowireConstructor() {
		

		BeanDefinition bd = factory.getBeanDefinition("petStoreV3");
		
		ConstructorResolver resolver = new ConstructorResolver(factory);
		
		PetStoreServiceV3 petStore = (PetStoreServiceV3)resolver.autowireConstructor(bd);
		
		assertNotNull(petStore.getAccount());
		assertEquals(3, petStore.getVersion());
		
		
		
		
	}

}
