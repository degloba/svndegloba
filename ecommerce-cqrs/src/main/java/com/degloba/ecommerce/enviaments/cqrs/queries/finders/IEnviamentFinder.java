package com.degloba.ecommerce.enviaments.cqrs.queries.finders;

import java.util.List;

import com.degloba.cqrs.queries.annotations.FinderAnnotation;
import com.degloba.ecommerce.enviaments.facade.dtos.EnviamentDto;


/**
 * @category un {@link FinderAnnotation} d'{@link Enviament}
 * 
 * @author degloba
 *
 */
@FinderAnnotation
public interface IEnviamentFinder {

    List<EnviamentDto> buscaEnviaments();

}
