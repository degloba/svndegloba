

import javax.faces.context.FacesContext;

public class client2 {
	
	private String nom;
	private String correo;
	private String error;
	
	
	public client2() {
		super();
		nom="";
		correo="";
			

	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		if (correo=="hola")
			setError("error de correo");
		this.correo = correo;
	}
	
	
	
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	public void doContactar()
	{

		// enviem correo a santasusanap@yahoo.es
		// utilitzant el mail.java (ENREDADOS)
		
		
		System.out.println("valor de cooo" + correo);
		if (!correo.contains("@"))
			{
			setError("Les dades son incorrectes");
				}
		else
		{
			setError("Enviant correo.....");

		FacesContext context = FacesContext.getCurrentInstance();
		client2 cl = (client2)context.getExternalContext().getSessionMap().get("client"); 
		
						
		System.out.println(cl.getCorreo() + cl.getNom());
		
		email carter=new email();
		
		carter.enviarMensaje("degloba@degloba.com","Client : " + cl.getNom(),cl.getCorreo());
		
		setError("");
		}    	

	}
	

}
