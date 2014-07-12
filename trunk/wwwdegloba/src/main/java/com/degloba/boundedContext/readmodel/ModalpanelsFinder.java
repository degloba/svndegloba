package com.degloba.boundedContext.readmodel;

import java.util.List;

import application.annotations.Finder;

import com.degloba.boundedContext.domain.Modalpanel;

@Finder
public interface ModalpanelsFinder {

	List<Modalpanel> findAll();

    
}
