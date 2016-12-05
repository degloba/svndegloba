package com.degloba.domain.entity;

import javax.persistence.Entity;

import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.BaseEntity;

@Entity
public class MyEntity extends BaseAggregateRoot {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
