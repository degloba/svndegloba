package com.degloba.persistence.mongo;

//
import com.degloba.persistence.mongo.config.SpringMongoConfig;

// Entitats de domini de test

import com.degloba.persistence.test.domain.User;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.*;

/**
* Utilitza Spring-data MongoDB per accedir a la BD
* 
* @author degloba
*/
public class ProvesTest  {

	MongoOperations mongoOperation;
	
   @Before
   public void setUp() {
        
       
    // For XML
   	//ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
    
   	// For Annotation
	   AbstractApplicationContext  ctx = 
                new AnnotationConfigApplicationContext(SpringMongoConfig.class);
   	mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
   	ctx.close();
    
   
       }

   @Test
   public void testEq() {
		User user = new User("mkyong", "password123");
	    
	   	// save
	   	mongoOperation.save(user);
	    
	   	// now user object got the created id.
	   	System.out.println("1. user : " + user);
	    
	   	// query to search user
	   	Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));
	    
	   	// find the saved user again.
	   	User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
	   	System.out.println("2. find - savedUser : " + savedUser);
	    
	   	// update password
	   	mongoOperation.updateFirst(searchUserQuery, 
	                            Update.update("password", "new password"),User.class);
	    
	   	// find the updated user object
	   	User updatedUser = mongoOperation.findOne(searchUserQuery, User.class);
	    
	   	System.out.println("3. updatedUser : " + updatedUser);
	    
	   	// delete
	   	mongoOperation.remove(searchUserQuery, User.class);
	    
	   	// List, it should be empty now.
	   	List<User> listUser = mongoOperation.findAll(User.class);
	   	System.out.println("4. Number of user = " + listUser.size());
	  
   }

  
}