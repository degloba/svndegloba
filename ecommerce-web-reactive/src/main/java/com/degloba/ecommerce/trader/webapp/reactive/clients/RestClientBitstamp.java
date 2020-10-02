package com.degloba.ecommerce.trader.webapp.reactive.clients;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.trader.facade.dtos.QuoteBs;



public class RestClientBitstamp {
	private static final String URL = "https://www.bitstamp.net/api";

	public static void main(String[] args) {
		WebClient wc = WebClient.create(URL);
		QuoteBs quote = wc.get().uri("/v2/ticker/xrpeur/")
                .accept(MediaType.APPLICATION_JSON).exchange().flatMap(response -> response.bodyToMono(QuoteBs.class))
                .map(res -> {res.setPair("xrpeur");return res;}).block();
		System.out.println(quote.toString());
	}
}