package com.insacosa.domain.repositories;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ddd.domain.BaseEntity;


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
	
	@Override    
	public E save(final E entity) {        
		em.persist(entity);        
	return entity;    }     
	
	@Override    
	public E update(final E entity) {        
		return em.merge(entity);    
		}     
	
	@Override    
	public void remove(final E entity) {        
			em.remove(em.merge(entity));    
			}    
	
	@Override    
	public E findById(final K id) {        
		return em.find(entityClass, id);    
				}   
		
	@Override    
	public List<E> findAll() {        
		return em.createNamedQuery(BaseEntity.FIND_ALL).getResultList();    
					}     
	
	@Override    
	public Long getTotalResult() {        
		return (Long) em.createNamedQuery(BaseEntity.TOTAL_RESULT).getSingleResult();    
	} 
	
	}