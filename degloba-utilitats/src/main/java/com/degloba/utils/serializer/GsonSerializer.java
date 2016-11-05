package com.degloba.utils.serializer;

import com.google.gson.*;

import java.io.Serializable;

import com.degloba.utils.IObjectSerializer;

/*
 */
public class GsonSerializer implements IObjectSerializer {

    private Gson gson;

    public GsonSerializer() {
        this.gson = new GsonBuilder().create();
    }

    public GsonSerializer(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String serialize(Serializable anObject) {
        return gson.toJson(anObject);
    }

    @Override
    public <T> T deserialize(String serializedString, Class<T> objectClass) {
        return gson.fromJson(serializedString, objectClass);
    }

}
