package com.degloba.persistence.rdbms.api.jpa;

import java.util.Collection;

/**
 * A utility class, as a variety of query conditions factory
 */
public interface CriterionBuilder {

    IQueryCriterion eq(String propName, Object value);

    IQueryCriterion notEq(String propName, Object value);

    IQueryCriterion ge(String propName, Comparable<?> value);

    IQueryCriterion gt(String propName, Comparable<?> value);

    IQueryCriterion le(String propName, Comparable<?> value);

    IQueryCriterion lt(String propName, Comparable<?> value);

    IQueryCriterion eqProp(String propName, String otherPropName);

    IQueryCriterion notEqProp(String propName, String otherPropName);

    IQueryCriterion gtProp(String propName, String otherPropName);

    IQueryCriterion geProp(String propName, String otherPropName);

    IQueryCriterion ltProp(String propName, String otherPropName);

    IQueryCriterion leProp(String propName, String otherPropName);

    IQueryCriterion sizeEq(String propName, int size);

    IQueryCriterion sizeNotEq(String propName, int size);

    IQueryCriterion sizeGt(String propName, int size);

    IQueryCriterion sizeGe(String propName, int size);

    IQueryCriterion sizeLt(String propName, int size);

    IQueryCriterion sizeLe(String propName, int size);

    IQueryCriterion containsText(String propName, String value);

    IQueryCriterion startsWithText(String propName, String value);

    IQueryCriterion in(String propName, Collection<?> value);

    IQueryCriterion in(String propName, Object[] value);

    IQueryCriterion notIn(String propName, Collection<?> value);

    IQueryCriterion notIn(String propName, Object[] value);

    IQueryCriterion between(String propName, Comparable<?> from, Comparable<?> to);

    IQueryCriterion isNull(String propName);

    IQueryCriterion notNull(String propName);

    IQueryCriterion isEmpty(String propName);

    IQueryCriterion notEmpty(String propName);

    IQueryCriterion not(IQueryCriterion criterion);

    IQueryCriterion and(IQueryCriterion... criterions);

    IQueryCriterion or(IQueryCriterion... criterions);

    IQueryCriterion isTrue(String propName);

    IQueryCriterion isFalse(String propName);

    IQueryCriterion isBlank(String propName);

    IQueryCriterion notBlank(String propName);

	IQueryCriterion empty();

}
