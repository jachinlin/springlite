package com.github.jachinlin.springlite.constructorinjection.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.service.Account;
import com.github.jachinlin.springlite.service.PetStoreServiceV3;

public class BeanFactoryTest {

	private DefaultBeanFactory factory;
	private XmlBeanDefinitionReader reader;
	
	@Before
	public void setUp() {
		this.factory = new DefaultBeanFactory();
		this.reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));
	}
	
	@Test
	public void testConstructorInjection() {
		
		PetStoreServiceV3 petStore = (PetStoreServiceV3)factory.getBean("petStoreV3");
	
		assertTrue(petStore.getAccount() instanceof Account );
		assertEquals(3, petStore.getVersion());
	}

}
