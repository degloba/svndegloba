package com.insacosa.vo;

// IOC - GUICE
/*import com.google.inject.Guice;
import com.google.inject.Injector;*/

// SERVEIS APLICACIO

import com.insacosa.Inmobles.application.services.UsuarisAplicationService;
import com.insacosa.Inmobles.domain.Usuaris;
import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.presentation.InmoblesFinder;
import com.insacosa.presentation.SolicitudsFinder;
import com.insacosa.presentation.TipusFinder;
import com.insacosa.presentation.UsuarisFinder;


import javax.servlet.http.*;
import javax.faces.context.*;
import javax.inject.Inject;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class LoginForm  {
	
	private String key;
	private String text;
	private String nomUsuari;
	private String password;
	
	
	UsuarisAplicationService usuarisService;
	
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
	

	public String getkey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
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


	public LoginForm() {
		super();
		// TODO Auto-generated constructor stub
		
		/* IOC = Spring */
		   ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/configurationContext.xml");
		   BeanFactory factory = context;
		   
		    usuarisService = (UsuarisAplicationService) factory
			        .getBean("usuarisApplicationService");
	  
		
	}
	
	
	
	public String validUser() throws Exception{
		
		Usuaris usuari = new Usuaris();
		usuari.setNomusuari(nomUsuari);
		
		usuari = usuarisFinder.usuariValid(usuari);
		
		if (usuari != null)  // existeix l'usuari ?
		{
			FacesContext context = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			
			if (usuari.getPassword().equals(this.password))
			{
				session.setAttribute("key", usuari);  // Guardem el registre en sessio
				
				// per
				UserForm userForm = (UserForm) context.getApplication().evaluateExpressionGet(context, "#{userForm}", UserForm.class);
				
				/////userForm.set(usuari.getKey());
				userForm.setAdreca(usuari.getAdreca());
				userForm.setEmail(usuari.getEmail());
				userForm.setCognoms(usuari.getCognoms());
				userForm.setLocalitat(usuari.getLocalitat());
				userForm.setProvincia(usuari.getProvincia());
				userForm.setProvincia(usuari.getProvincia());
				userForm.setTelefon(usuari.getTelefon());
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
		
