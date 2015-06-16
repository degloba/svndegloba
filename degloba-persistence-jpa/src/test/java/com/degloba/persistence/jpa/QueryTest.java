package com.degloba.persistence.jpa;

//
import com.degloba.domain.BaseEntity;
import com.degloba.domain.CriteriaQuery;
import com.degloba.domain.CriterionBuilder;
import com.degloba.domain.InstanceFactory;

// Entitats de domini de test
import com.degloba.persistence.test.domain.Dictionary;
import com.degloba.persistence.test.domain.DictionaryCategory;
import com.degloba.persistence.test.domain.jpa.Book;
import com.google.appengine.api.datastore.Key;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
*
* @author yang
*/
public class QueryTest extends AbstractIntegrationTest {

   private CriteriaQuery instance;

   private DictionaryCategory gender;

   private DictionaryCategory education;

   private Dictionary male;

   private Dictionary female;

   private Dictionary undergraduate;

   private CriterionBuilder criterionBuilder = InstanceFactory.getInstance(CriterionBuilder.class);

   @Before
   public void setUp() {
       super.setUp();

       gender = createCategory("gender", 1);
       education = createCategory("education", 2);
       
       male = createDictionary("01", "Homes", gender, 100, "01");
       female = createDictionary("02", "Dones", gender, 150, "01");
       undergraduate = createDictionary("01", "Pregrau", education, 200, "05");
       
       gender.getDictionaries().add(male);
       gender.getDictionaries().add(female);
       
       education.getDictionaries().add(undergraduate);
       
       /*DictionaryCategory.getRepository().save(gender);
       DictionaryCategory.getRepository().save(education);*/
       gender.save();
       education.save();
       
       instance = DictionaryCategory.getRepository().createCriteriaQuery(DictionaryCategory.class);
       instance = Dictionary.getRepository().createCriteriaQuery(Dictionary.class);
      
   }

   @Test
   public void testEq() {
	   
	   instance = BaseEntity.getRepository().createCriteriaQuery(DictionaryCategory.class);
       List<DictionaryCategory> results = instance.eq("name", "gender").list();
       DictionaryCategory dictionaryCategory = results.get(0);
	   assertEquals(gender, dictionaryCategory);
	   
	   instance = BaseEntity.getRepository().createCriteriaQuery(Dictionary.class);
       List<Dictionary> results2 = instance.eq("category", gender).list();
       Dictionary dictionary = results2.get(0);
	   assertEquals(male, dictionary);
/*       assertTrue(results.contains(male));
       assertTrue(results.contains(female));
       assertFalse(results.contains(undergraduate));*/
   }

   @Test
   public void testNotEq() {
	   instance = BaseEntity.getRepository().createCriteriaQuery(DictionaryCategory.class);
	   List<DictionaryCategory> results = instance.notEq("name", "gender").list();
	   DictionaryCategory dictionaryCategory = results.get(0);
	   assertEquals(education, dictionaryCategory);
	   
/*	   BUG 64 
  	   instance = BaseEntity.getRepository().createCriteriaQuery(Dictionary.class);
       List<Dictionary> results2 = instance.notEq("category", gender).list();
       Dictionary dictionary = results2.get(0);
       assertEquals(education, dictionary.getCategory());*/
   }

   @Test
   public void testGe() {
       List<Dictionary> results = instance.ge("sortOrder", 150).list();
       assertEquals(female, results.get(0));
     /*  assertFalse(results.contains(male));
       assertTrue(results.contains(female));
       assertTrue(results.contains(undergraduate));*/
   }

   @Test
   public void testGt() {
       List<Dictionary> results = instance.gt("sortOrder", 150).list();
       assertEquals(undergraduate, results.get(0));
   /*    assertFalse(results.contains(male));
       assertFalse(results.contains(female));
       assertTrue(results.contains(undergraduate));*/
   }

   @Test
   public void testLe() {
       List<Dictionary> results = instance.le("sortOrder", 150).list();
       assertEquals(male, results.get(0));
/*       assertTrue(results.contains(male));
       assertTrue(results.contains(female));
       assertFalse(results.contains(undergraduate));*/
   }

   @Test
   public void testLt() {
       List<Dictionary> results = instance.lt("sortOrder", 150).list();
       assertEquals(male, results.get(0));
       //List<Dictionary> a = new ArrayList<Dictionary>(results);
              
       for (Dictionary b : results) {
    	      assertEquals(b.getId(), male.getId());
    	    }
     /*  assertTrue(a.contains(male));
       assertFalse(a.contains(female));
       assertFalse(a.contains(undergraduate));*/
   }

