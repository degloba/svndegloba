package com.degloba.ecommerce.enviaments.webapp.reactive.service;

import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.persistence.rdbms.jpa.AggregateId;

import reactor.core.publisher.Mono;

public class CompresService {
	
	private WebClient webClient;
    public static String PATH_PARAM_BY_ID = "/compres/{id}";
    public static String ADD_COMPRA = "/compra";

    public CompresService(WebClient webClient) {
        this.webClient = webClient;
    }

    public CompresService(String baseUrl) {
        this.webClient = WebClient.create(baseUrl);
    }

    public Mono<Compra> getCompraById(AggregateId compraId) {
        return webClient
                .get()
                .uri(PATH_PARAM_BY_ID, compraId)
                .retrieve()
                .bodyToMono(Compra.class);
    }

    public Mono<Compra> addNewCompra(Compra newCompra) {

        return webClient
                .post()
                .uri(ADD_COMPRA)
                .syncBody(newCompra)
                .retrieve().
                bodyToMono(Compra.class);
    }

    public Mono<Compra> updateCompra(Integer compraId, Compra updateCompra) {

      return  webClient
                .put()
                .uri(PATH_PARAM_BY_ID,compraId)
                .syncBody(updateCompra)
                .retrieve()
                .bodyToMono(Compra.class);
    }

    public Mono<String> deleteECompraById(Integer compraId) {
        return webClient
                .delete()
                .uri(PATH_PARAM_BY_ID,compraId)
                .retrieve()
                .bodyToMono(String.class);
    }

}
