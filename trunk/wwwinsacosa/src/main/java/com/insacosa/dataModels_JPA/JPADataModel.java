package com.insacosa.dataModels_JPA;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


import javax.faces.context.FacesContext;
import javax.inject.Inject;


// JPA
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

// UI - RICHFACES
import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.ExtendedDataModel;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.richfaces.component.SortOrder;
import org.richfaces.model.Arrangeable;
import org.richfaces.model.ArrangeableState;
import org.richfaces.model.FilterField;
import org.richfaces.model.SortField;



import com.insacosa.domain.*;
import com.insacosa.presentation.CiutatsFinder;
import com.insacosa.presentation.InmoblesFinder;
import com.insacosa.presentation.SolicitudsFinder;
import com.insacosa.presentation.TipusFinder;
import com.insacosa.presentation.UsuarisFinder;
import com.insacosa.vo.InmobleForm;


import com.google.appengine.api.datastore.Key;
import com.google.common.collect.Lists;


public abstract class JPADataModel<T> extends ExtendedDataModel<T> implements Arrangeable {
	
		// FinderS (lectura)
		//---------------------
		 
	    @Inject
	    private SolicitudsFinder solicitudsFinder;
	    @Inject
	    private TipusFinder tipusFinder;
	    @Inject
	    private InmoblesFinder inmoblesFinder;
	    @Inject
	    private CiutatsFinder ciutatsFinder;
	    @Inject
	    private UsuarisFinder usuarisFinder;

    private EntityManager entityManager;
    
    private Object rowKey;
    
    private ArrangeableState arrangeableState;
    
    private Class<T> entityClass;
    
    List<InmobleCaract> data; // en el cas de que les caracteristiques d'un inmoble s'obtenen de la BD i no del bean
    
    
    /*----------------------------------------------------------------------*/
    /* per resoldre el problema de que no s'executi varies vegades la "query"
     * pel fet de que el metode walk() es cridat varies vegades per "request"
    /*-----------------------------------------------------------------------*/
    //private List<T> cachedItems; SI LES CARACTERISTIQUES ESTAN DEFINIDES EN EL BEAN FORM
    private List<InmobleCaract> cachedItems;
    private SequenceRange cachedRange;
    private static Integer rowCount = 0;
    private final String dataModelID = UUID.randomUUID().toString();
    
    
    
    
    public JPADataModel(EntityManager entityManager, Class<T> entityClass) {
        super();
        
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    public void arrange(FacesContext context, ArrangeableState state) {
        arrangeableState = state;
    }

    @Override
    public void setRowKey(Object key) {
        rowKey = key;
    }

    @Override
    public Object getRowKey() {
        return rowKey;
    }

    private CriteriaQuery<Long> createCountCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<T> root = criteriaQuery.from(entityClass);

        Expression<Boolean> filterCriteria = createFilterCriteria(criteriaBuilder, root);
        if (filterCriteria != null) {
            criteriaQuery.where(filterCriteria);
        }

        Expression<Long> count = criteriaBuilder.count(root);
        criteriaQuery.select(count);
        
        return criteriaQuery;
    }
    
    private CriteriaQuery<T> createSelectCriteriaQuery() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        
        // si el DataModel es de tipus ArrangeableModel 
        if (arrangeableState != null) {

            List<Order> orders = createOrders(criteriaBuilder, root);
            if (!orders.isEmpty()) {
                criteriaQuery.orderBy(orders);
            }
            
            Expression<Boolean> filterCriteria = createFilterCriteria(criteriaBuilder, root);
            if (filterCriteria != null) {
                criteriaQuery.where(filterCriteria);
            }
        }
        
