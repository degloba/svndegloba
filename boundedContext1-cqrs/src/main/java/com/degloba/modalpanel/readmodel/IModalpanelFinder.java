package com.degloba.modalpanel.readmodel;

import java.util.List;




import com.degloba.casino.modalpanel.Modalpanel;

import query.PaginatedResult;
import query.annotations.Finder;


@Finder
public interface IModalpanelFinder {

	List<Modalpanel> findAll();

	PaginatedResult<ModalpanelDto> query(ModalpanelQuery<?> orderQuery);

    
}
