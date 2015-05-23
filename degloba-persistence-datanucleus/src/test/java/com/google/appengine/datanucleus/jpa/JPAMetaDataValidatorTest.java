/**********************************************************************
Copyright (c) 2009 Google Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
**********************************************************************/
package com.google.appengine.datanucleus.jpa;

import com.degloba.persistence.test.domain.jpa.IgnorableMappingsJPA.HasInitialSequenceValue;
import com.degloba.persistence.test.domain.jpa.IgnorableMappingsJPA.HasUniqueConstraint;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.EncodedPkOnNonPrimaryKeyField;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.EncodedPkOnNonStringPrimaryKeyField;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.HasLongPkWithStringAncestor;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.HasMultiplePkIdFields;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.HasMultiplePkNameFields;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.HasPkIdSortOnOneToMany;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.HasPkNameSortOnOneToMany;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.HasUnencodedStringPkWithStringAncestor;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.LongParent;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.ManyToMany1;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.ManyToMany2;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.MultipleAncestors;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.OneToManyParentWithRootOnlyLongBiChild;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.OneToManyParentWithRootOnlyLongUniChild;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.OneToManyParentWithRootOnlyStringBiChild;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.OneToManyParentWithRootOnlyStringUniChild;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.OneToOneParentWithRootOnlyLongBiChild;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.OneToOneParentWithRootOnlyLongUniChild;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.OneToOneParentWithRootOnlyStringBiChild;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.OneToOneParentWithRootOnlyStringUniChild;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.PkIdOnNonLongField;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.PkIdWithUnencodedStringPrimaryKey;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.PkMarkedAsAncestor;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.PkMarkedAsPkId;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.PkMarkedAsPkName;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.PkNameOnNonStringField;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.PkNameWithUnencodedStringPrimaryKey;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.SequenceOnEncodedStringPk;
import com.degloba.persistence.test.domain.jpa.IllegalMappingsJPA.SequenceOnKeyPk;

import javax.persistence.PersistenceException;

import org.datanucleus.metadata.InvalidMetaDataException;

/**
 * @author Max Ross <maxr@google.com>
 */
public class JPAMetaDataValidatorTest extends JPATestCase {

