package com.github.jachinlin.springlite.autoscan.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.service.Account;
import com.github.jachinlin.springlite.service.PetStoreServiceV4;

public class BeanFactoryTest {

	private DefaultBeanFactory factory;
	private XmlBeanDefinitionReader reader;
	
	@Before
	public void setUp() {
		this.factory = new DefaultBeanFactory();
		this.reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("petstore-v4.xml"));
	}
	
	@Test
	public void testConstructorInjection() {
		
		PetStoreServiceV4 petStore = (PetStoreServiceV4)factory.getBean("petStore");
	
		assertTrue(petStore.getAccount() instanceof Account );
	}

}
