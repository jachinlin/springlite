package com.github.jachinlin.springlite.beans.factory.support;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.PropertyValue;
import com.github.jachinlin.springlite.beans.SimpleTypeConverter;
import com.github.jachinlin.springlite.beans.TypeConverter;
import com.github.jachinlin.springlite.beans.factory.BeanCreationException;
import com.github.jachinlin.springlite.beans.factory.config.ConfigurableBeanFactory;
import com.github.jachinlin.springlite.util.ClassUtils;;

public class DefaultBeanFactory extends DefaultSingletonRegistry 
	implements ConfigurableBeanFactory, BeanDefinitionRegister {
	
	
	private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);
	private ClassLoader beanClassLoader;

	public void registerBeanDefinition(String beanID, BeanDefinition bd) {
		this.beanDefinitionMap.put(beanID, bd);
	}
	public BeanDefinition getBeanDefinition(String beanID) {

		return this.beanDefinitionMap.get(beanID);
	}

	public Object getBean(String beanID) {
		BeanDefinition bd = this.getBeanDefinition(beanID);
		if(bd == null) {
			throw new BeanCreationException("Bean definition does not exist");
		}
		if(bd.isSingleton()) {
			Object bean = this.getSingleton(beanID);
			if(bean == null) {
				bean = createBean(bd);
				this.registerSingleton(beanID, bean);
			}
			return bean;
		}
		return createBean(bd); 		
	}
	private Object createBean(BeanDefinition bd) {
		Object bean = this.initiateBean(bd);
		this.populateBean(bd, bean);
		return bean;
	}
	
	protected void populateBean(BeanDefinition bd, Object bean){
		List<PropertyValue> pvs = bd.getPropertyValues();
		
		if (pvs == null || pvs.isEmpty()) {
			return;
		}
		
		BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this);
		TypeConverter converter = new SimpleTypeConverter();
	
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
			
			for (PropertyValue pv : pvs){
				String propertyName = pv.getName();
				Object originalValue = pv.getValue();
				Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);			
				
				for (PropertyDescriptor pd : pds) {
					if(pd.getName().equals(propertyName)){
						Object convertedValue = converter.convertIfNecessary(resolvedValue, pd.getPropertyType());
						pd.getWriteMethod().invoke(bean, convertedValue);
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new BeanCreationException("Failed to obtain BeanInfo for class [" + bd.getClassName() + "]", e);
		}
 
				
	}
	private Object initiateBean(BeanDefinition bd) {
		if(bd.hasConstructorArgumentValues()){
			ConstructorResolver resolver = new ConstructorResolver(this);
			return resolver.autowireConstructor(bd);
		} else {
			ClassLoader cl = this.getBeanClassLoader();
			String beanClassName = bd.getClassName();
			try {
				Class<?> cls = cl.loadClass(beanClassName);
				return cls.newInstance(); // 缺省无参构造函数
			 } catch (Exception e) {
				throw new BeanCreationException("Create bean for " + beanClassName + "failed", e);
			}
		}
	}
	
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
		
	}
	public ClassLoader getBeanClassLoader() {
		
		return (this.beanClassLoader != null? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
	}
	

}
