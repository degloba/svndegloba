package com.degloba.domain.internal.repo;

/**
 * 代表一个属性大于另一个属性的查询条件
 * @author degloba
 */
public class GtPropCriterion extends PropertyCompareCriterion {

    /**
     * 创建查询条件
     * @param propName Property name
     * @param otherPropName Another attribute name
     */
    public GtPropCriterion(String propName, String otherPropName) {
        super(propName, otherPropName);
        setOperator(" > ");
    }
}
