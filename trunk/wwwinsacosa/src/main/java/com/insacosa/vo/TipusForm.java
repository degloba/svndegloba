package com.insacosa.vo;


import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.insacosa.application.services.CaracteristiquesApplicationService;
import com.insacosa.application.services.UsuarisAplicationService;
import com.insacosa.domain.Caracteristiques;
import com.insacosa.domain.Tipus;


import java.io.Serializable;
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
import javax.inject.Inject;

import org.richfaces.component.UIDataTable;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.presentation.InmoblesFinder;
import com.insacosa.presentation.SolicitudsFinder;
import com.insacosa.presentation.TipusFinder;
import com.insacosa.presentation.UsuarisFinder;
import com.insacosa.utils.HtmlDinamic;


@ManagedBean(name = "tipus")
@SessionScoped
public class TipusForm 
	implements ValueChangeListener, Serializable{
	
		// FinderS (lectura)
		//---------------------
		 
	    @Inject
	    private SolicitudsFinder solicitudsFinder;
	    @Inject
	    private TipusFinder tipusFinder;
	    @Inject
	    private InmoblesFinder inmoblesFinder;
	    @Inject
	    private CiutatsFinder ciutatsFinder;
	    @Inject
	    private UsuarisFinder usuarisFinder;
	    
	
	private Key key;  //PIS
	private String nom;
	
	private List<SelectItem> tipusInmoble;
	
	private int valorActual = 1;
	
	
	CaracteristiquesApplicationService caracteristiquesService;
	
	
	public List<SelectItem> getTipusInmoble() {
		
		List<SelectItem> list = new ArrayList<SelectItem>();
		 
		SelectItem item;
		 
		if (tipusInmoble == null) 
		{
			
		
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


	public void processValueChange(ValueChangeEvent event)
			throws AbortProcessingException {
		
		FacesContext facesContext = FacesContext.getCurrentInstance(); 
		InmobleForm inmobleForm = (InmobleForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{inmobleForm}", InmobleForm.class);
	
		inmobleForm.setCambiDades(true); 
		inmobleForm.setKeyTipus((Key)event.getNewValue());
		
		// 1.- construim els controls dinamics (dins la qual modifica la hashMap de columnes visibles)
		// -------------------------------------------------------------------------------------------
		inmobleForm.buildControlsDinamics(inmobleForm.getContainerControlsDinamics(),(String) event.getNewValue());
		 
		
		// 2.- construim les llistes dragAndDrop de les caracteristiques booleanes
		// -----------------------------------------------------------------------
		inmobleForm.buildLlistaDragAndDrop((Key) event.getNewValue());
		
			
		// 3.- construim la datatable dinamica
		// -----------------------------------
		inmobleForm.getFilterValues().clear();   // com tornem a construir la datatable
		
		
		// AQUI HEM DE SABER SI ESTEM A LA PAGINA DELS INMOBLES DEL VENDOR
		// O A LA PAGINA DE CERCAR INMOBLES
		
		// calculem la nova llista d'inmobles venedor
		 
	    Tipus tipus = new Tipus();
	    tipus.setKey((Key)event.getNewValue());
	    
	    List<Caracteristiques> lc = tipusFinder.caractTipus(tipus,1, false);
	    
	    
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


	public TipusForm() {
		super();
		
		/* IOC = Spring */
		   ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/configurationContext.xml");
		   BeanFactory factory = context;
		   
		   caracteristiquesService = (CaracteristiquesApplicationService) factory
			        .getBean("caracteristiquesApplicationService");
	}



}
