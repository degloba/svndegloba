package com.insacosa.dataModels_JPA;


import java.io.InputStream;

import java.util.List;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;



import com.insacosa.entitats.Person;

import com.google.common.collect.Lists;
import com.google.common.io.Closeables;

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
            manager.getTransaction().begin();
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

    private List<Person> parseTestData() throws Exception {
        InputStream dataStream = null;
        try {
            dataStream = PersistenceService.class.getResourceAsStream("data.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Node node = documentBuilder.parse(dataStream).getDocumentElement();
            
            List<Person> persons = Lists.newArrayList();

            for (Node personNode = node.getFirstChild(); personNode != null; personNode = personNode.getNextSibling()) {
                if (personNode.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                
                Person person = new Person();
                persons.add(person);
         
                for (Node personDataNode = personNode.getFirstChild(); personDataNode != null; personDataNode = personDataNode.getNextSibling()) {
                    if (personDataNode.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
    }

                    String nodeName = personDataNode.getNodeName();
                    String text = personDataNode.getTextContent();
                    if ("name".equals(nodeName)) {
                        person.setName(text);
                    } else if ("surname".equals(nodeName)) {
                        person.setSurname(text);
                    } else if ("email".equals(nodeName)) {
                        person.setEmail(text);
                    }
                }
            }
    
            return persons;
        } finally {
            Closeables.closeQuietly(dataStream);
        }
    }

    @PreDestroy
    public void destroy() {
        entityManagerFactory.close();
        entityManagerFactory = null;
    }

}
