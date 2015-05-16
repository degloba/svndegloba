package com.degloba.organisation.domain.demo;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

  @Entity
  @MappedSuperclass
  public abstract class Base2 extends Base1 {
    private String base2Str;

    public String getBase2Str() {
      return base2Str;
    }

    public void setBase2Str(String base2Str) {
      this.base2Str = base2Str;
    }
  }