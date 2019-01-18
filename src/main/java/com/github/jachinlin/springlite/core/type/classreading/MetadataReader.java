package com.github.jachinlin.springlite.core.type.classreading;

import com.github.jachinlin.springlite.core.io.Resource;
import com.github.jachinlin.springlite.core.type.AnnotationMetadata;
import com.github.jachinlin.springlite.core.type.ClassMetadata;

/**
 * Simple facade for accessing class metadata,
 */
public interface MetadataReader {

	/**
	 * Return the resource reference for the class file.
	 */
	Resource getResource();

	/**
	 * Read basic class metadata for the underlying class.
	 */
	ClassMetadata getClassMetadata();

	/**
	 * Read full annotation metadata for the underlying class,
	 * including metadata for annotated methods.
	 */
	AnnotationMetadata getAnnotationMetadata();

}
