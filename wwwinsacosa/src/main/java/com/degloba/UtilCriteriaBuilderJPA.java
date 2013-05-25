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


	
	  
	/**
	 * Predicate = Exemple : nom = 'pere' 
	 * @param propertyName
	 * @param filterValue
	 * @param root
	 * @param criteriaBuilder
	 * @return
	 */
	protected Predicate createFilterCriteriaForField(String propertyName, Object filterValue, Root<T> root, CriteriaBuilder criteriaBuilder) {
	    	
    	Integer integerFilterValue = null;
    	
    	if (filterValue !=null)
    	{
    		
    		if (filterValue.getClass().toString().compareToIgnoreCase("class java.lang.Integer") == 0)
    		{
    			if (((String)filterValue).compareTo("") != 0)
    			{
	    			integerFilterValue = Integer.parseInt((String)filterValue);
	    			
	    			// CONSTRUIM LA INSTRUCCIO JPA
	    			// EN EL CAS DE QUE LA COLUMNA DE LA DATATABLE SIGUI UN INTEGER PODEM APLICAR
	    	        // DIFERENTS OPERADORS (< , >=, == )
	        	    Path<Integer> path = root.get(propertyName); 
	    			Predicate predicate = criteriaBuilder.ge(path,  integerFilterValue); 
	    			
	    			return predicate;
    			}
    		
    		}
    		else if (filterValue.getClass().toString().compareToIgnoreCase("class java.lang.String") == 0)
    		{
    	    			    		
	    		// CONSTRUIM LA INSTRUCCIO JPA
	    		// EN EL CAS DE QUE SIGUI UN STRING EL FILTRE ES QUE LA CADENA ESCRITA SIGUI UN SUBSTRING
	    		Path<String> path = root.get(propertyName);
		        Predicate predicate = criteriaBuilder.equal(path, filterValue);
		        
		        return predicate;
    		}
    		
    		
    	}
    	else
    		return null;
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
	            
	        
	        Predicate filterCriteria = createFilterCriteria(criteriaBuilder, root, campsFiltre, valorsFiltre);
	        if (filterCriteria != null) {
	                //criteriaQuery.where(criteriaBuilder.equal(root.get("nom"), "peresan"));
	        	criteriaQuery.where(filterCriteria);
	            }

	        return criteriaQuery;
	    }
	    
	    
	    
	    private void selectSpecificColumnsByTuple(List<String> columnesSelec)
	    {
	    	EntityManager em = this.getEntityManager();
	    	
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
