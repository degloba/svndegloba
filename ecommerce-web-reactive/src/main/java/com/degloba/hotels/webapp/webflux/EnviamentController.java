package com.degloba.hotels.webapp.webflux;

import java.awt.List;
import java.util.Arrays;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author degloba
 * 
 * @CrossOrigin En el món web cargar dades d'un servidor des d'un altra domini està prohibit
 * CrossOrigin resolt aquesta limitació, fent que els dos dominis (exemple : angular/4200 i web/8080)
 * s'acceptin 
 * 
 * https://www.baeldung.com/spring-boot-angular-web
 */
@RestController()
@RequestMapping("/hotels")
@Slf4j
///////@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {
	
	
	private HotelRepository employeeRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/{id}")
    private Mono<Hotel> getEmployeeById(@PathVariable String id) {
        return employeeRepository.findHotelById(id);
    }

    @GetMapping
    private Flux<Hotel> getAllEmployees() {
        return employeeRepository.findAllHotels();
    }

    @PostMapping("/update")
    private Mono<Hotel> updateEmployee(@RequestBody Hotel employee) {
        return employeeRepository.updateEmployee(employee);
    }
	
	
	
/*	final String urlServer="http://localhost:8081";
	
	
	@GetMapping("/{param}")
	public Mono<ResponseEntity<Mono<String>>> testGet(@PathVariable String param) {
		final long dateStarted = System.currentTimeMillis();

		log.debug("Client: In testGest");
		WebClient webClient = WebClient.create(urlServer+"/server/");
		Mono<ClientResponse> respuesta = webClient.get().uri("?queryParam={name}", param).exchange();

		log.debug("Client: After calling the server");

		WebClient webClient2 = WebClient.create(urlServer+"/server/");
		Mono<ClientResponse> respuesta1 = webClient2.get().uri("?queryParam={name}", "STOP").exchange();
		log.debug("llamada 2");

		log.debug("Client: Before zip");

		Mono<ResponseEntity<Mono<String>>> f1 = Mono.zip(respuesta, respuesta1).map(t -> {
			log.debug("Processing Map...");
			if (!t.getT1().statusCode().is2xxSuccessful()) {
				return ResponseEntity.status(t.getT1().statusCode()).body(t.getT1().bodyToMono(String.class));

			}
			if (!t.getT2().statusCode().is2xxSuccessful()) {
				return ResponseEntity.status(t.getT2().statusCode()).body(t.getT2().bodyToMono(String.class));
			}
			log.debug("Allright");
			return ResponseEntity.ok().body(Mono.just(
					"All OK. Seconds elapsed: " + (((double) (System.currentTimeMillis() - dateStarted) / 1000))));
		});
		return f1;
	}
	
	@PostMapping("")
	public Mono<String> testURLs(@RequestBody Map<String,String> body,
			@RequestParam(required = false) String url) {		

		log.debug("Client: in testURLs");
		WebClient.Builder builder = WebClient.builder().baseUrl(urlServer).
			defaultHeader(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
		if (body!=null && body.size()>0 )
		{
			for (Map.Entry<String, String> map : body.entrySet() )
			{
				builder.defaultHeader(map.getKey(), map.getValue());
			}
		}
	
		WebClient webClient = builder.build();	
		String urlFinal;
		if (url==null)
			urlFinal="/server/post";
		else
			urlFinal="/server/"+url;
		
		///////BodyInserters.fromPublisher( Flux.just(Arrays.asList("aa","ss")),List.class);
		Mono<String> respuesta1 = webClient.post().uri(urlFinal).body(BodyInserters.fromValue(body)).exchange()
			.flatMap( x -> 
			{ 
				if ( ! x.statusCode().is2xxSuccessful())
					return 	Mono.just(urlServer+urlFinal+" Called. Error 4xx: "+x.statusCode()+"\n");
				return x.bodyToMono(String.class);
			});		    	
		return respuesta1;		
	}	 */
}