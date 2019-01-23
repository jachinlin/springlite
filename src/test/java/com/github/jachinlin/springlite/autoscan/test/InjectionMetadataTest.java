package com.github.jachinlin.springlite.autoscan.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.LinkedList;

import org.junit.Test;

import com.github.jachinlin.springlite.beans.factory.annotation.AutowiredFieldElement;
import com.github.jachinlin.springlite.beans.factory.annotation.InjectionElement;
import com.github.jachinlin.springlite.beans.factory.annotation.InjectionMetadata;
import com.github.jachinlin.springlite.beans.factory.support.DefaultBeanFactory;
import com.github.jachinlin.springlite.beans.xml.XmlBeanDefinitionReader;
import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.core.io.Resource;
import com.github.jachinlin.springlite.service.Account;
import com.github.jachinlin.springlite.service.PetStoreServiceV4;

public class InjectionMetadataTest {

	@Test
	public void testInjection() throws Exception{
		
		DefaultBeanFactory factory = new DefaultBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		Resource resource = new ClassPathResource("petstore-v4.xml");
		reader.loadBeanDefinition(resource);
		
		Class<?> clz = PetStoreServiceV4.class;
		LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();
		
		Field f = PetStoreServiceV4.class.getDeclaredField("account");		
		InjectionElement injectionElem = new AutowiredFieldElement(f,true,factory);
		elements.add(injectionElem);
		
		InjectionMetadata metadata = new InjectionMetadata(clz,elements);
		
		PetStoreServiceV4 petStore = new PetStoreServiceV4();
		
		metadata.inject(petStore);
		
		assertTrue(petStore.getAccount() instanceof Account);
		
		
	}

}
