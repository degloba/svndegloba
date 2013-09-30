package com.degloba.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;


@Entity
public class Menubar {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key id;

	private String abrev1;
	private String abrev2;
	private String abrev3;
	private Integer idmenuitem;
	
	
	public Key getId() {
		return id;
	}
	public void setId(Key id) {
		this.id = id;
	}
	public String getAbrev1() {
		return abrev1;
	}
	public void setAbrev1(String abrev1) {
		this.abrev1 = abrev1;
	}
	public String getAbrev2() {
		return abrev2;
	}
	public void setAbrev2(String abrev2) {
		this.abrev2 = abrev2;
	}
	public String getAbrev3() {
		return abrev3;
	}
	public void setAbrev3(String abrev3) {
		this.abrev3 = abrev3;
	}
	public Integer getIdmenuitem() {
		return idmenuitem;
	}
	public void setIdmenuitem(Integer idmenuitem) {
		this.idmenuitem = idmenuitem;
	}
	
	

}
