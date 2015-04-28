package com.degloba.domain.internal.repo;

/**
 * 代表一个属性等于另一个属性的查询条件
 * @author degloba
 */
public class EqPropCriterion extends PropertyCompareCriterion {

    /**
     * 创建查询条件
     * @param propName Property name
     * @param otherPropName Another attribute name
     */
    public EqPropCriterion(String propName, String otherPropName) {
        super(propName, otherPropName);
        setOperator(" = ");
    }
}
