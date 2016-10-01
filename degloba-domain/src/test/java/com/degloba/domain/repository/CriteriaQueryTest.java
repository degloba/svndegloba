package com.degloba.domain.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;



import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;
import com.degloba.domain.OrderSettings;
import com.degloba.domain.persistence.rdbms.jpa.QueryCriterion;
import com.degloba.domain.entity.MyEntity;
import com.degloba.domain.internal.repo.AndCriterion;
import com.degloba.domain.internal.repo.BetweenCriterion;
import com.degloba.domain.internal.repo.ContainsTextCriterion;
import com.degloba.domain.internal.repo.EqCriterion;
import com.degloba.domain.internal.repo.EqPropCriterion;
import com.degloba.domain.internal.repo.GeCriterion;
import com.degloba.domain.internal.repo.GePropCriterion;
import com.degloba.domain.internal.repo.GtCriterion;
import com.degloba.domain.internal.repo.GtPropCriterion;
import com.degloba.domain.internal.repo.InCriterion;
import com.degloba.domain.internal.repo.IsEmptyCriterion;
import com.degloba.domain.internal.repo.IsNullCriterion;
import com.degloba.domain.internal.repo.LeCriterion;
import com.degloba.domain.internal.repo.LePropCriterion;
import com.degloba.domain.internal.repo.LtCriterion;
import com.degloba.domain.internal.repo.LtPropCriterion;
import com.degloba.domain.internal.repo.NotCriterion;
import com.degloba.domain.internal.repo.NotEmptyCriterion;
import com.degloba.domain.internal.repo.NotEqCriterion;
import com.degloba.domain.internal.repo.NotEqPropCriterion;
import com.degloba.domain.internal.repo.NotInCriterion;
import com.degloba.domain.internal.repo.NotNullCriterion;
import com.degloba.domain.internal.repo.OrCriterion;
import com.degloba.domain.internal.repo.SizeEqCriterion;
import com.degloba.domain.internal.repo.SizeGeCriterion;
import com.degloba.domain.internal.repo.SizeGtCriterion;
import com.degloba.domain.internal.repo.SizeLeCriterion;
import com.degloba.domain.internal.repo.SizeLtCriterion;
import com.degloba.domain.internal.repo.SizeNotEqCriterion;
import com.degloba.domain.internal.repo.StartsWithTextCriterion;
import com.degloba.domain.persistence.rdbms.jpa.CriteriaQuery;
import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;

import org.junit.Before;
import org.junit.Test;


public class CriteriaQueryTest {
    private CriteriaQuery instance;
    private IEntityRepository repository;
    private Class<MyEntity> entityClass = MyEntity.class;
    
    @Before
    public void setUp() {
        repository = mock(IEntityRepository.class);
        instance = new CriteriaQuery(repository, entityClass);
    }

    /**
     * Test of getEntityClass method, of class CriteriaQuery.
     */
    @Test
    public void testGetEntityClass() {
        assertEquals(entityClass, instance.getEntityClass());
    }

    /**
     * Test of getFirstResult method, of class JpqlQuery.
     */
    @Test
    public void testFirstResult() {
        assertEquals(3, instance.setFirstResult(3).getFirstResult());
    }

    /**
     * Test of getMaxResults method, of class JpqlQuery.
     */
    @Test
    public void testMaxResults() {
        assertEquals(10, instance.setMaxResults(10).getMaxResults());
    }

    /**
     * Test of getOrderSettings method, of class CriteriaQuery.
     */
    @Test
    public void testOrderSettings() {
        OrderSettings orderSettings = new OrderSettings();
        orderSettings.asc("id");
        orderSettings.desc("name");
        instance.asc("id").desc("name");
        assertEquals(orderSettings, instance.getOrderSettings());
    }

    /**
     * Test of eq method, of class CriteriaQuery.
     */
    @Test
    public void testEq() {
    	assertEquals(new EqCriterion("name", "abc"), instance.eq("name", "abc").getQueryCriterion());
    }

