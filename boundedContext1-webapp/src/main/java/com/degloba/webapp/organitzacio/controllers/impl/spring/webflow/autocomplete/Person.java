package com.degloba.organisation.ui.webui.spring.webflow.autocomplete;

import java.io.Serializable;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String name;

	public Person(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
