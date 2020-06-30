package com.degloba.ecommerce.vendes.comandes.cqrs.queries;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.jpa.domain.Specification;

import com.degloba.ecommerce.comandes.IComandaRepository;
import com.degloba.ecommerce.vendes.comandes.facade.dtos.ComandaDto;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.Comanda;
import com.degloba.ecommerce.vendes.domain.persistence.rdbms.jpa.IVendaRepository;
import com.degloba.ecommerce.vendes.domain.services.SuggerimentService;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;

import lombok.Value;

/**
 * @author degloba
 * 
 * @category Consulta : Order (patró CQRS)
 *
 */
@Value
public class ComandaQuery implements IComandaQuery{

	private String producteNom;
	

	@Inject
	private IComandaRepository comandaRepository; 

	
	@Inject
	private SuggerimentService suggerimentService;


	@Override
	public List<ComandaDto> CercarTotesComandes() {
		// TODO Auto-generated method stub
		return null;
	}


	public ComandaQuery(String producteNom, IComandaRepository comandaRepository,
			SuggerimentService suggerimentService) {
		super();
		this.producteNom = producteNom;
		this.comandaRepository = comandaRepository;
		this.suggerimentService = suggerimentService;
	}
	
	


	/*	@Override
	public List<ComandaDto> CercarTotesComandes() {
		
	    Specification<Comanda> spec = resolveSpecificationFromInfixExpr(search);
        return dao.findAll(spec);
		return null;
	}*/


}
