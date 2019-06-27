package com.degloba.ecommerce.vendes.cqrs.readmodel.finders;


import java.util.List;

import com.degloba.cqrs.query.PaginatedResult;
import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.vendes.ofertes.cqrs.readmodel.OfertaQuery;
import com.degloba.ecommerce.vendes.ofertes.cqrs.readmodel.dtos.ProducteOfertatDto;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.ComandesQuery;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos.ComandaDto;
import com.degloba.persistence.rdbms.jpa.AggregateId;


/**
 * 
 * @category Finder (CQRS)
 * 
 * @author degloba
 *
 */
@Finder
public interface IVendaFinder {

	/**
	 * @category Retorna un {@link ComandaDto} a partir del seu {@link AggregateId}
	 * 
	 * @param comandaId
	 * @return
	 */
	ComandaDto find(AggregateId comandaId);

	/**
	 * @category Retorna un resultat paginat {@link PaginatedResult} de {@link ComandaDto}
	 * 
	 * @param comandesQuery
	 * @return
	 */
	PaginatedResult<ComandaDto> query(ComandesQuery comandesQuery);
	
	/**
	 * @category Cerca una llista de {@link ProducteOfertatDto} a partir d'una {@link OfertaQuery}
	 * 
	 * @param ofertaQuery
	 * @return
	 */
	List<ProducteOfertatDto> find(OfertaQuery ofertaQuery);
	
	
}
