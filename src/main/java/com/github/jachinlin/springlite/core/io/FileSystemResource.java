package com.github.jachinlin.springlite.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileSystemResource implements Resource {

	private String path;
	private File file;
	
	public FileSystemResource(String path) {
		// TODO assert file exist
		
		this.path = path;
		this.file = new File(path);
		
	}
	public FileSystemResource(File file) {		
		this.path = file.getPath();
		this.file = file;
	}
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(this.file);
	}
	public String getDescription() {
		
		return this.path;
	}

}
