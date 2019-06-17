package com.degloba.ecommerce.vendes.cqrs.readmodel.finders;


import java.util.List;

import com.degloba.cqrs.query.PaginatedResult;
import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.vendes.ofertes.cqrs.readmodel.OfertaQuery;
import com.degloba.ecommerce.vendes.ofertes.cqrs.readmodel.dtos.ProducteOfertatDto;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.OrdreQuery;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos.OrderDto;
import com.degloba.persistence.domain.AggregateId;

/**
 * 
 * @author degloba
 * 
 * @category Interfície : Finder (CQRS)
 *
 */
@Finder
public interface IVendaFinder {

	/**
	 * @category Retorna un {@link OrderDto} a partir del seu {@link AggregateId}
	 * 
	 * @param orderId
	 * @return
	 */
	OrderDto find(AggregateId orderId);

	/**
	 * @category Retorna un resultat paginat {@link PaginatedResult} de {@link OrderDto}
	 * 
	 * @param ordreQuery
	 * @return
	 */
	PaginatedResult<OrderDto> query(OrdreQuery ordreQuery);
	
	/**
	 * @category Cerca una llista de {@link ProducteOfertatDto} a partir d'una {@link OfertaQuery}
	 * 
	 * @param query
	 * @return
	 */
	List<ProducteOfertatDto> find(OfertaQuery query);
	
	
}
