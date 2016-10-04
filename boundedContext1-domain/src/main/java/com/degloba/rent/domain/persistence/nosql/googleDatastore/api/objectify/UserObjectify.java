package com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify;


import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;


@Entity
public class UserObjectify  {
 
	@Id
	private Long id;
	
	@Unindex
	private String name;
	
	@Unindex
	/////@Embedded
	private AddressObjectify address;
	
	@Index
	private Key<RoleObjectify> role;

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

	public AddressObjectify getAddress() {
		return address;
	}

	public void setAddress(AddressObjectify address) {
		this.address = address;
	}

	public Key<RoleObjectify> getRole() {
		return role;
	}

	public void setRole(Key<RoleObjectify> role) {
		this.role = role;
	}



}