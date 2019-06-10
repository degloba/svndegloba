package com.degloba.domain;


import org.junit.Before;
import org.junit.Test;

import com.degloba.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.persistence.rdbms.jpa.BaseEntity;
import com.degloba.ecommerce.vendes.client.domain.persistence.rdbms.jpa.Client;
import com.degloba.ioc.spring.InstanceFactory;
import com.degloba.lloguer.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;
import com.degloba.lloguer.domain.persistence.rdbms.jpa.CategoryJpa;
import com.degloba.lloguer.domain.persistence.rdbms.jpa.CategoryJpa2;
import com.degloba.lloguer.domain.persistence.rdbms.jpa.ILloguerRepository;
import com.degloba.lloguer.domain.persistence.rdbms.jpa.SubcategoryJpa;
import com.degloba.lloguer.domain.persistence.rdbms.jpa.SubcategoryJpa2;

import static org.junit.Assert.*;

import java.util.List;

public class CategoriesTest extends AbstractIntegrationTest {

	
	private SubcategoryJpa2 subcategory2;
	
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        
        Client c = new Client();
               
        
        
        CategoryJpa2 category = new CategoryJpa2();
//        category.setAggregateId(AggregateId.generate()); //
        
	    category.setDescription("Oci");
	    
        // Categories/Subcategories
	    subcategory2 = new SubcategoryJpa2();
//	    subcategory2.setAggregateId(AggregateId.generate());
	    
	    subcategory2.setDescription("Natacion");
	    subcategory2.setCategory(category);
	    
	    category.getSubcategories().add(subcategory2);
	    	    
	    
	    // Categories/Subcategories
/*	    subcategory = new SubcategoryJpa();
	    subcategory.setDescription("Tenis");
//	    subcategory.setAggregateId(AggregateId.generate());
	    
//	    subcategory.setCategory(category);
	    category.getSubcategories().add(subcategory);
*/	    	    
//	    category.save();
	    
	    // ********************************************
	    
	    
      /*  CategoryJpa2 category2 = new CategoryJpa2();
      category2.setAggregateId(AggregateId.generate()); //
      
	    category2.setDescription("Oci");
	    
      // Categories/Subcategories
	    subcategory2 = new SubcategoryJpa2();
	    subcategory2.setAggregateId(AggregateId.generate());
	    
	    subcategory2.setDescription("Natacion");
	    subcategory2.setCategory(category2);
	    
	    category2.getSubcategories().add(subcategory2);
	    	    
	    	    
	    category2.save();*/
	    
	    
    }
    
    @Test
    public void testSubcategories() {
        String jpql = "select o from CategoryJpa2 o";
        IEntityRepository rentRepository = InstanceFactory.getInstance(IEntityRepository.class);
        List<CategoryJpa2> categories = rentRepository.createJpqlQuery(jpql).list();
        
        String jpql2 = "select o from SubcategoryJpa2 o";
        List<SubcategoryJpa2> subcategories = rentRepository.createJpqlQuery(jpql2).list();
                
//        assertEquals(subcategories.get(0).getDescription(),subcategory.getDescription());
        
        assertEquals(1,categories.size());
    }
   

}
