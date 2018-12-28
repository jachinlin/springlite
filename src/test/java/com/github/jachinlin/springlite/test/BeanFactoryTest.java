package com.github.jachinlin.springlite.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.BeanFactoy;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;

public class BeanFactoryTest {

	@Test
	public void testGetBeanDefinition() {
		BeanFactoy factory = new DefaultBeanFactory("petstore.xml");
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		assertEquals("com.github.jachinlin.springlite.service.PetStoreService", bd.getClassName());
	}

}