   /*
    * Valores de 2 propiedades son iguales
    * 
    * Not Supported by Datanucleus
    */
/*   @Test
   public void testEqProp() {
       List<Dictionary> results = instance.eqProp("code", "parentCode").list();
       assertEquals(male,results.get(0));
       assertTrue(results.contains(male));
       assertFalse(results.contains(female));
       assertFalse(results.contains(undergraduate));
   }*/

   /*
    * Valores de 2 propiedades NO son iguales
    * 
    *  Not Supported by Datanucleus
    */
/*   @Test
   public void testNotEqProp() {
       List<Dictionary> results = instance.notEqProp("code", "parentCode").list();
       assertEquals(male,results.get(0));
       assertFalse(results.contains(male));
       assertTrue(results.contains(female));
       assertTrue(results.contains(undergraduate));
   }*/

   
   /*
    * Not Supported by Datanucleus
    */
   /*@Test
   public void testGtProp() {
       List<Dictionary> results = instance.gtProp("code", "parentCode").list();
       assertEquals(male,results.get(0));
       assertFalse(results.contains(male));
       assertTrue(results.contains(female));
       assertFalse(results.contains(undergraduate));
   }*/

   /*
    * Not Supported by Datanucleus
    */
  /* @Test
   public void testGeProp() {
       List<Dictionary> results = instance.geProp("code", "parentCode").list();
       assertEquals(male,results.get(0));
       assertTrue(results.contains(male));
       assertTrue(results.contains(female));
       assertFalse(results.contains(undergraduate));
   }*/
   
   
   /*
    * Not Supported by Datanucleus
    */
  /* @Test
   public void testLtProp() {
       List<Dictionary> results = instance.ltProp("code", "parentCode").list();
       assertEquals(undergraduate,results.get(0));
       assertFalse(results.contains(male));
       assertFalse(results.contains(female));
       assertTrue(results.contains(undergraduate));
   }*/

   
   /* Not Supported by Datanucleus
    * 
    */
/*   @Test
   public void testLeProp() {
       List<Dictionary> results = instance.leProp("code", "parentCode").list();
       assertEquals(male,results.get(0));
       assertTrue(results.contains(male));
       assertFalse(results.contains(female));
       assertTrue(results.contains(undergraduate));
   }*/

   @Test
   public void testSizeEq() {
       //instance = repository.createCriteriaQuery(DictionaryCategory.class).sizeEq("dictionaries", 2);
       instance = BaseEntity.getRepository().createCriteriaQuery(DictionaryCategory.class).sizeEq("dictionaries", 2);
       
       List<DictionaryCategory> results = instance.list();
       assertEquals(gender,results.get(0));
  /*     assertTrue(results.contains(gender));
       assertFalse(results.contains(education));*/
   }

   @Test
   public void testSizeNotEq() {
       //instance = repository.createCriteriaQuery(DictionaryCategory.class).sizeNotEq("dictionaries", 2);
       instance = BaseEntity.getRepository().createCriteriaQuery(DictionaryCategory.class).sizeNotEq("dictionaries", 2);
       
       List<DictionaryCategory> results = instance.list();
       assertEquals(education,results.get(0));
       /*assertFalse(results.contains(gender));
       assertTrue(results.contains(education));*/
   }

   @Test
   public void testSizeGt() {
       //instance = repository.createCriteriaQuery(DictionaryCategory.class).sizeGt("dictionaries", 1);
       instance = BaseEntity.getRepository().createCriteriaQuery(DictionaryCategory.class).sizeGt("dictionaries", 1);
       List<DictionaryCategory> results = instance.list();
       assertEquals(gender,results.get(0));
       /*assertTrue(results.contains(gender));
       assertFalse(results.contains(education));*/
   }

   @Test
   public void testSizeGe() {
       //instance = repository.createCriteriaQuery(DictionaryCategory.class).sizeGe("dictionaries", 2);
       instance = BaseEntity.getRepository().createCriteriaQuery(DictionaryCategory.class).sizeGe("dictionaries", 2);
       List<DictionaryCategory> results = instance.list();
       assertEquals(gender,results.get(0));
       /*assertTrue(results.contains(gender));
       assertFalse(results.contains(education));*/
   }

