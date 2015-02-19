package com.degloba.domain.entity;

import java.util.Random;

import com.degloba.domain.BaseEntity;

//public abstract class Organization extends com.degloba.domain.support.BaseEntity {
public abstract class Organization extends com.degloba.domain.support.BaseEntity {

	private static final long serialVersionUID = -545941352163679365L;
	private Long id;
	private String name;
	private boolean disabled = false;

	public Organization(String name) {
		id = new Random().nextLong();
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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