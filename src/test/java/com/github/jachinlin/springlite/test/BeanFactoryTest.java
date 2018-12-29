package com.github.jachinlin.springlite.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.BeanFactory;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.service.PetStoreService;

public class BeanFactoryTest {

	@Test
	public void testGetBeanDefinition() {
		BeanFactory factory = new DefaultBeanFactory("petstore.xml");
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		assertEquals("com.github.jachinlin.springlite.service.PetStoreService", bd.getClassName());
	}
	
	@Test
	public void testGetBean() {
		BeanFactory factory = new DefaultBeanFactory("petstore.xml");
		
		PetStoreService petStore = (PetStoreService)factory.getBean("petStore");
		
		assertNotNull(petStore);
		
	}

}
