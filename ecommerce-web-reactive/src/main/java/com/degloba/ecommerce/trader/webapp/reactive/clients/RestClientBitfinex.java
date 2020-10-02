package com.degloba.ecommerce.trader.webapp.reactive.clients;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.trader.facade.dtos.QuoteBf;


public class RestClientBitfinex {
	private static final String URL = "https://api.bitfinex.com";
	
	public static void main(String[] args) {
		WebClient wc = WebClient.create(URL);
		QuoteBf quote = wc.get().uri("/v1/pubticker/xrpusd")
                .accept(MediaType.APPLICATION_JSON).exchange().flatMap(response -> response.bodyToMono(QuoteBf.class))
                .map(res -> {res.setPair("xprusd");return res;}).block();
		System.out.println(quote.toString());
	}

}