    /**
     * Test of notEq method, of class CriteriaQuery.
     */
    @Test
    public void testNotEq() {
    	assertEquals(new NotEqCriterion("name", "abc"), instance.notEq("name", "abc").getQueryCriterion());
    }

    /**
     * Test of gt method, of class CriteriaQuery.
     */
    @Test
    public void testGt() {
    	assertEquals(new GtCriterion("id", 5), instance.gt("id", 5).getQueryCriterion());
    }

    /**
     * Test of ge method, of class CriteriaQuery.
     */
    @Test
    public void testGe() {
    	assertEquals(new GeCriterion("id", 5), instance.ge("id", 5).getQueryCriterion());
    }

    /**
     * Test of lt method, of class CriteriaQuery.
     */
    @Test
    public void testLt() {
    	assertEquals(new LtCriterion("id", 5), instance.lt("id", 5).getQueryCriterion());
    }

    /**
     * Test of le method, of class CriteriaQuery.
     */
    @Test
    public void testLe() {
    	assertEquals(new LeCriterion("id", 5), instance.le("id", 5).getQueryCriterion());
    }

    /**
     * Test of eqProp method, of class CriteriaQuery.
     */
    @Test
    public void testEqProp() {
    	assertEquals(new EqPropCriterion("id", "name"), instance.eqProp("id", "name").getQueryCriterion());
    }

    /**
     * Test of notEqProp method, of class CriteriaQuery.
     */
    @Test
    public void testNotEqProp() {
    	assertEquals(new NotEqPropCriterion("id", "name"), instance.notEqProp("id", "name").getQueryCriterion());
    }

    /**
     * Test of gtProp method, of class CriteriaQuery.
     */
    @Test
    public void testGtProp() {
    	assertEquals(new GtPropCriterion("id", "name"), instance.gtProp("id", "name").getQueryCriterion());
    }

    /**
     * Test of geProp method, of class CriteriaQuery.
     */
    @Test
    public void testGeProp() {
    	assertEquals(new GePropCriterion("id", "name"), instance.geProp("id", "name").getQueryCriterion());
    }

    /**
     * Test of ltProp method, of class CriteriaQuery.
     */
    @Test
    public void testLtProp() {
    	assertEquals(new LtPropCriterion("id", "name"), instance.ltProp("id", "name").getQueryCriterion());
    }

    /**
     * Test of leProp method, of class CriteriaQuery.
     */
    @Test
    public void testLeProp() {
    	assertEquals(new LePropCriterion("id", "name"), instance.leProp("id", "name").getQueryCriterion());
    }

    /**
     * Test of sizeEq method, of class CriteriaQuery.
     */
    @Test
    public void testSizeEq() {
    	assertEquals(new SizeEqCriterion("id", 3), instance.sizeEq("id", 3).getQueryCriterion());
    }

    /**
     * Test of sizeNotEq method, of class CriteriaQuery.
     */
    @Test
    public void testSizeNotEq() {
    	assertEquals(new SizeNotEqCriterion("id", 3), instance.sizeNotEq("id", 3).getQueryCriterion());
    }

    /**
     * Test of sizeGt method, of class CriteriaQuery.
     */
    @Test
    public void testSizeGt() {
    	assertEquals(new SizeGtCriterion("id", 3), instance.sizeGt("id", 3).getQueryCriterion());
    }

    /**
     * Test of sizeGe method, of class CriteriaQuery.
     */
    @Test
    public void testSizeGe() {
    	assertEquals(new SizeGeCriterion("id", 3), instance.sizeGe("id", 3).getQueryCriterion());
    }

    /**
     * Test of sizeLt method, of class CriteriaQuery.
     */
    @Test
    public void testSizeLt() {
    	assertEquals(new SizeLtCriterion("id", 3), instance.sizeLt("id", 3).getQueryCriterion());
    }

