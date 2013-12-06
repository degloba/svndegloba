package com.insacosa.domain.repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ddd.domain.BaseEntity;
import ddd.domain.RepositoryService;


/**
 * @author degloba
 * Implementacio JPA del servei de persistencia genèric (agnostic a la persistència)
 * @param <K> = Tipus d'identificador
 * @param <E> = Tipus d'entitat que gestiona
 */
public abstract class AbstractJPARepositoryService<K, E extends BaseEntity> implements RepositoryService<K, E> {
    
	protected Class<E> entityClass;  
	
	@PersistenceContext    
	protected EntityManager em;  
	
	@PostConstruct    
	public void init() {        
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();        
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];    } 
	
	   
	public E save(final E entity) {        
		em.persist(entity);        
	return entity;    }     
	
	   
	public E update(final E entity) {        
		return em.merge(entity);    
		}     
	
	   
	public void remove(final E entity) {        
			em.remove(em.merge(entity));    
			}    
	
	   
	public E findById(final K id) {        
		return em.find(entityClass, id);    
				}   
  
	public List<E> findAll() {        
		return em.createNamedQuery(BaseEntity.FIND_ALL).getResultList();    
					}     
	
   
	public Long getTotalResult() {        
		return (Long) em.createNamedQuery(BaseEntity.TOTAL_RESULT).getSingleResult();    
	} 
	
	}