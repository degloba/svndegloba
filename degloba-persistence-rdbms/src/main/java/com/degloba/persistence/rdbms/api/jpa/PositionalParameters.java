package com.degloba.persistence.rdbms.api.jpa;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Value or named query language localization parameter set of a query. JPA, Hibernate and SQL are all support positioning
 * Parameter (such as "? ... Where e.name =") and named parameters (such as "... where name =: name") forms. <br>
 * As far as possible in the form of named parameters, positional parameters are behind the form.
 */
public class PositionalParameters implements IQueryParameters {
    
    private Object[] params;
    
    /**
     * Crea un conjunt buit de paràmetres de consulta
     * @return Un conjunt de paràmetres de consulta basat en un array
     */
    public static PositionalParameters create() {
        return new PositionalParameters(new Object[]{});
    }
    
    /**
     * Crea un conjunt de paràmetres de consulta, omplert amb un array de valors de paràmetres
     * @param params Parameter value array
     * @return Senate based on parameters set array
     */
    public static PositionalParameters create(Object... params) {
        return new PositionalParameters(params);
    }
    
    /**
     * Crea un conjunt de paràmetres de consulta, omplert d'una llista de valors de paràmetres
     * @param params Llista del valors de paràmetres
     * @return Un array basat en tel conjunt de paràmetres
     */
    public static PositionalParameters create(List<Object> params) {
        return new PositionalParameters(params.toArray());
    }

    private PositionalParameters(Object[] params) {
        if (params == null) {
            this.params = new Object[]{};
        } else {
            this.params = Arrays.copyOf(params, params.length);
        }
    }

    /**
     * Recupera un array de valors de paràmetres
     * @return Array de paràmetres
     */
    public Object[] getParams() {
        return Arrays.copyOf(params, params.length);
    }

    /**
     * Get the object hash
     * @return El valor hash del objecte
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 43).append(params).toHashCode();
    }

    /**
     * Analitza la igualtat  parameters set object Equivalence. 
     * If and only if the argument is an array of two PositionalParameters contain the same, the two objects is equivalent.
     * @param other Another object
     * @return If the current object is equivalent to the other returns true, otherwise it returns false.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PositionalParameters)) {
            return false;
        }
        PositionalParameters that = (PositionalParameters) other;
        return new EqualsBuilder().append(this.getParams(), that.getParams()).isEquals();
    }

    /**
     * String parameter set obtained representation
     * @return The current string representation of the object
     */
    @Override
    public String toString() {
        return Arrays.toString(params);
    }
}    