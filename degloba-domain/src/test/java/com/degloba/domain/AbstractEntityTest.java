package com.degloba.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.degloba.domain.entity.MyEntity;
import com.degloba.domain.ioc.InstanceFactory;
import com.degloba.domain.persistence.rdbms.jpa.BaseAggregateRoot;
import com.degloba.domain.persistence.rdbms.jpa.BaseEntity;
import com.degloba.domain.persistence.rdbms.jpa.CriteriaQuery;
import com.degloba.domain.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AbstractEntityTest {

    private MyEntity instance;
    
    @Mock
    private IEntityRepository repository;

    @Before
    public void setUp() throws Exception {
        instance = new MyEntity();
        MockitoAnnotations.initMocks(this);
        BaseAggregateRoot.setRepository(repository);
    }

    @After
    public void tearDown() throws Exception {
    	BaseAggregateRoot.setRepository(null);
    }

   /* @Test
    public void testIdAccessor() {
        Long id = 3L;
        instance.setId(id);
        assertEquals(id, instance.getId());
    }

    @Test
    public void testVersionAccessor() {
        int version = 10;    	
        instance.setVersion(version);
        assertEquals(version, instance.getVersion());
    }
*/
    /**
     * Test of existed method, of class AbstractEntity.
     */
  /*  @Test
    public void testExisted() {
        assertFalse(instance.existed());

        instance.setId(3L);
        MyEntity newEntity = new MyEntity("abc");
        when(repository.get(MyEntity.class, 3L)).thenReturn(newEntity);
        //assertTrue(instance.existed());
    }*/

    /**
     * Test of notExisted method, of class AbstractEntity.
     */
   /* @Test
    public void testNotExisted() {
        assertTrue(instance.notExisted());

        instance.setId(3L);
        when(repository.get(MyEntity.class, 3L)).thenReturn(null);
        assertTrue(instance.notExisted());
    }*/


    /**
     * Test of getRepository method, of class AbstractEntity.
     */
    @Test
    public void testRepositoryAccessor() {
        assertSame(repository, BaseAggregateRoot.getRepository());
        
        BaseAggregateRoot.setRepository(null);
        InstanceFactory.bind(IEntityRepository.class, repository);
        assertSame(repository, BaseAggregateRoot.getRepository());
    }

    /**
     * Test of save method, of class AbstractEntity.
     */
    @Test
    public void testSave() {
        instance.save();
        //verify(repository.save(instance));
    }

    /**
     * Test of remove method, of class AbstractEntity.
     */
    @Test
    public void testRemove() {
        instance.remove();
        verify(repository).remove(instance);
    }

    /**
     * Test of get method, of class AbstractEntity.
     */
  /*  @Test
    public void testGet() {
        when(repository.get(MyEntity.class, 3L)).thenReturn(instance);
        assertEquals(instance, AbstractEntity.get(MyEntity.class, 3L));
    }
*/
    /**
     * Test of getUnmodified method, of class AbstractEntity.
     */
    @Test
    public void testGetUnmodified() {
        MyEntity entity2 = new MyEntity("nnn");
        when(repository.getUnmodified(MyEntity.class, instance)).thenReturn(entity2);
        assertEquals(entity2, BaseAggregateRoot.getUnmodified(MyEntity.class, instance));
    }

    /**
     * Test of load method, of class AbstractEntity.
     */
    @Test
    public void testLoad() {
        when(repository.load(MyEntity.class, 3L)).thenReturn(instance);
        assertEquals(instance, BaseAggregateRoot.load(MyEntity.class, 3L));
    }

    /**
     * Test of findAll method, of class AbstractEntity.
     */
    @Test
    public void testFindAll() {
        List list = Arrays.asList("a", "b");
        CriteriaQuery query = mock(CriteriaQuery.class);
        when(repository.createCriteriaQuery(MyEntity.class)).thenReturn(query);
        when(query.list()).thenReturn(list);
        assertEquals(list, BaseAggregateRoot.findAll(MyEntity.class));
    }

    /**
     * Test of findByProperty method, of class AbstractEntity.
     */
    @Test
    public void testFindByProperty() {
        List list = Collections.singletonList(instance);
        when(repository.findByProperty(MyEntity.class, "name", "abc")).thenReturn(list);
        assertEquals(list, BaseAggregateRoot.findByProperty(MyEntity.class, "name", "abc"));
    }

    /**
     * Test of findByProperties method, of class AbstractEntity.
     */
    @Test
    public void testFindByProperties() {
        List list = Collections.singletonList(instance);
        Map props = Collections.singletonMap("name", "abc");
        when(repository.findByProperties(MyEntity.class, NamedParameters.create(props))).thenReturn(list);
        assertEquals(list, BaseAggregateRoot.findByProperties(MyEntity.class, props));
    }

}
