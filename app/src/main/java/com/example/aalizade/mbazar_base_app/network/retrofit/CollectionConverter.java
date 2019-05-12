package com.example.aalizade.mbazar_base_app.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by aalizade on 1/11/2018.
 */

public class CollectionConverter implements JsonDeserializer<Collection<Object>> {


    @Override
    public Collection<Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonArray())
        {
            Gson gson = new Gson();
            return gson.fromJson(json,typeOfT);
        }
        if(typeOfT.equals(List.class))
            return Collections.EMPTY_LIST;
        if(typeOfT.equals(Set.class))
            return Collections.EMPTY_SET;

        return null;

    }
}

