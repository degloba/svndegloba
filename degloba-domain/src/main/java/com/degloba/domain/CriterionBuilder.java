package com.degloba.domain;

import java.util.Collection;

/**
 * A utility class, as a variety of query conditions factory
 *
 * @author degloba 
 *
 */
public interface CriterionBuilder {

    QueryCriterion eq(String propName, Object value);

    QueryCriterion notEq(String propName, Object value);

    QueryCriterion ge(String propName, Comparable<?> value);

    QueryCriterion gt(String propName, Comparable<?> value);

    QueryCriterion le(String propName, Comparable<?> value);

    QueryCriterion lt(String propName, Comparable<?> value);

    QueryCriterion eqProp(String propName, String otherPropName);

    QueryCriterion notEqProp(String propName, String otherPropName);

    QueryCriterion gtProp(String propName, String otherPropName);

    QueryCriterion geProp(String propName, String otherPropName);

    QueryCriterion ltProp(String propName, String otherPropName);

    QueryCriterion leProp(String propName, String otherPropName);

    QueryCriterion sizeEq(String propName, int size);

    QueryCriterion sizeNotEq(String propName, int size);

    QueryCriterion sizeGt(String propName, int size);

    QueryCriterion sizeGe(String propName, int size);

    QueryCriterion sizeLt(String propName, int size);

    QueryCriterion sizeLe(String propName, int size);

    QueryCriterion containsText(String propName, String value);

    QueryCriterion startsWithText(String propName, String value);

    QueryCriterion in(String propName, Collection<?> value);

    QueryCriterion in(String propName, Object[] value);

    QueryCriterion notIn(String propName, Collection<?> value);

    QueryCriterion notIn(String propName, Object[] value);

    QueryCriterion between(String propName, Comparable<?> from, Comparable<?> to);

    QueryCriterion isNull(String propName);

    QueryCriterion notNull(String propName);

    QueryCriterion isEmpty(String propName);

    QueryCriterion notEmpty(String propName);

    QueryCriterion not(QueryCriterion criterion);

    QueryCriterion and(QueryCriterion... criterions);

    QueryCriterion or(QueryCriterion... criterions);

    QueryCriterion isTrue(String propName);

    QueryCriterion isFalse(String propName);

    QueryCriterion isBlank(String propName);

    QueryCriterion notBlank(String propName);

	QueryCriterion empty();

}
