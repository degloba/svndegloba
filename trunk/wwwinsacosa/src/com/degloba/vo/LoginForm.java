package com.degloba.vo;


import com.degloba.interfaces.Usuari_Impl;


import javax.servlet.http.*;
import javax.faces.context.*;


import com.degloba.HBM.*;



public class LoginForm  {
	private String text;
	private String nomUsuari;
	private String password;

	public String getNomUsuari() {
		return nomUsuari;
	}
	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getText() {
		return text;
	}
	public void setText(String text){
		this.text = text;
	}


	public String validUser() throws Exception{
		
		//Interfaces interfaceS = serviceFinder.findBean("Interfaces");
		Usuari_Impl r = new Usuari_Impl();
		
		Usuaris usuari = new Usuaris();
		usuari.setNomusuari(nomUsuari);
		
		if (r.usuariValid(usuari))
		{
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		
			usuari = r.cercarUsuari(nomUsuari);
			
			if (usuari.getPassword().equals(this.password))
			{
				session.setAttribute("nomUsuari", usuari);  // Guardem el registre en sessio
			
				// per
				UserForm userForm = (UserForm) context.getApplication().evaluateExpressionGet(context, "#{userForm}", UserForm.class);
				
				userForm.setNomUsuari(usuari.getNomusuari());
				userForm.setAdreca(usuari.getAdreca());
				userForm.setEmail(usuari.getEmail());
				// .........
				
				userForm.setLoginat(true);
				
				return "success";
			}
			else
			{
				setText("User Name or Password is incorrect.");
				return "failure";
			}
		}
		else
		{
			setText("User Name or Password is incorrect.");
			return "failure";
		}
		
	}

	public String logout(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		if(session==null){
			return "invalid";
		}
		else{
			
			// per 
			UserForm userForm = (UserForm) context.getApplication().evaluateExpressionGet(context, "#{userForm}", UserForm.class);
			userForm.setLoginat(false);
			
			session.invalidate();
			return "logoutsuccess";
		}
		
	}	

}
		
