package com.degloba.organisation.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
//@DiscriminatorValue("Comapny")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CompanyDepartment extends Organization {

    private static final long serialVersionUID = -7339118476080239701L;

    public CompanyDepartment() {
    }

    public CompanyDepartment(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append(getName()).build();
    }

}
