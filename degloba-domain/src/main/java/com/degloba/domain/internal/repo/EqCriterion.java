package com.degloba.domain.internal.repo;

/**
 * 代表属性等于指定值的查询条件
 * @author degloba
 */
public class EqCriterion extends ValueCompareCriterion {

    public EqCriterion(String propName, Object value) {
        super(propName, value);
        setOperator(" = ");
    }
}
