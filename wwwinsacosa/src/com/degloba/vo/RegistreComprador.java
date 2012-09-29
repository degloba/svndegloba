package com.degloba.vo;



public class RegistreComprador 
{

  	private String nom = null;
  	private String cognoms = null;
  	private String adreca = null;
  	private String localitat = null;
  	private String codi = null;
  	private String provincia = null;
  	private String telefon = null;
  	private String email = null;
  	private String email2 = null;
  	
  	
 	/**
   	 * Se llama por el controlador cuando un cliente intenta registrar-se
   	 * 
   	 **/
  	public String afegirInmoblesComprador()
  		throws Exception
	{

		
///////////List<InmobleForm> inmobles = (ArrayList) request.getSession().getAttribute("inmobles");
		
   		
///////////Iterator iter = inmobles.iterator();
   	
   		
///////////while (iter.hasNext())
///////////{

  ///////////	SolicitudForm SolicitudForm = (SolicitudForm)(iter.next());  
			
///////////Solicituds solicitudHib = new Solicituds();
   			
  ///////////solicitudHib.setComprador(SolicitudForm.getComprador());
  ///////////solicitudHib.setIdinmoble(SolicitudForm.getIdInmoble());
   			
	  		
      		// Control.lem entre d'altres, els errors
      		// de duplicats
      		// ex: un comprador (possible comprador)
      		// solicitant el mateix inmoble en diferentes 
      		// sessions (registres duplicats !!!!)
   			///////////solicitudHib.create();
   			
///////////	} 	
	
/*
	    	
	    	Comprador compradorHib = new Comprador();
	    	
	    	compradorHib.setNom(nom);
	    	compradorHib.setCognoms(cognoms);
	    	compradorHib.setAdreca(adreca);
	    	compradorHib.setLocalitat(localitat);
	    	compradorHib.setCodi(codi);
	    	compradorHib.setProvincia(provincia);
	    	compradorHib.setTelefon(telefon);
	    	compradorHib.setEmail(email);
	*/    	     		
      		
      		// Insertem el comprador a la taula compradors
///////////compradorHib.create();
	
    	
	    	return "Success";

  	}
	
  	
  	public void setNom(String nom) 
  	{
	    this.nom = nom;
  	}
	
  	public String getNom() 
  	{
	    return (this.nom);
  	}
 
  	public void setCognoms(String cognoms) 
  	{
	    this.cognoms = cognoms;
  	}
	
  	public String getCognoms() 
  	{
	    return (this.cognoms);
  	}  	
  	
  	public void setAdreca(String adreca) 
  	{
	    this.adreca = adreca;
  	}
	
  	public String getAdreca() 
  	{
	    return (this.adreca);
  	}
  	
  	public void setLocalitat(String localitat) 
  	{
	    this.localitat = localitat;
  	}
	
  	public String getLocalitat() 
  	{
	    return (this.localitat);
  	}
  	
  	public void setCodi(String codi) 
  	{
	    this.codi = codi;
  	}
	
  	public String getCodi() 
  	{
	    return (this.codi);
  	}

  	public void setProvincia(String provincia) 
  	{
	    this.provincia = provincia;
  	}
	
  	public String getProvincia() 
  	{
	    return (this.provincia);
  	}
  	
  	public void setTelefon(String telefon) 
  	{
	    this.telefon = telefon;
  	}
	
  	public String getTelefon() 
  	{
	    return (this.telefon);
  	}
  	
  	public void setEmail(String email) 
  	{
	    this.email = email;
  	}
  	
  	public String getEmail() 
  	{
	    return(this.email);
  	}
  	

  	public void setEmail2(String email2) 
  	{
	    this.email2 = email2;
  	}
  	
  	public String getEmail2() 
  	{
	    return(this.email2);
  	}
  	
  	
 	
  	
/* PER PODER ACCEDIR DES DE UN JSP A UN METODE DEL
 * BEAN CAL QUE ES DIGUI getNom_metode on el primer caracter
 * del nom del metode ha de ser majuscula
 * Mirar registre_comprador_KO.jsp
 */  	
  	public boolean getCorreos_iguals()
  	{
    	/*
    	 * Cal que els 2 correos (el primer i el de comprovaci?) siguin iguals
    	 */
    	
    	if(getEmail().compareTo(getEmail2()) != 0 ) 
    	{
 //     		errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("global.required", "email"));
      		System.err.println("Els 2 correos no son iguals!!!!!");
      		
      		return false;
    	}  
    	
    	return true;
  	}

  	
  	
}
