package com.example.aalizade.mbazar_base_app.network.retrofit;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by aalizade on 1/11/2018.
 */

public class SetAdapter extends TypeAdapter<Collection<Object>> {

    protected TypeAdapter<Collection<Object>> defaultAdapter;


    public SetAdapter(TypeAdapter<Collection<Object>> defaultAdapter) {
        this.defaultAdapter = defaultAdapter;
    }


    @Override
    public void write(JsonWriter out, Collection<Object> value) throws IOException {
        defaultAdapter.write(out, value);
    }

    @Override
    public Collection<Object> read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.STRING) {
            in.skipValue();
            return null;
        }
        return defaultAdapter.read(in);
    }
}