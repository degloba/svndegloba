package com.insacosa.utils;

import com.insacosa.interfaces.Inmoble_Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.context.FacesContext;

import com.insacosa.vo.CaracteristicaForm;
import com.insacosa.vo.FotoForm;
import com.insacosa.vo.InmobleForm;
import com.insacosa.vo.UserForm;

import com.insacosa.domain.*;

import com.insacosa.dragdrop.DragDropBean;

public class FormHBM {

	
	static FacesContext facesContext;
	
	static List<FotoForm> fotos = new ArrayList<FotoForm>();
	
	/*
     * Passa d'un objecte Hibernate (HBM) a objecte de Formulari
     */
    public static InmobleForm passarHBMtoForm(Inmobles inmoble)
    {
    	InmobleForm inmobleForm = new InmobleForm();
    	
    	inmobleForm.setKey(inmoble.getInmobleKey());
		inmobleForm.setNom(inmoble.getNom());
		inmobleForm.setAdreca(inmoble.getAdreca());
	    
		////////////inmobleForm.setProvincia(inmoble.getProvincies().getKey());

		inmobleForm.setKeyTipus(inmoble.getTipus().getKey());
		
		//inmobleForm.setLloguer(inmoble.getLloguer());
		inmobleForm.setNumero(inmoble.getNumero());
		inmobleForm.setPlanta(inmoble.getPlanta());
		inmobleForm.setPuerta(inmoble.getPuerta());
		inmobleForm.setMetres(inmoble.getMetres());
		inmobleForm.setPreu(inmoble.getPreu());
	    
		// Objecte venedor
		  
		inmobleForm.setVenedor(inmoble.getUsuaris().getUsuariKey());
		
		inmobleForm.setVisitat(inmoble.isVisitat());
	    
		// carreguem les fotos de l'inmoble
		Set<Fotos> fotos = inmoble.getFotoses();
		
		List<FotoForm> fotosForm = new ArrayList<FotoForm>();
		
		Iterator<?> it = fotos.iterator();
		while (it.hasNext())
		{
			FotoForm fotoForm = new FotoForm();
			
			Fotos foto = (Fotos) it.next();
			
			fotoForm.setKey(foto.getKey());
			fotoForm.setImatge(foto.getImatge());
			fotoForm.setDescripcio(foto.getDescripcio());
			fotoForm.setKeyInmoble(foto.getInmobles().getInmobleKey());
			
			fotosForm.add(fotoForm);
		}
		
		inmobleForm.setFotos(fotosForm);
		
		return inmobleForm;
    	
    }
	
    
     public static Inmobles passarFormtoHBM(InmobleForm inmobleForm)
    {
    
    	Inmobles inmoble = new Inmobles();
    	    	
    	inmoble.setInmobleKey(inmobleForm.getKey()); 
		inmoble.setNom(inmobleForm.getNom());
		inmoble.setAdreca(inmobleForm.getAdreca());
		
		Ciutats ciutat = new Ciutats();
		//////////ciutat.setKey(inmobleForm.getLocalitat());
		//////////ciutat.setIdProv(inmobleForm.getProvincia());
		inmoble.setCiutats(ciutat);
		
		
		Provincies provincia = new Provincies();
		///////////provincia.setKey(inmobleForm.getProvincia());
		inmoble.setProvincies(provincia);
		
		Tipus tipus = new Tipus();
		tipus.setKey(inmobleForm.getKeyTipus());
		inmoble.setTipus(tipus);

		//inmoble.setLloguer(lloguer);
		inmoble.setNumero(inmobleForm.getNumero());
		inmoble.setPlanta(inmobleForm.getPlanta());
		inmoble.setPuerta(inmobleForm.getPuerta());
		inmoble.setMetres(inmobleForm.getMetres());
		inmoble.setPreu(inmobleForm.getPreu());
	
		facesContext = FacesContext.getCurrentInstance(); // Contexte JSF
		UserForm userForm = (UserForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userForm}", UserForm.class);
		Usuaris venedor = new Usuaris();
		
	
		venedor.setUsuariKey(userForm.getKey());
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
			
			//////////caracteristica.setKey(c.getKey());

			caracteristica = (Caracteristiques) r.caractPerKey(c.getKey());
						
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
			foto.setKey(fotoForm.getKey());
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

			usuariForm.setKey(usuari.getUsuariKey());
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
			
		usuariForm.setKey(usuari.getUsuariKey());
		usuariForm.setAdreca(usuari.getAdreca());
		usuariForm.setCognoms(usuari.getCognoms());
		usuariForm.setEmail(usuari.getEmail());
		usuariForm.setLocalitat(usuari.getLocalitat());
		
		return usuariForm;
    }
    
    
 
    public static Usuaris passarUsuariFormtoHBM(UserForm usuariForm)
    {

		Usuaris usuari = new Usuaris();
		
		usuari.setUsuariKey(usuariForm.getKey());
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
			
			caracteristicaForm.setKey(caract.getCaracteristicaKey());
			caracteristicaForm.setNom(caract.getNom());
			//caracteristicaForm.setKeyTipus(caract.getTipus().getTipusKey());
			
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
