package com.degloba.ecommerce.enviaments.webapp.reactive;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;
import com.degloba.ecommerce.enviaments.webapp.reactive.service.ClientEnviamentsService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EnviamentsRestController {
	
	  ClientEnviamentsService clientEnviamentsService = new ClientEnviamentsService();
		
		@RequestMapping("/enviaments/")
		@ResponseBody
		public List<EnviamentDto> getEnviaments(@RequestParam(required = false) String queryParam) {
									
			return clientEnviamentsService.buscarTotsEnviaments().collectList().block();
		}

}
