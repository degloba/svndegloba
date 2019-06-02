package com.degloba.ioc.spring;


import java.lang.annotation.Annotation;

/**
 * @author degloba
 * 
 * @category Proveidor d'instàncies java.
 * La classe que l'implementa es pot adaptar a per exemple un contenidor IoC real,com Spring IoC o Google Guice, o qualsevol altra
 * framework que proveeixi instàncies.
 */
public interface IInstanceProvider {

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
