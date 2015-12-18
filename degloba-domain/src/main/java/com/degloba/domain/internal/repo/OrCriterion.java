package com.degloba.domain.internal.repo;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.degloba.domain.NamedParameters;
import com.degloba.domain.QueryCriterion;
import com.degloba.utils.Assert;


/**
 * On behalf of two or more of the query results query criteria OR operation
 */
public class OrCriterion extends AbstractCriterion {

    private final List<QueryCriterion> criterions;

    /**
     * Create OR query condition based on multiple search criteria. 
     * Creation process removes the null or EmptyCriterion query criteria. In case
     * The remaining two less than the query, an exception is thrown.
     * @param criterions To be used to perform an OR operation of the query
     */
    public OrCriterion(QueryCriterion... criterions) {
        Assert.notNull(criterions, "Criterions to \"OR\" is null!");
        this.criterions = removeNullOrEmptyCriterion(criterions);
        Assert.isTrue(criterions.length > 1, "At least two query criterions required!");
    }

    /**
     * Get the query to be used to perform an OR operation
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
		return "(" + StringUtils.join(subCriterionsStr, " or ") + ")";
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
        if (!(other instanceof OrCriterion)) {
            return false;
        }
        OrCriterion that = (OrCriterion) other;
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
