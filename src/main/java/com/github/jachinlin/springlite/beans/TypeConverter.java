package com.github.jachinlin.springlite.beans;

public interface TypeConverter {

	<T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;

}