  public void testStringAncestorPlusNameOnlyPK() {
    HasUnencodedStringPkWithStringAncestor pojo = new HasUnencodedStringPkWithStringAncestor();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testStringAncestorPlusLongPK() {
    HasLongPkWithStringAncestor pojo = new HasLongPkWithStringAncestor();
    assertMetaDataException(pojo);
  }

  public void testMultiplePKNameFields() {
    HasMultiplePkNameFields pojo = new HasMultiplePkNameFields();
    assertMetaDataException(pojo);
  }

  public void testMultiplePKIdFields() {
    HasMultiplePkIdFields pojo = new HasMultiplePkIdFields();
    assertMetaDataException(pojo);
  }

  public void testMultipleAncestors() {
    MultipleAncestors pojo = new MultipleAncestors();
    assertMetaDataException(pojo);
  }

  public void testEncodedPkOnNonPrimaryKeyField() {
    EncodedPkOnNonPrimaryKeyField pojo = new EncodedPkOnNonPrimaryKeyField();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testEncodedPkOnNonStringPrimaryKeyField() {
    EncodedPkOnNonStringPrimaryKeyField pojo = new EncodedPkOnNonStringPrimaryKeyField();
    assertMetaDataException(pojo);
  }

  public void testPkNameOnNonStringField() {
    PkNameOnNonStringField pojo = new PkNameOnNonStringField();
    assertMetaDataException(pojo);
  }

  public void testPkIdOnNonLongField() {
    PkIdOnNonLongField pojo = new PkIdOnNonLongField();
    assertMetaDataException(pojo);
  }

  public void testPkMarkedAsAncestor() {
    PkMarkedAsAncestor pojo = new PkMarkedAsAncestor();
    assertMetaDataException(pojo);
  }

  public void testPkMarkedAsPkId() {
    PkMarkedAsPkId pojo = new PkMarkedAsPkId();
    assertMetaDataException(pojo);
  }

  public void testPkMarkedAsPkName() {
    PkMarkedAsPkName pojo = new PkMarkedAsPkName();
    assertMetaDataException(pojo);
  }

  public void testPkIdWithUnencodedStringPrimaryKey() {
    PkIdWithUnencodedStringPrimaryKey pojo = new PkIdWithUnencodedStringPrimaryKey();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testPkNameWithUnencodedStringPrimaryKey() {
    PkNameWithUnencodedStringPrimaryKey pojo = new PkNameWithUnencodedStringPrimaryKey();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testLongPkWithUnidirectionalOneToManyChild() {
    OneToManyParentWithRootOnlyLongUniChild pojo = new OneToManyParentWithRootOnlyLongUniChild();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testLongPkWithBidirectionalOneToManyChild() {
    OneToManyParentWithRootOnlyLongBiChild pojo = new OneToManyParentWithRootOnlyLongBiChild();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testStringPkWithUnidirectionalOneToManyChild() {
    OneToManyParentWithRootOnlyStringUniChild pojo = new OneToManyParentWithRootOnlyStringUniChild();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testStringPkWithBidirectionalOneToManyChild() {
    OneToManyParentWithRootOnlyStringBiChild pojo = new OneToManyParentWithRootOnlyStringBiChild();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testLongPkWithUnidirectionalOneToOneChild() {
    OneToOneParentWithRootOnlyLongUniChild pojo = new OneToOneParentWithRootOnlyLongUniChild();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testLongPkWithBidirectionalOneToOneChild() {
    OneToOneParentWithRootOnlyLongBiChild pojo = new OneToOneParentWithRootOnlyLongBiChild();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testStringPkWithUnidirectionalOneToOneChild() {
    OneToOneParentWithRootOnlyStringUniChild pojo = new OneToOneParentWithRootOnlyStringUniChild();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }

  public void testStringPkWithBidirectionalOneToOneChild() {
    OneToOneParentWithRootOnlyStringBiChild pojo = new OneToOneParentWithRootOnlyStringBiChild();
    pojo.id = "yar";
    assertMetaDataException(pojo);
  }
  public void testAncestorOfIllegalType_Long() {
    LongParent pojo = new LongParent();
    assertMetaDataException(pojo);
  }

  public void testUniqueConstraints() {
    assertMetaDataException(new HasUniqueConstraint());
  }

  public void testManyToMany() {
    assertMetaDataException(new ManyToMany1());
    assertMetaDataException(new ManyToMany2());
  }

  public void testInitialSequenceValue() {
    assertMetaDataException(new HasInitialSequenceValue());
  }

  private void assertMetaDataException(Object pojo) {
    beginTxn();
    try {
      em.persist(pojo);
      commitTxn();
      fail("expected exception");
    } catch (PersistenceException e) {
      // good
      assertTrue(e.getCause().getClass().getName(),
                 e.getCause() instanceof InvalidMetaDataException);
      rollbackTxn();
    }
  }

  public void testEncodedStringPkWithSequence() {
    assertMetaDataException(new SequenceOnEncodedStringPk());
  }

  public void testKeyPkWithSequence() {
    assertMetaDataException(new SequenceOnKeyPk());
  }

  // Only applicable to earlier storage versions
  /*public void testHasMultipleRelationshipFieldsOfSameType() {
    assertMetaDataException(new Has2CollectionsOfSameType());
    assertMetaDataException(new Has2OneToOnesOfSameType());
    assertMetaDataException(new HasOneToOneAndOneToManyOfSameType());
    assertMetaDataException(new Has2CollectionsOfSameTypeChild());
    assertMetaDataException(new Has2CollectionsOfAssignableType());
    assertMetaDataException(new Has2CollectionsOfAssignableTypeSub());
  }*/

  public void testHasKeySubComponentSortOnOneToMany() {
    assertMetaDataException(new HasPkIdSortOnOneToMany());
    assertMetaDataException(new HasPkNameSortOnOneToMany());
  }

}
