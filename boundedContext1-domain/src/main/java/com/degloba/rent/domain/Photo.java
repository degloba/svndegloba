package com.degloba.rent.domain;

import com.degloba.domain.BaseAggregateRoot;

import javax.persistence.*;


@Entity
@Table(name = "photos")
public class Photo extends BaseAggregateRoot {
  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idGcs; 
	
	@Embedded
    private Category category;
    
    @Embedded
    private Owner owner;
  

    public Photo(Category category) {
        this.category = category;
    }
   
 
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public Owner getOwner() {
		return owner;
	}


	public void setOwner(Owner owner) {
		this.owner = owner;
	}


	public String getIdGcs() {
		return idGcs;
	}


	public void setIdGcs(String idGcs) {
		this.idGcs = idGcs;
	}

}
