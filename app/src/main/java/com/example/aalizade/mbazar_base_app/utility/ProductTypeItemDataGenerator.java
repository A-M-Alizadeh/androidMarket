package com.example.aalizade.mbazar_base_app.utility;

import android.content.Context;

import com.example.aalizade.mbazar_base_app.entities.FilterTypeItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aalizade on 11/8/2017.
 */

public class ProductTypeItemDataGenerator {

    public ProductTypeItemDataGenerator(Context context) {
    }

    public static List<FilterTypeItemEntity> getData(Context context) {
        List<FilterTypeItemEntity> filterItems = new ArrayList<>();

        FilterTypeItemEntity filter = new FilterTypeItemEntity();
        filter.setItemName("اپل");
        filterItems.add(filter);

        FilterTypeItemEntity filter2 = new FilterTypeItemEntity();
        filter2.setItemName("سامسونگ");
        filterItems.add(filter2);

        FilterTypeItemEntity filter3 = new FilterTypeItemEntity();
        filter3.setItemName("نوکیا");
        filterItems.add(filter3);

        FilterTypeItemEntity filter4 = new FilterTypeItemEntity();
        filter4.setItemName("هوآوی");
        filterItems.add(filter4);

        FilterTypeItemEntity filter5 = new FilterTypeItemEntity();
        filter5.setItemName("بلک بری");
        filterItems.add(filter5);

        FilterTypeItemEntity filter6 = new FilterTypeItemEntity();
        filter6.setItemName("وان پلاس");
        filterItems.add(filter6);

        FilterTypeItemEntity filter7 = new FilterTypeItemEntity();
        filter7.setItemName("ال جی");
        filterItems.add(filter7);

        FilterTypeItemEntity filter8 = new FilterTypeItemEntity();
        filter8.setItemName("جی ال ایکس");
        filterItems.add(filter8);

        FilterTypeItemEntity filter9 = new FilterTypeItemEntity();
        filter9.setItemName("ایسوز");
        filterItems.add(filter9);


        return filterItems;
    }
}
