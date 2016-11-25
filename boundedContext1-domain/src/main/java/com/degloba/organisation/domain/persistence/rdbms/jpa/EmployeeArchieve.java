package com.degloba.organisation.domain.persistence.rdbms.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.degloba.domain.annotations.AggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.AbstractEntity;
import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;


@Entity
//@AggregateRoot
public class EmployeeArchieve extends AbstractEntity {
    
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

    /*public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }*/

    @Override
    public String[] businessKeys() {
        return new String[] {"employee"};
    }
    
}
