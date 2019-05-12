package com.example.aalizade.mbazar_base_app.network.retrofit;

import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Created by aalizade on 1/11/2018.
 */

public class MyResultObjectAdapter extends TypeAdapter<AutoCompleteModel> {

    protected TypeAdapter<AutoCompleteModel> defaultAdapter;


    public MyResultObjectAdapter(TypeAdapter<AutoCompleteModel> defaultAdapter) {
        this.defaultAdapter = defaultAdapter;
    }

    @Override
    public void write(JsonWriter out, AutoCompleteModel value) throws IOException {
        defaultAdapter.write(out, value);
    }

    @Override
    public AutoCompleteModel read(JsonReader in) throws IOException {
            /*
            This is the critical part. So if the value is a string,
            Skip it (no exception) and return null.
            */
        if (in.peek() == JsonToken.STRING) {
            in.skipValue();
            return null;
        }
        return defaultAdapter.read(in);
    }
}