package com.degloba.ecommerce.vendes.webapp.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.degloba.ecommerce.vendes.comandes.cqrs.queries.IComandaQuery;
import com.degloba.ecommerce.vendes.comandes.facade.dtos.ComandaDto;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.Comanda;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.User;

/**
 * 
 * 
 * @author degloba
 *
 */
@Controller
@RequestMapping(value = "/")
public class ComandaController {
	
	 @Autowired
	    private IComandaQuery comandaQuery;
	 
	    @Autowired
	    private IScheduledPostQueryService scheduledPostService;
	 
	    @Autowired
	    private ModelMapper modelMapper;
	    

	@GetMapping(value = "/api/comandes")
    @ResponseBody
    public List<ComandaDto> CercarTotesComandes(@RequestParam(value = "search") String search) {
       /* Specification<User> spec = resolveSpecificationFromInfixExpr(search);
        return dao.findAll(spec);*/
		return comandaQuery.CercarTotesComandes();
    }
	
	@GetMapping(value = "/api/comandes/{id}")
    @ResponseBody
    public List<ComandaDto> cercarComandesPerId(@RequestParam(value = "search") String search) {
    /*    Specification<User> spec = resolveSpecificationFromInfixExpr(search);
        return dao.findAll(spec);*/
    }
	
/*	@GetMapping(value = "/users/spec/adv")
    @ResponseBody
    public List<Comanda> findAllByAdvPredicate(@RequestParam(value = "search") String search) {
        Specification<User> spec = resolveSpecificationFromInfixExpr(search);
        return dao.findAll(spec);
    }*/

	   private ComandaDto convertComandaEntityToDto(Comanda comanda) {
		   ComandaDto dto = modelMapper.map(comanda, ComandaDto.class);
	        dto.setScheduledPostsCount(scheduledPostService.countScheduledPostsByUser(comanda));
	        return dto;
	    }
}