        return criteriaQuery;
    }

    private List<Order> createOrders(CriteriaBuilder criteriaBuilder, Root<T> root) {
        List<Order> orders = Lists.newArrayList();
        List<SortField> sortFields = arrangeableState.getSortFields();
        
        // IMPORTANT !
        // perque sortFields no sigui buit cal que el SortBy d'algun dels sortFields
        // tingui un ValueExpression (ex : sortBy=#{item.metres}
        // El problema està en que sortBy ha de ser el nom d'una columna de la datatable,
        // per tant el ValueExpression del sortBy ha de ser tal que el seu valor calculat
        // sigui el nom de la columna
        if (sortFields != null && !sortFields.isEmpty()) {
            
            FacesContext facesContext = FacesContext.getCurrentInstance();
            
            for (SortField sortField: sortFields) {
       
            	
            	// propertyName = nom de la columna de la datatable
            	// cal que sortField.getSortBy() que es un ValueExpression sigui efectivament una "Expressio"
            	// NO PAS un literal (per exemple sortBy="metres"
            	
            	// AIXO ES UNA XAPUSSA !!!! però resolt el problema
            	// CAL OBLIGATORIAMENT QUE : sortBy=#{<nom_columna>}
        /* funciona */    	
         		String propertyName = null;
            	if (sortField.getSortBy().getValue(facesContext.getELContext()) == null)
            	{
	            	int endIndex = sortField.getSortBy().getExpressionString().indexOf('}');
	                propertyName = (String) sortField.getSortBy().getExpressionString().substring(2,endIndex);
            	}
            	else
            		propertyName = (String) sortField.getSortBy().getValue(facesContext.getELContext());
                
                
                ///////////////String propertyName = (String) sortField.getSortBy().getValue(facesContext.getELContext());
                
                Path<Object> expression = root.get(propertyName);
                
                Order jpaOrder;
                SortOrder sortOrder = sortField.getSortOrder();
                if (sortOrder == SortOrder.ascending) {
                    jpaOrder = criteriaBuilder.asc(expression);
                } else if (sortOrder == SortOrder.descending) {
                    jpaOrder = criteriaBuilder.desc(expression);
                } else {
                    throw new IllegalArgumentException(sortOrder.toString());
                }
            
                orders.add(jpaOrder);
            }
        }
        
        return orders;
    }

    protected ArrangeableState getArrangeableState() {
        return arrangeableState;
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }
    
    
    /*---------------------------------------------------------
     * AQUI ES ON REALMENT CREEM ELS FILTRES. CAL DISTINGUIR 
     * SI EL FILTRE S'APLICA SOBRE UNA
     * STRING O UN NUMBER I QUINA OPERACIO S'HA DE FER 
     ---------------------------------------------------------*/
    protected Expression<Boolean> createFilterCriteriaForField(String propertyName, Object filterValue, Root<T> root, CriteriaBuilder criteriaBuilder) {
    	
    	String stringFilterValue = null;
    	Integer integerFilterValue = null;
    	
    	if (filterValue !=null)
    	{
   		 
    		String tipusColumna = inmoblesFinder.tipusColumnaCaract(propertyName);  
    		
    		if (tipusColumna != null && tipusColumna.compareToIgnoreCase("INT") == 0)
    		{
    			if (((String)filterValue).compareTo("") != 0)
    			{
	    			integerFilterValue = Integer.parseInt((String)filterValue);
	    			
	    			// CONSTRUIM LA INSTRUCCIO JPA
	    			// EN EL CAS DE QUE LA COLUMNA DE LA DATATABLE SIGUI UN INTEGER PODEM APLICAR
	    	        // DIFERENTS OPERADORS (< , >=, == )
	        	    Path<Integer> expression = root.get(propertyName); ////// exemple : propertyName="metres", "habitacions",....
	    			Predicate predicate = criteriaBuilder.ge(expression,  integerFilterValue); 
	    			//System.out.println("createFilterCriteriaForField : " + propertyName + " Valor : " + (String) filterValue);
	    			return predicate;
    			}
    		
    		}
    		else if (tipusColumna != null && tipusColumna.compareToIgnoreCase("VCHR") == 0)
    		{
    			stringFilterValue = (String) filterValue;
	    		stringFilterValue = stringFilterValue.toLowerCase(arrangeableState.getLocale());
	    		
	    		// CONSTRUIM LA INSTRUCCIO JPA
	    		// EN EL CAS DE QUE SIGUI UN STRING EL FILTRE ES QUE LA CADENA ESCRITA SIGUI UN SUBSTRING
	    		Path<String> expression = root.get(propertyName);////// name, metres,....
		        Expression<Integer> locator = criteriaBuilder.locate(criteriaBuilder.lower(expression), stringFilterValue, 1);
		        return criteriaBuilder.gt(locator, 0);
    		}
    		
    		
    		/* AQUEST OPCIO FUNCIONA SI UTILITZEM lLA VARIABLE :  Map<String, Object> filterValues
    		 * DE INMOBLEFORM
    		
    		if (filterValue instanceof Integer)
	    		{         
	    			System.out.println((Integer)filterValue);    
	    			integerFilterValue = (Integer) filterValue;
	    		}     
	    	else if (filterValue instanceof String)
	    		{         
		    		System.out.println((String)filterValue);
		    		stringFilterValue = (String) filterValue;
		    		stringFilterValue = stringFilterValue.toLowerCase(arrangeableState.getLocale());
	    		}
	    	*/ 
    	}
    	else
    		return null;
    	

     
     // si el valor del filtre no es de cap tipus (Integer, String,...) que controlem
     return null;
     
    }
    
    
    private Expression<Boolean> createFilterCriteria(CriteriaBuilder criteriaBuilder, Root<T> root) {
        Expression<Boolean> filterCriteria = null;
        List<FilterField> filterFields = arrangeableState.getFilterFields();
        if (filterFields != null && !filterFields.isEmpty()) {
            FacesContext facesContext = FacesContext.getCurrentInstance();

            for (FilterField filterField : filterFields) {
            	
            	// propertyName = nom de la columna de la datatable
            	// cal que filterField.getFilterExpression() que es un ValueExpression sigui efectivament una "Expressio"
            	// NO PAS un literal (per exemple filterExpression="metres"
            	
            	// AIXO ES UNA XAPUSSA !!!! per resolt el problema
            	// CAL OBLIGATORIAMENT QUE : filterExpression=#{<nom_columna>}
            	
            /* funciona */
             	String propertyName = null;	
              	//if  (filterField.getFilterExpression().getValue(facesContext.getELContext()) == null)
            	//{
	            	int endIndex = filterField.getFilterExpression().getExpressionString().indexOf('}');
	                propertyName = (String) filterField.getFilterExpression().getExpressionString().substring(2,endIndex);
            	//}
            	//else
            		//propertyName = (String) filterField.getFilterExpression().getValue(facesContext.getELContext());
                
            	
            	///////String propertyName = (String) filterField.getFilterExpression().getValue(facesContext.getELContext());
            	
                Object filterValue = filterField.getFilterValue();
                
                Expression<Boolean> predicate = createFilterCriteriaForField(propertyName, filterValue, root, criteriaBuilder);
                
                if (predicate == null) {
                    continue;
                }
                
                if (filterCriteria == null) {
                    filterCriteria = predicate.as(Boolean.class);
                } else {
                    filterCriteria = criteriaBuilder.and(filterCriteria, predicate.as(Boolean.class));
                }
            }
            
        }
        return filterCriteria;
    }
    
    
    
    /*------------------------------------------------------------------------
     * Retorna la SQL que permet obtenir els inmobles que cumpleixen
     * unes determinades condicions (condicions equivalents al filterValues)
     * Mètode utilitzat nomes si les caracteristiques NO son properties de Bean
     * (tramita) 
     ------------------------------------------------------------------------*/
    private String cercaTabular(InmobleForm inmobleForm)
    {
    	
    	
		Set<String> idCaracts = inmobleForm.getFilterValues().keySet(); //ids caracteristiques
		 
		String sql ="";
		Boolean primeraSelect = true;
		 
		// per cada una de les caracteristiques construim la select anidada
		Iterator<String> idCaractsIt = idCaracts.iterator();
		while (idCaractsIt.hasNext())
		    {
				String idCaract = (String) idCaractsIt.next();
				
				// comprovem si el valor del filtre (sigui numeric o cadena) es ""
				if (String.valueOf(inmobleForm.getFilterValues().get(idCaract)).compareTo("") != 0 &&
						 String.valueOf(inmobleForm.getFilterValues().get(idCaract)).compareTo("null") != 0)
				{
		    	
					sql += (primeraSelect ? "select inmobles from Inmobles as inmobles " +
			    				"where inmobles.id IN (" : " and inmobles.id IN (");
			    	
			    	sql +=	"select  DISTINCT valuescaracteristiques.id.idinmoble " +
			    			"from  Caracteristiques as caracteristiques , " +
			    			"ValuesCaracteristiques as valuescaracteristiques, " +
			    			"Inmobles as inmobles " + 
			    			"where " +
			    			"caracteristiques.id = valuescaracteristiques.caracteristiques.id " +
			    			"and caracteristiques.id = " + idCaract;  
			    			
			    			// comprovem el tipus de columna per aplicar el filtre corresponent
			    			String tipusColumna = inmoblesFinder.tipusColumnaCaract(idCaract);  
			    			
			        		if (tipusColumna != null && tipusColumna.compareToIgnoreCase("INT") == 0)
			        		{
			        			String operacio = (inmobleForm.getColumnesOperacions().get(idCaract).compareTo("ge") == 0 ? ">" : "<");
				    			
			    				sql +=	" and valuescaracteristiques.value " + operacio + inmobleForm.getFilterValues().get(idCaract);
			        		}
			        		else if (tipusColumna != null && tipusColumna.compareToIgnoreCase("VCHR") == 0)
			        		{
			        			sql +=	" and valuescaracteristiques.value = '" + inmobleForm.getFilterValues().get(idCaract) + "'";
			        		}
			        			
			        		
			    	sql += ")";
			    	
			    	primeraSelect = false;
				}
		    }
    	
		 return sql;
		 
    }
    
    /*------------------------------------------------------------------------------
     * Construeix el List dels inmobles que cumpleixen les condicions (filterValues)
     * Mètode utilitzat nomes si les caracteristiques NO son properties de Bean
     * (tramita) 
     -------------------------------------------------------------------------------*/
    private List<InmobleCaract> datatableToList(String sql, Range range)
    {
    	
    	List<InmobleCaract> listDataRow = new ArrayList<InmobleCaract>();
			 
    	if (sql.compareTo("") != 0)
    	{	
    		Query query = entityManager.createQuery(sql);
         
    		// Recuperem NOMES els inmobles segons la pagina seleccionada
    		SequenceRange sequenceRange = (SequenceRange) range;
            query.setFirstResult(sequenceRange.getFirstRow());
            query.setMaxResults(sequenceRange.getRows());
 			 
            List<Inmobles> inmoblesCondicions =  query.getResultList();
			
			Iterator<Inmobles> inmoblesCondicionsIt = inmoblesCondicions.iterator();
			while (inmoblesCondicionsIt.hasNext())
			    {
					Inmobles inmoble = inmoblesCondicionsIt.next();
					InmobleCaract valorsCaract = inmoblesFinder.valorsCaracteristiquesInmoble(inmoble.getInmobleKey());
					
					listDataRow.add(valorsCaract);
			    }
	    	}
    	
    	///////////////rowCount = listDataRow.size();
    	
		return listDataRow;
    	 
    }
    
    
    
    
    
    /*
     * Per cachejar les dades ja que el metode walk s'executa varies vegades durant una request
     */
    private static boolean areEqualRanges(SequenceRange range1, SequenceRange range2) {

    	if (range1 == null || range2 == null) {
            return range1 == null && range2 == null;
        } else {
            return range1.getFirstRow() == range2.getFirstRow() && range1.getRows() == range2.getRows();
        }
    }

    
    
    
    
    
    
    
    @Override
    public void walk(FacesContext context, DataVisitor visitor, Range range, Object argument) {
    	
    	/*
    	 * 2 OPCIONS :
    	 * - 1.-CARACTERISTIQUES COM A PROPIETATS BEAN --> LES CARACTERISTIQUES ES CORRESPONEN AMB LES COLUMNES DE LA TAULA INMOBLES (HBM.Inmobles)
    	 * - 2.-CARACTERISTIQUES COM A VALORS DE LA TAULA CARACTERISTIQUES --> ACCEDIR A LES CARACTERISTIQUES COM EL TRAMITA
    	 */
    	
    	
    	SequenceRange sequenceRange = (SequenceRange) range;
    	
        if (this.cachedItems == null || !areEqualRanges(this.cachedRange, sequenceRange)) {

            this.cachedRange = sequenceRange;

     	
	        /* OPCIO 2 */
	    	/*---------*/
            FacesContext facesContext = FacesContext.getCurrentInstance(); // Contexte JSF
    		InmobleForm inmobleForm = (InmobleForm) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{inmobleForm}", InmobleForm.class);
    		
	    	String sql = cercaTabular(inmobleForm);
	    	this.cachedItems = datatableToList(sql, range);
	    	
	    	
	    	
	    	/* OPCIO 1 */
	    	/*---------*/
	    	 
	    	/*   CriteriaQuery<T> criteriaQuery = createSelectCriteriaQuery();
	        
	        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
	        
	        SequenceRange sequenceRange = (SequenceRange) range;
	        if (sequenceRange.getFirstRow() >= 0 && sequenceRange.getRows() > 0) {
	            query.setFirstResult(sequenceRange.getFirstRow());
	            query.setMaxResults(sequenceRange.getRows());
	        }
	        
	        List<T> data = query.getResultList();  // llista entitats persistents
	        
	        */
    	
    	
        }
         
        // data es una llista d'objectes que pot venir de una taula de BD
        // o un fitxer XML ,...
        //for (T t : data) {     	
        for (InmobleCaract t : this.cachedItems) {
        	// Aquest mètode retornarà la trucada (com a visitant) de 
        	// ExtendedDataModel.walk 
        	// (FacesContext, DataVisitor, Range) per a cada fila.
        	
            visitor.process(context, getId(t), argument);
        }
        
    }
    

    @Override
    public boolean isRowAvailable() {
        return rowKey != null;
    }

    
    
    @Override
    public int getRowCount() {
    	
    	
    	Map<String, Object> mapRow = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();

        if (rowCount == null || mapRow.get(dataModelID + "_asked") == null) { // perque no entri varies vegades

    	
	    	/* OPCIO 2 */
	    	/*---------*/
	    	
        	String sql = "";  //cercaTabular();   // calculem la SQL a executar a partir dels filtres
 			    	
			 mapRow.put(dataModelID + "_asked", "");
			 
			 if (sql.compareTo("") !=0)
				 try {
			 
				  rowCount = entityManager.createQuery(sql).getResultList().size();
				 }
				 catch (Exception ex)
				 {
					 rowCount = 0;
				 }
				 
			 
			 /* OPCIO 1 - SI UTILITZEM CARACTERISITQUES COM A PROPIETATS BEAN*/
		    	/*--------------------------------------------------------------*/
		        /*CriteriaQuery<Long> criteriaQuery = createCountCriteriaQuery();
		        return entityManager.createQuery(criteriaQuery).getSingleResult().intValue();
		        */
			 
			 

        }

        return rowCount.intValue();
		     	
    }

    
    /*
     * (non-Javadoc)
     * @see javax.faces.model.DataModel#getRowData()
     */
    @Override
    //public T getRowData() {
    public T getRowData() {
    		
    	/*
    	 * 2 OPCIONS :
    	 * - 1.-CARACTERISTIQUES COM A PROPIETATS BEAN --> LES CARACTERISTIQUES ES CORRESPONEN AMB LES COLUMNES DE LA TAULA INMOBLES (HBM.Inmobles)
    	 * - 2.-CARACTERISTIQUES COM A VALORS DE LA TAULA CARACTERISTIQUES --> ACCEDIR A LES CARACTERISTIQUES COM EL TRAMITA
    	 */
    	
    	/* OPCIO 1 */
    	/*---------*/
        //return entityManager.find(entityClass, rowKey);
        
        
        /* OPCIO 2 */
    	/*---------*/
    	// HEM DE TROBAR DINS LA LLISTA L'INMOBLE TAL QUE IDCOLUMNA = ROWKEY
    	Iterator it = this.cachedItems.iterator();
    	while(it.hasNext())
    	{
    		InmobleCaract inmoble = (InmobleCaract) it.next();
    		
    		if (inmoble.getKeyInmoble() == (Key) rowKey)
    			return (T) inmoble;
    	}
    	
        return null;
    }

    
    @Override
    public int getRowIndex() {
        return -1;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getWrappedData() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setWrappedData(Object data) {
        throw new UnsupportedOperationException();
    }
    
    
    //TODO - implement using metadata
    // Identifica de forma unica l'Objecte (inmoble).
    // Utilitzem el mètode getId() de l'objecte inmoble
    // T = CLASSE ENTITAT HBM
    ////////////protected abstract Object getId(T t);
    protected abstract Object getId(InmobleCaract t);

}
