package com.degloba.boundedContext.modalpanel.readmodel;

import java.util.List;

import com.degloba.boundedContext.modalpanel.domain.Modalpanel;

import query.PaginatedResult;
import application.annotations.Finder;


@Finder
public interface IModalpanelFinder {

	List<Modalpanel> findAll();

	PaginatedResult<ModalpanelDto> query(ModalpanelQuery<?> orderQuery);

    
}
