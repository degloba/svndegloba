package com.degloba.persistence.rdbms.jpa;


import com.degloba.persistence.rdbms.jpa.IEntityRepository;
import com.degloba.persistence.rdbms.jpa.NamedParameters;
import com.degloba.persistence.rdbms.jpa.QueryCriterion;
import com.degloba.domain.KeyValue;
import com.degloba.domain.OrderSettings;
import com.degloba.ioc.InstanceFactory;
import com.degloba.utils.Assert;

import java.util.*;

import org.apache.commons.lang3.StringUtils;


/**
 * Conditions queries. One of the four inquiries form DDDLib support. 
 * Specify the query criteria, sorting properties and for taking a subset of the results for a specific entity, such as via DSL.
 */
public class CriteriaQuery {

    private final IEntityRepository repository;
    private final CriterionBuilder criterionBuilder = InstanceFactory.getInstance(CriterionBuilder.class);
    private final Class<? extends BaseAggregateRoot> entityClass;
    private int firstResult;
    private int maxResults;
    private QueryCriterion criterion = criterionBuilder.empty();
    private final OrderSettings orderSettings = new OrderSettings();

    public CriteriaQuery(IEntityRepository repository, Class<? extends BaseAggregateRoot> entityClass) {
        Assert.notNull(repository);
        Assert.notNull(entityClass);
        this.repository = repository;
        this.entityClass = entityClass;
    }

    /**
     * Get the query root entity class
     *
     * @return Class root entity queries
     */
    public Class<? extends BaseAggregateRoot> getEntityClass() {
        return entityClass;
    }

    /**
     * For centralized choose from a large part of the query result, the data set starting position (0 represents the first record)
     *
     * @return A number that represents a large result set from the first few records began to select a subset of
     */
    public int getFirstResult() {
        return firstResult;
    }

    /**
     * For centralized choose from a large part of the query result, the initial set of data sets to obtain the position (0 represents the first record)
     *
     * @param firstResult A number that represents a large result set from the first few records began to select a subset of
     * @return The current query object
     */
    public CriteriaQuery setFirstResult(int firstResult) {
        this.firstResult = firstResult;
        return this;
    }

    /**
     * For centralized choose from a large part of the query result, this query returns the maximum number of records
     *
     * @return A number that represents the number of records selected from a large data set up to
     */
    public int getMaxResults() {
        return maxResults;
    }

    /**
     * For centralized choose from a large part of the query result, this query returns the maximum number of records
     *
     * @param maxResults A number that represents the number of records selected from a large data set up to
     * @return The current query object
     */
    public CriteriaQuery setMaxResults(int maxResults) {
        this.maxResults = maxResults;
        return this;
    }

    /**
     * Get the query
     *
     * @return The query specifies the query conditions
     */
    public QueryCriterion getQueryCriterion() {
        return criterion;
    }

    /**
     * Get sort options
     *
     * @return The query specifies sorting options
     */
    public OrderSettings getOrderSettings() {
        return orderSettings;
    }

    /**
     * Get JPQL query string
     *
     * @return JPQL query string corresponding to the query
     */
    public String getQueryString() {
        //String result = String.format("select distinct(%s) from %s as %s ",
        String result = String.format("select distinct %s from %s %s ",   // suporta DataNucleus
        		QueryCriterion.ROOT_ALIAS, entityClass.getName(), QueryCriterion.ROOT_ALIAS);
        if (StringUtils.isNotEmpty(criterion.toQueryString())) {
            result += " where " + criterion.toQueryString();
        }
        result += getOrderClause();
        return result;
    }

    private String getOrderClause() {
        List<KeyValue<String, Boolean>> orderBy = orderSettings.getOrderBy();
        if (orderBy.isEmpty()) {
            return "";
        }
        List<String> elements = new ArrayList<String>();
        for (KeyValue<String, Boolean> each : orderBy) {
            elements.add(QueryCriterion.ROOT_ALIAS + "." + each.getKey() + (each.getValue().booleanValue() ? " asc" : " desc"));
        }
        return " order by " + StringUtils.join(elements, ", ");
    }

    /**
     * Get query parameters
     *
     * @return Query parameters
     */
    public NamedParameters getParameters() {
        return criterion.getParameters();
    }

    /**
     * Adding an "attribute name = attribute value" of the query
     *
     * @param propName Property name
     * @param value Property Value
     * @return The current query object
     */
    public CriteriaQuery eq(String propName, Object value) {
        criterion = criterion.and(criterionBuilder.eq(propName, value));
        return this;
    }

