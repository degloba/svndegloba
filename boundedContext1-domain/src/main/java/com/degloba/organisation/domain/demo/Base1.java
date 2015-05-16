package com.degloba.organisation.domain.demo;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.degloba.domain.BaseAggregateRoot;
import com.google.appengine.api.datastore.Key;


  @Entity
  @MappedSuperclass
  public abstract class Base1 extends BaseAggregateRoot {
 /*   @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Key id;*/

    private String base1Str;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Concrete3 concrete3;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Concrete4> concrete4;

  /*  public Key getId() {
      return id;
    }

    public void setId(Key id) {
      this.id = id;
    }*/

    public String getBase1Str() {
      return base1Str;
    }

    public void setBase1Str(String base1Str) {
      this.base1Str = base1Str;
    }

    public Concrete3 getConcrete3() {
      return concrete3;
    }

    public void setConcrete3(Concrete3 concrete3) {
      this.concrete3 = concrete3;
    }

    public List<Concrete4> getConcrete4() {
      return concrete4;
    }

    public void setConcrete4(List<Concrete4> concrete4) {
      this.concrete4 = concrete4;
    }
  }
