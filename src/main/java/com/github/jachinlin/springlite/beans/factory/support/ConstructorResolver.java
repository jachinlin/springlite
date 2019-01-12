package com.github.jachinlin.springlite.beans.factory.support;

import java.lang.reflect.Constructor;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.ConstructorArgument;
import com.github.jachinlin.springlite.beans.SimpleTypeConverter;
import com.github.jachinlin.springlite.beans.factory.BeanCreationException;
import com.github.jachinlin.springlite.beans.factory.config.ConfigurableBeanFactory;

public class ConstructorResolver {
	
	protected final Log logger = LogFactory.getLog(getClass());
	private ConfigurableBeanFactory beanFactory;
	
	public ConstructorResolver(DefaultBeanFactory factory) {
		this.beanFactory = factory;
	}

	public Object autowireConstructor(BeanDefinition bd) {
		Constructor<?> constructorToUse = null;
		Object[] argsToUse = null;
		Class<?> beanClass = null;
		
		try {
			beanClass = this.beanFactory.getBeanClassLoader().loadClass(bd.getClassName());
		} catch (ClassNotFoundException e){
			throw new BeanCreationException("Instantiation of bean '" + bd.getBeanID() + "' failed, can't resolve class", e);
		}
		
		Constructor<?>[] constructorCandidates = beanClass.getConstructors();
		BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this.beanFactory);
		SimpleTypeConverter typeConverter = new SimpleTypeConverter();
		ConstructorArgument constructorArgument = bd.getConstructorArgument();
		
		for(int i=0; i<constructorCandidates.length; i++) {
			Class<?>[] parameterTypes = constructorCandidates[i].getParameterTypes();
			
			if(parameterTypes.length != constructorArgument.getArgumentValues().size()) {
				continue;
			}
			argsToUse = new Object[parameterTypes.length];
			boolean result = this.isValuesMatchTypes(parameterTypes, 
					constructorArgument.getArgumentValues(), 
					argsToUse, 
					valueResolver, 
					typeConverter);
			
			if(result){
				constructorToUse = constructorCandidates[i];
				break;
			}
		}
		// no suitable constructor
		if(constructorToUse == null){
			throw new BeanCreationException("Error creating bean with name '" + bd.getBeanID() + "': " + "can't find a apporiate constructor");
		}
		
		try {
			return constructorToUse.newInstance(argsToUse);
		} catch (Exception e) {
			throw new BeanCreationException("Error creating bean with name '" + bd.getBeanID() + "': " + "can't find a create instance using "+ constructorToUse);
		}
	}
	
	private boolean isValuesMatchTypes(Class<?> [] parameterTypes,
			List<ConstructorArgument.ValueHolder> valueHolders,
			Object[] argsToUse,
			BeanDefinitionValueResolver valueResolver,
			SimpleTypeConverter typeConverter ){
		
		
		for(int i=0;i<parameterTypes.length;i++){
			ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);
			Object originalValue = valueHolder.getValue();
			
			try{
				Object resolvedValue = valueResolver.resolveValueIfNecessary( originalValue);
				Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
				argsToUse[i] = convertedValue;
			}catch(Exception e){
				logger.error(e);
				return false;
			}				
		}
		return true;
	}

}
