package com.degloba.ecommerce.vendes.comandes.cqrs.queries.finders;


import java.util.List;

import com.degloba.cqrs.query.PaginatedResult;
import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.vendes.comandes.cqrs.queries.ComandesQuery;
import com.degloba.ecommerce.vendes.comandes.facade.dtos.ComandaDto;
import com.degloba.ecommerce.vendes.ofertes.cqrs.queries.OfertaQuery;
import com.degloba.ecommerce.vendes.ofertes.facade.dtos.ProducteOfertatDto;
import com.degloba.persistence.rdbms.api.jpa.AggregateId;


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
