package com.degloba.domain;


import org.junit.Before;
import org.junit.Test;

import com.degloba.domain.ioc.InstanceFactory;
import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.domain.persistence.rdbms.jpa.canonicalmodel.publishedlanguage.AggregateId;
import com.degloba.rent.domain.persistence.nosql.googleDatastore.api.objectify.Subcategory;
import com.degloba.rent.domain.persistence.rdbms.jpa.CategoryJpa;
import com.degloba.rent.domain.persistence.rdbms.jpa.IRentRepository;
import com.degloba.rent.domain.persistence.rdbms.jpa.SubcategoryJpa;

import static org.junit.Assert.*;

import java.util.List;

public class CategoriesTest extends AbstractIntegrationTest {


	private SubcategoryJpa subcategory;
	
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        
        CategoryJpa category = new CategoryJpa();
        category.setAggregateId(AggregateId.generate());
        
	    category.setDescription("Oci");
	    
        // Categories/Subcategories
	    subcategory = new SubcategoryJpa();
	    subcategory.setDescription("Natacion");
	    subcategory.setAggregateId(AggregateId.generate());
	    
	    subcategory.setCategory(category);
	    category.getSubcategories().add(subcategory);
	    
	    category.save();
	    
    }
    
    @Test
    public void testSubcategories() {
        String jpql = "select o from CategoryJpa o";
        IEntityRepository rentRepository = InstanceFactory.getInstance(IEntityRepository.class);
        List<CategoryJpa> categories = rentRepository.createJpqlQuery(jpql).list();
        
        String jpql2 = "select o from SubcategoryJpa o";
        List<SubcategoryJpa> subcategories = rentRepository.createJpqlQuery(jpql2).list();
                
 //       assertFalse(persons.contains(person1));
        assertEquals(subcategories.get(0).getDescription(),subcategory.getDescription());
        
        assertEquals(1,categories.size());
    }
   

}
