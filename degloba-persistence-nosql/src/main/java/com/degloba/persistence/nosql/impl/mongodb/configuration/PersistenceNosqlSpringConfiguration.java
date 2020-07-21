package com.degloba.persistence.nosql.impl.mongodb.configuration;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

/**
 * @category Configuració Spring  per MongoDB que registra un {@link LoggingEventListener} 
 * per demostrar el comportament quan s'utilitza Java 8 Streams.
 * 
 * Registra {@link MongoMappingEvent} que es publiquen en l'infraestructura 
 * {@link ApplicationContextEvent}
 * 
 * @implNote Spring
 *
 * @author degloba
 * @param <MongoMappingEvent>
 */
@SpringBootApplication
class PersistenceNosqlSpringConfiguration<MongoMappingEvent> {

	public @Bean LoggingEventListener mongoEventListener() {
		return new LoggingEventListener();
	}
}
