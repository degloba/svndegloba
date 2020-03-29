package com.degloba.ecommerce.webapp;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.Collections;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.degloba.ecommerce.enviaments.webapp.reactive.service.CompresService;
import com.degloba.ecommerce.vendes.compres.facade.dtos.CompraDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


/**
 * @category Test Web Layer + Mock Backend (Service Layer)
 * 
 * @see https://www.baeldung.com/spring-mocking-webclient
 * 
 * @author degloba
 *
 */
public class CompresServiceMockWebServerTest {
	
	String baseUrl;
	
	public static MockWebServer mockBackEnd;
	 
    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }
 
    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

	private CompresService compresService;
    
    //assignem el port de la crida del servei REST real al port de MockWebServer.
    @BeforeEach
    void initialize() {
    	baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
    	compresService = new CompresService(baseUrl);
    }
    

    @Test
    void getCompraById() throws Exception {
    	ObjectMapper objectMapper = new ObjectMapper();
    			
    	//AggregateId compraId = new AggregateId("100");
    	//AggregateId comandaId = new AggregateId("200");
    	//AggregateId comandaKOId = new AggregateId("300");
    	
    	String compraId = "100";
    	String comandaId = "200";
    	String comandaKOId = "300";
    	
    	CompraDto mockCompra = new CompraDto(compraId);
    	
        // Utilitzem el métode en cola de MockWebServer per posar en cola una resposta de prova en el servidor web
        // Cuando la llamada API real se realiza desde el método getCompraById(AggregateId compraId) en la classe CompraService, 
        // MockWebServer respondra amb el codi auxiliar en cola.
        mockBackEnd.enqueue(new MockResponse()
          .setBody(objectMapper.writeValueAsString(mockCompra))
          .addHeader("Content-Type", "application/json"));
     
        Mono<CompraDto> enviamentMono = compresService.getCompraById(compraId);
     
        /*StepVerifier.create(enviamentMono)
          .expectNextMatches(enviament -> enviament.getComandaId()
            .equals(comandaId))
          .verifyComplete();
        */
        
        // Tambié es possible que vulguem assegurar-nos de que MockWebServer ha rebut la HttpRequest correcta.
        // MockWebServer té un métode (takeRequest) que retorna una instancia de RecordedRequest.
        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
  	  
    	assertEquals("GET", recordedRequest.getMethod());
    	assertEquals("/compra/100", recordedRequest.getPath());
    }
    
    
    @Test
    public void testRecursivelyCreateObjectNestedBean() throws Exception {
        
        WebClient client3 = WebClient.builder().baseUrl(baseUrl).defaultCookie("cookieKey", "cookieValue")
    			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    			.defaultUriVariables(Collections.singletonMap("url", baseUrl)).build();

    	WebClient.UriSpec<WebClient.RequestBodySpec> request1 = client3.method(HttpMethod.POST);
    	WebClient.UriSpec<WebClient.RequestBodySpec> request2 = client3.post();

    	WebClient.RequestBodySpec uri1 = client3.method(HttpMethod.POST).uri("/resource");
    	WebClient.RequestBodySpec uri2 = client3.post().uri(URI.create("/resource"));

    	/*WebClient.RequestHeadersSpec requestSpec1 = WebClient.create().method(HttpMethod.POST).uri("/resource")
    			.body(BodyInserters.fromPublisher(Mono.just("data")), String.class);
    */
    	WebClient.RequestHeadersSpec<?> requestSpec2 = WebClient.create("http://localhost:8080").post()
    			.uri(URI.create("/resource")).body(BodyInserters.fromValue("data"));

    	BodyInserter<Publisher<String>, ReactiveHttpOutputMessage> inserter1 = BodyInserters
    			.fromPublisher(Subscriber::onComplete, String.class);

    	LinkedMultiValueMap<String, Object> formValues = new LinkedMultiValueMap<String, Object>();
    	 
    	formValues.add("key1", "value1");
    	formValues.add("key2", "value2");
    	 
    	BodyInserter<MultiValueMap<String, Object>, ClientHttpRequest> inserter2 = BodyInserters.fromMultipartData(formValues);
    	  	

    	BodyInserter<Object, ReactiveHttpOutputMessage> inserter3 = BodyInserters.fromValue(new Object());

    	WebClient.ResponseSpec response1 = uri1.body(inserter3)
    			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
    			.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML).acceptCharset(Charset.forName("UTF-8"))
    			.ifNoneMatch("*").ifModifiedSince(ZonedDateTime.now()).retrieve();

        
        
    }
    
   
}
