package com.degloba.ioc.spring.factory;


import com.degloba.ioc.interfaces.IInstanceProvider;
import com.degloba.ioc.sharedkernel.exceptions.IocInstanceNotUniqueException;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @category Implementació de {@link IInstanceProvider} utilitzant {@link ApplicationContext} del IOC de Spring.
 */
public class SpringInstanceProvider implements IInstanceProvider {

	/**
	 * @category Contexte Spring de l'aplicació
	 */
    private ApplicationContext applicationContext;

    /**
     *
     * @param locations Spring estableix el path del fitxer de configuració.S'inicia a partir del classpath per començar 
     * a obtenir aquests fitxers de recursos.
     */
    public SpringInstanceProvider(String... locations) {
        applicationContext = new ClassPathXmlApplicationContext(locations);
    }

    /**
     * SpringProvider generat des de {@link ApplicationContext}
     *
     * @param applicationContext
     */
    public SpringInstanceProvider(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Inicialitza Spring segons un nombre de classe anotades proporcionades pel fitxer de configuració de Spring.
     *
     * @param annotatedClasses
     */
    public SpringInstanceProvider(Class<?>... annotatedClasses) {
        applicationContext = new AnnotationConfigApplicationContext(annotatedClasses);
    }

    /**
     * Recupera una instància a partir del tipus de classe. 
     * Retorna una instància de l'objecte de la classe de tipus T. 
     * Si no troba una instància del tipus retorna null.
     * If you have deployed multiple types of Bean T NoUniqueBeanDefinitionException exception is thrown.
     *
     * @param <T>      Tipus de paràmetre
     * @param beanType Tipus d'instància
     * @return Examples of the specified type.
     */
    public <T> T getInstance(Class<T> beanType) {
        try {
            return applicationContext.getBean(beanType);
        } catch (NoUniqueBeanDefinitionException cause) {
            throw new IocInstanceNotUniqueException(cause);
        } catch (NoSuchBeanDefinitionException cause) {
            return null;
        }
    }

    /**
     * Id Gets an object instance based on the type and Bean. If you can not find an instance of the type of returns null.
     * If there are two classes MyService1 and MyService2 implements the interface Service, so that the deployment in applicationContext:
     * <blockquote>
     * <pre>
     * <bean id="service1" class="MyService1"/>
     * <bean id="service2" class="MyService2"/>
     * </pre>
     * </blockquote>
     * Or to configure classes deployed:
     * <blockquote>
     * <pre>
     *
     * @param <T>      Type Parameter
     * @param beanName Implementation class name in the container configuration
     * @param beanType Type instance
     * @return Examples of the specified type.
     * @Configuration public class SpringConfiguration {
     * <p/>
     * @Bean(name = "service1")
     * public Service service1() {
     * return new MyService1();
     * }
     * <p/>
     * @Bean(name = "service2")
     * public Service service2() {
     * return new MyService2();
     * }
     * }
     * </pre>
     * </blockquote>
     * ThengetInstance(Service.class, "service2")MyService2 will return an instance of.
     */
    public <T> T getInstance(Class<T> beanType, String beanName) {
        try {
            return (T) applicationContext.getBean(beanName, beanType);
        } catch (NoUniqueBeanDefinitionException e) {
            throw new IocInstanceNotUniqueException(e);
        } catch (NoSuchBeanDefinitionException e) {
            return null;
        }
    }


    /**
     * Recupera una instància basada en el tipus i {@link Annotation}.Si no troba una instància del tipus retorna null.
     * If there are two classes MyService1 and MyService2 implements the interface Service, which marked MyService2
     * TheAnnotation, so getInstance (Service.class, TheAnnotation.class) will return
     * Examples MyService2 of.
     *
     * @param <T>            Type Parameter
     * @param beanType       Type instance
     * @param annotationType Annotation type class implementation
     * @return Examples of the specified type.
     */
    public <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        if (annotationType == null) {
            return getInstance(beanType);
        }
        Map<String, T> results = applicationContext.getBeansOfType(beanType);
        List<T> resultsWithAnnotation = new ArrayList<T>();
        for (Map.Entry<String, T> entry : results.entrySet()) {
            if (applicationContext.findAnnotationOnBean(entry.getKey(), annotationType) != null) {
                resultsWithAnnotation.add(entry.getValue());
            }
        }
        if (resultsWithAnnotation.isEmpty()) {
            return null;
        }
        if (resultsWithAnnotation.size() == 1) {
            return resultsWithAnnotation.get(0);
        }
        throw new IocInstanceNotUniqueException();
    }

    @SuppressWarnings("unchecked")
    public <T> T getByBeanName(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }
}
