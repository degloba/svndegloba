package com.degloba.domain.repository;

import java.util.HashMap;
import java.util.Map;
import com.degloba.domain.persistence.rdbms.jpa.NamedParameters;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class MapParametersTest {
    
    private NamedParameters instance;
    private Map<String, Object> params;
    
    @Before
    public void setUp() {
        params = createParams();
    }

    @Test
    public void testCreateWithoutParameters() {
        instance = NamedParameters.create();
        assertNotNull(instance.getParams());
        assertTrue(instance.getParams().isEmpty());
    }

    @Test
    public void testCreateWithMap() {
        assertEquals(params, NamedParameters.create(params).getParams());
    }

    @Test
    public void testAddSingle() {
        instance = NamedParameters.create().add("id", 12).add("name", "abc");
        assertEquals(params, instance.getParams());
    }

    @Test
    public void testAddAnother() {
        NamedParameters other = NamedParameters.create().add("age", 15);
        instance = NamedParameters.create().add("id", 12).add("name", "abc")
                .add(other);
        params.put("age", 15);
        assertEquals(params, instance.getParams());
    }

    @Test
    public void testEquals() {
        instance = NamedParameters.create(params);
        assertTrue(instance.equals(instance));
        assertFalse(instance.equals(params));
        NamedParameters other = NamedParameters.create(params);
        assertTrue(instance.equals(other));
        assertTrue(other.equals(instance));
        
        other = NamedParameters.create().add("name", "abc");
        assertFalse(instance.equals(other));
        assertFalse(other.equals(instance));
    }

    @Test
    public void testToString() {
        assertEquals(params.toString(), NamedParameters.create(params).toString());
    }

    private Map<String, Object> createParams() {
        Map<String, Object> results = new HashMap<String, Object>();
        results.put("name", "abc");
        results.put("id", 12);
        return results;
    }
    
}
