package com.degloba.persistence.nosql.googleDatastore.api.lowlevel;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.ProjectionEntity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;

public class DatastoreQuery {

	private final Datastore datastore;

	  public DatastoreQuery(Datastore datastore) {
	    this.datastore = datastore;
	  }

	  /**
	   * Example of creating and running a GQL query.
	   */
	  // [TARGET gqlQueryBuilder(String)]
	  // [VARIABLE "my_kind"]
	  public QueryResults<?> newQuery(String kind) {
	    // [START newQuery]
	    String gqlQuery = "select * from " + kind;
	    Query<?> query = Query.newGqlQueryBuilder(gqlQuery).build();
	    QueryResults<?> results = datastore.run(query);
	    // Use results
	    // [END newQuery]
	    return results;
	  }

	  /**
	   * Example of creating and running a typed GQL query.
	   */
	  // [TARGET gqlQueryBuilder(ResultType, String)]
	  // [VARIABLE "my_kind"]
	  public QueryResults<Entity> newTypedQuery(String kind) {
	    // [START newTypedQuery]
	    String gqlQuery = "select * from " + kind;
	    Query<Entity> query = Query.newGqlQueryBuilder(Query.ResultType.ENTITY, gqlQuery).build();
	    QueryResults<Entity> results = datastore.run(query);
	    // Use results
	    // [END newTypedQuery]
	    return results;
	  }

	  /**
	   * Example of creating and running an entity query.
	   */
	  // [TARGET entityQueryBuilder()]
	  // [VARIABLE "my_kind"]
	  public QueryResults<Entity> newEntityQuery(String kind) {
	    // [START newEntityQuery]
	    Query<Entity> query = Query.newEntityQueryBuilder().setKind(kind).build();
	    QueryResults<Entity> results = datastore.run(query);
	    // Use results
	    // [END newEntityQuery]
	    return results;
	  }

	  /**
	   * Example of creating and running a key query.
	   */
	  // [TARGET keyQueryBuilder()]
	  // [VARIABLE "my_kind"]
	  public QueryResults<Key> newKeyQuery(String kind) {
	    // [START newKeyQuery]
	    Query<Key> query = Query.newKeyQueryBuilder().setKind(kind).build();
	    QueryResults<Key> results = datastore.run(query);
	    // Use results
	    // [END newKeyQuery]
	    return results;
	  }

	  /**
	   * Example of creating and running a projection entity query.
	   */
	  // [TARGET projectionEntityQueryBuilder()]
	  // [VARIABLE "my_kind"]
	  // [VARIABLE "my_property"]
	  public QueryResults<ProjectionEntity> newProjectionEntityQuery(String kind, String property) {
	    // [START newProjectionEntityQuery]
	    Query<ProjectionEntity> query = Query.newProjectionEntityQueryBuilder()
	        .setKind(kind)
	        .addProjection(property)
	        .build();
	    QueryResults<ProjectionEntity> results = datastore.run(query);
	    // Use results
	    // [END newProjectionEntityQuery]
	    return results;
	  }
	  
}
