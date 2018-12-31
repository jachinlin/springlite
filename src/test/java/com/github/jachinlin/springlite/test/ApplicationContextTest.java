package com.github.jachinlin.springlite.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.jachinlin.springlite.context.ApplicationContext;
import com.github.jachinlin.springlite.context.support.ClassPathXmlApplicationContext;
import com.github.jachinlin.springlite.context.support.FileSystemApplicationContext;
import com.github.jachinlin.springlite.service.PetStoreService;

public class ApplicationContextTest {

	@Test
	public void testGetBean() {
		ApplicationContext context = new ClassPathXmlApplicationContext("petstore.xml");
		PetStoreService petStore = (PetStoreService) context.getBean("petStore");
		
		assertNotNull(petStore);
	}
	
	@Test
	public void testFileSystemeApplicationContextGetBean() {
		ApplicationContext context = new FileSystemApplicationContext("src/test/resources/petstore.xml");
		PetStoreService petStore = (PetStoreService) context.getBean("petStore");
		
		assertNotNull(petStore);
	}

}
