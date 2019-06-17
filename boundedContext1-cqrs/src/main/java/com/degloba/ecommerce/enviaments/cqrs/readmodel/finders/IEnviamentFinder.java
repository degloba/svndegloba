package com.degloba.ecommerce.enviaments.cqrs.readmodel.finders;

import java.util.List;

import com.degloba.cqrs.query.annotations.Finder;
import com.degloba.ecommerce.enviaments.cqrs.readmodel.dtos.EnviamentDto;


@Finder
public interface IEnviamentFinder {

    List<EnviamentDto> findShipment();

}
