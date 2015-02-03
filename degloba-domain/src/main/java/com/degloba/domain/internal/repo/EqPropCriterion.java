package com.degloba.domain.internal.repo;

/**
 * 代表一个属性等于另一个属性的查询条件
 * @author yyang
 */
public class EqPropCriterion extends PropertyCompareCriterion {

    /**
     * 创建查询条件
     * @param propName 属性名
     * @param otherPropName 另一个属性名
     */
    public EqPropCriterion(String propName, String otherPropName) {
        super(propName, otherPropName);
        setOperator(" = ");
    }
}
