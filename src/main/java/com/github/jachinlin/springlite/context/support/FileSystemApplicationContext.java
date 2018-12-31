package com.github.jachinlin.springlite.context.support;

import com.github.jachinlin.springlite.core.io.FileSystemResource;
import com.github.jachinlin.springlite.core.io.Resource;

public class FileSystemApplicationContext extends AbstractApplicationContext {

	public FileSystemApplicationContext(String configFile) {
		super(configFile);
	}

	@Override
	protected Resource getResource(String configFile) {
		return new FileSystemResource(configFile);
	}
	
}
