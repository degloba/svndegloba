package com.degloba.events.impl.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

/**
 * En alguns casos, publicar events de forma síncrona no és realment el que estem buscant: 
 * és possible que necessitem un tractament asíncron dels nostres events.
 * L’event, el publicador i les implementacions del listener continuen sent els mateixos que abans, 
 * però ara, el listener, tractarà de manera asíncrona l’event en un thread separat.
 * 
 * @author degloba
 *
 */
@Configuration
public class AsynchronousSpringEventsConfig {
	
    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster 
          = new SimpleApplicationEventMulticaster();
         
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
}