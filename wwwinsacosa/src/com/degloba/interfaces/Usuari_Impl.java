package com.degloba.interfaces;

import java.util.List;

//import org.hibernate.HibernateException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.Order;


import com.degloba.HBM.*;


public class Usuari_Impl implements Usuari_If {


	public Usuari_Impl() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void afegirUsuari(Usuaris usuari) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;  
		
		SessionFactory sf = SessionFactoryUtil.getInstance();
		Session session = sf.getCurrentSession();
		
		try {      
				tx = session.beginTransaction();      
				session.save(usuari);      
				tx.commit();    
				} 
		catch (RuntimeException e) 
			{     
			
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
						catch (HibernateException e1) 
						{       
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
	}


	public void eliminarUsuari(String usuari) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				session.delete(usuari);      
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well    
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
	}


	public Usuaris cercarUsuari(String nomUsuari) {
		// TODO Auto-generated method stub
		
		Usuaris usuari = null;
		Transaction tx = null;  
		
		SessionFactory sf = SessionFactoryUtil.getInstance();
		Session session = sf.getCurrentSession();
		
		try {      
				tx = session.beginTransaction();      
				usuari = (Usuaris) session.get(Usuaris.class, nomUsuari);      
				tx.commit();    
				} 
		catch (RuntimeException e) 
			{     
			
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
						catch (HibernateException e1) 
						{       
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		
		return usuari;
	}


	public Usuaris editPerfil(String nomUsuari) {
		// TODO Auto-generated method stub
		
		Usuaris usuari = null;
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				usuari = (Usuaris) session.get(Usuaris.class , nomUsuari);  
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}
		return usuari;  
		
	}


	public boolean usuariValid(Usuaris usuari) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;    
		Boolean existeix = false;
		
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				
				if ((Usuaris) session.get(Usuaris.class, usuari.getNomusuari()) != null)
					existeix = true;
				else
					existeix = false;
				
				tx.commit();    
			} 
		catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}
		
		return existeix;
		
		
	}


	public boolean emailValid(String email) {
		// TODO Auto-generated method stub
		
		Long num = null;
		Boolean existeix = false;
		
		List<Objecte> ret = null;
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
				
			num = ((Long) session.createQuery("select count(*) from Usuaris as usuari where usuari.email = '" + email + "'").iterate().next() ).longValue();
				
			if (num == 0)
				existeix = false;
			else
				existeix = true;
				
			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
					
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return existeix;

	}

	

	public String passwordEmail(String email) {
		// TODO Auto-generated method stub
		
		String password = null;
		Boolean existeix = false;
		
		List<Objecte> ret = null;
		
		Transaction tx = null;    

		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
			tx = session.beginTransaction();
				
			password = ((String) session.createQuery("select password from Usuaris as usuari where usuari.email = '" + email + "'").iterate().next() ).toString();
			
				
			tx.commit();    
			} 
		catch (RuntimeException e) {      
			if (tx != null && tx.isActive()) 
			{        
				try {
					
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
				catch (HibernateException e1) 
					{          
						System.out.println("Error rolling back transaction");
						//logger.debug("Error rolling back transaction");        
					}
					// throw again the first exception        
					throw e;      
					}    
			}  
		
		return password;

	}


	

	public String cambiaPassword(Usuaris usuari) {
		// TODO Auto-generated method stub
		
		
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				
				
				session.update(usuari);      
				tx.commit();    
			} 
			catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
					// throw again the first exception        
					throw e;      
				}    
			}  
		
			return null;
	}


	

	public Usuaris password(String nomUsuari)
	{
		Usuaris usuari = null;
		
		Transaction tx = null;    
		Session session = SessionFactoryUtil.getInstance().getCurrentSession();    
		try {      
				tx = session.beginTransaction();      
				usuari = (Usuaris) session.load(Usuaris.class, nomUsuari);      
				tx.commit();    
			} 
			catch (RuntimeException e) 
			{      
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
					catch (HibernateException e1) 
						{          
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
					// throw again the first exception        
					throw e;      
				}    
			}  
		
		
		return usuari;
	}


	@Override
	public String cambiaPassword() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String modificarUsuari(Usuaris usuari) {
		// TODO Auto-generated method stub´
		
		Transaction tx = null;  
		
		SessionFactory sf = SessionFactoryUtil.getInstance();
		Session session = sf.getCurrentSession();
		
		try {      
				tx = session.beginTransaction();      
				session.update(usuari);      
				tx.commit();    
				} 
		catch (RuntimeException e) 
			{     
			
				if (tx != null && tx.isActive()) 
				{        
					try {
						// Second try catch as the rollback could fail as well          
						tx.rollback();        
						} 
						catch (HibernateException e1) 
						{       
							System.out.println("Error rolling back transaction");
							//logger.debug("Error rolling back transaction");        
						}
						// throw again the first exception        
						throw e;      
				}    
			}  
		return null;
	}

	

}
