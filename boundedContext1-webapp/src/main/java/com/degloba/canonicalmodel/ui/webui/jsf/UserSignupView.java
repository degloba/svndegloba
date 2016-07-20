package com.degloba.canonicalmodel.ui.webui.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
 
import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Component;
 
@Component
@ManagedBean
@ViewScoped
public class UserSignupView {
     
    private String name;
    
    private String email;
     
    private String password;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
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

	public void signupEmail(ActionEvent event) {
		
		// Comprovar si ja està signUp a l'aplicació amb Email
				
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean signUpExist = false;
         
       if(name != null && name.equals("admin") && password != null && password.equals("admin")) {
    	   signUpExist = true;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ya està registrado", name);
        } else {
        	signUpExist = false;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sign up ok", name);
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("signUpExist", signUpExist);
    }   
	
	public void signupGoogle(ActionEvent event) {
		
		// Comprovar si ja està signUp a l'aplicació amb Google
				
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean signUpExist = false;
         
       if(name != null && name.equals("admin") && password != null && password.equals("admin")) {
    	   signUpExist = true;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ya està registrado", name);
        } else {
        	signUpExist = false;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sign up ok", name);
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("signUpExist", signUpExist);
    }   
	
	public void signupTwitter(ActionEvent event) {
		
		// Comprovar si ja està signUp a l'aplicació amb Twitter
				
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean signUpExist = false;
         
       if(name != null && name.equals("admin") && password != null && password.equals("admin")) {
    	   signUpExist = true;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ya està registrado", name);
        } else {
        	signUpExist = false;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sign up ok", name);
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("signUpExist", signUpExist);
    }   
	
	public void signupFacebook(ActionEvent event) {
		
		// Comprovar si ja està signUp a l'aplicació amb Facebook
				
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean signUpExist = false;
         
       if(name != null && name.equals("admin") && password != null && password.equals("admin")) {
    	   signUpExist = true;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ya està registrado", name);
        } else {
        	signUpExist = false;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sign up ok", name);
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("signUpExist", signUpExist);
    } 
	
}
