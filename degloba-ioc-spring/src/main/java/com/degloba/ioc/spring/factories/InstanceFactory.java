package com.degloba.ioc.spring.factories;


import com.degloba.ioc.spring.locators.IInstanceLocator;
import com.degloba.ioc.spring.locators.InstanceProviderInstanceLocator;
import com.degloba.ioc.spring.providers.IInstanceProvider;
import com.degloba.ioc.spring.sharedkernel.exceptions.IocInstanceNotFoundException;

import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * @category
 * <p>
 * Classe Factory que actua com a contenidor IoC, mitjançant els quals podem obtenir instàncies desplegades de Beans del contenidor d’IoC. 
 * 
 * Ens permet seleccionar diferents Factory IoC, com ara Spring, Google Guice, TapestryIoC, etc.
 * <p>
 * Quan Spring ha de començar en l’aplicació s’assembla, és inicialitzar la classe disponible per aconseguir una instància de bon instància. 
 * Per a aplicacions web, la millor manera és crear un filtre d’inicialització de Servlet i definir-lo dins web.xml 
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
@Slf4j
public class InstanceFactory {

    /**
     * La secció següent només s'utilitza per proporcionar funcions de codi de test, no utilitzeu el codi del producte
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
     * Set el proveidor d'instàncies.
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
     * Obté una instància d'objecte depenent del tipus. 
     * Retorna una instància de l'objecte de classe de tipus T, o la seva classe d'implementació o subclasse. 
     * Si no pot trobar una instància d’aquest tipus llença una excepció {@link IocInstanceNotFoundException}.
     *
     * @param <T> Tipus d'objecte
     * @param beanType Tipus d'object al qual pertany
     * @return Instància de l'objecte de tipus T,
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
     * Obté una instància d'objecte segons el tipus i el nom. 
     * Retorna una instància de l'objecte de classe de tipus T, o la seva classe d'implementació o subclasse. 
     * Contenidor IoC diferent de diferents maneres d'explicar beanName.
     * See detailed explanation of the way various InstanceProvider implementation class Javadoc. 
     * Si no pot trobar una instància d’aquest tipus llença una excepcio {@link IocInstanceNotFoundException}.
     *
     * @param <T> Tipus d'objecte
     * @param beanName Nom del bean
     * @param beanType Tipus d'instància
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
     * Obté una instància d'objecte en funció del tipus i de l’anotació. 
     * Retorna una instància de l'objecte de classe de tipus T, o la seva classe d'implementació o subclasse. 
     * Contenidor IoC diferent de diferents maneres d'explicar l'anotació.
     * See detailed explanation of the way various InstanceProvider implementation class Javadoc. 
     * Si no pot trobar una instància d’aquest tipus llença una excepció {@link IocInstanceNotFoundException}.
     *
     * @param <T> Tipus
     * @param beanType Tipus d'object al qual pertany
     * @param annotationType Classe d'implementació de tipus {@link Annotation}
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
     * El servei està vinculat a una instància concreta
     *
     * @param <T> Bean Tipus d'objecte
     * @param serviceInterface Registration Type
     * @param serviceImplementation Object instance
     */
    public static <T> void bind(Class<T> serviceInterface, T serviceImplementation) {
        instances.put(serviceInterface, serviceImplementation);
    }

    /**
     * El servei està vinculat a una instància específica i especifica el nom
     *
     * @param <T> Bean Tipus d'objecte
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
     * El servei es vincula a una instància específica i especifica la notació associada
     *
     * @param <T> Bean Tipus d'objecte
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
