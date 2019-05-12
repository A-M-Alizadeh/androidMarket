package com.example.aalizade.mbazar_base_app.utility;

import android.content.Context;

import com.example.aalizade.mbazar_base_app.entities.FilterTypeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aalizade on 11/8/2017.
 */

public class ProductTypeDataGenerator {

    public ProductTypeDataGenerator(Context context) {
    }

    public static List<FilterTypeEntity> getData(Context context) {
        List<FilterTypeEntity> filters = new ArrayList<>();

        FilterTypeEntity filter = new FilterTypeEntity();
        filter.setName("برند");
        filters.add(filter);

        FilterTypeEntity filter2 = new FilterTypeEntity();
        filter2.setName("قیمت");
        filters.add(filter2);

        FilterTypeEntity filter3 = new FilterTypeEntity();
        filter3.setName("سازنده");
        filters.add(filter3);

        FilterTypeEntity filter4 = new FilterTypeEntity();
        filter4.setName("نوع");
        filters.add(filter4);

        FilterTypeEntity filter5 = new FilterTypeEntity();
        filter5.setName("رنگ");
        filters.add(filter5);

        FilterTypeEntity filter6 = new FilterTypeEntity();
        filter6.setName("عرضه کننده");
        filters.add(filter6);

//        for (int i = 0; i < 6; i++) {
//            FilterTypeEntity filter = new FilterTypeEntity();
//            filter.setName("برند");
//            filters.add(filter);
//        }
        return filters;
    }
}