    /**
     * Adding an "attribute name! = Attribute value" of the query
     *
     * @param propName Property name
     * @param value Property Value
     * @return The current query object
     */
    public CriteriaQuery notEq(String propName, Object value) {
        criterion = criterion.and(criterionBuilder.notEq(propName, value));
        return this;
    }

    /**
     * Adding an "attribute name is greater than the property value," the query conditions
     *
     * @param propName Property name
     * @param value Property Value
     * @return The current query object
     */
    public CriteriaQuery gt(String propName, Comparable<?> value) {
        criterion = criterion.and(criterionBuilder.gt(propName, value));
        return this;
    }

    /**
     * The query to add a "greater than or equal to the attribute name attribute value"
     *
     * @param propName Property name
     * @param value Property Value
     * @return The current query object
     */
    public CriteriaQuery ge(String propName, Comparable<?> value) {
        criterion = criterion.and(criterionBuilder.ge(propName, value));
        return this;
    }

    /**
     * Adding an "attribute name is less than the property value," the query conditions
     * @param propName Property name
     * @param value Property Value
     * @return The current query object
     */
    public CriteriaQuery lt(String propName, Comparable<?> value) {
        criterion = criterion.and(criterionBuilder.lt(propName, value));
        return this;
    }

    /**
     * Adding an "attribute name attribute value is less than or equal to" the query conditions
     * @param propName Property name
     * @param value Property Value
     * @return The current query object
     */
    public CriteriaQuery le(String propName, Comparable<?> value) {
        criterion = criterion.and(criterionBuilder.le(propName, value));
        return this;
    }

    /**
     * Adding an "attribute an equal attribute 2" query criteria
     *
     * @param propName Property name
     * @param otherProp Another attribute name
     * @return The current query object
     */
    public CriteriaQuery eqProp(String propName, String otherProp) {
        criterion = criterion.and(criterionBuilder.eqProp(propName, otherProp));
        return this;
    }

    /**
     * Adding an "attribute is not equal to a property 2" query criteria
     *
     * @param propName Property name
     * @param otherProp Another attribute name
     * @return The current query object
     */
    public CriteriaQuery notEqProp(String propName, String otherProp) {
        criterion = criterion.and(criterionBuilder.notEqProp(propName, otherProp));
        return this;
    }

    /**
     * Adding an "attribute is greater than attribute 2" query condition
     *
     * @param propName Property name
     * @param otherProp Another attribute name
     * @return The current query object
     */
    public CriteriaQuery gtProp(String propName, String otherProp) {
        criterion = criterion.and(criterionBuilder.gtProp(propName, otherProp));
        return this;
    }

    /**
     * 添加一个“属性1 大于或等于 属性2”的查询条件
     *
     * @param propName Property name
     * @param otherProp Another attribute name
     * @return The current query object
     */
    public CriteriaQuery geProp(String propName, String otherProp) {
        criterion = criterion.and(criterionBuilder.geProp(propName, otherProp));
        return this;
    }

    /**
     * Adding an "attribute a property is less than 2" of the query
     * @param propName Property name
     * @param otherProp Another attribute name
     * @return The current query object
     */
    public CriteriaQuery ltProp(String propName, String otherProp) {
        criterion = criterion.and(criterionBuilder.ltProp(propName, otherProp));
        return this;
    }

    /**
     * Adding a "less than or equal to attribute an attribute 2" query condition
     * @param propName Property name
     * @param otherProp Another attribute name
     * @return The current query object
     */
    public CriteriaQuery leProp(String propName, String otherProp) {
        criterion = criterion.and(criterionBuilder.leProp(propName, otherProp));
        return this;
    }

