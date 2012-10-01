package com.degloba.interfaces;

import java.util.List;

import javax.persistence.criteria.Expression;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class QuerysJPA<T> {
		
    private EntityManager entityManager;
    
    private Class<T> entityClass;
    
    private CriteriaQuery<Object> criteriaQuery;
    
    private CriteriaBuilder criteriaBuilder;
    
    private Root<T> from;
    
	
    public QuerysJPA(EntityManager entityManager, Class<T> entityClass) {
        super();
        
        this.entityManager = entityManager;
        this.entityClass = entityClass;
        
		this.criteriaBuilder = entityManager.getCriteriaBuilder(); 
		this.criteriaQuery = criteriaBuilder.createQuery(); 
		this.from = criteriaQuery.from(entityClass); 
    }
    
    
	public void querySimple()
	{
	
		CriteriaQuery<Object> select = criteriaQuery.select(from); 
		TypedQuery<Object> typedQuery = entityManager.createQuery(select); 
		List<Object> resultList = typedQuery.getResultList(); 
		
		System.out.println("querySimple : " + resultList.size());
		
	}
	
	/*
	 * select from SimpleBean s order by s.pbyte asc ,s.pint desc") 
	 */
	public void queryWithOrder(EntityManager entityManager, Class<T> entityClass)
	{
	
		CriteriaQuery<Object> select = criteriaQuery.select(from);   
		
		select.orderBy(criteriaBuilder.asc(from.get("pbyte"))                         ,
				criteriaBuilder.desc(from.get("pint"))); 
		
		TypedQuery<Object> typedQuery = entityManager.createQuery(select);   
		List<Object> resultList = typedQuery.getResultList();
		
		System.out.println("queryWithOrder : " + resultList.size());
		
	}

	/*
	 * select s.id,s.pbyte from SimpleBean s ");
	 */
	public void queryWithFieldSelected(EntityManager entityManager, Class<T> entityClass)
	{
		CriteriaQuery<Object> select = criteriaQuery.multiselect(from.get("id"),from.get("pbyte"));
		
		TypedQuery<Object> typedQuery = entityManager.createQuery(select);
		List<Object> resultList = typedQuery.getResultList();
		
		System.out.println("queryWithFieldSelected : " + resultList.size());
	}
	
	
	/*
	 * select from SimpleBean s where s.pint>=:arg1");
	 */
	public void queryWithSingleCriteria(EntityManager entityManager, Class<T> entityClass, String expr, Integer valor)
	{
		
		//The method ge(Expression<? extends Number>, Expression<? extends Number>) 
		//in the type CriteriaBuilder is not applicable for the arguments (Path<Object>, String)
		
		Path<Integer> expression = from.get(expr); ////// exemple : expr="metres", "habitacions",....
		
		Predicate predicate = criteriaBuilder.ge(expression, valor); 
		
		
		
		// NOTA : Predicate extends Expression<Boolean>
		
		CriteriaQuery<Object> select = criteriaQuery.where(predicate); 
		TypedQuery<Object> typedQuery = entityManager.createQuery(select);
		List<Object> resultList = typedQuery.getResultList();
		
		System.out.println("queryWithSingleCriteria : " + resultList.size());
		
		 
	}
	
	
	/*
	 * from SimpleBean s where s.pint>=:arg1 and s.pint<=:arg2"); 
	 * 
	 * Per tal de fer-ho n-vegades, 1 per cada argument caldria fer un for del seguent :
	 *  filterCriteria = criteriaBuilder.and(filterCriteria, predicate.as(Boolean.class));
	 */
	public void queryWithMultipleCriteria(EntityManager entityManager, Class<T> entityClass)
	{
		Path<Integer> expression1 = from.get("metres");//////  metres,....
		Path<Integer> expression2 = from.get("habitacions");//////  habitacions,....
		
		Predicate predicate1 = criteriaBuilder.ge(expression1, 1); 
		Predicate predicate2 = criteriaBuilder.le(expression2, 2); 
		
		CriteriaQuery<Object> select = criteriaQuery.where(criteriaBuilder.and(predicate1, predicate2)); //.. 
		TypedQuery<Object> typedQuery = entityManager.createQuery(select);
		List<Object> resultList = typedQuery.getResultList();
	}

	
	/*
	 * select from SimpleBean s where upper(s.pstring) like upper(:arg1)
	 */
	public void querySingleLiteral(EntityManager entityManager, Class<T> entityClass)
	{
		Path<String> expression = from.get("nom");////// name, metres,....
		
		Expression<String> literal = criteriaBuilder.upper(criteriaBuilder.literal((String) "1")); 
		
		Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(expression), literal);  
		
		CriteriaQuery<Object> select = criteriaQuery.where(predicate);  
		TypedQuery<Object> typedQuery = entityManager.createQuery(select); 
		List<Object> resultList = typedQuery.getResultList(); 
	}
	
	/*
	 * select min(s.pint) from SimpleBean s"); 
	 */
	public void queryWithSummary(EntityManager entityManager, Class<T> entityClass)
	{
		Path<Integer> expression = from.get("metres");////// habitacions, metres,....
		
		Expression minExpression = criteriaBuilder.min(expression); 
		
		CriteriaQuery<Object> select = criteriaQuery.select(minExpression);  
		TypedQuery<Object> typedQuery = entityManager.createQuery(select); 
		Object minExpected = typedQuery.getSingleResult(); 
		
	}
	
	
	/*
	 * select min(s.pint),s.pbyte from SimpleBean s group by s.pbyte"); 
	 */
	public void queryWithAggregation(EntityManager entityManager, Class<T> entityClass)
	{
		Path<Integer> expression = from.get("metres");////// habitacions, metres,....
		
		Expression minExpression = criteriaBuilder.min(expression); 
		Path pbytePath = from.get("pbyte"); 
		
		CriteriaQuery<Object> select = criteriaQuery.multiselect(minExpression, pbytePath);  
		CriteriaQuery<Object> groupBy = select.groupBy(pbytePath);  
		
		TypedQuery<Object> typedQuery = entityManager.createQuery(select); 
		List listActual = typedQuery.getResultList();
	}

	
	public void queryJoin(EntityManager entityManager, Class<T> entityClass)
	{
		/*long category=200L; 
		Query query = entityManager.createQuery("select s from OrderItem s " +         
			"where s.product.category=:cat"); 
		query.setParameter("cat", category); 
			
		List<T> list = query.getResultList();  
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder(); */
		
		
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(); 
		
		Root<T> from = criteriaQuery.from(entityClass); 
		Path<Object> path = from.join("product").get("category"); 
		
		CriteriaQuery<Object> select = criteriaQuery.select(from); 
		
		/* RESOLDRE !!!!!!!!! select.where(criteriaBuilder.equal(path, category));  
		TypedQuery<Object> typedQuery = entityManager.createQuery(select); 
		List<Object> resultList = typedQuery.getResultList(); */
		 
	}
			
			
	/*		
	*simple fetch join query
	*/
	public void queryFetchJoin(EntityManager entityManager, Class<T> entityClass)
	{
		/*long category=200L; 
		Query query = entityManager.createQuery("select s from OrderItem s " +         
				"join fetch s.product where s.product.category=:cat");
		
		query.setParameter("cat", category); 
		List<OrderItem> list = query.getResultList();  */
		
		Root<T> from = criteriaQuery.from(entityClass); 
		
		Path<Object> path = from.join("product").get("category");  
		from.fetch("product"); //FETCH product  
		
		CriteriaQuery<Object> select = criteriaQuery.select(from); 
		
		/* RESOLDRE !!!!!!!!! select.where(criteriaBuilder.equal(path, category));  
		TypedQuery<Object> typedQuery = entityManager.createQuery(select); 
		List<Object> resultList = typedQuery.getResultList();*/
	}

	
	
	/*		
	*subselect (subquery) join query
	*/
	public void querySubQueryJoin(EntityManager entityManager, Class<T> entityClass)
	{
	 
		/*Query query = entityManager.createQuery(         
				"select s from OrderItem s join fetch s.product" +         
				" where s.product.category in" +         
				" (select sb.pbyte from SimpleBean sb where sb.pint>=30000)");  
		List<OrderItem> list = query.getResultList();*/
	  
		 
		 
		/*Root<T> from = criteriaQuery.from(entityClass); 
		Path<Object> path = from.join("product").get("category"); 
		from.fetch("product"); 
		
		CriteriaQuery<Object> select = criteriaQuery.select(from);  
		Subquery<SimpleBean> subquery = criteriaQuery.subquery(SimpleBean.class); 
		Root fromSimpleBean = subquery.from(SimpleBean.class); 
		subquery.select(fromSimpleBean.get("pbyte")); 
		subquery.where(criteriaBuilder.ge(fromSimpleBean.get("pint"),30000)); 
		select.where(criteriaBuilder.in(path).value(subquery));  
		TypedQuery<Object> typedQuery = entityManager.createQuery(select); 
		List<Object> resultList = typedQuery.getResultList();*/ 
	
	}
	
	
}
