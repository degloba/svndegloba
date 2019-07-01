package com.degloba.infrastructure.sagas.impl;

// Reflection
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.Collection;

// is used on a method that needs to be executed after dependency injection is done to perform any initialization
import javax.annotation.PostConstruct;

import javax.inject.Inject;

// JPA
//import javax.persistence.NoResultException;

// Spring
import org.springframework.stereotype.Component;

import com.degloba.event.api.IEvent;
import com.degloba.event.api.IEventHandler;
// Events
import com.degloba.event.impl.DomainEventPublisher;
import com.degloba.infrastructure.sagas.ISagaEngine;
import com.degloba.infrastructure.sagas.ISagaManager;
import com.degloba.infrastructure.sagas.LoadSaga;
import com.degloba.infrastructure.sagas.SagaAction;

// Sagas


@Component
public class SimpleSagaEngine<S extends SagaInstance<D>, D> implements ISagaEngine<IEvent> {

    private final ISagaRegistry<S, D> sagaRegistry;

    private final DomainEventPublisher<IEvent> eventPublisher;

    @Inject
    public SimpleSagaEngine(ISagaRegistry<S,D> sagaRegistry, DomainEventPublisher<IEvent> eventPublisher) {
        this.sagaRegistry = sagaRegistry;
        this.eventPublisher = eventPublisher;
    }

    @PostConstruct
    public void registerEventHandler() {
        eventPublisher.registerEventHandler(new SagaEventHandler<IEvent>(this));
    }

   /* public void handleSagasEvent(S saga) {
        Collection<ISagaManager<S, D>> loaders = sagaRegistry.getLoadersForEvent(saga);
        for (ISagaManager<S, D> loader : loaders) {
            SagaInstance<D> sagaInstance = loadSaga(loader, saga);
            invokeSagaActionForEvent(sagaInstance, saga);
            if (sagaInstance.isCompleted()) {
                loader.removeSaga(sagaInstance);
            }
        }
    }*/
 
    /*private SagaInstance<D> loadSaga(ISagaManager<S, D> loader, S saga) {
        Class<? extends SagaInstance<D>> sagaType = determineSagaTypeByLoader(loader);
        D sagaData = loadSagaData(loader, saga);
        if (sagaData == null) {
            sagaData = loader.createNewSagaData();
        }
        SagaInstance<D> sagaInstance = sagaRegistry.createSagaInstance(sagaType);
        sagaInstance.setData(sagaData);
        return sagaInstance;
    }*/

    // TODO determine saga type more reliably
    private Class<? extends SagaInstance<D>> determineSagaTypeByLoader(ISagaManager<S, D> loader) {
        Type type = ((ParameterizedType) loader.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        return (Class<? extends SagaInstance<D>>) type;
    }

    /**
     * TODO handle exception in more generic way
     */
    /*private D loadSagaData(ISagaManager<S, D> loader, S saga) {
        Method loaderMethod = findHandlerMethodForEvent(loader.getClass(), saga);
        try {
            D sagaData = (D) loaderMethod.invoke(loader, saga);
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
    }*/

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

    private static class SagaEventHandler<T extends IEvent> implements IEventHandler<T> {

        private final ISagaEngine<T> sagaEngine;

        public SagaEventHandler(ISagaEngine<T> sagaEngine) {
            this.sagaEngine = sagaEngine;
        }

        public boolean potGestionar(T event) {
            return true;
        }

        public void gestiona(T event) {
            sagaEngine.handleSagasEvent(event);
        }
    }

	@Override
	public void handleSagasEvent(IEvent event) {
		// TODO Auto-generated method stub
		
	}


}
