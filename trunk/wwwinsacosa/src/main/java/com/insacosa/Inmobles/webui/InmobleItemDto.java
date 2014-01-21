package com.insacosa.Inmobles.webui;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.insacosa.vo.FotoForm;

/**
 * Helper DTO to created by a controller (and not by finder like other DTOs).
 * 
 * @author Rafał Jamróz
 */
public class InmobleItemDto {
   
    
    
    public InmobleItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public void setInmobleKey(String keyInmoble) {
		// TODO Auto-generated method stub
		
	}

	public void setNom(String nom) {
		// TODO Auto-generated method stub
		
	}

	public void setAdreca(String adreca) {
		// TODO Auto-generated method stub
		
	}

	public void setPreu(long preu) {
		// TODO Auto-generated method stub
		
	}

	public List<FotoForm> getFotoses() {
		// TODO Auto-generated method stub
		return null;
	}

	public Key getInmobleKey() {
		// TODO Auto-generated method stub
		return null;
	}



	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}



	public String getAdreca() {
		// TODO Auto-generated method stub
		return null;
	}



	public boolean isVisitat() {
		// TODO Auto-generated method stub
		return false;
	}



	public Short getPlanta() {
		// TODO Auto-generated method stub
		return null;
	}



	public Short getNumero() {
		// TODO Auto-generated method stub
		return null;
	}



	public Short getMetres() {
		// TODO Auto-generated method stub
		return null;
	}



	public long getPreu() {
		// TODO Auto-generated method stub
		return 0;
	}



	public String getPuerta() {
		// TODO Auto-generated method stub
		return null;
	}
}
