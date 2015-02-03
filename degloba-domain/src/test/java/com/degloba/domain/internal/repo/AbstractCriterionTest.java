package com.degloba.domain.internal.repo;

import com.degloba.domain.NamedParameters;
import static org.junit.Assert.*;

import com.degloba.domain.internal.repo.AbstractCriterion;
import com.degloba.domain.internal.repo.AndCriterion;
import com.degloba.domain.internal.repo.NotCriterion;
import com.degloba.domain.internal.repo.OrCriterion;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AbstractCriterionTest {

    private AbstractCriterion criterion1 = new AbstractCriterion() {

        @Override
        public String toQueryString() {
            return "";
        }

        public NamedParameters getParameters() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean equals(Object other) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    private AbstractCriterion criterion2 = new AbstractCriterion() {

        @Override
        public String toQueryString() {
            return "";
        }

        public NamedParameters getParameters() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean equals(Object other) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int hashCode() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    };

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAnd() {
        assertEquals(new AndCriterion(criterion1, criterion2), criterion1.and(criterion2));
    }

    @Test
    public void testOr() {
        assertEquals(new OrCriterion(criterion1, criterion2), criterion1.or(criterion2));
    }

    @Test
    public void testNot() {
        assertEquals(new NotCriterion(criterion1), criterion1.not());
    }

}
