package com.degloba.boundedContext.readmodel.modalpanel;

import java.util.List;

import query.PaginatedResult;
import application.annotations.Finder;

import com.degloba.boundedContext.domain.modalpanel.Modalpanel;

@Finder
public interface IModalpanelFinder {

	List<Modalpanel> findAll();

	PaginatedResult<ModalpanelDto> query(ModalpanelQuery<?> orderQuery);

    
}
