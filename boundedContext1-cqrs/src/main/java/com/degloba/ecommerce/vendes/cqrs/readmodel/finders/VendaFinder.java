package com.degloba.ecommerce.vendes.cqrs.readmodel.finders;

import static com.google.common.collect.Lists.transform;

import java.util.ArrayList;
import java.util.List;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// Spring
import org.springframework.beans.factory.annotation.Qualifier;

import com.degloba.cqrs.query.PaginatedResult;
import com.degloba.domain.annotations.FinderImpl;

import com.degloba.ecommerce.vendes.compres.domain.persistence.rdbms.jpa.Compra;
import com.degloba.ecommerce.vendes.ofertes.cqrs.readmodel.OfertaQuery;
import com.degloba.ecommerce.vendes.ofertes.cqrs.readmodel.dtos.ProducteOfertatDto;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.ComandesQuery;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.EstatComanda;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos.ComandaDto;
import com.degloba.ecommerce.vendes.ordres.cqrs.readmodel.dtos.OrderedProductDto;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.Reserva;
import com.degloba.persistence.rdbms.jpa.AggregateId;
import com.degloba.ecommerce.vendes.reserves.domain.persistence.rdbms.jpa.ProducteReservat;
import com.google.common.base.Function;

/**
 * 
 * @author degloba
 * 
 * @category Finder (CQRS)</br>
 * Implementacio amb JPA
 *
 */
@FinderImpl
public class VendaFinder implements IVendaFinder {

	@PersistenceContext(unitName="transactions-optional")
    @Qualifier(value="entityManagerFactoryDatastore")
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<ProducteOfertatDto> find(OfertaQuery query) {
		@SuppressWarnings("unused")
		boolean bestBeforeExpired = query.isBestBeforeExpired();
		// TODO take into consideration in query

		return (List<ProducteOfertatDto>) entityManager
				.createQuery(
						"SELECT NEW com.degloba.ecommerce.sales.readmodel.offer.OfferedProductDto(p.aggregateId) FROM Product p")
				.getResultList();
	}
	
	@Override
	public ComandaDto find(AggregateId comandaId) {
		Reserva reserva = entityManager.find(Reserva.class, comandaId);
		Compra compra = entityManager.find(Compra.class, comandaId);
		
		return toOrderDto(reserva, compra);
	}

	private ComandaDto toOrderDto(Reserva reserva, Compra compra) {
		ComandaDto dto = new ComandaDto();
		dto.setComandaId(reserva.getAggregateId());
		List<ProducteReservat> producteReservats = reserva.getReservedProducts();
		dto.setOrderedProducts(new ArrayList<OrderedProductDto>(transform(producteReservats,
				reservedProductToOrderedProductDto())));
		if (compra != null) {
			dto.setEstatComanda(EstatComanda.CONFIRMED);

			// TODO CHECK PAYMENT!
			
		} else {
			dto.setEstatComanda(EstatComanda.NEW);
		}
		return dto;
	}

	private static Function<ProducteReservat, OrderedProductDto> reservedProductToOrderedProductDto() {
		return new Function<ProducteReservat, OrderedProductDto>() {
			public OrderedProductDto apply(ProducteReservat product) {
				OrderedProductDto dto = new OrderedProductDto();
				dto.setOfferId(product.getProducteId());
				return dto;
			}
		};
	}

	@Override
	public PaginatedResult<ComandaDto> query(ComandesQuery comandesQuery) {
		return null;
	}

}
