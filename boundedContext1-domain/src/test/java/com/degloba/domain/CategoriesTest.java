package com.degloba.domain;


import org.junit.Before;
import org.junit.Test;

import com.degloba.domain.IEntityRepository;
import com.degloba.rent.domain.jpa.Category;
import com.degloba.rent.domain.jpa.Subcategory;

import static org.junit.Assert.*;

import java.util.List;

public class CategoriesTest extends AbstractIntegrationTest {


	private Subcategory subcategory;
	
    @Before
    public void setUp() throws Exception {
        super.setUp();
        
        Category category = new Category();
	    category.setDescription("Oci");
	    
        // Categories/Subcategories
	    subcategory = new Subcategory();
	    subcategory.setDescription("Natacion");
	    
	    ///////subcategory.setCategory(category);
	    category.getSubcategories().add(subcategory);
	    
	    category.save();
	    
    }
    
    @Test
    public void testSubcategories() {
        String jpql = "select o from Category o";
        IEntityRepository repository = InstanceFactory.getInstance(IEntityRepository.class);
        List<Category> categories = repository.createJpqlQuery(jpql).list();
        
        String jpql2 = "select o from Subcategory o";
        List<Subcategory> subcategories = repository.createJpqlQuery(jpql2).list();
                
 //       assertFalse(persons.contains(person1));
        assertEquals(subcategories.get(0).getDescription(),subcategory.getDescription());
        
        assertEquals(1,categories.size());
    }
   

}