   @Test
   public void testSizeLt() {
       instance = BaseEntity.getRepository().createCriteriaQuery(DictionaryCategory.class).sizeLt("dictionaries", 2);
       List<DictionaryCategory> results = instance.list();
       assertEquals(education,results.get(0));
      /* assertFalse(results.contains(gender));
       assertTrue(results.contains(education));*/
   }

   @Test
   public void testSizeLe() {
       instance = BaseEntity.getRepository().createCriteriaQuery(DictionaryCategory.class).sizeLe("dictionaries", 2);
       List<DictionaryCategory> results = instance.list();
       assertEquals(gender,results.get(0));
       /*assertTrue(results.contains(gender));
       assertTrue(results.contains(education));*/
   }

   @Test
   public void testIsEmpty() {
       DictionaryCategory empty = createCategory("a", 3);
       //instance = repository.createCriteriaQuery(DictionaryCategory.class);
       instance = BaseEntity.getRepository().createCriteriaQuery(DictionaryCategory.class);
       List<DictionaryCategory> results = instance.isEmpty("dictionaries").list();
       assertEquals(empty,results.get(0));
       /*assertTrue(results.contains(empty));
       assertFalse(results.contains(gender));
       assertFalse(results.contains(education));*/
   }

   @Test
   public void testNotEmpty() {
       DictionaryCategory empty = createCategory("a", 3);
       //instance = repository.createCriteriaQuery(DictionaryCategory.class);
       instance = BaseEntity.getRepository().createCriteriaQuery(DictionaryCategory.class);
       List<DictionaryCategory> results = instance.notEmpty("dictionaries").list();
       /*assertFalse(results.contains(empty));
       assertTrue(results.contains(gender));
       assertTrue(results.contains(education))*/;
      // List<Dictionary> results = instance.lt("sortOrder", 150).list();
   }

   @Test
   public void testContainsText() {
       List<Dictionary> results = instance.containsText("text", "Preg").list();
       assertEquals(undergraduate, results.get(0));
       /*assertTrue(results.contains(undergraduate));
       assertFalse(results.contains(male));
       assertFalse(results.contains(female));*/
   }

   @Test
   public void testStartsWithText() {
	   List<Dictionary> results = instance.startsWithText("text", "Pregrau").list();
       Dictionary dictionary = results.get(0);
       assertEquals(dictionary, undergraduate);
       //assertTrue(results.contains(undergraduate));

      
       results = instance.startsWithText("text", "Homes").list();
       dictionary = results.get(0);
       assertNotEquals(dictionary, undergraduate);
       /*assertFalse(results.contains(undergraduate));*/
   }

   @Test
   public void testInEntity() {
       Set<DictionaryCategory> params = new HashSet<DictionaryCategory>();
       params.add(education);
       params.add(gender);
       List<Dictionary> results = instance.in("category", params).list();
       assertEquals(results.get(0),male);
       /*assertTrue(results.contains(male));
       assertTrue(results.contains(female));
       assertTrue(results.contains(undergraduate));*/
   }

   @Test
   public void testInString() {
       Set<String> params = new HashSet<String>();
       params.add("Homes");
       params.add("Dones");
       List<Dictionary> results = instance.in("text", params).list();
       assertEquals(results.get(0),male);
       /*assertTrue(results.contains(male));
       assertTrue(results.contains(female));
       assertFalse(results.contains(undergraduate));*/
   }

