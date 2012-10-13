package com.insacosa.interfaces;

import java.util.List;

import javax.persistence.EntityManager;

import com.degloba.EMF;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.insacosa.entitats.*;


public class Usuari_Impl implements Usuari_If {


	public Usuari_Impl() {
		super();
		// TODO Auto-generated constructor stub
	}


	public void afegirUsuari(Usuaris usuari) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;  
		EntityManager em = EMF.get().createEntityManager();
		
		try {      
				em.persist(usuari);
				      
				tx.commit();    
		} finally {        
			em.close();    
		} 
		
	}


	public void eliminarUsuari(Key keyUsuari) {
		
		Transaction tx = null;    
		EntityManager em = EMF.get().createEntityManager();
		
		try {
				Usuaris usuari = em.find(Usuaris.class, keyUsuari);
				em.remove(usuari);
     
				tx.commit();    
		} finally {        
			em.close();    
		} 
		
	}


	public Usuaris cercarUsuari(Key keyUsuari) {
		
		Usuaris usuari = null;
		Transaction tx = null;  
		EntityManager em = EMF.get().createEntityManager();
		
		try {      
				em.find(Usuaris.class, keyUsuari);
     
				tx.commit();    
		} finally {        
			em.close();    
		} 
		
		return usuari;
	}


	public Usuaris editPerfil(Key keyUsuari) {
		
		Usuaris usuari = null;
		Transaction tx = null;    
		EntityManager em = EMF.get().createEntityManager();
		
		try {   
				usuari = em.find( Usuaris.class, keyUsuari);
				 
				tx.commit();  
				
		} finally {        
			em.close();    
		}
		return usuari;  
		
	}


	public boolean usuariValid(Usuaris usuari) {
		// TODO Auto-generated method stub
		
		Transaction tx = null;    
		Boolean existeix = false;
		EntityManager em = EMF.get().createEntityManager();
		try {      
	    
				
				if ((Usuaris) session.get(Usuaris.class, usuari.getNomusuari()) != null)
					existeix = true;
				else
					existeix = false;
				
				tx.commit();    
		} finally {        
			em.close();    
		}
		
		return existeix;
		
		
	}


	public boolean emailValid(String email) {
		// TODO Auto-generated method stub
		
		Long num = null;
		Boolean existeix = false;
		
		List<Objecte> ret = null;
		
		Transaction tx = null;    
		EntityManager em = EMF.get().createEntityManager();
		try {      
	
				
			num = ((Long) session.createQuery("select count(*) from Usuaris as usuari where usuari.email = '" + email + "'").iterate().next() ).longValue();
				
			if (num == 0)
				existeix = false;
			else
				existeix = true;
				
			tx.commit();    
		} finally {        
			em.close();    
		}
		
		return existeix;

	}

	

	public String passwordEmail(String email) {
		// TODO Auto-generated method stub
		
		String password = null;
		Boolean existeix = false;
		
		List<Objecte> ret = null;
		
		Transaction tx = null;    
		EntityManager em = EMF.get().createEntityManager();
		try {      

				
			password = ((String) session.createQuery("select password from Usuaris as usuari where usuari.email = '" + email + "'").iterate().next() ).toString();
			
				
			tx.commit();    
		} finally {        
			em.close();    
		} 
		
		return password;

	}


	

	public String cambiaPassword(Usuaris usuari) {
		
		Transaction tx = null;    
		EntityManager em = EMF.get().createEntityManager();
		try {      
				em.persist(usuari);
				      
				tx.commit();    
		} finally {        
			em.close();    
		} 
		
		return null;
	}
	

	public Usuaris password(String nomUsuari)
	{
		Usuaris usuari = null;
		
		Transaction tx = null;    
		EntityManager em = EMF.get().createEntityManager();
		try {      
    
				usuari = (Usuaris) session.load(Usuaris.class, nomUsuari);      
				tx.commit();    
		} finally {        
			em.close();    
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
		
		EntityManager em = EMF.get().createEntityManager();
		try {      
				em.persist(usuari);
				      
				tx.commit();    
		} finally {        
			em.close();    
		}
		return null;
	}

	

}
