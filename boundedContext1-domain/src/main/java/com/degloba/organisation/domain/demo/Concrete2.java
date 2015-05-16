package com.degloba.organisation.domain.demo;


import javax.persistence.Entity;


  @Entity
  public class Concrete2 extends Base2 {
    private String concrete2Str;

    public String getConcrete2Str() {
      return concrete2Str;
    }

    public void setConcrete2Str(String concrete2Str) {
      this.concrete2Str = concrete2Str;
    }
  }

