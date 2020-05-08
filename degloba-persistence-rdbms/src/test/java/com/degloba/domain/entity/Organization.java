package com.degloba.domain.entity;

import java.util.Random;

import com.degloba.persistence.rdbms.api.jpa.BaseEntity;



public abstract class Organization extends BaseEntity {

	private static final long serialVersionUID = -545941352163679365L;
	private Long id;
	private String name;
	private boolean disabled = false;

	public Organization(String name) {
		id = new Random().nextLong();
		this.name = name;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDisabled() {
		return disabled;
	}

	void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void disable() {
		disabled = true;
	}

	public void enable() {
		disabled = false;
	}
}