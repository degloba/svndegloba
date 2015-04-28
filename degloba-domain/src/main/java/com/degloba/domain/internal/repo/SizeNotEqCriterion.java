package com.degloba.domain.internal.repo;

/**
 * 判断某个集合属性的记录数不等于指定值的查询条件
 * @author degloba
 */
public class SizeNotEqCriterion extends SizeCompareCriterion {

    /**
     * 创建查询条件
     * @param propName Property name
     * @param value 属性值
     */
    public SizeNotEqCriterion(String propName, int value) {
        super(propName, value);
        setOperator(" != ");
    }
}
