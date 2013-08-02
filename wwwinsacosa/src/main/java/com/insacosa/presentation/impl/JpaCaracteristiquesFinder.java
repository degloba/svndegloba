package com.insacosa.presentation.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import query.annotations.Finder;

import com.insacosa.presentation.CaracteristiquesFinder;
import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.webui.CiutatItemDto;
import com.insacosa.webui.ProvinciaItemDto;


/**
 * @author Pere Santasusana
 */
@Finder
public class JpaCaracteristiquesFinder implements CaracteristiquesFinder {

    @PersistenceContext
    private EntityManager entityManager;

	
   
    
}
