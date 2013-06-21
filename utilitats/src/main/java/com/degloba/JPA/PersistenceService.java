package com.degloba.JPA;

import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;


/**
 * @author Nick Belaevski
 * 
 */
@ManagedBean(eager = true)
@ApplicationScoped
public class PersistenceService {

    private static final Logger LOGGER = Logger.getLogger(PersistenceService.class.getName());
    
    private EntityManagerFactory entityManagerFactory;
    
    
    /*
     * Retorna un EntityManager a partir del FacesContext(attribute)
     * NOTA : com el FacesContext es diferent per cada request JSF
     * l'EntityManager també serà diferents per cada request JSF
     */
    public EntityManager getEntityManager() {
        Map<Object, Object> attributes = FacesContext.getCurrentInstance().getAttributes();
        
        EntityManager manager = (EntityManager) attributes.get(PersistenceService.class);
        
        if (manager == null) {
            manager = entityManagerFactory.createEntityManager();
            attributes.put(PersistenceService.class, manager);
           // manager.getTransaction().begin();          

        }
        
        return manager;
    }

    void closeEntityManager() {
        Map<Object, Object> attributes = FacesContext.getCurrentInstance().getAttributes();
        
        EntityManager entityManager = (EntityManager) attributes.remove(PersistenceService.class);

        if (entityManager != null) {
            try {
                entityManager.getTransaction().commit();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, e.getMessage(), e);
                try {
                    entityManager.getTransaction().rollback();
                } catch (Exception e1) {
                    LOGGER.log(Level.SEVERE, e1.getMessage(), e1);
                }
            } finally {
                entityManager.close();
            }
        }
    }
    
    @PostConstruct
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("transactions-optional", new Properties());

    }

    @PreDestroy
    public void destroy() {
        entityManagerFactory.close();
        entityManagerFactory = null;
    }

}
