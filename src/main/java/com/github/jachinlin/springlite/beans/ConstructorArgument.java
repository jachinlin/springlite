package com.github.jachinlin.springlite.beans;

import java.util.LinkedList;
import java.util.List;


public class ConstructorArgument {
	
	private List<ValueHolder> argumentValues = new LinkedList<ValueHolder>();
	
	public List<ValueHolder> getArgumentValues() {
		return argumentValues;
	}
	
	public void addArgumentValue(ValueHolder valueHolder) {
		this.argumentValues.add(valueHolder);
		
	}
	
	public static class ValueHolder{
		private Object value;
		
		public ValueHolder(Object value) {
			this.value = value;
		}

		public Object getValue() {
			return value;
		}
		
	}

}
