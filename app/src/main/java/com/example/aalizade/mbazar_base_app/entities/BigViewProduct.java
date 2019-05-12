package com.example.aalizade.mbazar_base_app.entities;

import android.graphics.drawable.Drawable;

/**
 * Created by aalizade on 11/8/2017.
 */

public class BigViewProduct {
    private long id;
    private Drawable productImage;
    private String fa_name;
    private String en_name;
    private int type;
    private int price;

    public Drawable getProductImage() {
        return productImage;
    }

    public void setProductImage(Drawable productImage) {
        this.productImage = productImage;
    }

    public String getFa_name() {
        return fa_name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFa_name(String fa_name) {
        this.fa_name = fa_name;
    }

    public String getEn_name() {
        return en_name;
    }

    public void setEn_name(String en_name) {
        this.en_name = en_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
