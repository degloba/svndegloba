package com.insacosa.vo;


import com.google.appengine.api.datastore.Key;
import com.insacosa.interfaces.Inmoble_Impl;
import com.insacosa.interfaces.Objecte;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;

import org.richfaces.component.UIDataTable;

import com.insacosa.utils.HtmlDinamic;

import com.insacosa.entitats.Caracteristiques;
import com.insacosa.entitats.Tipus;

@ManagedBean(name = "tipus")
@SessionScoped
public class TipusForm extends Objecte 
	implements ValueChangeListener{
	
	private Key key;  //PIS
	private String nom;
	
	private List<SelectItem> tipusInmoble;
	
	private int valorActual = 1;
	
	
	public List<SelectItem> getTipusInmoble() {
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		 
		SelectItem item;
		 
		if (tipusInmoble == null) 
		{
			
			Iterator<Objecte> iter = llistaObjectes(Tipus.class, "nom", "").iterator();
			while (iter.hasNext())
				{				
					Tipus tipusHib = (Tipus)(iter.next());  // objecte Hibernate  
							
					item = new SelectItem(tipusHib.getId(), tipusHib.getNom() , "", false, false);
					
					list.add(item); 
				}
		
			setTipusInmoble(list);  // gravem la llista en memoria
		}

					
	return tipusInmoble;
		
	}

	
	public void setTipusInmoble(List<SelectItem> tipusInmoble) {
		this.tipusInmoble = tipusInmoble;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}


	@Override
	public void processValueChange(ValueChangeEvent event)
			throws AbortProcessingException {
		// TODO Auto-generated method stub
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		InmobleForm inmobleForm = (InmobleForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{inmobleForm}", InmobleForm.class);
	
		inmobleForm.setCambiDades(true); 
		inmobleForm.setKeyTipus((Key)event.getNewValue());
		
		// 1.- construim els controls dinamics (dins la qual modifica la hashMap de columnes visibles)
		// -------------------------------------------------------------------------------------------
		inmobleForm.buildControlsDinamics(inmobleForm.getContainerControlsDinamics(),(Key) event.getNewValue());
		 
		
		// 2.- construim les llistes dragAndDrop de les caracteristiques booleanes
		// -----------------------------------------------------------------------
		inmobleForm.buildLlistaDragAndDrop((Key) event.getNewValue());
		
			
		// 3.- construim la datatable dinamica
		// -----------------------------------
		inmobleForm.getFilterValues().clear();   // com tornem a construir la datatable
		
		
		// AQUI HEM DE SABER SI ESTEM A LA PAGINA DELS INMOBLES DEL VENDOR
		// O A LA PAGINA DE CERCAR INMOBLES
		
		// calculem la nova llista d'inmobles venedor
		Inmoble_Impl r = new Inmoble_Impl();
		 
	    Tipus tipus = new Tipus();
	    tipus.setId((Integer)event.getNewValue());
	    
	    List<Caracteristiques> lc = r.caractTipus(tipus,1, false);
		
		if (inmobleForm.isCercable())
		{
			inmobleForm.getContainerHtmlDataTableFS().getChildren().clear();
				        	
			UIDataTable datatable = HtmlDinamic.buildDatatableFS(
					"dyn_taulaInmobles",
					"#{inmobleForm.dataModel}",
					lc,
					inmobleForm.getColumnesVisibles(), 
					10,
					"odd-row, even-row",
					"stable",
					"datatable");
			
			inmobleForm.getContainerHtmlDataTableFS().getChildren().add(datatable);
			
		}
		else  
		{
			inmobleForm.getContainerHtmlDataTableVenedor().getChildren().clear();  
        		
			UIDataTable datatable = HtmlDinamic.buildDatatable(
					"dyn_taulaInmoblesVenedor",
					"#{inmobleForm.inmoblesVenedorCaract}",
					lc,
					inmobleForm.getColumnesVisibles(), 
					10,
					"odd-row, even-row",
					"stable",
					"datatable");
			
			inmobleForm.getContainerHtmlDataTableVenedor().getChildren().add(datatable);
			
		}
			
	}


	public int getValorActual() {
		return valorActual;
	}


	public void setValorActual(int valorActual) {
		this.valorActual = valorActual;
	}


}
