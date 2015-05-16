package com.degloba.organisation.domain.demo;

import com.google.appengine.api.datastore.Key;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

 
  @Entity
  public class Concrete3 {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Key id;

    private String str;

    public Key getId() {
      return id;
    }

    public String getStr() {
      return str;
    }

    public void setStr(String str) {
      this.str = str;
    }
  }
