package com.example.aalizade.mbazar_base_app.network.models.grid;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by sbayatani on 4/8/2018.
 */

public class RowGridModel implements Serializable{

    private Integer id;
    private ArrayList<Object> cell;

    public RowGridModel(Integer id, ArrayList<Object> cell) {
        this.id = id;
        this.cell = cell;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<Object> getCell() {
        return cell;
    }

    public void setCell(ArrayList<Object> cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "RowGridModel{" +
                "id=" + id +
                ", cell=" + cell +
                '}';
    }
}
