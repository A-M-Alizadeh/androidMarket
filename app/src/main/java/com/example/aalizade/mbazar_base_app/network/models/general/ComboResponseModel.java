package com.example.aalizade.mbazar_base_app.network.models.general;

/**
 * Created by sajad on 2/27/18.
 */

public class ComboResponseModel {
    private String name;
    private String id;

    public ComboResponseModel(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
