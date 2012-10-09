package com.degloba.utils;

import com.degloba.interfaces.Inmoble_Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import com.degloba.vo.CaracteristicaForm;
import com.degloba.vo.FotoForm;
import com.degloba.vo.InmobleForm;
import com.degloba.vo.UserForm;
import com.degloba.HBM.Caracteristiques;
import com.degloba.HBM.Ciutats;
import com.degloba.HBM.Fotos;
import com.degloba.HBM.Inmobles;
import com.degloba.HBM.Provincies;
import com.degloba.HBM.Tipus;
import com.degloba.HBM.Usuaris;
import com.degloba.dragdrop.DragDropBean;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class FormHBM {

	
	static FacesContext facesContext;
	
	static List<FotoForm> fotos = new ArrayList<FotoForm>();
	
	/*
     * Passa d'una Entity Google DataStore a objecte de Formulari
     */
    public static InmobleForm passarHBMtoForm(Entity inmoble)
    {
    	InmobleForm inmobleForm = new InmobleForm();
    	
    	String k = KeyFactory.keyToString(inmoble.getKey());
    	
    	inmobleForm.setKey(k);
		inmobleForm.setNom((String)inmoble.getProperty("Nom"));
		inmobleForm.setAdreca((String)inmoble.getProperty("Adreca"));
	
		inmobleForm.setIdTipus(inmoble.getTipus().getId());
		
		inmobleForm.setNumero((Short)inmoble.getProperty("Numero"));
		inmobleForm.setPlanta((Short)inmoble.getProperty("Planta"));
		inmobleForm.setPuerta((String)inmoble.getProperty("Puerta"));
		inmobleForm.setMetres((Short)inmoble.getProperty("Metres"));
		inmobleForm.setPreu((Long)inmoble.getProperty("Preu"));
	    
		// Objecte venedor
		inmobleForm.setVenedor(inmoble.getUsuaris().getNomusuari());
		
		inmobleForm.setVisitat((Boolean)inmoble.getProperty("isVisitat"));
	    
		// carreguem les fotos de l'inmoble
		Set<Fotos> fotos = inmoble.getFotoses();
		
		List<FotoForm> fotosForm = new ArrayList<FotoForm>();
		
		Iterator<?> it = fotos.iterator();
		while (it.hasNext())
		{
			FotoForm fotoForm = new FotoForm();
			
			Fotos foto = (Fotos) it.next();
			
			fotoForm.setId(foto.getId());
			fotoForm.setImatge(foto.getImatge());
			fotoForm.setDescripcio(foto.getDescripcio());
			fotoForm.setIdInmoble(foto.getInmobles().getId());
			
			fotosForm.add(fotoForm);
		}
		
		inmobleForm.setFotos(fotosForm);
		
		return inmobleForm;
    	
    }
	
    
     public static Inmobles passarFormtoEntity(InmobleForm inmobleForm)
    {
    
    	Entity inmoble = new Entity("Inmobles");
    	    	
    	inmoble.setKey(KeyFactory.stringToKey(inmobleForm.getKey())); 
		inmoble.setProperty("Nom", inmobleForm.getNom());
		inmoble.setProperty("Adreca", inmobleForm.getAdreca());
		
		Ciutats ciutat = new Ciutats();
		inmoble.setCiutats(ciutat);
	
		
		Provincies provincia = new Provincies();

		inmoble.setProvincies(provincia);
		
		Tipus tipus = new Tipus();
		tipus.setId(inmobleForm.getIdTipus());
		inmoble.setTipus(tipus);

		inmoble.setProperty("Numero",inmobleForm.getNumero());
		inmoble.setProperty("Planta",inmobleForm.getPlanta());
		inmoble.setProperty("Puerta",inmobleForm.getPuerta());
		inmoble.setProperty("Metres",inmobleForm.getMetres());
		inmoble.setProperty("Preu",inmobleForm.getPreu());
	
		facesContext = FacesContext.getCurrentInstance(); // Contexte JSF
		UserForm userForm = (UserForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userForm}", UserForm.class);
		Usuaris venedor = new Usuaris();
		venedor.setNomusuari(userForm.getNomUsuari());
		inmoble.setUsuaris(venedor);

		
		// **********************
		// COLECCIONS
		// 1.- CARACTERISTIQUES INMOBLE
		
		// afegim les coleccions (fotos,caracteristiques,...)
		facesContext = FacesContext.getCurrentInstance(); // Contexte JSF
		DragDropBean dragDropBean = (DragDropBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{dragDropBeanCaract}", DragDropBean.class);
		Inmoble_Impl r = new Inmoble_Impl();
		
		Iterator<?> it = dragDropBean.getTarget().iterator();
		while (it.hasNext())
		{
			CaracteristicaForm c = (CaracteristicaForm) it.next();
	
			Caracteristiques caracteristica = new Caracteristiques();
			
			//////////caracteristica.setId(c.getId());

			caracteristica = (Caracteristiques) r.caractPerId(c.getId());
						
			inmoble.getCaracteristiqueses().add(caracteristica);
	
		}
		
		
		
		// 2.- FOTOS INMOBLE
		Set<Fotos> fotos = new HashSet<Fotos>(); // Objectes Hibernate (Fotos)
		
		it = getFotos().iterator();
		while (it.hasNext())
		{
			FotoForm fotoForm = (FotoForm)it.next();
			
			Fotos foto = new Fotos();
			foto.setDescripcio(fotoForm.getDescripcio());
			foto.setId(fotoForm.getId());
			foto.setImatge(fotoForm.getImatge());
			//fotosetInmobles(fotoForm.getIdInmoble());
			
			fotos.add(foto);
			
		}
		
		inmoble.setFotoses(fotos);
    	
		
		
    	return inmoble;
    }

    
  
    public static List<UserForm> passarUsuarisHBMtoForm(List<Usuaris> usuaris)
    {
    	List<UserForm> usuarisForm = new ArrayList<UserForm>();
    	
        Iterator<Usuaris> it = usuaris.iterator();
		while (it.hasNext())
		{
			UserForm usuariForm = new UserForm();
			
			Usuaris usuari = (Usuaris)it.next();
			
			usuariForm.setNomUsuari(usuari.getNomusuari());
			usuariForm.setAdreca(usuari.getAdreca());
			usuariForm.setCognoms(usuari.getCognoms());
			usuariForm.setEmail(usuari.getEmail());
			usuariForm.setLocalitat(usuari.getLocalitat());
			
			usuarisForm.add(usuariForm);
		}
	
		return usuarisForm;
    }
    
    
    
 
    public static UserForm passarUsuariHBMtoForm(Usuaris usuari)
    {

		UserForm usuariForm = new UserForm();
			
		usuariForm.setNomUsuari(usuari.getNomusuari());
		usuariForm.setAdreca(usuari.getAdreca());
		usuariForm.setCognoms(usuari.getCognoms());
		usuariForm.setEmail(usuari.getEmail());
		usuariForm.setLocalitat(usuari.getLocalitat());
		
		return usuariForm;
    }
    
    
 
    public static Usuaris passarUsuariFormtoHBM(UserForm usuariForm)
    {

		Usuaris usuari = new Usuaris();
			
		usuari.setNomusuari(usuariForm.getNomUsuari());
		usuari.setAdreca(usuariForm.getAdreca());
		usuari.setCognoms(usuariForm.getCognoms());
		usuari.setEmail(usuariForm.getEmail());
		usuari.setLocalitat(usuariForm.getLocalitat());
		
		return usuari;
    }
    
    
  
    public static List<CaracteristicaForm> passarCaracteristiquesHBMtoForm(List<Caracteristiques> caracteristiques)
    {
    	List<CaracteristicaForm> caracteristiquesForm = new ArrayList<CaracteristicaForm>();
    	
        Iterator<?> it = caracteristiques.iterator();
		while (it.hasNext())
		{
			CaracteristicaForm caracteristicaForm = new CaracteristicaForm();
			
			Caracteristiques caract = (Caracteristiques)it.next();
			
			caracteristicaForm.setId(caract.getId());
			caracteristicaForm.setNom(caract.getNom());
			caracteristicaForm.setIdTipus(caract.getTipus().getId());
			
			caracteristiquesForm.add(caracteristicaForm);
		}
	
		return caracteristiquesForm;
		
    }    
	
    
	public static List<FotoForm> getFotos() {
		return fotos;
	}

	public void setFotos(List<FotoForm> fotos) {
		this.fotos = fotos;
	}
	
	
}
