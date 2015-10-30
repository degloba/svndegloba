package com.degloba.infrastructure.sagas.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Component;

import com.degloba.infrastructure.events.impl.SimpleEventPublisher;
import com.degloba.infrastructure.events.impl.handlers.IEventHandler;
import com.degloba.infrastructure.sagas.ISagaEngine;
import com.degloba.sagas.LoadSaga;
import com.degloba.sagas.SagaAction;
import com.degloba.sagas.SagaInstance;
import com.degloba.sagas.ISagaManager;

/**
 * @author Rafal Jamroz
 */
@Component
public class SimpleSagaEngine implements ISagaEngine {

    private final ISagaRegistry sagaRegistry;

    private final SimpleEventPublisher eventPublisher;

    @Inject
    public SimpleSagaEngine(ISagaRegistry sagaRegistry, SimpleEventPublisher eventPublisher) {
        this.sagaRegistry = sagaRegistry;
        this.eventPublisher = eventPublisher;
    }

    @PostConstruct
    public void registerEventHandler() {
        eventPublisher.registerEventHandler(new SagaEventHandler(this));
    }

    @SuppressWarnings("rawtypes")
    public void handleSagasEvent(Object event) {
        Collection<ISagaManager> loaders = sagaRegistry.getLoadersForEvent(event);
        for (ISagaManager loader : loaders) {
            SagaInstance sagaInstance = loadSaga(loader, event);
            invokeSagaActionForEvent(sagaInstance, event);
            if (sagaInstance.isCompleted()) {
                loader.removeSaga(sagaInstance);
            }
        }
    }

    private SagaInstance loadSaga(ISagaManager loader, Object event) {
        Class<? extends SagaInstance> sagaType = determineSagaTypeByLoader(loader);
        Object sagaData = loadSagaData(loader, event);
        if (sagaData == null) {
            sagaData = loader.createNewSagaData();
        }
        SagaInstance sagaInstance = sagaRegistry.createSagaInstance(sagaType);
        sagaInstance.setData(sagaData);
        return sagaInstance;
    }

    // TODO determine saga type more reliably
    private Class<? extends SagaInstance> determineSagaTypeByLoader(ISagaManager loader) {
        Type type = ((ParameterizedType) loader.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return (Class<? extends SagaInstance>) type;
    }

    /**
     * TODO handle exception in more generic way
     */
    private Object loadSagaData(ISagaManager loader, Object event) {
        Method loaderMethod = findHandlerMethodForEvent(loader.getClass(), event);
        try {
            Object sagaData = loaderMethod.invoke(loader, event);
            return sagaData;
        } catch (InvocationTargetException e) {
            // NRE is ok here, it means that saga hasn't been started yet
            if (e.getTargetException() instanceof NoResultException) {
                return null;
            } else {
                throw new RuntimeException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeSagaActionForEvent(SagaInstance<?> saga, Object event) {
        Method eventHandler = findHandlerMethodForEvent(saga.getClass(), event);
        try {
            eventHandler.invoke(saga, event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Method findHandlerMethodForEvent(Class<?> type, Object event) {
        for (Method method : type.getMethods()) {
            if (method.getAnnotation(SagaAction.class) != null || method.getAnnotation(LoadSaga.class) != null) {
                if (method.getParameterTypes().length == 1
                        && method.getParameterTypes()[0].isAssignableFrom(event.getClass())) {
                    return method;
                }
            }
        }
        throw new RuntimeException("no method handling " + event.getClass());
    }

    private static class SagaEventHandler implements IEventHandler {

        private final ISagaEngine sagaEngine;

        public SagaEventHandler(ISagaEngine sagaEngine) {
            this.sagaEngine = sagaEngine;
        }

        public boolean canHandle(Object event) {
            return true;
        }

        public void handle(Object event) {
            sagaEngine.handleSagasEvent(event);
        }
    }
}