    /**
     * Test of sizeLe method, of class CriteriaQuery.
     */
    @Test
    public void testSizeLe() {
    	assertEquals(new SizeLeCriterion("id", 3), instance.sizeLe("id", 3).getQueryCriterion());
    }

    /**
     * Test of containsText method, of class CriteriaQuery.
     */
    @Test
    public void testContainsText() {
    	assertEquals(new ContainsTextCriterion("name", "a"), instance.containsText("name", "a").getQueryCriterion());
    }

    /**
     * Test of startsWithText method, of class CriteriaQuery.
     */
    @Test
    public void testStartsWithText() {
    	assertEquals(new StartsWithTextCriterion("name", "a"), instance.startsWithText("name", "a").getQueryCriterion());
    }

    /**
     * Test of in method, of class CriteriaQuery.
     */
    @Test
    public void testInCollection() {
        List<?> criterions = Arrays.asList("a", "b");
    	assertEquals(new InCriterion("name", criterions), instance.in("name", criterions).getQueryCriterion());
    }

    /**
     * Test of in method, of class CriteriaQuery.
     */
    @Test
    public void testInArray() {
        Object[] criterions = new Object[] {"a", "b"};
    	assertEquals(new InCriterion("name", criterions), instance.in("name", criterions).getQueryCriterion());
    }

    /**
     * Test of notIn method, of class CriteriaQuery.
     */
    @Test
    public void testNotInCollection() {
        List<?> criterions = Arrays.asList("a", "b");
    	assertEquals(new NotInCriterion("name", criterions), instance.notIn("name", criterions).getQueryCriterion());
    }

    /**
     * Test of notIn method, of class CriteriaQuery.
     */
    @Test
    public void testNotInArray() {
        Object[] criterions = new Object[] {"a", "b"};
    	assertEquals(new NotInCriterion("name", criterions), instance.notIn("name", criterions).getQueryCriterion());
    }

    /**
     * Test of between method, of class CriteriaQuery.
     */
    @Test
    public void testBetween() {
    	assertEquals(new BetweenCriterion("name", "a", "b"), instance.between("name", "a", "b").getQueryCriterion());
    }

    /**
     * Test of isNull method, of class CriteriaQuery.
     */
    @Test
    public void testIsNull() {
    	assertEquals(new IsNullCriterion("name"), instance.isNull("name").getQueryCriterion());
    }

    /**
     * Test of notNull method, of class CriteriaQuery.
     */
    @Test
    public void testNotNull() {
    	assertEquals(new NotNullCriterion("name"), instance.notNull("name").getQueryCriterion());
    }

    /**
     * Test of isEmpty method, of class CriteriaQuery.
     */
    @Test
    public void testIsEmpty() {
    	assertEquals(new IsEmptyCriterion("name"), instance.isEmpty("name").getQueryCriterion());
    }

    /**
     * Test of notEmpty method, of class CriteriaQuery.
     */
    @Test
    public void testNotEmpty() {
    	assertEquals(new NotEmptyCriterion("name"), instance.notEmpty("name").getQueryCriterion());
    }

    /**
     * Test of isTrue method, of class CriteriaQuery.
     */
    @Test
    public void testIsTrue() {
    	assertEquals(new EqCriterion("name", true), instance.isTrue("name").getQueryCriterion());
    }

    /**
     * Test of isFalse method, of class CriteriaQuery.
     */
    @Test
    public void testIsFalse() {
    	assertEquals(new EqCriterion("name", false), instance.isFalse("name").getQueryCriterion());
    }

    /**
     * Test of isBlank method, of class CriteriaQuery.
     */
    @Test
    public void testIsBlank() {
        QueryCriterion criterion1 = new IsNullCriterion("name");
        QueryCriterion criterion2 = new EqCriterion("name", "");
        QueryCriterion criterion3 = new OrCriterion(criterion1, criterion2);
        
        assertEquals(criterion3, instance.isBlank("name").getQueryCriterion());
    }

