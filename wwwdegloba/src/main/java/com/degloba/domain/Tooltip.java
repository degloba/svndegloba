package com.degloba.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;

@Entity
public class Tooltip {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;
	
	private Integer tooltipid;
	private String abrev;
	private String per;
	
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public Integer getTooltipid() {
		return tooltipid;
	}
	public void setTooltipid(Integer tooltipid) {
		this.tooltipid = tooltipid;
	}
	public String getAbrev() {
		return abrev;
	}
	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}
	public String getPer() {
		return per;
	}
	public void setPer(String per) {
		this.per = per;
	}
	
	
	


}
