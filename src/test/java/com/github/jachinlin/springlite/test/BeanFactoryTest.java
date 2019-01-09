package com.github.jachinlin.springlite.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.BeanCreationException;
import com.github.jachinlin.springlite.beans.factory.BeanDefinitionStoreException;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.service.PetStoreService;

public class BeanFactoryTest {
	
	private DefaultBeanFactory factory;
	private XmlBeanDefinitionReader reader;
	
	@Before
	public void setUp() {
		this.factory = new DefaultBeanFactory();
		this.reader = new XmlBeanDefinitionReader(factory);
	}
	@Test
	public void testGetBeanDefinition() {
		reader.loadBeanDefinition(new ClassPathResource("petstore.xml"));
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		assertEquals("com.github.jachinlin.springlite.service.PetStoreService", bd.getClassName());
	}
	
	@Test
	public void testGetBean() {
		reader.loadBeanDefinition(new ClassPathResource("petstore.xml"));
		PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
		assertNotNull(petStore);
		
	}
	
	@Test(expected=BeanCreationException.class)
	public void testNotExistBean() {
		reader.loadBeanDefinition(new ClassPathResource("petstore.xml"));
		factory.getBean("notExistBean");
	}
	
	@Test(expected=BeanCreationException.class)
	public void testInvalidBean() {
		reader.loadBeanDefinition(new ClassPathResource("petstore.xml"));
		factory.getBean("invalidBean");
	}
	
	@Test(expected=BeanDefinitionStoreException.class)
	public void testNotExistXML() {
		reader.loadBeanDefinition(new ClassPathResource("notExistXML.xml"));
	}
	
	@Test(expected=BeanDefinitionStoreException.class)
	public void testInvalidXML() {
		reader.loadBeanDefinition(new ClassPathResource("invalidXML.xml"));
	}
	
	@Test
	public void testSingletonBean() {
		reader.loadBeanDefinition(new ClassPathResource("petstore.xml"));
		BeanDefinition bd = factory.getBeanDefinition("petStoreSingleton");
		
		assertTrue(bd.isSingleton());
		assertFalse(bd.isPrototype());
		
		PetStoreService petStore1 = (PetStoreService)factory.getBean("petStoreSingleton");
		PetStoreService petStore2 = (PetStoreService)factory.getBean("petStoreSingleton");
		assertTrue(petStore1.equals(petStore2));
	}
	
	@Test
	public void testDefaultScope() {
		reader.loadBeanDefinition(new ClassPathResource("petstore.xml"));
		BeanDefinition bd = factory.getBeanDefinition("petStoreDefaultScope");
		
		assertTrue(bd.isSingleton());
		assertFalse(bd.isPrototype());
		
		PetStoreService petStore1 = (PetStoreService)factory.getBean("petStoreDefaultScope");
		PetStoreService petStore2 = (PetStoreService)factory.getBean("petStoreDefaultScope");
		assertTrue(petStore1.equals(petStore2));
	}
	
	@Test
	public void testPrototypeBean() {
		reader.loadBeanDefinition(new ClassPathResource("petstore.xml"));
		BeanDefinition bd = factory.getBeanDefinition("petStorePrototype");
		
		assertFalse(bd.isSingleton());
		assertTrue(bd.isPrototype());
		
		PetStoreService petStore1 = (PetStoreService)factory.getBean("petStorePrototype");
		PetStoreService petStore2 = (PetStoreService)factory.getBean("petStorePrototype");
		assertFalse(petStore1.equals(petStore2));
	}

}
