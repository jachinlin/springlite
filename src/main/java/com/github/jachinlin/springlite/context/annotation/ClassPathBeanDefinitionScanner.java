package com.github.jachinlin.springlite.context.annotation;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import com.github.jachinlin.springlite.beans.BeanDefinition;
import com.github.jachinlin.springlite.beans.factory.BeanDefinitionStoreException;
import com.github.jachinlin.springlite.beans.factory.support.BeanDefinitionRegister;
import com.github.jachinlin.springlite.beans.factory.support.BeanNameGenerator;
import com.github.jachinlin.springlite.core.io.Resource;
import com.github.jachinlin.springlite.core.io.support.PackageResourceLoader;
import com.github.jachinlin.springlite.core.type.classreading.MetadataReader;
import com.github.jachinlin.springlite.core.type.classreading.SimpleMetadataReader;
import com.github.jachinlin.springlite.stereotype.Component;
import com.github.jachinlin.springlite.util.StringUtils;

public class ClassPathBeanDefinitionScanner {
	
	private final BeanDefinitionRegister registry;
	private PackageResourceLoader resourceLoader = new PackageResourceLoader();
	private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
	
	public ClassPathBeanDefinitionScanner(BeanDefinitionRegister registry) {
		this.registry = registry;
	}

	public Set<BeanDefinition> doScan(String packagesToScan) {
		
		String[] basePackages = StringUtils.tokenizeToStringArray(packagesToScan, ",");
		
		Set<BeanDefinition> beanDefinitions = new LinkedHashSet<BeanDefinition>();
		for (String basePackage : basePackages) {
			Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
			for (BeanDefinition candidate : candidates) {
				beanDefinitions.add(candidate);
				registry.registerBeanDefinition(candidate.getBeanID(), candidate);
				
			}
		}
		return beanDefinitions;
	}
	
	public Set<BeanDefinition> findCandidateComponents(String basePackage) {
		Set<BeanDefinition> candidates = new LinkedHashSet<BeanDefinition>();
		try {
			
			Resource[] resources = this.resourceLoader.getResources(basePackage);
			
			for (Resource resource : resources) {
				try {
					
					MetadataReader metadataReader = new SimpleMetadataReader(resource);
				
					if(metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())){
						ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition(metadataReader.getAnnotationMetadata());
						String beanName = this.beanNameGenerator.generateBeanName(sbd, this.registry);
						sbd.setBeanId(beanName);
						candidates.add(sbd);					
					}
				}
				catch (Throwable ex) {
					throw new BeanDefinitionStoreException(
							"Failed to read candidate component class: " + resource, ex);
				}
				
			}
		}
		catch (IOException ex) {
			throw new BeanDefinitionStoreException("I/O failure during classpath scanning", ex);
		}
		return candidates;
	}
}
