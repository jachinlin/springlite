package com.github.jachinlin.springlite.autoscan.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.github.jachinlin.springlite.core.io.Resource;
import com.github.jachinlin.springlite.core.io.support.PackageResourceLoader;

public class PackageResourceLoaderTest {

	@Test
	public void testGetResources() throws IOException{
		PackageResourceLoader loader = new PackageResourceLoader();
		Resource[] resources = loader.getResources("com.github.jachinlin.springlite.service");
		assertEquals(5, resources.length);
		
	}

}
