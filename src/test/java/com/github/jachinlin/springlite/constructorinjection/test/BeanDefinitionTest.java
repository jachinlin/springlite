package com.github.jachinlin.springlite.constructorinjection.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.ConstructorArgument;
import com.github.jachinlin.springlite.beans.ConstructorArgument.ValueHolder;
import com.github.jachinlin.springlite.beans.factory.config.RuntimeBeanReference;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.core.io.ClassPathResource;

public class BeanDefinitionTest {
	
	private DefaultBeanFactory factory;
	private XmlBeanDefinitionReader reader;
	
	@Before
	public void setUp() {
		this.factory = new DefaultBeanFactory();
		this.reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinition(new ClassPathResource("petstore-v3.xml"));
	}
	
	@Test
	public void testConstrutorArgument() {
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		
		ConstructorArgument args = bd.getConstructorArgument();
		List<ValueHolder> valueHolders = args.getArgumentValues();
		Object accountRef = valueHolders.get(0).getValue();
		
		assertTrue(accountRef instanceof RuntimeBeanReference );
		assertEquals("account", ((RuntimeBeanReference) accountRef).getBeanName());
	}

}
