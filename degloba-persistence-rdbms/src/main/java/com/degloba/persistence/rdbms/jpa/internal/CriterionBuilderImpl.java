package com.degloba.persistence.rdbms.jpa.internal;


import java.util.Collection;

import com.degloba.persistence.rdbms.jpa.CriterionBuilder;
import com.degloba.persistence.rdbms.jpa.IQueryCriterion;


/**
 * A utility class, as a variety of search criteria factory
 */
public class CriterionBuilderImpl implements CriterionBuilder {

    public IQueryCriterion eq(String propName, Object value) {
        return new EqCriterion(propName, value);
    }

    public IQueryCriterion notEq(String propName, Object value) {
        return new NotEqCriterion(propName, value);
    }

    public IQueryCriterion ge(String propName, Comparable<?> value) {
        return new GeCriterion(propName, value);
    }

    public IQueryCriterion gt(String propName, Comparable<?> value) {
        return new GtCriterion(propName, value);
    }

    public IQueryCriterion le(String propName, Comparable<?> value) {
        return new LeCriterion(propName, value);
    }

    public IQueryCriterion lt(String propName, Comparable<?> value) {
        return new LtCriterion(propName, value);
    }

    public IQueryCriterion eqProp(String propName, String otherPropName) {
        return new EqPropCriterion(propName, otherPropName);
    }

    public IQueryCriterion notEqProp(String propName, String otherPropName) {
        return new NotEqPropCriterion(propName, otherPropName);
    }

    public IQueryCriterion gtProp(String propName, String otherPropName) {
        return new GtPropCriterion(propName, otherPropName);
    }

    public IQueryCriterion geProp(String propName, String otherPropName) {
        return new GePropCriterion(propName, otherPropName);
    }

    public IQueryCriterion ltProp(String propName, String otherPropName) {
        return new LtPropCriterion(propName, otherPropName);
    }

    public IQueryCriterion leProp(String propName, String otherPropName) {
        return new LePropCriterion(propName, otherPropName);
    }

    public IQueryCriterion sizeEq(String propName, int size) {
        return new SizeEqCriterion(propName, size);
    }

    public IQueryCriterion sizeNotEq(String propName, int size) {
        return new SizeNotEqCriterion(propName, size);
    }

    public IQueryCriterion sizeGt(String propName, int size) {
        return new SizeGtCriterion(propName, size);
    }

    public IQueryCriterion sizeGe(String propName, int size) {
        return new SizeGeCriterion(propName, size);
    }

    public IQueryCriterion sizeLt(String propName, int size) {
        return new SizeLtCriterion(propName, size);
    }

    public IQueryCriterion sizeLe(String propName, int size) {
        return new SizeLeCriterion(propName, size);
    }

    public IQueryCriterion containsText(String propName, String value) {
        return new ContainsTextCriterion(propName, value);
    }

    public IQueryCriterion startsWithText(String propName, String value) {
        return new StartsWithTextCriterion(propName, value);
    }

    public IQueryCriterion in(String propName, Collection<?> value) {
        return new InCriterion(propName, value);
    }

    public IQueryCriterion in(String propName, Object[] value) {
        return new InCriterion(propName, value);
    }

    public IQueryCriterion notIn(String propName, Collection<?> value) {
        return new NotInCriterion(propName, value);
    }

    public IQueryCriterion notIn(String propName, Object[] value) {
        return new NotInCriterion(propName, value);
    }

    public IQueryCriterion between(String propName, Comparable<?> from, Comparable<?> to) {
        return new BetweenCriterion(propName, from, to);
    }

    public IQueryCriterion isNull(String propName) {
        return new IsNullCriterion(propName);
    }

    public IQueryCriterion notNull(String propName) {
        return new NotNullCriterion(propName);
    }

    public IQueryCriterion isEmpty(String propName) {
        return new IsEmptyCriterion(propName);
    }

    public IQueryCriterion notEmpty(String propName) {
        return new NotEmptyCriterion(propName);
    }

    public IQueryCriterion isTrue(String propName) {
        return eq(propName, true);
    }

    public IQueryCriterion isFalse(String propName) {
        return eq(propName, false);
    }

    public IQueryCriterion isBlank(String propName) {
        return or(isNull(propName), eq(propName, ""));
    }

    public IQueryCriterion notBlank(String propName) {
        return and(notNull(propName), notEq(propName, ""));
    }

    public IQueryCriterion not(IQueryCriterion criterion) {
        return new NotCriterion(criterion);
    }

    public IQueryCriterion and(IQueryCriterion... criterions) {
        return new AndCriterion(criterions);
    }

    public IQueryCriterion or(IQueryCriterion... criterions) {
        return new OrCriterion(criterions);
    }

	public IQueryCriterion empty() {
		return new EmptyCriterion();
	}

}
