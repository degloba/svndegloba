

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class email{
	
	
	
  public email() {
		super();
		// TODO Auto-generated constructor stub
	     
	}

public static boolean enviarMensaje(String destinatario,String asunto,String cuerpo){
    try{
      Properties props = new Properties();
//      props.put("mail.smtp.host", "SERVIDOR_MAIL_SMTP");
      props.put("mail.smtp.host", "mail.degloba.com");

      System.out.println("arriba!!!!)");
      
      Session sesion = Session.getInstance(props);
      
      // Tanto el usuario como la clave son de la cuenta de correo que env?a el mensaje.
      sesion.setPasswordAuthentication(new URLName("degloba.com"), new PasswordAuthentication("degloba","Gl0baD3"));
      //sesion.setPasswordAuthentication(new URLName("URL_DOMINIO_DE_ORIGEN"), new PasswordAuthentication("USUARIO_MAIL","CLAVE_MAIL"));
      
      Store buzon=sesion.getStore("pop3");
      buzon.connect("mail.degloba.com", "degloba", "Gl0baD3");
      //buzon.connect("SERVIDOR_MAIL_POP", "USUARIO_MAIL", "CLAVE_MAIL");      
      buzon.close();

      System.out.println(destinatario + " " + asunto + " " + cuerpo);
      MimeMessage mensaje = new MimeMessage(sesion);
      mensaje.setFrom(new InternetAddress("degloba@degloba.com"));
      //mensaje.setFrom(new InternetAddress("DIRECCION_DEL_REMITENTE"));
      mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
      mensaje.setSubject(asunto);
      mensaje.setText(cuerpo);

      try{
        Transport mta = sesion.getTransport("smtp");
        mta.connect();
        try{
            Transport.send(mensaje);
        }catch(SendFailedException ex){return false;}
        mta.close();
      }catch(Exception ex){
        System.out.println("Cartero: Error al enviar "+ex.toString());
      }
    }catch(Exception ex){return false;}

    return true;
  }
}