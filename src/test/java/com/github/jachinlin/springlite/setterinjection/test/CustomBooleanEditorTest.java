package com.github.jachinlin.springlite.setterinjection.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.propertyeditors.CustomBooleanEditor;

public class CustomBooleanEditorTest {
	
	private CustomBooleanEditor editor;
	
	@Before
	public void setUp() {
		this.editor = new CustomBooleanEditor(true);
	}
	
	@Test
	public void testConvertStringTrue(){
		editor.setAsText("true");
		assertEquals(true, ((Boolean)editor.getValue()).booleanValue());
	}
	
	@Test
	public void testConvertStringFalse(){
		editor.setAsText("false");
		assertEquals(false, ((Boolean)editor.getValue()).booleanValue());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testConvertStringException(){	
		editor.setAsText("abc");
	}

}
