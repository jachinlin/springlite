package com.github.jachinlin.springlite.aop;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;

import com.github.jachinlin.springlite.aop.config.MethodLocatingFactory;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.core.io.Resource;
import com.github.jachinlin.springlite.service.TransactionManager;

public class MethodLocatingFactoryTest {

	@Test
	public void testGetMethod() throws Exception{
		DefaultBeanFactory beanFactory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
		Resource resource = new ClassPathResource("petstore-v5.xml");
		reader.loadBeanDefinition(resource);
		
		MethodLocatingFactory methodLocatingFactory = new MethodLocatingFactory();
		methodLocatingFactory.setTargetBeanName("tx");
		methodLocatingFactory.setMethodName("start");
		methodLocatingFactory.setBeanFactory(beanFactory);
		
		Method m = methodLocatingFactory.getObject();
		
		assertTrue(TransactionManager.class.equals(m.getDeclaringClass()));
		assertTrue(m.equals(TransactionManager.class.getMethod("start")));
		
	}

}
