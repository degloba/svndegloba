/**********************************************************************
Copyright (c) 2012 Google Inc.

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

import java.util.List;
import java.util.Set;

import com.google.appengine.api.datastore.EntityNotFoundException;

// Entitats de domini de test
import com.degloba.persistence.test.domain.jpa.UnownedJPAOneToManyUniListSideA;
import com.degloba.persistence.test.domain.jpa.UnownedJPAOneToManyUniSetSideA;
import com.degloba.persistence.test.domain.jpa.UnownedJPAOneToManyUniSideB;

/**
 * Tests for 1-N unowned JPA relations.
 */
public class JPAUnownedOneToManyTest extends JPATestCase {

  public void testSetPersistUniNewBoth() throws EntityNotFoundException {
    // Persist A-B as unowned
    UnownedJPAOneToManyUniSetSideA a = new UnownedJPAOneToManyUniSetSideA();
    a.setName("Side A");
    UnownedJPAOneToManyUniSideB b = new UnownedJPAOneToManyUniSideB();
    b.setName("Side B");
    a.addB(b);

    em.persist(a);

    Long aId = a.getId();
    Long bId = b.getId();

    em.clear(); // Make sure we go to the datastore

    // Retrieve by id and check
    UnownedJPAOneToManyUniSetSideA a2 = em.find(UnownedJPAOneToManyUniSetSideA.class, aId);
    assertNotNull(a2);
    assertEquals("Side A", a2.getName());
    Set<UnownedJPAOneToManyUniSideB> others = a2.getBs();
    assertNotNull(others);
    assertEquals(1, others.size());
    UnownedJPAOneToManyUniSideB b2 = others.iterator().next();
    assertNotNull(b2);
    assertNotNull("Side B", b2.getName());
    assertEquals(bId, b2.getId());
  }

  public void testListPersistUniNewBoth() throws EntityNotFoundException {
    // Persist A-B as unowned
    UnownedJPAOneToManyUniListSideA a = new UnownedJPAOneToManyUniListSideA();
    a.setName("Side A");
    UnownedJPAOneToManyUniSideB b = new UnownedJPAOneToManyUniSideB();
    b.setName("Side B");
    a.addB(b);

    em.persist(a);

    Long aId = a.getId();
    Long bId = b.getId();

    em.clear(); // Make sure we go to the datastore

    // Retrieve by id and check
    UnownedJPAOneToManyUniListSideA a2 = em.find(UnownedJPAOneToManyUniListSideA.class, aId);
    assertNotNull(a2);
    assertEquals("Side A", a2.getName());
    List<UnownedJPAOneToManyUniSideB> others = a2.getBs();
    assertNotNull(others);
    assertEquals(1, others.size());
    UnownedJPAOneToManyUniSideB b2 = others.iterator().next();
    assertNotNull(b2);
    assertNotNull("Side B", b2.getName());
    assertEquals(bId, b2.getId());
  }
}