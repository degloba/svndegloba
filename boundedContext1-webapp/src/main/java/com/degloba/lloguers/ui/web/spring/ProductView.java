package com.degloba.lloguers.ui.web.spring;


import java.io.Serializable;


import javax.annotation.PostConstruct;

// JSF
import javax.faces.application.FacesMessage;


// 
import javax.inject.Inject;


// Spring
import org.springframework.stereotype.Component;

// Spring-Webflow
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.mvc.servlet.MvcExternalContext;

import com.degloba.domain.event.IDomainEventBus;
import com.degloba.ecommerce.vendes.application.events.CashPurchaseEvent;
import com.degloba.ecommerce.vendes.application.events.guava.eventbus.subscriber.CashPurchaseEventSubscriber;
import com.degloba.ioc.spring.InstanceFactory;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Producte;
import com.degloba.lloguers.domain.persistence.nosql.googleDatastore.api.objectify.Propietari;
import com.degloba.persistence.nosql.googleDatastore.api.objectify.DatabaseException;
import com.degloba.persistence.nosql.googleDatastore.api.objectify.IBaseRepository;
import com.degloba.persistence.rdbms.jpa.IEntityRepository;
import com.google.common.eventbus.EventBus;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

// Validation
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;


/**
 * @author degloba
 *
 */
@Component
//@ManagedBean
//@SessionScoped
public class ProductView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long productId; 
	
	//@Size(min=2,max=5)  NO FUNCIONA.Mirar http://stackoverflow.com/questions/19216495/app-engine-jar-in-web-inf-lib-but-still-getting-java-lang-classnotfoundexceptio
	String name;
	
	String description;
	
		
	@Digits(integer=3,fraction=2)
	private Double price;
	
    
    @Inject
    protected IBaseRepository ownerRepositoryObjectify;
    
 	
    @PostConstruct
    public void init() {
				   
    }
	
	 public void onAddProduct(RequestContext context) throws DatabaseException {
		 
		 // 1.- Envia l'event al "EventBus" (Google Guava) 
		 // 2.- Guarda l'event "publish" en un repositori d'events
		 EventBus eventbus = new EventBus();
		 eventbus.register(new CashPurchaseEventSubscriber());
		 
//		 DomainEventBusImpl d = new DomainEventBusImpl(eventbus,new StoredDomainEventRepository());
		 
		 
		 IDomainEventBus<?> d =  InstanceFactory.getInstance(IDomainEventBus.class);
		 
		 d.publishEvent(new CashPurchaseEvent(1232,"chocolate"));
		 
		 
		 MvcExternalContext externalContext =
				  (MvcExternalContext)context.getExternalContext();
		 HttpServletRequest request = (HttpServletRequest) externalContext.getNativeRequest();
								
			    Cookie[] cookies = request.getCookies();
	
			    for (Cookie cookie : cookies) {
			      if ("uid".equals(cookie.getName())) {
			    	  String uid = cookie.getValue();
			    	  
			    	 Propietari o = ownerRepositoryObjectify.getById(Propietari.class, uid);
			    	  
			    	 // Comprovem si tenim l'owner al datastore
			    	 // si no, el creem
			    	 String ownerId = "";
			    	 if (o == null) {
			    		 Propietari propietari = new Propietari();
				    	  
				    	  propietari.setId(uid);
				    	  ownerId = ownerRepositoryObjectify.createWithKey(propietari);
			    	 }				    	  
			    	 else {
				    	  
				    //ownerId = ownerRepositoryObjectify.geto.getId();  
			    	 }
			    	 
			    	 // Producte
			    	 Producte producte = new Producte();
			    	 producte.setDescription(this.description);
			    	 producte.setPrice(price);
			    	 			    	
			    	 producte.setOwner(com.googlecode.objectify.Key.create(Propietari.class, uid));
			    	 
			    	 // creem el producte i guardem el seu Id 
			    	 productId = this.ownerRepositoryObjectify.createWithID(producte);
			    	  
			      }
			    }
	    }
	 
	 	
	 
	 // getters - setters
	 
	 	public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void displayLocation() {
		        FacesMessage msg;
		       
		    }

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		

}
