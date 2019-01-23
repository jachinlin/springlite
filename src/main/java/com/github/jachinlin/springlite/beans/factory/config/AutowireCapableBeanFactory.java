package com.github.jachinlin.springlite.beans.factory.config;

import com.github.jachinlin.springlite.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {
	public Object resolveDependency(DependencyDescriptor descriptor);
}
