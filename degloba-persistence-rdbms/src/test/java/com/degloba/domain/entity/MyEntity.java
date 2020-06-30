package com.degloba.domain.entity;

import javax.persistence.Entity;

import com.degloba.persistence.rdbms.api.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.api.jpa.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyEntity extends BaseAggregateRoot {
    private String name;


    @Override
    public String toString() {
        return name;
    }

    @Override
    public String[] businessKeys() {
        return new String [] {"name"};
    }



}
