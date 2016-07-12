package com.degloba.rent.domain.objectify;

import com.degloba.objectify.EntityAggregateRootObjectify;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Indexed;
import com.googlecode.objectify.annotation.Unindexed;

@Entity
public class UserObjectify implements EntityAggregateRootObjectify {
 
	@Id
	private Long id;
	
	@Unindexed
	private String name;
	
	@Unindexed
	/////@Embedded
	private AddressObjectify address;
	
	@Indexed
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