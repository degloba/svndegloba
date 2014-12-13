package com.degloba.boundedContext.enviaments.readmodel;

import java.util.List;

import com.degloba.boundedContext.modalpanel.domain.Modalpanel;
import com.degloba.boundedContext.modalpanel.readmodel.ModalpanelDto;
import com.degloba.boundedContext.modalpanel.readmodel.ModalpanelQuery;

import query.PaginatedResult;
import application.annotations.Finder;


@Finder
public interface IEnviamentFinder {

	List<Modalpanel> findAll();

	PaginatedResult<ModalpanelDto> query(ModalpanelQuery<?> orderQuery);

    
}
