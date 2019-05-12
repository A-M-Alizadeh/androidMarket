package com.example.aalizade.mbazar_base_app.network.retrofit;

import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created by aalizade on 1/11/2018.
 */

class MyResultObjectAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        if (type.getRawType() == AutoCompleteModel.class){
            TypeAdapter<AutoCompleteModel> defaultAdapter = (TypeAdapter<AutoCompleteModel>) gson.getDelegateAdapter(this, type);
            return (TypeAdapter<T>) new MyResultObjectAdapter(defaultAdapter);
        }


        if(type.getRawType() == Set.class || type.getRawType() == List.class){
            TypeAdapter<Collection<Object>> defaultAdapter = (TypeAdapter<Collection<Object>>) gson.getDelegateAdapter(this, type);
            return (TypeAdapter<T>) new SetAdapter(defaultAdapter);
        }
        return null;

    }


}

