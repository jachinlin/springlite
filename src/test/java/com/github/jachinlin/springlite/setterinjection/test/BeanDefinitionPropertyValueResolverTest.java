package com.github.jachinlin.springlite.setterinjection.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.factory.config.RuntimeBeanReference;
import com.github.jachinlin.springlite.beans.factory.support.BeanDefinitionValueResolver;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.service.Account;

public class BeanDefinitionPropertyValueResolverTest {
	
	private DefaultBeanFactory factory;
	private XmlBeanDefinitionReader reader;
	
	@Before
	public void setUp() {
		this.factory = new DefaultBeanFactory();
		this.reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));
	}
	
	@Test
	public void testResolveRuntimeBeanReference() {
		
		BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
		
		RuntimeBeanReference reference = new RuntimeBeanReference("account");
		Object value = resolver.resolveValueIfNecessary(reference);
		
		assertNotNull(value);		
		assertTrue(value instanceof Account);				
	}

}
