package com.degloba.persistence.nosql.impl.mongodb.configuration;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

/**
 * Simple configuration that registers a {@link LoggingEventListener} to demonstrate mapping behaviour when Java 8
 * Streams are used.
 *
 * @author Thomas Darimont
 */
@SpringBootApplication
class PersistenceNosqlSpringConfiguration {

	public @Bean LoggingEventListener mongoEventListener() {
		return new LoggingEventListener();
	}
}
