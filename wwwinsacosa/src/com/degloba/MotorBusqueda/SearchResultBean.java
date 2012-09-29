package com.degloba.MotorBusqueda;

/**
 * Title: 
 * Description: 
 * Copyright:    Copyright (c) 2009
 * Company: Summa
 * @author Pere Santasusana
 * @version 1.0
 */

public class SearchResultBean {
	
	
	// Aqui posem els camps (Fields) que guardarem en el Index de Lucene
	private String path; 
	private String title;
	private String kExpedient;
	private String numAny;
	
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String Path) {
		this.path = Path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String Title) {
		this.title = Title;
	}
	
	public String getkExpedient() {
		return kExpedient;
	}
	public void setkExpedient(String KExpedient) {
		this.kExpedient = KExpedient;
	}
	
	public String getNumAny() {
		return numAny;
	}
	public void setNumAny(String numAny) {
		this.numAny = numAny;
	}	
	
	
	
}
