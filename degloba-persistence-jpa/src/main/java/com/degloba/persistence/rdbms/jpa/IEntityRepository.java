package com.degloba.persistence.rdbms.jpa;

import java.io.Serializable;
import java.util.List;

//import com.degloba.domain.ExampleSettings;
import com.degloba.persistence.rdbms.jpa.NamedParameters;
import com.degloba.persistence.rdbms.jpa.QueryCriterion;


/**
 * Interfície Repositori. 
 * Accedir i consultar la base de dades per a diversos tipus d’entitat.
 */
public interface IEntityRepository {   
	
    /**
     * L’entitat (nova o modificada) s’ha desat al repositori.
     *
     * @param <T> Class entities Type
     * @param entity To store the Entity instance.
     * @return After persistent current Entity
     */
     <T extends BaseAggregateRoot> T save(T entity);
    

    /**
     * L’entitat s’eliminarà del repositori.
     * Si aquesta instància no existeix al repositori, llançarà una excepció EntityNotExistedException.
     *
     * @param entity To delete the Entity instance.
     */
    void remove(BaseAggregateRoot entity);

    /**
     * Determina si hi ha una instància d’entitat per id al repositori.
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param id Entity identification
     * @return If the Entity instance exists, returns true, otherwise false
     */
    <T extends BaseAggregateRoot> boolean exists(Class<T> clazz, Serializable id);

    /**
     * Obté l’entitat del tipus especificat, amb l’id especificat
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param id Entity identification
     * @return An Entity instance.
     */
    <T extends BaseAggregateRoot> T get(Class<T> clazz, Serializable id);

    /**
     * Carrega el tipus especificat, especificant l'id de l'entitat
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param id Entity identification
     * @return An Entity instance.
     */
    <T extends BaseAggregateRoot> T load(Class<T> clazz, Serializable id);

    /**
     * A partir del repositori de Getentity els paràmetres representen l’entitat sense modificar
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param entity To query the Entity
     * @return Parameter entity in the unmodified version Repository
     */
    <T extends BaseAggregateRoot> T getUnmodified(Class<T> clazz, T entity);
    
    /**
     * Entity specified type from the Get Repository according to Natural key
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @param keyValues Representatives named primary key business parameters. key primary key Property name, value as a primary key attribute values
     * @return An Entity instance.
     */
    <T extends BaseAggregateRoot> T getByBusinessKeys(Class<T> clazz, NamedParameters keyValues);

    /**
     * Cerca totes les entitats del tipus especificat
     *
     * @param <T> Entity Type
     * @param clazz Class entities
     * @return Eligible Entity collection
     */
    <T extends BaseAggregateRoot> List<T> findAll(Class<T> clazz);

    /**
     * Crea condicions per a una consulta
     *
     * @param entityClass Creative Entity class to query
     * @param <T> Class type entities
     * @return A conditional query
     */
    <T extends BaseAggregateRoot> CriteriaQuery createCriteriaQuery(Class<T> entityClass);

    /**
     * La consulta de la condició d'execució retorna la llista d'entitats elegibles
     *
     * @param criteriaQuery Conditions to execute a query
     * @param <T> Return result element type
     * @return Entity match the query returns a list of
     */
    <T> List<T> find(CriteriaQuery criteriaQuery);

    /**
     * Execution condition query returns qualified individual Entity
     *
     * @param criteriaQuery Conditions to execute a query
     * @param <T> Return result type to be performed
     * @return Individual results matching the query conditions
     */
    <T> T getSingleResult(CriteriaQuery criteriaQuery);

    /**
     * Create a query in line JPQL
     *
     * @param jpql JPQL statement
     * @return A JPQL inquiry
     */
    JpqlQuery createJpqlQuery(String jpql);

    /**
     * JPQL execute the query and return Eligible Entity List
     *
     * @param jpqlQuery JPQL query to be executed
     * @param <T> Return result element type
     * @return The results match the query list
     */
    <T> List<T> find(JpqlQuery jpqlQuery);

    /**
     * Operators perform JPQL query returns qualified individual Entity
     *
     * @param jpqlQuery JPQL query to be executed
     * @param <T> To return the result type
     * @return Individual results matching the query conditions
     */
    <T> T getSingleResult(JpqlQuery jpqlQuery);

