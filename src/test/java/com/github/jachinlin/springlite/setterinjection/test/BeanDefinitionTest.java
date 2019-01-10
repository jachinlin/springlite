package com.github.jachinlin.springlite.setterinjection.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.PropertyValue;
import com.github.jachinlin.springlite.beans.factory.config.RuntimeBeanReference;
import com.github.jachinlin.springlite.beans.factory.config.TypeStringValue;
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
		reader.loadBeanDefinition(new ClassPathResource("petstore-v2.xml"));
	}
	
	@Test
	public void testPropertyValueOfRef() {
		
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		List<PropertyValue> pvs = bd.getPropertyValues();
		PropertyValue pv = this.getPropertyValue("account", pvs);
		
		assertNotNull(pv);
		assertTrue(pv.getValue() instanceof RuntimeBeanReference);
		
	}
	
	@Test
	public void testPropertyValueOfValue() {
		
		BeanDefinition bd = factory.getBeanDefinition("petStore");
		List<PropertyValue> pvs = bd.getPropertyValues();
		PropertyValue pv = this.getPropertyValue("owner", pvs);
		
		assertNotNull(pv);
		assertTrue(pv.getValue() instanceof TypeStringValue);
		assertTrue(((TypeStringValue)pv.getValue()).getValue().equals("com.github.jachinlin"));
		
	}
	
	private PropertyValue getPropertyValue(String name, List<PropertyValue> pvs){
		for(PropertyValue pv : pvs){
			if(pv.getName().equals(name)){
				return pv;
			}
		}
		return null;
	}

}
