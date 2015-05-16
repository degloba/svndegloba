package com.degloba.organisation.domain.demo;

import javax.persistence.Entity;


  @Entity
  public class Concrete1 extends Base1 {
    private String concrete1Str;

    public String getConcrete1Str() {
      return concrete1Str;
    }

    public void setConcrete1Str(String concrete1Str) {
      this.concrete1Str = concrete1Str;
    }
    
    public Concrete1 getConcrete1(Concrete1 concrete1) {
        return getRepository().get(Concrete1.class, concrete1.getId());
    }

    
  }