    /**
     * Perform the update Repository operation.
     *
     * @param jpqlQuery JPQL to execute the query.
     * @return Number of updated or deleted entities
     */
    int executeUpdate(JpqlQuery jpqlQuery);

    /**
     * Create named queries
     *
     * @param queryName Named query's name
     * @return A named query
     */
    NamedQuery createNamedQuery(String queryName);

    /**
     * Execute named queries return Eligible Entity List
     *
     * @param namedQuery Named queries to be executed
     * @param <T> Return result element type
     * @return The results match the query returns a list of
     */
    <T> List<T> find(NamedQuery namedQuery);

    /**
     * Back to execute a named query and returns the qualified individual Entity
     *
     * @param namedQuery Named queries to be executed
     * @param <T> To return the result type
     * @return Individual results matching the query conditions
     */
    <T> T getSingleResult(NamedQuery namedQuery);

    /**
     * In line with the use of named queries Perform the update Repository operation.
     *
     * @param namedQuery Named to execute the query.
     * @return Number of updated or deleted entities
     */
    int executeUpdate(NamedQuery namedQuery);

    /**
     * Create a native SQL query
     *
     * @param sql SQL statements
     * @return A native SQL query
     */
    SqlQuery createSqlQuery(String sql);

    /**
     * Execute SQL queries and return Eligible Entity List
     *
     * @param sqlQuery SQL query to be executed.
     * @param <T> Return result element type
     * @return The results match the query returns a list of
     */
    <T> List<T> find(SqlQuery sqlQuery);

    /**
     * Execute SQL queries and return the qualified individual Entity
     *
     * @param sqlQuery SQL query to be executed.
     * @param <T> To return the result type
     * @return Individual results to match the query
     */
    <T> T getSingleResult(SqlQuery sqlQuery);

    /**
     * To use SQL query operators Perform the update Repository operation.
     *
     * @param sqlQuery SQL query to be executed.
     * @return Number of updated or deleted entities
     */
    int executeUpdate(SqlQuery sqlQuery);

    /**
     * Example query.
     *
     * @param <T> Target Entity Type query
     * @param <E> The sample of the type of query
     * @param example Sample Query
     * @param settings Query Settings
     * @return Examples and example of a similar type T
     */
    /*<T extends BaseAggregateRoot, E extends T> List<T> findByExample(E example, ExampleSettings<T> settings);*/

    /**
     * Find Entity based on the value of a single attribute
     *
     * @param <T> Class entities type to be queried
     * @param clazz To Class entities query
     * @param propertyName To attribute query
     * @param propertyValue Match Property Value
     * @return Clazz type, the value of property is equal to the set propertyName propertyValue of the Entity
     */
    <T extends BaseAggregateRoot> List<T> findByProperty(Class<T> clazz, String propertyName, Object propertyValue);

    /**
     * Find more properties based on the value Entity
     *
     * @param <T> Class entities type to be queried
     * @param clazz To check the Class entities to query
     * @param properties Named parameters, including key is Property name, value is to match Property Value.
     * @return Type clazz, multiple attributes are equal to a specified set of Property Value of Entity.
     */
    <T extends BaseAggregateRoot> List<T> findByProperties(Class<T> clazz, NamedParameters properties);
    
    /**
     * GetNamed query string query
     * @param queryName Named query's name
     * @return Named query string corresponding JPQL
     */
    String getQueryStringOfNamedQuery(String queryName);


    /**
     * The in-memory persistent object status instantly written to the database
     */
    void flush();

    /**
     * Entity of the current state of the database to update the latest data. 
     * Entity any have changed but persistent Property Value will be overwritten by the new value in the database.
     *
     * @param entity To refresh the Entity
     */
    void refresh(BaseAggregateRoot entity);

    /**
     * Empty persistent cache
     */
    void clear();


    <T extends BaseAggregateRoot>List<T> find(Class<T> entityClass, QueryCriterion criterion);


    <T extends BaseAggregateRoot>T getSingleResult(Class<T> entityClass, QueryCriterion criterion);



}
