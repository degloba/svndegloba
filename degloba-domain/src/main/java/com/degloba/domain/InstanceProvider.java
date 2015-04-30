package com.degloba.domain;


import java.lang.annotation.Annotation;

/**
 * Examples provider interface, and its implementation class to the adapter to find ways Bean delegate the task to the real IoC container, 
 * such as SpringIoC or Google Guice.
 */
public interface InstanceProvider {

	/**
     * Get in accordance with the type of the object instance. Class belongs returned object instance is T or its implementation class or sub-class. 
     * If you can not find an instance of this type is thrown Exception.
	 * @param <T> Type Parameter
	 * @param beanType Type instance
	 * @return Examples of the specified type.
	 */
	<T> T getInstance(Class<T> beanType);

	/**
     * Get depending on the type and name of an object instance. 
     * Class belongs returned object instance is T or its implementation class or sub-class. 
     * Different IoC container in different ways to explain beanName.
     * See the detailed explanation of the way the various InstanceProvider implementation class Javadoc.
     * If you can not find an instance of this type is thrown Exception.
	 * @param <T> Type Parameter
	 * @param beanName Implementation class name in the container configuration
	 * @param beanType Type instance
	 * @return Examples of the specified type.
	 */
	<T> T getInstance(Class<T> beanType, String beanName);

    /**
     * Depending on the type and AnnotationGet object instance. Class belongs returned object instance is T or its implementation class or sub-class. 
     * Different IoC container in different ways to explain annotation.
     * See the detailed explanation of the way the various InstanceProvider implementation class Javadoc.
     * If you can not find an instance of this type is thrown Exception.
     * @param <T> Type Parameter
     * @param beanType Type instance
     * @param annotationType Annotation type class implementation
     * @return Examples of the specified type.
     */
    <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType);
}
