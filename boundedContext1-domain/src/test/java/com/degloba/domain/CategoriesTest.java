package com.degloba.domain;


import org.junit.Before;
import org.junit.Test;

import com.degloba.domain.ioc.InstanceFactory;
import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.rent.domain.factories.CategoryFactory;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;
import com.degloba.rent.domain.persistence.rdbms.jpa.CategoryJpa;
import com.degloba.rent.domain.persistence.rdbms.jpa.CategoryJpa2;
import com.degloba.rent.domain.persistence.rdbms.jpa.IRentRepository;
import com.degloba.rent.domain.persistence.rdbms.jpa.SubcategoryJpa;
import com.degloba.rent.domain.persistence.rdbms.jpa.SubcategoryJpa2;

import static org.junit.Assert.*;

import java.util.List;

public class CategoriesTest extends AbstractIntegrationTest {


	private SubcategoryJpa subcategory;
	private SubcategoryJpa2 subcategory2;
	
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        
        CategoryJpa category = new CategoryJpa();
//        category.setAggregateId(AggregateId.generate()); //
        
	    category.setDescription("Oci");
	    
        // Categories/Subcategories
	    subcategory = new SubcategoryJpa();
	    subcategory.setAggregateId(AggregateId.generate());
	    
	    subcategory.setDescription("Natacion");
	    subcategory.setCategory(category);
	    
	    category.getSubcategories().add(subcategory);
	    	    
	    
	    // Categories/Subcategories
/*	    subcategory = new SubcategoryJpa();
	    subcategory.setDescription("Tenis");
//	    subcategory.setAggregateId(AggregateId.generate());
	    
//	    subcategory.setCategory(category);
	    category.getSubcategories().add(subcategory);
*/	    	    
	    category.save();
	    
	    // ********************************************
	    
	    
        CategoryJpa2 category2 = new CategoryJpa2();
//      category2.setAggregateId(AggregateId.generate()); //
      
	    category2.setDescription("Oci");
	    
      // Categories/Subcategories
	    subcategory2 = new SubcategoryJpa2();
	    subcategory2.setAggregateId(AggregateId.generate());
	    
	    subcategory2.setDescription("Natacion");
	    subcategory2.setCategory(category2);
	    
	    category2.getSubcategories().add(subcategory2);
	    	    
	    	    
	    category2.save();
	    
	    
    }
    
    @Test
    public void testSubcategories() {
        String jpql = "select o from CategoryJpa o";
        IEntityRepository rentRepository = InstanceFactory.getInstance(IEntityRepository.class);
        List<CategoryJpa> categories = rentRepository.createJpqlQuery(jpql).list();
        
        String jpql2 = "select o from SubcategoryJpa o";
        List<SubcategoryJpa> subcategories = rentRepository.createJpqlQuery(jpql2).list();
                
//        assertEquals(subcategories.get(0).getDescription(),subcategory.getDescription());
        
        assertEquals(2,categories.size());
    }
   

}
