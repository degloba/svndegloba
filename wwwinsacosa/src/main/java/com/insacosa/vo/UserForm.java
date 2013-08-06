package com.insacosa.vo;


import com.degloba.JPA.*;

// IOC - GUICE
import com.google.inject.Guice;
import com.google.inject.Injector;

// SERVEIS APLICACIO
import com.insacosa.application.services.UsuarisAplicationService;
import com.insacosa.webui.UsuariItemDto;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import guice.modules.BillingModule;

import java.util.*;
import java.util.regex.*;

import com.degloba.jsf.*;

import javax.servlet.http.*;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserForm  implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;

	UsuarisAplicationService usuarisService;

	public UserForm(){
		
		/* IOC = Spring */
		   ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/configurationContext.xml");
		   BeanFactory factory = context;
		   
		    usuarisService = (UsuarisAplicationService) factory
			        .getBean("usuarisApplicationService");
		
	}

	private String guid;	
  	private String nomUsuari = null;
  	private String nom = null;
  	private String cognoms = null;
  	private String adreca = null;
  	private String localitat = null;
  	private String codi = null;
  	private String provincia = null;
  	private String telefon = null;
  	private String email = null;
  	private String email2 = null;

	private String password ;
	private String confirmPassword ;

	private String select;

	private String contactNumber ;

	private Boolean loginat = false;  // indica si es un usuari loginat

	private boolean isNomUsuariDisabled;
	private String buttonName="Register";	
	
	private String oldPwd;
    private String newPwd;
    private String newPwdConfirm;
    private String text;

	private Transport transport;
	private Message message;
	private Session session;
        

	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getNomUsuari() {
		return nomUsuari;
	}
	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCognoms() {
		return cognoms;
	}
	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}
	public String getAdreca() {
		return adreca;
	}
	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}
	public String getLocalitat() {
		return localitat;
	}
	public void setLocalitat(String localitat) {
		this.localitat = localitat;
	}
	public String getCodi() {
		return codi;
	}
	public void setCodi(String codi) {
		this.codi = codi;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}	

	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public boolean getIsNomUsuariDisabled() {
		return isNomUsuariDisabled;
	}
	public void setIsNomUsuariDisabled(boolean isNomUsuariDisabled) {
		this.isNomUsuariDisabled = isNomUsuariDisabled;
	}

	public String getButtonName() {
		return buttonName;
	}
	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getOldPwd(){
        return oldPwd;
    }

    public void setOldPwd(String oldPwd){
        this.oldPwd = oldPwd;
    }

    public String getNewPwd(){
        return newPwd;
    }

    public void setNewPwd(String newPwd){
        this.newPwd = newPwd;
    }

    public String getNewPwdConfirm(){
        return newPwdConfirm;
    }

    public void setNewPwdConfirm(String newPwdConfirm){
        this.newPwdConfirm = newPwdConfirm;
    }

    public String getText(){
        return text;
    }

    public void setText(String text){
        this.text = text;
    }
    
	public Boolean getLoginat() {
		return loginat;
	}
	public void setLoginat(Boolean loginat) {
		this.loginat = loginat;
	}
	

	
	public String changePassword() throws Exception {
		
		if(validatePassword()){
                      
			boolean isPwdValid = false;
			isPwdValid = this.getPassword().equals(getOldPwd());			
			if(isPwdValid){
			
				this.setPassword(this.newPwd);

				usuarisService.cambiaPassword(this.getGuid(),this.newPwd);

				return "success";
			}
			else{
				
				MessageFactory mf = new MessageFactory();
				setText(mf.getMessage("errorOldPasswordIncorrect"));
				
				return "failure";
			}
		}
		else{
			return "failure";
		}
    }

	private boolean validatePassword(){
        boolean status = true;
        MessageFactory mf = new MessageFactory();
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(newPwd.length() < 6 || newPwd.length() > 20){
			ctx.addMessage("changePwdForm:newPwd", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorPasswordLength"), null));
            status = false;
        }
        Pattern p1 = Pattern.compile("^[a-zA-Z0-9]+$");
        Matcher m1 = p1.matcher(newPwd);
        boolean matchFound1 = m1.matches();
        if(!matchFound1){
            ctx.addMessage("changePwdForm:newPwd", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorPassword"), null));
            status = false;
        }
        if(!newPwdConfirm.equals(newPwd)){
            ctx.addMessage("changePwdForm:newPwdConfirm", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorPasswordConfirm"), null));
            status = false;
        }
        return status;
    }

	public String editProfile() throws Exception{
		
   		/*Injector injector = Guice.createInjector(new BillingModule()); 
		IUsuaris usuaris_app = injector.getInstance(IUsuaris.class);*/
		
		com.insacosa.domain.Usuaris usuari = usuarisService.editPerfil(this.getGuid());
		
		this.setGuid(usuari.getUsuariKey());
		this.setNom(usuari.getNom());
		this.setCognoms(usuari.getCognoms());
		this.setPassword(usuari.getPassword());
		this.setConfirmPassword(usuari.getPassword());
		this.setEmail(usuari.getEmail());
		this.setAdreca(usuari.getAdreca());
		
		return "editSuccess";

	}

	public String  saveUser() throws Exception{
		
		if (validateData()){
								
			// si no esta loginat es un nou registre
			if(!loginat){
								
		   		/*Injector injector = Guice.createInjector(new BillingModule()); 
				IUsuaris usuaris_app = injector.getInstance(IUsuaris.class);*/
				
				com.insacosa.domain.Usuaris usuari = new com.insacosa.domain.Usuaris();
				
				usuari.setUsuariKey((String)this.getGuid());
				usuari.setNomusuari(this.getNomUsuari());
				usuari.setAdreca(this.getAdreca());
				//registreHib.setCountry(this.getContactNumber());
				usuari.setEmail(this.getEmail());
				usuari.setCognoms(this.getCognoms());
				usuari.setNom(this.getNom());
				usuari.setCognoms(this.getCognoms());
				usuari.setPassword(this.getPassword());
				
				
				usuarisService.afegirUsuari(usuari);
				
				/////////////sendMail(getEmail());
				return "successUserRegistration";
			}
			else{
				
		   		/*Injector injector = Guice.createInjector(new BillingModule()); 
				IUsuaris usuaris_app = injector.getInstance(IUsuaris.class);
				*/
				com.insacosa.domain.Usuaris usuari = new com.insacosa.domain.Usuaris();
				
				usuari.setUsuariKey((String)this.getGuid());
				usuari.setNomusuari(this.getNomUsuari());
				usuari.setAdreca(this.getAdreca());
				usuari.setEmail(this.getEmail());
				usuari.setCognoms(this.getCognoms());
				usuari.setNom(this.getNom());				
				usuari.setCognoms(this.getCognoms());
				usuari.setPassword(this.password);
												
				usuarisService.modificarUsuari(usuari);

				return "successUserUpdate";
			}
		}
		else{
			return "error";
		}
	}


	public boolean validEmail(){
		String email_check = email.trim();
		
   		/*Injector injector = Guice.createInjector(new BillingModule()); 
		IUsuaris usuaris_app = injector.getInstance(IUsuaris.class);*/
		
		return usuarisService.emailValid(email_check);
	}

	private boolean validateData() throws Exception {
		boolean status = true;
		MessageFactory mf = new MessageFactory();
		FacesContext ctx = FacesContext.getCurrentInstance();

		String firstname = nom.trim();
		if(!(((nom.length())>=1) && ((nom.length())<=25))){
			ctx.addMessage("UserForm:nom", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorFirstNameLength"), null));
			status = false;
		}

		Pattern p3 = Pattern.compile("^[a-zA-Z]+$");
		Matcher m3 = p3.matcher(nom);
		boolean matchFound3 = m3.matches();

		if (!matchFound3) {
			ctx.addMessage("UserForm:nom",new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorNom"), null));
			status = false;
		}

		if(!(((cognoms.length())>=1) && ((cognoms.length())<=25))){
			ctx.addMessage("UserForm:cognoms", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorCognomsLength"), null));
			status = false;
		}

		Pattern p4 = Pattern.compile("^[a-zA-Z]+$");
		Matcher m4 = p4.matcher(cognoms);
		boolean matchFound4 = m4.matches();

		if (!matchFound4) {
			ctx.addMessage("UserForm:cognoms",new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorCognoms"), null));
			status = false;
		}
		
			

		if((password.length())<6 || (password.length())>20){
			ctx.addMessage("UserForm:password", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorPasswordLength"), null));
			status = false;
		}

		Pattern p1 = Pattern.compile("^[a-zA-Z0-9]+$");
		Matcher m1 = p1.matcher(password);
		boolean matchFound1 = m1.matches();

		if (!matchFound1) {
			ctx.addMessage("UserForm:password",new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorPassword"), null));
			status = false;
		}

		if((confirmPassword.length())<6 || (confirmPassword.length())>20){
			ctx.addMessage("UserForm:confirmPassword", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorConfirmPasswordLength"), null));
			status = false;
		}

		Pattern p2 = Pattern.compile("^[a-zA-Z0-9]+$");
		Matcher m2 = p2.matcher(confirmPassword);
		boolean matchFound2 = m2.matches();

		if (!matchFound2) {
			ctx.addMessage("UserForm:confirmPassword",new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorConfirmPasswordValid"), null));
			status = false;
		}

		if (!confirmPassword.equals(password)) {
			ctx.addMessage("UserForm:confirmPassword", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorConfirmPassword"), null));
			status = false;
		}
		

		//Checking Email adreca
		Pattern p6 = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m6 = p6.matcher(email);
		boolean matchFound6 = m6.matches();
		 
		if (!matchFound6) {
			ctx.addMessage("UserForm:email",new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorEmail"), null));
			status = false;
		}
		
		
		// End of checking Email adreca

		String adrs = adreca.trim();
		if((adrs.length())==0){
			ctx.addMessage("UserForm:adreca", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorAddressBlank"), null));
			status = false;
		}

		String countryName = select.trim();
		if((countryName.length())==0){
			ctx.addMessage("UserForm:country", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorCountryBlank"), null));
			status = false;
		}

		String cn = contactNumber.trim();
		if((cn.length())==0){
			ctx.addMessage("UserForm:contactNumber", new FacesMessage(FacesMessage.SEVERITY_ERROR, mf.getMessage("errorContactNumberBlank"), null));
			status = false;
		}

		

		return status;
	}


	public void setMessage(String fromAddress,String toAddress,String subject,String content) throws Exception {
		String[] adreca = {toAddress};
		setMessage(fromAddress,adreca,subject,content);
	}


	public void sendMail(String email) throws Exception {
		String from = "admin@roseindia.net ";
		String to[] = new String[]{email};
		String subject = "Successful Registeration";
		String content = "Thanks for registering with us. \n";
		content += "Your login details are: \n";
		content += "Username : ";
		content += getNom() + getCognoms();
		content += "\n";
		content += "Password: ";
		content += getPassword();

		setMessage(from,to,subject,content);
		//setSMTPServer("smtp.mail.yahoo.es",25,"santasusanap@yahoo.es","tivoli00");
		setSMTPServer("smtp.gmail.com",587,"santasusanap@gmail.com","tivoli00");

		send();
	}

	public void setMessage(String fromAddress,String[] toAddresses,String subject,String content) throws Exception {

		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		//session = Session.getInstance(System.getProperties());		
		session = Session.getInstance(props);
		
		message = new MimeMessage(session);
		
		message.setFrom(new InternetAddress(fromAddress));
		InternetAddress[] toIntAdds = new InternetAddress[toAddresses.length];

		for (int i=0;i<toAddresses.length;i++)
		toIntAdds[i] = new InternetAddress(toAddresses[i]);

		message.setRecipients(Message.RecipientType.TO,toIntAdds);
		message.setSubject(subject);
		message.setSentDate(new java.util.Date());
		message.setText(content);
	}

	public void setSMTPServer(String host,int port,String user,String password) throws Exception{
		//transport = new SMTPTransport(session,new URLName(host));
		transport = session.getTransport("smtp");

		transport.connect(host,port,user,password);
	}

	public void send() throws Exception{
		message.saveChanges();
		transport.sendMessage(message, message.getAllRecipients());
		System.out.println("Message is sent.");
		transport.close();
	}

}