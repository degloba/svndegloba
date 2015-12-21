package com.degloba.infrastructure.sagas.impl;

// Reflection
import java.lang.reflect.Method;

import java.util.Collection;
import java.util.HashSet;

import javax.inject.Inject;

// Spring
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// Sagas
import com.degloba.infrastructure.sagas.ISagaManager;
import com.degloba.infrastructure.sagas.LoadSaga;
import com.degloba.infrastructure.sagas.SagaAction;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * @author Rafał Jamróz
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
@Component
public class SpringSagaRegistry implements ISagaRegistry, ApplicationListener<ContextRefreshedEvent> {

    private Multimap<Class<?>, String> loadersInterestedIn = HashMultimap.create();

    @Inject
    private ConfigurableListableBeanFactory beanFactory;

    public Collection<ISagaManager> getLoadersForEvent(Object event) {
        Collection<ISagaManager> results = new HashSet<ISagaManager>();
        Collection<String> loadersBeansNames = loadersInterestedIn.get(event.getClass());
        for (String loaderBeanName : loadersBeansNames) {
            ISagaManager loader = beanFactory.getBean(loaderBeanName, ISagaManager.class);
            results.add(loader);
        }
        return results;
    }

    public SagaInstance createSagaInstance(Class<? extends SagaInstance> sagaType) {
        return (SagaInstance) beanFactory.getBean(sagaType);
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadersInterestedIn.clear();
        registerSagaLoaderBeans();
    }

    private void registerSagaLoaderBeans() {
        String[] loadersNames = beanFactory.getBeanNamesForType(ISagaManager.class);
        for (String loaderBeanName : loadersNames) {
            BeanDefinition loaderBeanDefinition = beanFactory.getBeanDefinition(loaderBeanName);
            try {
                registerSagaLoader(Class.forName(loaderBeanDefinition.getBeanClassName()), loaderBeanName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void registerSagaLoader(Class<?> loaderClass, String beanName) {
        for (Method method : loaderClass.getMethods()) {
            if (method.getAnnotation(SagaAction.class) != null || method.getAnnotation(LoadSaga.class) != null) {
                Class<?>[] params = method.getParameterTypes();
                if (params.length == 1) {
                    loadersInterestedIn.put(params[0], beanName);
                } else {
                    throw new RuntimeException("incorred event hadndler: " + method);
                }
            }
        }
    }

}
