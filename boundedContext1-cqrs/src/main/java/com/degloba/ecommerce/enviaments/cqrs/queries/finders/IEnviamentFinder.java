package com.degloba.ecommerce.enviaments.cqrs.queries.finders;

import java.util.List;

import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;


/**
 * @category un {@link Finder} d'{@link Enviament}
 * 
 * @author degloba
 *
 */
@Finder
public interface IEnviamentFinder {

    List<EnviamentDto> buscaEnviaments();

}