    /**
     * Add a "result set property is equal to the number of size" of the query. For example, there is a type of object Order List & lt; OrderItem & gt; the
     * Collection property items, we want to query the order five kinds of items ordered, the propName for the items, size 5.
     *
     * @param propName Property name，Must be a set of attributes (x-to-many or ElementCollection)
     * @param size The results set the number of attributes
     * @return The current query object
     */
    public CriteriaQuery sizeEq(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeEq(propName, size));
        return this;
    }

    /**
     * Adding a "The results set the number of attributes is not equal size" of the query. For example, there is a type of object Order List & lt; OrderItem & gt; the
     * Collection property items, we want to query the order was not ordered five kinds of goods, the propName for items, size 5.
     *
     * @param propName Property name，Must be a set of attributes (x-to-many or ElementCollection)
     * @param size The results set the number of attributes
     * @return The current query object
     */
    public CriteriaQuery sizeNotEq(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeNotEq(propName, size));
        return this;
    }

    /**
     * Adding a "The results set the number of attributes is greater than the size" of the query. For example, there is a type of object Order List & lt; OrderItem & gt; the
     * Collection property items, we want to query more than five kinds of ordered goods orders, the propName for items, size 5.
     *
     * @param propName Property name，Must be a set of attributes (x-to-many or ElementCollection)
     * @param size The results set the number of attributes
     * @return The current query object
     */
    public CriteriaQuery sizeGt(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeGt(propName, size));
        return this;
    }

    /**
     * Adding a "The results set the number of attributes is greater than or equal to size" of the query. For example, there is a type of object Order List & lt; OrderItem & gt; the
     * Collection property items, we want to query ordered five kinds of orders or five or more items, then propName for the items, size 5.
     *
     * @param propName Property name，Must be a set of attributes (x-to-many or ElementCollection)
     * @param size The results set the number of attributes
     * @return The current query object
     */
    public CriteriaQuery sizeGe(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeGe(propName, size));
        return this;
    }

    /**
     * Adding a "The results set the number of attributes is less than size" of the query. For example, there is a type of object Order List & lt; OrderItem & gt; the
     * Collection property items, we want to query ordered goods orders less than five, the propName for items, size 5.
     *
     * @param propName Property name，Must be a set of attributes (x-to-many or ElementCollection)
     * @param size The results set the number of attributes
     * @return The current query object
     */
    public CriteriaQuery sizeLt(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeLt(propName, size));
        return this;
    }

    /**
     * Adding a "The results set the number of attributes is less than or equal to size" of the query. For example, there is a type of object Order List & lt; OrderItem & gt; the
     * Collection property items, we want to query ordered five kinds of orders or five kinds of the following items, then propName for items, size 5.
     *
     * @param propName Property name，Must be a set of attributes (x-to-many or ElementCollection)
     * @param size The results set the number of attributes
     * @return The current query object
     */
    public CriteriaQuery sizeLe(String propName, int size) {
        criterion = criterion.and(criterionBuilder.sizeLe(propName, size));
        return this;
    }

    /**
     * Adding a query condition "attribute contains the specified text," the
     *
     * @param propName Property name，The property must be a string type
     * @param value Text content
     * @return The current query object
     */
    public CriteriaQuery containsText(String propName, String value) {
        criterion = criterion.and(criterionBuilder.containsText(propName, value));
        return this;
    }

    /**
     * Add a "property specifies the text beginning with" query criteria
     *
     * @param propName Property name，The property must be a string type
     * @param value Text content
     * @return The current query object
     */
    public CriteriaQuery startsWithText(String propName, String value) {
        criterion = criterion.and(criterionBuilder.startsWithText(propName, value));
        return this;
    }

    /**
     * The query to add a "property values contained in the specified collection," the
     *
     * @param propName Property name
     * @param value A collection of property values that satisfy the query conditions must be included in the collection within
     * @return The current query object
     */
    public CriteriaQuery in(String propName, Collection<? extends Object> value) {
        criterion = criterion.and(criterionBuilder.in(propName, value));
        return this;
    }

    /**
     * The query to add a "property values contained in the specified array" of
     *
     * @param propName Property name
     * @param value An array of property values that satisfy the query conditions must be included in the array within
     * @return The current query object
     */
    public CriteriaQuery in(String propName, Object[] value) {
        criterion = criterion.and(criterionBuilder.in(propName, value));
        return this;
    }

    /**
     * The query to add a "property value is not included in the specified collections"
     *
     * @param propName Property name
     * @param value A collection of property values that satisfy the query conditions must not be included in the collection within
     * @return The current query object
     */
    public CriteriaQuery notIn(String propName, Collection<? extends Object> value) {
        criterion = criterion.and(criterionBuilder.notIn(propName, value));
        return this;
    }

    /**
     * Adding a query condition "attribute value is not included in the specified array" of
     *
     * @param propName Property name
     * @param value An array of property values match the query must not be included in the array within
     * @return The current query object
     */
    public CriteriaQuery notIn(String propName, Object[] value) {
        criterion = criterion.and(criterionBuilder.notIn(propName, value));
        return this;
    }

    /**
     * Add a "property value between two values (including left and right borders)" query conditions
     *
     * @param propName Property name
     * @param from The first boundary value
     * @param to The second boundary value
     * @param <E> Type is the value of comparison, that is compatible with the type of attribute
     * @return The current query object
     */
    public <E> CriteriaQuery between(String propName, Comparable<E> from, Comparable<E> to) {
        criterion = criterion.and(criterionBuilder.between(propName, from, to));
        return this;
    }

    /**
     * Adding an "attribute value is Null" query criteria
     *
     * @param propName Property name
     * @return The current query object
     */
    public CriteriaQuery isNull(String propName) {
        criterion = criterion.and(criterionBuilder.isNull(propName));
        return this;
    }

    /**
     * Adding an "attribute value is not Null" query criteria
     *
     * @param propName Property name
     * @return The current query object
     */
    public CriteriaQuery notNull(String propName) {
        criterion = criterion.and(criterionBuilder.notNull(propName));
        return this;
    }

    /**
     * Adding an "empty set type attribute value" of the query
     *
     * @param propName Property name，Must be a set of attributes (x-to-many or ElementCollection)
     * @return The current query object
     */
    public CriteriaQuery isEmpty(String propName) {
        criterion = criterion.and(criterionBuilder.isEmpty(propName));
        return this;
    }

    /**
     * Add a "set type attribute value is not empty," the query conditions
     *
     * @param propName Property name，Must be a set of attributes (x-to-many or ElementCollection)
     * @return The current query object
     */
    public CriteriaQuery notEmpty(String propName) {
        criterion = criterion.and(criterionBuilder.notEmpty(propName));
        return this;
    }

    /**
     * Adding an "attribute value true" query criteria
     *
     * @param propName Property name
     * @return The current query object
     */
    public CriteriaQuery isTrue(String propName) {
        criterion = criterion.and(criterionBuilder.isTrue(propName));
        return this;
    }

    /**
     * Adding an "attribute value true" query criteria
     *
     * @param propName Property name
     * @return The current query object
     */
    public CriteriaQuery isFalse(String propName) {
        criterion = criterion.and(criterionBuilder.isFalse(propName));
        return this;
    }

    /**
     * Adding a "property value is blank, that Null or empty string" query criteria
     *
     * @param propName Property name，Must be a string type attribute
     * @return The current query object
     */
    public CriteriaQuery isBlank(String propName) {
        criterion = criterion.and(criterionBuilder.isBlank(propName));
        return this;
    }

    /**
     * Adding an "attribute value is not empty," the query conditions
     *
     * @param propName Property name，Must be a string type attribute
     * @return The current query object
     */
    public CriteriaQuery notBlank(String propName) {
        criterion = criterion.and(criterionBuilder.notBlank(propName));
        return this;
    }

    /**
     * Adding a "negation" of the query
     *
     * @param otherCriterion The original query conditions
     * @return The current query object
     */
    public CriteriaQuery not(QueryCriterion otherCriterion) {
        criterion = criterion.and(criterionBuilder.not(otherCriterion));
        return this;
    }

    /**
     * Adding an "and" query condition that several queries at the same time meet specified conditions
     *
     * @param queryCriterions Multiple basic query conditions
     * @return The current query object
     */
    public CriteriaQuery and(QueryCriterion... queryCriterions) {
        criterion = criterion.and(criterionBuilder.and(queryCriterions));
        return this;
    }

    /**
     * Add a "or" query criteria, one of the conditions specified in line with several queries
     *
     * @param queryCriterions Multiple basic query conditions
     * @return The current query object
     */
    public CriteriaQuery or(QueryCriterion... queryCriterions) {
        criterion = criterion.and(criterionBuilder.or(queryCriterions));
        return this;
    }

    /**
     * The result set in ascending order of the specified property
     *
     * @param propName To sort of Property name
     * @return The current query object
     */
    public CriteriaQuery asc(String propName) {
        orderSettings.asc(propName);
        return this;
    }

    /**
     * The result set to sort in descending order specified attribute
     *
     * @param propName To sort of Property name
     * @return The current query object
     */
    public CriteriaQuery desc(String propName) {
        orderSettings.desc(propName);
        return this;
    }

    /**
     * Return query results list.
     *
     * @param <T> Query results list element type
     * @return Query results.
     */
    public <T> List<T> list() {
        return repository.find(this);
     }

    /**
     * Returns single Query results.
     *
     * @param <T> Type of query results
     * @return Query results.
     */
    public <T> T singleResult() {
        return repository.getSingleResult(this);
    }

}
