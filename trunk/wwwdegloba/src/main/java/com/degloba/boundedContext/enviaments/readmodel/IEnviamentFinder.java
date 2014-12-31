package com.degloba.boundedContext.enviaments.readmodel;

import java.util.List;

import com.degloba.boundedContext.enviaments.domain.Enviament;
import application.annotations.Finder;


@Finder
public interface IEnviamentFinder {

	List<Enviament> findAll();
    
}
