package com.degloba.enviaments.application.readmodel;

import java.util.List;

import com.degloba.casino.enviaments.Enviament;

import query.annotations.Finder;


@Finder
public interface IEnviamentFinder {

	List<Enviament> findAll();

	List<EnviamentDto> findShipment();
    
}
