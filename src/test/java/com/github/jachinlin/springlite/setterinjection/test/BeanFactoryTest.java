package com.github.jachinlin.springlite.setterinjection.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.service.Account;
import com.github.jachinlin.springlite.service.PetStoreServiceV2;

public class BeanFactoryTest {

	private DefaultBeanFactory factory;
	private XmlBeanDefinitionReader reader;
	
	@Before
	public void setUp() {
		this.factory = new DefaultBeanFactory();
		this.reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));
	}
	
	@Test
	public void testRefInjection() {
		
		PetStoreServiceV2 petStore = (PetStoreServiceV2)factory.getBean("petStore");
		
		 assertNotNull(petStore.getAccount());
		 assertTrue(petStore.getAccount() instanceof Account );
	}

}
