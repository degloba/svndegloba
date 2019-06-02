package com.degloba.persistence.rdbms.jpa.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.degloba.persistence.rdbms.jpa.NamedParameters;
import com.degloba.persistence.rdbms.jpa.QueryCriterion;
import com.degloba.utils.Assert;

/**
 * On behalf of two or more of the query results query criteria AND operation
 */
public class AndCriterion extends AbstractCriterion {

    private final List<QueryCriterion> criterions;

    /**
     * Crea i consulta condicions basades en diversos criteris de cerca. 
     * El procés de creació elimina els criteris de consulta nuls o EmptyCriterion. En el cas que
     * el resultat tingui menys de dos llança una Exception.
     * @param criterions S'utilitza per realitzar una operació AND de les condicions de la consulta
     */
    public AndCriterion(QueryCriterion... criterions) {
        Assert.notNull(criterions, "Criterions to \"AND\" is null!");
        this.criterions = removeNullOrEmptyCriterion(criterions);
        Assert.isTrue(criterions.length > 1, "At least two query criterions required!");
    }

    /**
     * Per obtenir la consulta per dur a terme l’operació AND
     * @return Llista que s'utilitzarà per realitzar una operació AND de les condicions de la consulta, 
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
     * Equivalencia
     * @param other S'utilitza per comprovar la equivalencia amb un altre objecte
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
