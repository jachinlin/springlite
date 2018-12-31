package com.github.jachinlin.springlite.beans.factory.support;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.BeanCreationException;
import com.github.jachinlin.springlite.beans.factory.config.ConfigurableBeanFactory;
import com.github.jachinlin.springlite.util.ClassUtils;;

public class DefaultBeanFactory implements ConfigurableBeanFactory, BeanDefinitionRegister {
	
	
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
		
		ClassLoader cl = this.getBeanClassLoader();
		String beanClassName = bd.getClassName();
		try {
			Class<?> cls = cl.loadClass(beanClassName);
			return cls.newInstance(); // 缺省无参构造函数
		 } catch (Exception e) {
			throw new BeanCreationException("Create bean for " + beanClassName + "failed", e);
		} 		
	}
	public void setBeanClassLoader(ClassLoader beanClassLoader) {
		this.beanClassLoader = beanClassLoader;
		
	}
	public ClassLoader getBeanClassLoader() {
		
		return (this.beanClassLoader != null? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
	}
	

}
