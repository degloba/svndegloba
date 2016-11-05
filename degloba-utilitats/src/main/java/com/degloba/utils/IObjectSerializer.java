package com.degloba.utils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Object serialization is the Java objects serialized as a string, or vice versa, from a string deserialized object.
 */
public interface IObjectSerializer {

    /**
     * Serialize an object to a string
     * @param anObject The object to be serialized
     * @return Serialized form of an object
     */
    String serialize(Serializable anObject) throws IOException;

    /**
     * The string deserialize objects
     * @param serializedString String objects serialized form
     * @param objectClass Class Object
     * @param <T> On the type of object
     * @return An object instance
     */
    <T> T deserialize(String serializedString, Class<T> objectClass);

	
}
