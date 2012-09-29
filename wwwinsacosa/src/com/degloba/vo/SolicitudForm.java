package com.degloba.vo;

import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;


public class SolicitudForm 
{
	private int idInmoble;
  	private String nomInmoble;
  	private String comprador;
  	
  	private int comptador;  // per comptabilitzar les solicituds per usuari i inmoble
  	
  	private List<SolicitudForm> listaSolicituds;
  	private List<SolicitudForm> listaSolicitudsPerInmoble;
	
  	public SolicitudForm()
  	{
    	super();
  	}
  	
  	
  	/*
	 * solicituds fetes a un determinat Venedor   	
	 */
  	public String solicituds() throws Exception
	{
  		 // recuperem l'usuari loginat
  		// a partir de l'atribut "usuari" guardat
  		FacesContext context = FacesContext.getCurrentInstance(); 
  		UserForm userForm = (UserForm) context.getApplication().evaluateExpressionGet(context, "#{userForm}", UserForm.class);

    	String idVenedor=userForm.getNomUsuari();
		
     	
    	this.setListaSolicituds(listaSolicituds);
   	
   	return "Success";

  	}
  	
  	
	/*
	 * solicituds fetes a un determinat Venedor i inmoble (group by idinmoble)  	
	 */
	public List<SolicitudForm> solicitudsPerInmoble() throws Exception
	{
		// recuperem l'usuari loginat
		// a partir de l'atribut "usuari" guardat
		FacesContext context = FacesContext.getCurrentInstance(); 
		UserForm userForm = (UserForm) context.getApplication().evaluateExpressionGet(context, "#{userForm}", UserForm.class);
	
		String email = userForm.getEmail();
			
		Iterator iter = listaSolicituds.iterator();
		
		// agrupem per calcular el nombre se soliciutds x inmoble
		while (iter.hasNext())
		{	
			SolicitudForm SolicitudForm = (SolicitudForm)iter.next();
			
			//comprovem si existeix un idInmoble en algun element de la llista
			int pos = cercaInmobleAllista(SolicitudForm.idInmoble,listaSolicitudsPerInmoble);
			if (pos==0)
				listaSolicitudsPerInmoble.add(SolicitudForm);
			else
				listaSolicitudsPerInmoble.get(pos).comptador++;
		}
		
		this.setListaSolicitudsPerInmoble(listaSolicitudsPerInmoble);
	    
		return listaSolicitudsPerInmoble;

	}
  	
  	

	public int getIdInmoble() {
		return idInmoble;
	}

	public void setIdInmoble(int idInmoble) {
		this.idInmoble = idInmoble;
	}

	public String getNomInmoble() {
		return nomInmoble;
	}

	public void setNomInmoble(String nomInmoble) {
		this.nomInmoble = nomInmoble;
	}

	public String getComprador() {
		return comprador;
	}

	public void setComprador(String comprador) {
		this.comprador = comprador;
	}



	public List<SolicitudForm> getListaSolicituds() {
		return listaSolicituds;
	}



	public void setListaSolicituds(List<SolicitudForm> listaSolicituds) {
		this.listaSolicituds = listaSolicituds;
	}



	public int getComptador() {
		return comptador;
	}



	public void setComptador(int comptador) {
		this.comptador = comptador;
	}


	public void setListaSolicitudsPerInmoble(
			List<SolicitudForm> listaSolicitudsPerInmoble) {
		this.listaSolicitudsPerInmoble = listaSolicitudsPerInmoble;
	}
	
  	
/*
 * Funcio auxiliar per trobar un element a la llista
 * retorna la posicio dins la llista
 * 0 si no existeix	
 */
	private Integer cercaInmobleAllista(int key, List<SolicitudForm> llista)
	{
		
		Iterator iter = llista.iterator();
		
		int i = 0;
		while (iter.hasNext())
		{
			SolicitudForm objecte = (SolicitudForm)iter.next();
			
			if (objecte.getIdInmoble() == key)
				return i; 
		}
		
		return 0;
	}
	

}
