package com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;


@Entity
public class User  {
 
	@Id
	private Long id;
	
	@Unindex
	private String name;
	
	@Unindex
	/////@Embedded
	private Address address;
	
	@Index
	private Key<Role> role;

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Key<Role> getRole() {
		return role;
	}

	public void setRole(Key<Role> role) {
		this.role = role;
	}



}