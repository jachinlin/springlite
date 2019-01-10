package com.github.jachinlin.springlite.setterinjection.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.propertyeditors.CustomNumberEditor;


public class CustomNumberEditorTest {
	
	private CustomNumberEditor editor;
	
	@Before
	public void setUp() {
		this.editor = new CustomNumberEditor(Integer.class, true);
	}
	
	@Test
	public void testConvertString() {
		
		editor.setAsText("3");
		Object value = editor.getValue();
		assertTrue(value instanceof Integer);		
		assertEquals(3, ((Integer)editor.getValue()).intValue());		
		
	}
	
	@Test
	public void testConvertNull() {
		
		editor.setAsText("");
		assertTrue(editor.getValue() == null);	
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertException() {
		
		editor.setAsText("3.1");	
		
	}
}
