package com.degloba.ecommerce;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import com.degloba.ecommerce.enviaments.domain.persistence.rdbms.jpa.Enviament;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * 
 * @see https://www.baeldung.com/spring-mocking-webclient
 * 
 * @author degloba
 *
 */
public class EnviamentServiceMockWebServerTest {
	
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

	private EnviamentService enviamentService;
    
    //assignem el port de la crida del servei REST real al port de MockWebServer.
    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s", mockBackEnd.getPort());
        enviamentService = new EnviamentService(baseUrl);
    }
    
    //Usemos el práctico método en cola de MockWebServer para poner en cola una respuesta de prueba en el servidor web:
    //Cuando la llamada API real se realiza desde el método getEnviamentById (Integer employeeId) en nuestra clase EmployeeService, 
    // MockWebServer responderá con el código auxiliar en cola.
    @Test
    void getEnviamentById() throws Exception {
    	ObjectMapper objectMapper = new ObjectMapper();
    			
    	AggregateId enviamentId = new AggregateId("100");
    	AggregateId comandaId = new AggregateId("200");
    	AggregateId comandaKOId = new AggregateId("300");
    	
    	Enviament mockEnviament = new Enviament(enviamentId, comandaId);
        mockBackEnd.enqueue(new MockResponse()
          .setBody(objectMapper.writeValueAsString(mockEnviament))
          .addHeader("Content-Type", "application/json"));
     
        Mono<Enviament> enviamentMono = enviamentService.getEnviamentById(enviamentId);
     
        StepVerifier.create(enviamentMono)
          .expectNextMatches(enviament -> enviament.getComandaId()
            .equals(comandaId))
          .verifyComplete();
        
        RecordedRequest recordedRequest = mockBackEnd.takeRequest();
  	  
    	assertEquals("GET", recordedRequest.getMethod());
    	assertEquals("/enviament/100", recordedRequest.getPath());
    }
    
   
}
