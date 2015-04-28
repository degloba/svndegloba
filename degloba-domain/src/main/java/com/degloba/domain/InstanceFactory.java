package com.degloba.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.lang.annotation.Annotation;
import java.util.*;

/**
 * <p>
 * Examples factory class act IoC container facade, through which you can get deployed instances Bean in IoC container. InstanceFactory client code to hide the IoC
 * Implementation of the plant. In the background, through InstanceProvider policy interface, allows you to select a different IoC factories, such as Spring, Google Guice and
 * TapestryIoC more.
 * <p>
 * IoC When the plant should start in the application is assembled, it is to initialize the class available to achieve good InstanceProvider InstanceFactory. For web applications
  *, The best way is to create a Servlet initialization filter or listener, and deployed to web.xml inside; ordinary java applications, best initialization
  * Location is the main () function inside; for unit testing, the best position is to initialize internal tagging methodBeforeClass orBefore<br>
 * <p>
 * InstanceFactor Bean instance order to obtain three ways. (1) If you have to set up InstanceFactory InstanceProvider, then through the latter
  * Find Bean; (2) if there is no set InstanceProvider, or can not find the Bean by InstanceProvider, on the adoption of ServiceLoader JDK6 find (through
  * Over the classpath or jar the / META-INF / services / abcAbc document set content xyzXyz, it indicates the type of abcAbc xyzXyz by category
  * The examples provided); (3) If you still can not find an instance Bean, Bean instance will be returned to those () method set by the bind of. (4) If you still can not find the final, to throw
  * IocInstanceNotFoundException exception.
 *
 * @author degloba (<a href="mailto:gdyangyu@gmail.com">gdyangyu@gmail.com</a>)
 */
public class InstanceFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(InstanceFactory.class);

    /**
     * The following section is only used to provide code test function, do not use the product code
     */
    private static final Map<Object, Object> instances = new HashMap<Object, Object>();


    //Examples of providers, represent the true IoC container
    private static InstanceProvider instanceProvider;

    private static InstanceLocatorFactory instanceLocatorFactory = ServiceLoader.load(InstanceLocatorFactory.class).iterator().next();

    private static List<InstanceLocator> instanceLocators = new ArrayList<InstanceLocator>();

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
    public static void setInstanceProvider(InstanceProvider provider) {
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
        for (InstanceLocator locator : instanceLocators) {
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
        for (InstanceLocator locator : instanceLocators) {
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
        for (InstanceLocator locator : instanceLocators) {
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
