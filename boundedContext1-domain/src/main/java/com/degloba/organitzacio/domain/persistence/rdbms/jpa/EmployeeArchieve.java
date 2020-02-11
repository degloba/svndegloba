package com.degloba.organitzacio.domain.persistence.rdbms.jpa;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.degloba.domain.annotations.AggregateRoot;

import com.degloba.persistence.rdbms.jpa.BaseEntity;

import com.degloba.persistence.rdbms.jpa.BaseEntity;


@Entity
//@AggregateRoot
public class EmployeeArchieve extends BaseEntity {
    
    
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


	@Override
	public boolean existed() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean notExisted() {
		// TODO Auto-generated method stub
		return false;
	}
    
}
