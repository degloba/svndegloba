package com.degloba.dataModels_JPA;


import javax.persistence.EntityManager;

import org.ajax4jsf.model.ExtendedDataModel;

import org.richfaces.model.TreeDataModel;


public abstract class JPADataModeltreeSequenceKey<T> extends ExtendedDataModel<T> implements TreeDataModel<Object> {

    private EntityManager entityManager;

    private Class<T> entityClass;
    
    public JPADataModeltreeSequenceKey(EntityManager entityManager, Class<T> entityClass) {
        super();
        
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }
     
}
