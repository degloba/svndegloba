package com.degloba.ecommerce.vendes.comandes.cqrs.queries;

import java.util.List;

import com.degloba.ecommerce.vendes.comandes.facade.dtos.ComandaDto;

public interface IComandaQuery {

	List<ComandaDto> CercarTotesComandes();

}
