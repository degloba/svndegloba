package com.degloba.domain.internal.repo;

import com.degloba.utils.Assert;

/**
 * Basic query, meaning a majority of query conditions except AND / OR / NOT query criteria, 
 * basically determine the value of a property meets certain conditions
 */
public abstract class BasicCriterion extends AbstractCriterion {

    private final String propName;

    public BasicCriterion(String propName) {
        Assert.notBlank(propName, "Property name is null or blank!");
        this.propName = propName;
    }

    /**
     * GetProperty name
     *
     * @return Property name
     */
    public String getPropName() {
        return propName;
    }

    /**
     * Get Property name prefixed with an alias
     *
     * @return Aliased prefix Property name
     */
    protected String getPropNameWithAlias() {
        return ROOT_ALIAS + "." + propName;
    }

    /**
     * Get parameter name
     *
     * @return Parameter name
     */
    protected String getParamName() {
        String result = ROOT_ALIAS + "_" + propName + hashCode();
        result = result.replace(".", "_");
        result = result.replace("-", "_");
        return result;
    }

    /**
     * Get a colon prefix Parameter name
     *
     * @return Prefixed with a colon Parameter name
     */
    protected String getParamNameWithColon() {
        return ":" + getParamName();
    }
}
