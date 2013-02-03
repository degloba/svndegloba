package utils;

/** 
 * *  
 * * @author Pere Santasusana
 * * created Juliol 22, 2011 
 * * copyright 2011 by http://www.summa-eng.com */


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/** 
 * *	This class garanties that only one single SessionFactory 
 * *    is instanciated and that the configuration is done thread safe as 
 * *    singleton. Actually it only wraps the Hibernate SessionFactory. 
 * * You are free to use any kind of JTA or Thread transactionFactories. */
 
 public class SessionFactoryUtil {  
	 
 /** The single instance of hibernate SessionFactory */  
 private static org.hibernate.SessionFactory sessionFactory;	
 
 /**	 
  * * disable contructor to guaranty a single instance	 
  * */	
 
 private SessionFactoryUtil() {	}	
 
 static{// Annotation and XML 
	 try { 
		 sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		 //sessionFactory = new Configuration().configure().buildSessionFactory();
	 } catch (Throwable ex) { 
		 System.err.println("Initial SessionFactory creation failed." + ex); 
		 throw new ExceptionInInitializerError(ex); 
	 } 

 
 // XML only//    
//	 Configuration config = new Configuration().configure();
// sessionFactory = config.configure().buildSessionFactory(); //buildSessionFactory();  
 }	
 
 public static SessionFactory getInstance() {		
	 return sessionFactory;	
 }  
 
 
 /**   
  * * Opens a session and will not bind it to a session context   
  * * @return the session   
  * */	
 public Session openSession() {		
  return sessionFactory.openSession();	
 }
 
 
  /**   
   * * Returns a session from the session context. If there is no session in the context it opens a session,   
   * * stores it in the context and returns it.	 
   * * This factory is intended to be used with a hibernate.cfg.xml	 
   * * including the following property &lt;property	 
   * * name="current_session_context_class"&gt;thread&lt;/property&gt; This would return	 
   * * the current open session or if this does not exist, will create a new	 
   * * session	 
   * * 	 * @return the session	 
   * */	
 public Session getCurrentSession() {		
   return sessionFactory.getCurrentSession();	
   }  
   
   /**   
    * * closes the session factory   
    * */	
   public static void close(){		
	   if (sessionFactory != null)			
		   sessionFactory.close();		
   	   	   sessionFactory = null;
   	}
   
 }

