package com.github.jachinlin.springlite.setterinjection.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.jachinlin.springlite.beans.SimpleTypeConverter;
import com.github.jachinlin.springlite.beans.TypeConverter;
import com.github.jachinlin.springlite.beans.TypeMismatchException;

public class TypeConverterTest {
	
	private TypeConverter converter;
	
	@Before
	public void setUp() {
		this.converter = new SimpleTypeConverter();
	}
	
	@Test
	public void testConvertStringToInt() {
		Integer i = converter.convertIfNecessary("3", Integer.class);
		assertEquals(3,i.intValue());	
	}
	
	@Test(expected=TypeMismatchException.class)
	public void testConvertStringToIntEx() {
		Integer i = converter.convertIfNecessary("3.1", Integer.class);
	}
	
	@Test 
	public void testConvertStringToBoolean(){
		Boolean b = converter.convertIfNecessary("true", Boolean.class);
		assertEquals(true,b.booleanValue());	
	}
	
	@Test(expected=TypeMismatchException.class)
	public void testConvertStringToBooleanEx(){
		Boolean b = converter.convertIfNecessary("abc", Boolean.class);
	}

}
