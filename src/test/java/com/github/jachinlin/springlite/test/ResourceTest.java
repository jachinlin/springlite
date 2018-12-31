package com.github.jachinlin.springlite.test;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import com.github.jachinlin.springlite.core.io.ClassPathResource;
import com.github.jachinlin.springlite.core.io.FileSystemResource;
import com.github.jachinlin.springlite.core.io.Resource;

public class ResourceTest {

	@Test
	public void testClassPathResource() throws IOException{
		Resource r = new ClassPathResource("petstore.xml");
		
		InputStream is = null;
		
		try {
			is = r.getInputStream();
			assertNotNull(is);
		} finally {
			if(is != null) {
				is.close();
			}
		}
		
	}
	
	@Test
	public void testFileSystemResource() throws IOException{
		Resource r = new FileSystemResource("src/test/resources/petstore.xml");
		
		InputStream is = null;
		
		try {
			is = r.getInputStream();
			assertNotNull(is);
		} finally {
			if(is != null) {
				is.close();
			}
		}
		
	}

}
