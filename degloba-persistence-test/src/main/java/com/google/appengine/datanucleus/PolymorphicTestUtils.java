package com.google.appengine.datanucleus;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import static junit.framework.Assert.assertEquals;

public class PolymorphicTestUtils {

  public static void assertKeyParentEquals(String parentKey, Entity childEntity, Key childKey) {
    assertEquals(KeyFactory.stringToKey(parentKey), childEntity.getKey().getParent());
    assertEquals(KeyFactory.stringToKey(parentKey), childKey.getParent());
  }

  public static void assertKeyParentEquals(Class<?> parentClass, long parentId, Entity childEntity, Key childKey) {
    Key parentKey = KeyFactory.createKey(getEntityKind(parentClass), parentId);
    assertEquals(parentKey, childEntity.getKey().getParent());
    assertEquals(parentKey, childKey.getParent());
  }

  public static void assertKeyParentEquals(String parentKey, Entity childEntity, String childKey) {
    TestUtils.assertKeyParentEquals(parentKey, childEntity, childKey);
  }

  public static void assertKeyParentEquals(Class<?> parentClass, long parentId, Entity childEntity, String childKey) {
    Key parentKey = KeyFactory.createKey(getEntityKind(parentClass), parentId);
    assertEquals(parentKey, childEntity.getKey().getParent());
    assertEquals(parentKey, KeyFactory.stringToKey(childKey).getParent());
  }

  public static void assertKeyParentEquals(Class<?> parentClass, String parentId,
      Entity childEntity, String childKey) {
    Key parentKey = KeyFactory.createKey(getEntityKind(parentClass), parentId);
    assertEquals(parentKey, childEntity.getKey().getParent());
    assertEquals(parentKey, KeyFactory.stringToKey(childKey).getParent());
  }

  public static void assertKeyParentEquals(Class<?> parentClass, String parentId, Entity childEntity, Key childKey) {
    Key parentKey = KeyFactory.createKey(getEntityKind(parentClass), parentId);
    assertEquals(parentKey, childEntity.getKey().getParent());
    assertEquals(parentKey, childKey.getParent());
  }

  public static String getEntityKind(Class<?> clazz) {
    return clazz.getName().substring(clazz.getPackage().getName().length() + 1);
  }

}
