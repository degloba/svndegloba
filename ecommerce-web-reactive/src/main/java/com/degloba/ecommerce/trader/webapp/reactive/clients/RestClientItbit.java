package com.degloba.ecommerce.trader.webapp.reactive.clients;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.trader.facade.dtos.QuoteIb;



public class RestClientItbit {

	private static final String URL = "https://api.itbit.com";
	
	public static void main(String[] args) {
		WebClient wc = WebClient.create(URL);
		QuoteIb quote = wc.get().uri("/v1/markets/XBTUSD/ticker")
                .accept(MediaType.APPLICATION_JSON).exchange().flatMap(response -> response.bodyToMono(QuoteIb.class))
                .block();
		System.out.println(quote.toString());
	}
}
