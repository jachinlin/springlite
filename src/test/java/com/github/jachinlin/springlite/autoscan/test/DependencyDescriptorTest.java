package com.github.jachinlin.springlite.autoscan.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;

import com.github.jachinlin.springlite.beans.factory.config.DependencyDescriptor;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.core.io.Resource;
import com.github.jachinlin.springlite.service.Account;
import com.github.jachinlin.springlite.service.PetStoreServiceV4;

public class DependencyDescriptorTest {

	@Test
	public void testResolveDependency() throws Exception{
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("petstore-v4.xml");
		reader.loadBeanDefinition(resource);
		
		Field f = PetStoreServiceV4.class.getDeclaredField("account");
		DependencyDescriptor  descriptor = new DependencyDescriptor(f,true);
		Object o = factory.resolveDependency(descriptor);
		assertTrue(o instanceof Account);
	}

}
