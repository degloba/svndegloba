package com.degloba;

import java.util.ArrayList;

public  class dropDownMenu  {
	
	public ArrayList<menuItem>  menuItems = new ArrayList<menuItem>();
	private  String nom = "1";
	
	public dropDownMenu() {
		super();  
	}
	
	public dropDownMenu(String nom) {	
		super();
		this.nom=nom;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	public  ArrayList<menuItem> getMenuItems() {
		return this.menuItems;
	}

	public void setMenuItems(ArrayList<menuItem> menuItems) {
		this.menuItems = menuItems;
	}
	
}
