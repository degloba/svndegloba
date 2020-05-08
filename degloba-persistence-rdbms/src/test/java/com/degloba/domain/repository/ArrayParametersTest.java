package com.degloba.domain.repository;

import java.util.Arrays;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.degloba.persistence.rdbms.api.jpa.PositionalParameters;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayParametersTest {
    
    private PositionalParameters instance;
    private Object[] params;
    
    @BeforeEach
    public void setUp() {
        params = new Object[] {"abc", 12, new Date()};
    }

    @Test
    public void testCreateWithoutParameters() {
        instance = PositionalParameters.create();
        assertNotNull(instance.getParams());
        assertTrue(instance.getParams().length == 0);
    }

    @Test
    public void testCreateWithArray() {
        instance = PositionalParameters.create(params);
        assertArrayEquals(params, instance.getParams());
    }

    @Test
    public void testCreateWithList() {
        instance = PositionalParameters.create(Arrays.asList(params));
        assertArrayEquals(params, instance.getParams());
    }

    @Test
    public void testEquals() {
        instance = PositionalParameters.create(params);
        assertTrue(instance.equals(instance));
        assertFalse(instance.equals(params));
        PositionalParameters other = PositionalParameters.create(params);
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
        
        other = PositionalParameters.create("name", "abc");
        assertFalse(instance.equals(other));
        assertFalse(other.equals(instance));
    }

    @Test
    public void testToString() {
        assertEquals(Arrays.toString(params), PositionalParameters.create(params).toString());
    }
    
}
