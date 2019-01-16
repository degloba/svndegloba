package com.degloba.canonicalmodel.ui.webui.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Component;
 

//@ManagedBean
//@RequestScoped
@Component
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
        String signUPType = "email";
         
       if(name != null && name.equals("admin") && password != null && password.equals("admin")) {
    	   signUpExist = true;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ya està registrado", name);
        } else {
        	signUpExist = false;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sign up ok", name);
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("signUpExist", signUpExist);
        PrimeFaces.current().ajax().addCallbackParam("signUPType", signUPType);
    }   
	
	public void signupGoogle() {
		
		// Comprovar si ja està signUp a l'aplicació amb Google
				
		RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        boolean signUpExist = false;
        String signUPType = "google";
         
       if(name != null && name.equals("admin") && password != null && password.equals("admin")) {
    	   signUpExist = true;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Ya està registrado", name);
        } else {
        	signUpExist = false;
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sign up ok", name);
        }
         
        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("signUpExist", signUpExist);
        PrimeFaces.current().ajax().addCallbackParam("signUPType", signUPType);
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
        PrimeFaces.current().ajax().addCallbackParam("signUpExist", signUpExist);
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
        PrimeFaces.current().ajax().addCallbackParam("signUpExist", signUpExist);
    }

	public UserSignupView() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
	
	
}
