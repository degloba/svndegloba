package com.degloba.utils;

/**
 * Object serialization is the Java objects serialized as a string, or vice versa, from a string deserialized object.
 */
public interface ObjectSerializer {

    /**
     * Serialize an object to a string
     * @param anObject The object to be serialized
     * @return Serialized form of an object
     */
    String serialize(Object anObject);

    /**
     * The string deserialize objects
     * @param serializedString String objects serialized form
     * @param objectClass Class Object
     * @param <T> On the type of object
     * @return An object instance
     */
    <T> T deserialize(String serializedString, Class<T> objectClass);
}