    /**
     * Test of notBlank method, of class CriteriaQuery.
     */
    @Test
    public void testNotBlank() {
        QueryCriterion criterion1 = new NotNullCriterion("name");
        QueryCriterion criterion2 = new NotEqCriterion("name", "");
        QueryCriterion criterion3 = new AndCriterion(criterion1, criterion2);
        
        assertEquals(criterion3, instance.notBlank("name").getQueryCriterion());
    }

    /**
     * Test of not method, of class CriteriaQuery.
     */
    @Test
    public void testNot() {
        QueryCriterion criterion1 = new EqCriterion("name", "abc");
        QueryCriterion criterion2 = new NotCriterion(criterion1);
        
        assertEquals(criterion2, instance.not(criterion1).getQueryCriterion());
    }

    /**
     * Test of and method, of class CriteriaQuery.
     */
    @Test
    public void testAnd() {
        QueryCriterion criterion1 = new EqCriterion("name", "abc");
        QueryCriterion criterion2 = new GtCriterion("id", 5);
        QueryCriterion criterion3 = new AndCriterion(criterion1, criterion2);
        
        assertEquals(criterion3, instance.and(criterion1, criterion2).getQueryCriterion());
    }

    /**
     * Test of or method, of class CriteriaQuery.
     */
    @Test
    public void testOr() {
        QueryCriterion criterion1 = new EqCriterion("name", "abc");
        QueryCriterion criterion2 = new GtCriterion("id", 5);
        QueryCriterion criterion3 = new OrCriterion(criterion1, criterion2);
        
        assertEquals(criterion3, instance.or(criterion1, criterion2).getQueryCriterion());
    }

    /**
     * Test of list method, of class CriteriaQuery.
     */
    @Test
    public void testList() {
        List<Object> results = Arrays.asList(new Object[] {"a", "b"});
        when(repository.find(instance)).thenReturn(results);
        assertEquals(results, instance.list());
    }

    /**
     * Test of singleResult method, of class CriteriaQuery.
     */
    @Test
    public void testSingleResult() {
        when(repository.getSingleResult(instance)).thenReturn("abc");
        assertEquals("abc", instance.singleResult());
    }
    
    @Test
    public void testGetQueryString() {
    	EqCriterion criterion1 = new EqCriterion("name", "abc");
    	InCriterion criterion2 = new InCriterion("age", Arrays.asList(1, 2));
        instance.eq("name", "abc")
                        .isEmpty("post")
                        .notNull("birthday")
                        .in("age", Arrays.asList(1, 2))
                        .getQueryString();        
        assertEquals("select distinct(rootEntity) from com.degloba.domain.entity.MyEntity as rootEntity  "
                + "where rootEntity.name = :rootEntity_name" + criterion1.hashCode() + " "
                + "and rootEntity.post is empty "
                + "and rootEntity.birthday is not null "
                + "and rootEntity.age in :rootEntity_age" + criterion2.hashCode(),
                instance.getQueryString());
        assertEquals(NamedParameters.create()
                .add("rootEntity_name" + criterion1.hashCode(), "abc")
                .add("rootEntity_age" + criterion2.hashCode(), Arrays.asList(1, 2)),
                instance.getParameters());
    }
    
    @Test
    public void testGetQueryString2() {
    	EqCriterion criterion1 = new EqCriterion("name", "abc");
    	InCriterion criterion2 = new InCriterion("age", Arrays.asList(1, 2));
    	
    	
        assertEquals("select distinct(rootEntity) from com.degloba.domain.entity.MyEntity as rootEntity  "
                + "where rootEntity.name = :rootEntity_name" + criterion1.hashCode() + " "
                + "and rootEntity.post is empty "
                + "and rootEntity.birthday is not null "
                + "and rootEntity.age in :rootEntity_age" + criterion2.hashCode() + " "
                + "order by rootEntity.name asc", 
                instance.eq("name", "abc")
                        .isEmpty("post")
                        .notNull("birthday")
                        .in("age", Arrays.asList(1, 2))
                        .asc("name")
                        .getQueryString());
    }
}
