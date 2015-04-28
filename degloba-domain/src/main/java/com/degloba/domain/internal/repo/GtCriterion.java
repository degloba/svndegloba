package com.degloba.domain.internal.repo;

/**
 * 代表属性大于指定值的查询条件
 * @author degloba
 */
public class GtCriterion extends ValueCompareCriterion {

    public GtCriterion(String propName, Comparable<?> value) {
        super(propName, value);
        setOperator(" > ");
    }
}
