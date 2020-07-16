package com.degloba.domain;

import com.degloba.domain.entity.Company;
import com.degloba.domain.entity.Dept;
import com.degloba.domain.entity.Organization;
import com.degloba.persistence.rdbms.api.jpa.BaseAggregateRoot;
import com.degloba.persistence.rdbms.api.jpa.IEntityRepository;


import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BaseEntityTest {

    private IEntityRepository repository;

    private final Organization guangdong = new Company("Guangdong", 1);

    private final Organization guangzhou = new Company("Guangdong", 2);

    private final Organization financial = new Dept("Guangdong", 1);

    @BeforeEach
    public void setUp() {
        repository = mock(IEntityRepository.class);
        BaseAggregateRoot.setRepository(repository);
    }

    @AfterEach
    public void tearDown() {
        reset(repository);
        BaseAggregateRoot.setRepository(null);
    }

 /*   @Test
    public void testExistedIdIsNull() {
        guangdong.setId(null);
        assertFalse(guangdong.existed());
    }

    @Test
    public void testExistedIdIsZero() {
        guangdong.setId((long)0);
        assertFalse(guangdong.existed());
    }

    @Test
    public void testExistedRepositoryNotFound() {
        guangdong.setId((long)3);
        when(repository.exists(Organization.class,(long) 3)).thenReturn(false);
        assertFalse(guangdong.existed());
    }

    @Test
    public void testExistedRepositoryFound() {
        guangdong.setId((long)3);
        when(repository.exists(Company.class, (long) 3)).thenReturn(true);
        //assertTrue(guangdong.existed());
    }

    @Test
    public void testNotExistedIdIsNull() {
        guangdong.setId(null);
        //assertTrue(guangdong.notExisted());
    }

    @Test
    public void testNotExistedIdIsZero() {
        guangdong.setId((long)0);
        //assertTrue(guangdong.notExisted());
    }

    @Test
    public void testNotExistedRepositoryNotFound() {
        guangdong.setId((long)3);
        when(repository.exists(Company.class,(long) 3)).thenReturn(false);
        //assertTrue(guangdong.notExisted());
    }

    @Test
    public void testNotExistedRepositoryFound() {
        guangdong.setId((long)3);
        when(repository.exists(Company.class,(long) 3)).thenReturn(true);
        assertFalse(guangdong.notExisted());
    }

    @Test
    public void testNotExisted() {
        guangdong.setId(null);
        //assertTrue(guangdong.notExisted());

        guangdong.setId((long)0);
        //assertTrue(guangdong.notExisted());

        guangdong.setId((long)3);
        when(repository.exists(Company.class,(long) 3)).thenReturn(false);
        //assertTrue(guangdong.notExisted());

        reset(repository);
        when(repository.exists(Company.class,(long) 3)).thenReturn(true);
        assertFalse(guangdong.notExisted());
    }

    @Test
    public void testHashCode() {
        assertEquals(guangdong.hashCode(), new Company("Guangdong", 1).hashCode());
    }
*/
    @Test
    public void testEquals() {
        //对象永远等价于自身
        assertTrue(guangdong.equals(guangdong));
        
        //类型相同、业务主键不同的对象不等价
        assertFalse(guangdong.equals(guangzhou));
        
        //类型不同、业务主键相同的对象不等价
        assertFalse(guangdong.equals(financial));
        
        //非业务主键不影响等价性
        guangdong.disable();
        assertTrue(guangdong.equals(new Company("Guangdong", 1)));
        
        //如果a.equals(b)，那么b.equals(a)
        assertTrue(new Company("Guangdong", 1).equals(guangdong));
    }

}
