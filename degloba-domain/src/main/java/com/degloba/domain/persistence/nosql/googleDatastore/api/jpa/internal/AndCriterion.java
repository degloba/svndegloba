package com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.NamedParameters;
import com.degloba.domain.persistence.nosql.googleDatastore.api.jpa.QueryCriterion;
import com.degloba.utils.Assert;

/**
 * On behalf of two or more of the query results query criteria AND operation
 */
public class AndCriterion extends AbstractCriterion {

    private final List<QueryCriterion> criterions;

    /**
     * Create AND query conditions based on multiple search criteria. 
     * Creation process removes the null or EmptyCriterion query criteria. In case
     * The remaining two less than the query throws Exception.
     * @param criterions To be used to perform an AND operation of query conditions
     */
    public AndCriterion(QueryCriterion... criterions) {
        Assert.notNull(criterions, "Criterions to \"AND\" is null!");
        this.criterions = removeNullOrEmptyCriterion(criterions);
        Assert.isTrue(criterions.length > 1, "At least two query criterions required!");
    }

    /**
     * To get the query to perform the AND operation
     * @return To be used to perform an AND operation of query conditions, 
     * in addition to Null and EmptyCriterion types of elements.
     */
    public List<QueryCriterion> getCriterons() {
        return criterions;
    }

    @Override
	public String toQueryString() {
        List<String> subCriterionsStr = new ArrayList<String>();
        for (QueryCriterion each : getCriterons()) {
            subCriterionsStr.add(each.toQueryString());
        }
		return StringUtils.join(subCriterionsStr, " and ");
	}

	public NamedParameters getParameters() {
		NamedParameters result = NamedParameters.create();
        for (QueryCriterion each : getCriterons()) {
        	result.add(each.getParameters());
        }
		return result;
	}

	/**
     * Equivalence of judgment
     * @param other To be used to convict like another object
     * @return If the current object and other equivalent returns true, false otherwise
     */
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AndCriterion)) {
            return false;
        }
        AndCriterion that = (AndCriterion) other;
        return new EqualsBuilder()
                .append(this.getCriterons(), that.getCriterons())
                .isEquals();
    }

    /**
     * Calculate the hash value
     * @return The hash value of the object instance
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getCriterons()).toHashCode();
    }

}