   @Test
   public void testInNull() {
       Collection<Object> value = null;
       List<Dictionary> results = instance.in("id", value).list();
       assertTrue(results.isEmpty());
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testInEmpty() {
       List<Dictionary> results = instance.in("id", Collections.EMPTY_LIST).list();
       assertTrue(results.isEmpty());
   }

/* Not Supported by Datanucleus
 * 
 * @Test
   public void testNotInEntity() {
       Set<Key> params = new HashSet<Key>();
       params.add(male.getId());
       params.add(female.getId());
       List<Dictionary> results = instance.notIn("id", params).list();
       assertEquals(results.get(0),undergraduate);
       assertFalse(results.contains(male));
       assertFalse(results.contains(female));
       assertTrue(results.contains(undergraduate));
   }*/

   /* Not Supported by Datanucleus
    * 
    * @Test
   public void testNotInString() {
       Set<String> params = new HashSet<String>();
       params.add("Homes");
       params.add("Dones");
       List<Dictionary> results = instance.notIn("text", params).list();
       assertEquals(results.get(0),undergraduate);
       assertFalse(results.contains(male));
       assertFalse(results.contains(female));
       assertTrue(results.contains(undergraduate));
   }*/

   @Test
   public void testNotInNull() {
       Collection<Object> value = null;
       List<Dictionary> results = instance.notIn("id", value).list();
       assertFalse(results.isEmpty());
   }

   @SuppressWarnings("unchecked")
   @Test
   public void testNotInEmpty() {
       List<Dictionary> results = instance.notIn("id", Collections.EMPTY_LIST).list();
       assertFalse(results.isEmpty());
   }

   @Test
   public void testIsNull() {
       List<Dictionary> results = instance.isNull("description").list();
       assertEquals(results.get(0),male);
       /*assertTrue(results.contains(male));
       assertTrue(results.contains(female));
       assertTrue(results.contains(undergraduate));*/
   }

   @Test
   public void testNotNull() {
       List<Dictionary> results = instance.notNull("text").list();
       assertEquals(results.get(0),female);
       /*assertTrue(results.contains(male));
       assertTrue(results.contains(female));
       assertTrue(results.contains(undergraduate));*/
   }

   @Test
   public void testBetween() {
       List<Dictionary> results = instance.between("parentCode", "01", "02").list();
       assertEquals(results.get(0),male);
       /*assertTrue(results.contains(male));
       assertTrue(results.contains(female));
       assertFalse(results.contains(undergraduate));*/
   }

   @Test
   public void testAnd() {
       List<Dictionary> results = instance.and(criterionBuilder.eq("code", "01"), criterionBuilder.eq("category", gender)).list();
	   assertEquals(male, results.get(0));
       /*assertTrue(results.contains(male));
       assertFalse(results.contains(female));
       assertFalse(results.contains(undergraduate));*/
   }

   @Test
   public void testOr() {
       List<Dictionary> results = instance.or(criterionBuilder.eq("text", "Homes"), criterionBuilder.eq("sortOrder", 150)).list();
       assertEquals(male, results.get(0));
       /*assertTrue(results.contains(male));
       assertTrue(results.contains(female));
       assertFalse(results.contains(undergraduate));*/
   }

  /* Not Supported by Datanucleus
   * 
   * @Test  
   public void testNot() {
       List<Dictionary> results = instance.not(criterionBuilder.eq("code", "01")).list(); 
       assertEquals(male, results.get(0));
       assertFalse(results.contains(male));
       assertTrue(results.contains(female));
       assertFalse(results.contains(undergraduate));
   }*/

   @Test
   public void testFindPaging() {
       createDictionary("08", "xyz", education, 150, "01");
       createDictionary("09", "xyy", education, 160, "02");
       List<Dictionary> results = instance.setFirstResult(1).setMaxResults(2).list();
       assertEquals(2, results.size());
   }

   @Test
   public void testFindOrder() {
       instance.asc("sortOrder");
       List<Dictionary> results = instance.asc("sortOrder").list();
/*       assertTrue(results.indexOf(male) < results.indexOf(female));
       assertTrue(results.indexOf(female) < results.indexOf(undergraduate));*/
      
       results = instance.desc("sortOrder").list();
       /*assertTrue(results.indexOf(male) > results.indexOf(female));
       assertTrue(results.indexOf(female) > results.indexOf(undergraduate));*/
   }

   //@Test
   public void testAlias() {
      /* List<Dictionary> results = repository.find(instance.eq("category.name", education));
       Dictionary graduate = Dictionary.get(4L);
       assertTrue(results.contains(graduate));
       Dictionary doctor = Dictionary.get(46L);
       assertFalse(results.contains(doctor));*/
   }

   private DictionaryCategory createCategory(String name, int sortOrder) {
       DictionaryCategory category = new DictionaryCategory();
       category.setName(name);
       category.setSortOrder(sortOrder);
       //BaseEntity.getRepository().save(category);
       //entityManager.persist(category);
       return category;
   }

   private Dictionary createDictionary(String code, String text, DictionaryCategory category, int sortOrder,
                                       String parentCode) {
       Dictionary dictionary = new Dictionary(code, text, category);
       dictionary.setSortOrder(sortOrder);
       dictionary.setParentCode(parentCode);
       //BaseEntity.getRepository().save(dictionary);
       //entityManager.persist(dictionary);
       return dictionary;
   }
}