package com.degloba.domain;


import org.junit.Before;
import org.junit.Test;

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
	    category.setDescription("Oci");
	    
        // Categories/Subcategories
	    subcategory = new SubcategoryJpa();
	    subcategory.setDescription("Natacion");
	    
	    ///////subcategory.setCategory(category);
	    category.getSubcategories().add(subcategory);
	    
	    category.save();
	    
    }
    
    @Test
    public void testSubcategories() {
        String jpql = "select o from Category o";
        IRentRepository repository = InstanceFactory.getInstance(IRentRepository.class);
        List<CategoryJpa> categories = repository.createJpqlQuery(jpql).list();
        
        String jpql2 = "select o from Subcategory o";
        List<Subcategory> subcategories = repository.createJpqlQuery(jpql2).list();
                
 //       assertFalse(persons.contains(person1));
        assertEquals(subcategories.get(0).getDescription(),subcategory.getDescription());
        
        assertEquals(1,categories.size());
    }
   

}
