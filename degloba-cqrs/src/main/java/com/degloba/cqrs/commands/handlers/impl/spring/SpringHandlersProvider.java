package com.degloba.cqrs.commands.handlers.impl.spring;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.degloba.cqrs.commands.handlers.ICommandHandler;
import com.degloba.cqrs.commands.impl.RunEnvironment.IHandlersProvider;

/**
 * 
 * @author degloba
 * 
 * @see
 * 
 * @apiNote This method was added after the interface was released in
 *          version 1.0. It is defined as a default method for compatibility
 *          reasons. From version 2.0 on, the method will be abstract and
 *          all implementations of this interface have to provide their own
 *          implementation of the method.
 *          
 *          Comentaris, racionals o exemples relacionats amb l'API.
 * 
 * @implNote This implementation has linear runtime and does not filter out
 *           null players.
 *           
 *           notes informatives sobre la implementació, com ara les característiques de rendiment 
 *           específiques de la implementació en aquesta classe en aquest JDK en aquesta versió, 
 *           i que poden canviar. 
 *           Aquestes coses poden variar segons les plataformes, venedors i versions.
 * 
 * @implSpec The default implementation will consider each player a winner
 *           and return them in an unspecified order.
 *           
 *           Aquí és on diem què significa ser una implementació predeterminada vàlida 
 *           (o una implementació imperdible en una classe), com ara "llança UnknownObjectException". 
 *           De la mateixa manera és aquí on descriurem què fa el valor predeterminat de XXXXXXX. 
 *           És aquí on l’implementador té informació suficient per prendre 
 *           una decisió assenyada sobre si es pot anul·lar o no.
 * 
 * @serial
 * 
 * @since  1.1
 * 
 * @version
 * 
 * {@code}
 * 
 * {@docRoot}
 * 
 * {@linkplain}
 * 
 * {@link}
 * 
 * {@literal}
 * 
 * {@value}
 * 
 *
 * @category Proveidor de handlers 
 * 
 * 
 * {@link Component} que encapsula un {@link Map} de handlers i retorna un handler a partir del {@link ICommandAnnotation} 
 */
@Component
public class SpringHandlersProvider implements IHandlersProvider, ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ConfigurableListableBeanFactory beanFactory;

    private Map<Class<?>, String> handlers = new HashMap<Class<?>, String>();

    @SuppressWarnings("unchecked")
    public ICommandHandler<Object,Object> getHandler(Object command) {
        String beanName = handlers.get(command.getClass());
        if (beanName == null) {
            throw new RuntimeException("command handler not found. Command class is " + command.getClass());
        }
        ICommandHandler<Object,Object> handler = beanFactory.getBean(beanName, ICommandHandler.class);
        return handler;
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        handlers.clear();
        String[] commandHandlersNames = beanFactory.getBeanNamesForType(ICommandHandler.class);
        for (String beanName : commandHandlersNames) {
            BeanDefinition commandHandler = beanFactory.getBeanDefinition(beanName);
            try {
                Class<?> handlerClass = Class.forName(commandHandler.getBeanClassName());
                handlers.put(getHandledCommandType(handlerClass), beanName);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Class<?> getHandledCommandType(Class<?> clazz) {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        ParameterizedType type = findByRawType(genericInterfaces, ICommandHandler.class);
        return (Class<?>) type.getActualTypeArguments()[0];
    }

    private ParameterizedType findByRawType(Type[] genericInterfaces, Class<?> expectedRawType) {
        for (Type type : genericInterfaces) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parametrized = (ParameterizedType) type;
                if (expectedRawType.equals(parametrized.getRawType())) {
                    return parametrized;
                }
            }
        }
        throw new RuntimeException();
    }
}
