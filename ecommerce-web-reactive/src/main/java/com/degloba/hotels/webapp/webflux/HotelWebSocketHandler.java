package com.degloba.hotels.webapp.webflux;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;

import java.time.Duration;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * https://github.com/eugenp/tutorials/blob/master/spring-5-reactive-security/src/main/java/com/baeldung/webflux/EmployeeWebSocketHandler.java
 * 
 * @author pere
 *
 */
@Component("HotelWebSocketHandler")
public class HotelWebSocketHandler implements WebSocketHandler {

    ObjectMapper om = new ObjectMapper();

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {

        Flux<String> hotelCreationEvent = Flux.generate(sink -> {
            HotelCreationEvent event = new HotelCreationEvent(randomUUID().toString(), now().toString());
            try {
                sink.next(om.writeValueAsString(event));
            } catch (JsonProcessingException e) {
                sink.error(e);
            }
        });

        return webSocketSession.send(hotelCreationEvent
            .map(webSocketSession::textMessage)
            .delayElements(Duration.ofSeconds(1)));
    }
}
