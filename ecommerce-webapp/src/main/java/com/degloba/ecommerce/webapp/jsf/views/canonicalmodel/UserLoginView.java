package com.degloba.webapp.jsf.views.canonicalmodel;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;

import org.springframework.stereotype.Component;
 

//@ManagedBean
@ViewScoped
@Component
public class UserLoginView {
        
    private String email;
     
    private String password;
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
       
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void loginEmail(ActionEvent event) {
		
		// Comprovar si ja està signUp a l'aplicació amb Email
						
        FacesMessage message = null;
        boolean loggedIn = false;
         
       if(1 == 1) {
    	   loggedIn = true;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "name");
        } else {
        	loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);        
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
        
    }   
	
	public void loginGoogle(ActionEvent event) {
		
		// Comprovar si ja està signUp a l'aplicació amb Google
				
		//RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean loggedIn = false;
         
        if(1 == 1) {
     	   loggedIn = true;
             message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "name");
         } else {
         	loggedIn = false;
             message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
         }
         
        FacesContext.getCurrentInstance().addMessage(null, message);        
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }   
	
	public void loginTwitter(ActionEvent event) {
		
		// Comprovar si ja està signUp a l'aplicació amb Twitter
				
		FacesMessage message = null;
        boolean loggedIn = false;
         
        if(1 == 1) {
     	   loggedIn = true;
             message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "name");
         } else {
         	loggedIn = false;
             message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
         }
         
        FacesContext.getCurrentInstance().addMessage(null, message);        
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }   
	
	public void loginFacebook(ActionEvent event) {
		
		// Comprovar si ja està signUp a l'aplicació amb Facebook
						
        FacesMessage message = null;
        boolean loggedIn = false;
         
        if(1 == 1) {
     	   loggedIn = true;
             message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", "name");
         } else {
         	loggedIn = false;
             message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
         }
         
        FacesContext.getCurrentInstance().addMessage(null, message);        
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }

	public UserLoginView() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
	
}
