package com.degloba.domain.entity;

import javax.persistence.Entity;

import com.degloba.persistence.rdbms.api.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;

@Entity
public class MyEntity extends BaseAggregateRoot {
    private String name;

    public MyEntity() {
    	this.name="prova";
    }

    public MyEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String[] businessKeys() {
        return new String [] {"name"};
    }



}
