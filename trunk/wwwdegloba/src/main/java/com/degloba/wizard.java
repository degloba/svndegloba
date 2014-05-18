package com.degloba;

import java.util.ArrayList;

public  class wizard  extends ObjecteNode{
	
	private int id;
	private String abrev;  // Es correspon am idioma_XX.properties i la columna de la taula
	private int ordre;
	private ArrayList<jsp> jsps = new ArrayList<jsp>();
	private ArrayList<jspgrafic> jspsgrafics = new ArrayList<jspgrafic>();  // url d'un HTML que conté un png/gif "desglossat" amb ImageCut 
	private int idAnterior;  // id de l'anterior wizard dins l'arbre de wizards
	private int idArbre;  // id dins l'arbre del wizard 
	
	
	public wizard() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbrev() {
		return abrev;
	}

	public void setAbrev(String abrev) {
		this.abrev = abrev;
	}

	public int getOrdre() {
		return ordre;
	}

	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}


	public ArrayList<jsp> getJsps() {
		return jsps;
	}


	public void setJsps(ArrayList<jsp> jsps) {
		this.jsps = jsps;
	}


	public ArrayList<jspgrafic> getJspsgrafics() {
		return jspsgrafics;
	}


	public void setJspsgrafics(ArrayList<jspgrafic> jspsgrafics) {
		this.jspsgrafics = jspsgrafics;
	}

	
	public int getIdArbre() {
		return idArbre;
	}


	public void setIdArbre(int idArbre) {
		this.idArbre = idArbre;
	}


	public int getIdAnterior() {
		return idAnterior;
	}


	public void setIdAnterior(int idAnterior) {
		this.idAnterior = idAnterior;
	}
	
}
