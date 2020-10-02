package com.degloba.ecommerce.enviaments.webapp.webflux;

import java.net.URI;

import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;

public class EnviamentWebSocketClient {

	   public static void main(String[] args) {

	        WebSocketClient client = new ReactorNettyWebSocketClient();
	        
	        client.execute(URI.create("ws://localhost:8080/enviament-feed"), session -> session.receive()
	            .map(WebSocketMessage::getPayloadAsText)
	            .doOnNext(System.out::println)
	            .then())
	            .block(); // to subscribe and return the value
	    }
}
