package com.degloba.boundedContext.domain;

import javax.jdo.annotations.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.mongodb.core.mapping.Document;

@Entity  
@Table(name="persons") 
//@Document(collection = "persons")
public class Person {  
  @Id   
  @GeneratedValue(strategy=GenerationType.AUTO)  
  @Column(name="_id") // comment this if you use alternative  
  private String id;  // comment this if you use alternative  
  // alternative to string mongo id, but then remove name '_id'  
  // private Long id;  
   
  private String firstName = null;  
  private String lastName = null;  
  private int level = 0;  
   
  // getters and setters  
  public String getId() {  
    return id;  
  }  
  public void setId(String id) {  
    this.id = id;  
  }  
  public String getFirstName() {  
    return firstName;  
  }  
  public void setFirstName(String firstName) {  
    this.firstName = firstName;  
  }  
  public String getLastName() {  
    return lastName;  
  }  
  public void setLastName(String lastName) {  
    this.lastName = lastName;  
  }  
  public int getLevel() {  
    return level;  
  }  
  public void setLevel(int level) {  
    this.level = level;  
  }  
   
  // to string  
  @Override  
  public String toString() {  
    return "Person [id=" + id + ", firstName=" + firstName + ", lastName="  
    + lastName + ", level=" + level + "]";  
  }  
   
   
}  