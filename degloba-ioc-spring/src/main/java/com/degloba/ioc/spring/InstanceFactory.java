package com.degloba.ioc.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.degloba.ioc.sharedkernel.exceptions.IocInstanceNotFoundException;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * <p>
 * Classe Factory que actua com a contenidor IoC, mitjançant els quals podem obtenir instàncies desplegades de Beans al contenidor d’IoC. 
 * Codi de client d'instànciaFactory per ocultar l'IoC
 * 
 * Implementació de la planta. Al fons, a través de la interfície de la política InstanceProvider,
 * 
 * Ens permet seleccionar diferents Factory IoC, com ara Spring, Google Guice, TapestryIoC, etc.
 * <p>
 * Quan la planta ha de començar en l’aplicació s’assembla, és inicialitzar la classe disponible per aconseguir una instància de bon instància. 
 * Per a aplicacions web, la millor manera és crear un filtre d’inicialització de Servlet i definir-lo din web.xml ; 
 * Per aplicacions java ordinàries, millor inicialització
 * La ubicació és la funció principal () dins; per a proves unitàries, la millor posició és inicialitzar el mètode d'etiquetatge internBeforeClass oBefore <br>
 * <p>
 * InstanceFactor Bean order order per obtenir tres maneres. 
 * (1) Si heu de configurar InstanceFactory InstanceProvider, a través d’aquest últim Troba Bean; 
 * (2) si no hi ha configurat InstanceProvider, o no es pot trobar el Bean per InstanceProvider, en l'adopció de ServiceLoader JDK6 find (through
* Sobre el camí de classe o jar el contingut del document / META-INF / services / abcAbc xyzXyz, indica el tipus de abcAbc xyzXyz per categoria
* 
* Els exemples proporcionats); (3) Si encara no trobeu una instància Bean, es tornarà la instància de Bean a aquells () mètodes establerts per la vinculació de. (4) 
* Si encara no podeu trobar la final, llença IocInstanceNotFoundException exception.
 */
public class InstanceFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstanceFactory.class);

    /**
     * The following section is only used to provide code test function, do not use the product code
     */
    private static final Map<Object, Object> instances = new HashMap<Object, Object>();

    private static IInstanceProvider instanceProvider;
           
    private static IInstanceLocatorFactory instanceLocatorFactory = ServiceLoader.load(IInstanceLocatorFactory.class).iterator().next();

    /**
     * Llista de cercadors d'instàncies.
     * Poden ser de diferent tipus :  {@link InstanceProviderInstanceLocator}, {@link MapInstanceLocator}, {@link ServiceLoaderInstanceLocator}
     */
    private static List<IInstanceLocator> instanceLocators = new ArrayList<IInstanceLocator>();

    static {
        instanceLocators.add(instanceLocatorFactory.createByServiceLoader());
        instanceLocators.add(instanceLocatorFactory.create(instances));
    }

    private InstanceFactory() {
    }

    /**
     * Set the instance provider.
     *
     * @param provider An example of those who provide examples.
     */
    public static void setInstanceProvider(IInstanceProvider provider) {
        instanceProvider = provider;
        if (instanceProvider == null) {
            return;
        }
        instanceLocators.add(0, instanceLocatorFactory.create(instanceProvider));
    }

    /**
     * Gets the object instances depending on the type. Returns an instance of the class object belongs to a T, or its implementation class or subclass. If you can not find an instance of this type is thrown.
     *
     * @param <T> Type of object
     * @param beanType Type object belongs
     * @return Object instance of type T,
     */
    public static <T> T getInstance(Class<T> beanType) {
        for (IInstanceLocator locator : instanceLocators) {
            T result = locator.getInstance(beanType);
            if (result != null) {
                return result;
            }
        }
        throw new IocInstanceNotFoundException("There's not bean of type '" + beanType + "' exists in IoC container!");
    }

    /**
     * Gets an object instance based on the type and name. Returns an instance of the class object belongs to a T, or its implementation class or subclass. Different IoC container in different ways to explain beanName.
     * See detailed explanation of the way various InstanceProvider implementation class Javadoc. If you can not find an instance of this type is thrown.
     *
     * @param <T> Type Parameter
     * @param beanName bean The name
     * @param beanType Type instance
     * @return Examples of the specified type.
     */
    public static <T> T getInstance(Class<T> beanType, String beanName) {
        for (IInstanceLocator locator : instanceLocators) {
            T result = locator.getInstance(beanType, beanName);
            if (result != null) {
                return result;
            }
        }
        throw new IocInstanceNotFoundException("There's not bean of type '" + beanType + "' exists in IoC container!");
    }

    /**
     * Gets an object instance based on the type and Annotation. Returns an instance of the class object belongs to a T, or its implementation class or subclass. Different IoC container in different ways to explain annotation.
     * See detailed explanation of the way various InstanceProvider implementation class Javadoc. If you can not find an instance of this type is thrown.
     *
     * @param <T> Type Parameter
     * @param beanType Type instance
     * @param annotationType Annotation type implementation class
     * @return Examples of the specified type.
     */
    public static <T> T getInstance(Class<T> beanType, Class<? extends Annotation> annotationType) {
        for (IInstanceLocator locator : instanceLocators) {
            T result = locator.getInstance(beanType, annotationType);
            if (result != null) {
                return result;
            }
        }
        throw new IocInstanceNotFoundException("There's not bean '"
                + annotationType + "' of type '" + beanType + "' exists in IoC container!");
    }

    /**
     * The service is bound to a specific instance
     *
     * @param <T> Bean Type instance
     * @param serviceInterface Registration Type
     * @param serviceImplementation Object instance
     */
    public static <T> void bind(Class<T> serviceInterface, T serviceImplementation) {
        instances.put(serviceInterface, serviceImplementation);
    }

    /**
     * The service is bound to a specific instance and specify the name
     *
     * @param <T> Bean Type instance
     * @param serviceInterface Registration Type
     * @param serviceImplementation Object instance
     * @param beanName Instance name
     */
    public static <T> void bind(Class<T> serviceInterface, T serviceImplementation, String beanName) {
        instances.put(toName(serviceInterface, beanName), serviceImplementation);
    }

    /**
     * Remove cached bean instance
     */
    public static void clear() {
        instances.clear();
    }

    /**
     * The service is bound to a specific instance and specify the associated Annotation
     *
     * @param <T> Bean Type instance
     * @param serviceInterface Registration Type
     * @param serviceImplementation Object instance
     * @param annotationType Annotation type
     */
    public static <T> void bind(Class<T> serviceInterface, T serviceImplementation, Class<? extends Annotation> annotationType) {
        instances.put(toName(serviceInterface, annotationType), serviceImplementation);
    }

    private static String toName(Class<?> beanType, String beanName) {
        return beanType.getName() + ":" + beanName;
    }

    private static String toName(Class<?> beanType, Class<? extends Annotation> annotationType) {
        return beanType.getName() + ":" + annotationType.getName();
    }
}
