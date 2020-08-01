package com.degloba.hotels;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.degloba.ecommerce.enviaments.webapp.controllers.impl.spring.ServerController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/hotels/")
public class HotelsRestController {
	
	@GetMapping("")
	public ResponseEntity<String> getHotels(@RequestParam(required = false) String queryParam) {
		log.debug("Received request at getExample:" + queryParam);
		
		if (queryParam == null  || "NULL".equals(queryParam))
			throw new RuntimeException("Give me a Param!");
		if (queryParam.equals("STOP"))
		{
			try {
				Thread.sleep(5000);
			} catch (Exception k) {k.printStackTrace();}
		}	
	
		return ResponseEntity.ok().body(queryParam);
	}
	
	@PostMapping("post")
	public ResponseEntity<String> postHotel(@RequestBody Map<String,String> body,ServerHttpRequest  request) {
		
		String s="the server said: "+body+"\n";
		for (Entry<String, List<String>> map : request.getHeaders().entrySet())
		{
			log.debug("Headers -> "+map.getKey()+ ":"+map.getValue().get(0));
			s+="Headers: "+map.getKey()+ ":"+map.getValue().get(0)+"\n";			
		}		
		return ResponseEntity.ok().body(s);
	}

}
