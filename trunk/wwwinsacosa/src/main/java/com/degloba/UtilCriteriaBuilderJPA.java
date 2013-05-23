package com.degloba;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.google.common.collect.Lists;
import com.insacosa.dataModels_JPA.PersistenceService;
import com.insacosa.interfaces.Inmoble_Impl;


public class UtilCriteriaBuilderJPA<T> {
	
	static PersistenceService persistenceService;
	
	private EntityManager entityManager;
	 
	private Class<T> entityClass;
	

    public UtilCriteriaBuilderJPA(EntityManager entityManager, Class<T> entityClass) {
        super();
        
        this.setEntityManager(entityManager);
        this.entityClass = entityClass;
    }

	
	
	
	  public UtilCriteriaBuilderJPA() {
		super();
		// TODO Auto-generated constructor stub
		
			FacesContext facesContext = FacesContext.getCurrentInstance(); 
		
			//La classe PersistenceService es "ApplicationScoped"
			persistenceService = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{persistenceService}", PersistenceService.class);
		
	}


	protected Predicate createFilterCriteriaForField(String propertyName, Object filterValue, Root<T> root, CriteriaBuilder criteriaBuilder) {
	    	
	    	
		String stringFilterValue = null;
    	Integer integerFilterValue = null;
    	
    	if (filterValue !=null)
    	{
    		
    		if (filterValue.getClass().toString().compareToIgnoreCase("Integer") == 0)
    		{
    			if (((String)filterValue).compareTo("") != 0)
    			{
	    			integerFilterValue = Integer.parseInt((String)filterValue);
	    			
	    			// CONSTRUIM LA INSTRUCCIO JPA
	    			// EN EL CAS DE QUE LA COLUMNA DE LA DATATABLE SIGUI UN INTEGER PODEM APLICAR
	    	        // DIFERENTS OPERADORS (< , >=, == )
	        	    Path<Integer> expression = root.get(propertyName); ////// exemple : propertyName="metres", "habitacions",....
	    			Predicate predicate = criteriaBuilder.ge(expression,  integerFilterValue); 
	    			
	    			return predicate;
    			}
    		
    		}
    		else if (filterValue.getClass().toString().compareToIgnoreCase("class java.lang.String") == 0)
    		{
    			stringFilterValue = (String) filterValue;
	    		
	    		
	    		// CONSTRUIM LA INSTRUCCIO JPA
	    		// EN EL CAS DE QUE SIGUI UN STRING EL FILTRE ES QUE LA CADENA ESCRITA SIGUI UN SUBSTRING
	    		Path<String> expression = root.get(propertyName);////// name, metres,....
		        Expression<Integer> locator = criteriaBuilder.locate(criteriaBuilder.lower(expression), stringFilterValue, 1);
		        return criteriaBuilder.gt(locator, 0);
    		}
    		
    		
    	}
    	else
    		return null;
    	

     
     // si el valor del filtre no es de cap tipus (Integer, String,...) que controlem
     return null;
	     
	    }
	    
	
	protected Expression<Boolean> createFilterCriteriaForField(String propertyName, String filterValue, Root<T> root, CriteriaBuilder criteriaBuilder) {
    	
    	String stringFilterValue = null;
    	
    	if (filterValue !=null)
    	{
    		
    			stringFilterValue = (String) filterValue;
	    		
	    		// CONSTRUIM LA INSTRUCCIO JPA
	    		// EN EL CAS DE QUE SIGUI UN STRING EL FILTRE ES QUE LA CADENA ESCRITA SIGUI UN SUBSTRING
	    		Path<String> expression = root.get(propertyName);////// name, metres,....
		        Expression<Integer> locator = criteriaBuilder.locate(criteriaBuilder.lower(expression), stringFilterValue, 1);
		        return criteriaBuilder.gt(locator, 0);
   
    	}
    	else
    		return null;
    	
     
    }
    
	
	  
	  private List<Order> createOrders(CriteriaBuilder criteriaBuilder, Root<T> root, List<String> sortFields, String sortOrder) {

	        List<Order> orders = Lists.newArrayList();
	        
	        if (sortFields != null && !sortFields.isEmpty()) {
	          
	            for (String sortField: sortFields) {
	         	
	         		String propertyName = null;
	            	
	            	propertyName = (String) sortField;
	                	                	                 
	                Path<Object> expression = root.get(propertyName);
	                
	                Order jpaOrder;
	                
	                if (sortOrder == "ASC") {
	                    jpaOrder = criteriaBuilder.asc(expression);
	                } else if (sortOrder == "DESC") {
	                    jpaOrder = criteriaBuilder.desc(expression);
	                } else {
	                    throw new IllegalArgumentException(sortOrder.toString());
	                }
	            
	                orders.add(jpaOrder);
	            }
	        }
	        
	        return orders;
	    }

	  
	  
	
	    protected CriteriaQuery<T> createSelectCriteriaQuery(List<String> campsFiltre,List<Object> valorsFiltre, List<String> campsOrdre, String ordre) {
	    	
	    	EntityManager em = this.getEntityManager();
	    	
	        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
	        Root<T> root = criteriaQuery.from(entityClass);
	        
	        List<Order> orders = createOrders(criteriaBuilder, root, campsOrdre, ordre);
	        if (!orders.isEmpty()) {
	                criteriaQuery.orderBy(orders);
	            }
	            
	        
	  //      criteriaQuery.select(root);
	        
	        Predicate filterCriteria = createFilterCriteria(criteriaBuilder, root, campsFiltre, valorsFiltre);
	        if (filterCriteria != null) {
	                //criteriaQuery.where(criteriaBuilder.equal(root.get("nom"), "peresan"));
	        	criteriaQuery.where(filterCriteria);
	            }

	        return criteriaQuery;
	    }
	    
	    
	    
	    private void selectSpecificColumnsByTuple(List<String> columnesSelec)
	    {
	    	EntityManager em = persistenceService.getEntityManager();
	    	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	    	
	    	CriteriaQuery<Tuple> cq = criteriaBuilder.createTupleQuery();
	    	// write the Root, Path elements as usual
	    	Root<T> root = cq.from(entityClass);
	    	
	    	List<Selection<?>> lSelec = new ArrayList<Selection<?>>();
	    	
	    	for (String columnaSelec: columnesSelec) {
	    		lSelec.add(root.get(columnaSelec));
	    	}
	    	
	    	cq.multiselect(lSelec);  //using metamodel
	    	List<Tuple> tupleResult = em.createQuery(cq).getResultList();
	    	for (Tuple t : tupleResult) {
	    			    		
	    	    String nom = (String) t.get(0);
	    	    String cognoms = (String) t.get(1);
	    	    	    	    
	    	}
	    }
	    
	    
	    
	        
	
	  private Predicate createFilterCriteria(CriteriaBuilder criteriaBuilder, Root<T> root, List<String> propertyNames, List<Object> filterValue) {
		  
	        Predicate filterCriteria = null;
	        
	        for (String propertyName : propertyNames) {

	        	Predicate predicate = createFilterCriteriaForField(propertyName, filterValue.get(0), root, criteriaBuilder);
              
		       if (filterCriteria == null) {
		                    filterCriteria = predicate;
		                } else { 
		                    filterCriteria = criteriaBuilder.and(filterCriteria, predicate.as(Boolean.class));
		                }
	        }
	        
	        return filterCriteria;
	    }




	public EntityManager getEntityManager() {
		return entityManager;
	}




	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	
	
	
}
