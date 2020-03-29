package com.degloba.ecommerce.enviaments.webapp.reactive.service;

import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.vendes.compres.facade.dtos.CompraDto;

import reactor.core.publisher.Mono;

/**
 * @category Logica
 * @author degloba
 *
 */
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

    public Mono<CompraDto> getCompraById(String compraId) {
        return webClient
                .get()
                .uri(PATH_PARAM_BY_ID, compraId)
                .retrieve()
                .bodyToMono(CompraDto.class);
    }

    public Mono<CompraDto> afegirNovaCompra(CompraDto newCompra) {

        return webClient
                .post()
                .uri(ADD_COMPRA)
                .syncBody(newCompra)
                .retrieve().
                bodyToMono(CompraDto.class);
    }

    public Mono<CompraDto> modificarCompra(Integer compraId, CompraDto updateCompra) {

      return  webClient
                .put()
                .uri(PATH_PARAM_BY_ID,compraId)
                .syncBody(updateCompra)
                .retrieve()
                .bodyToMono(CompraDto.class);
    }

    public Mono<String> deleteECompraById(Integer compraId) {
        return webClient
                .delete()
                .uri(PATH_PARAM_BY_ID,compraId)
                .retrieve()
                .bodyToMono(String.class);
    }

}
