package com.example.aalizade.mbazar_base_app.utility.customComponents;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.basic_pages_adapters.HorizontaProductsRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefFrontModel;

import java.util.List;

/**
 * Created by aalizade on 5/15/2018.
 */

public class CustomHorizontalProductRecyclerview extends LinearLayout {
    private RecyclerView recyclerView;
    private TextView recyclerTitle;
    Context _context;
    HorizontaProductsRecyclerAdapter RecyclerViewHorizontalAdapter;
    LinearLayoutManager HorizontalLayout;

    public CustomHorizontalProductRecyclerview(Context context) {
        super(context);
        initializeViews(context);
    }

    public CustomHorizontalProductRecyclerview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeViews(context);
    }

    public CustomHorizontalProductRecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeViews(context);
    }

    public void configAdapter(String recyclerTitleText,List<ProductTypeBriefFrontModel> productsList) {
        recyclerTitle.setText(recyclerTitleText);

        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager RecyclerViewLayoutManager;
        RecyclerViewLayoutManager = new LinearLayoutManager(_context);
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        RecyclerViewHorizontalAdapter = new HorizontaProductsRecyclerAdapter(_context, productsList);
        HorizontalLayout = new LinearLayoutManager(_context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);

        notifyAdapter();
    }

    public void notifyAdapter() {
        RecyclerViewHorizontalAdapter.notifyDataSetChanged();
    }

    private void initializeViews(Context context) {
        this._context = context;
        LayoutInflater.from(context).inflate(R.layout.custom_horizontal_product_recyclerview,this);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_id);
        recyclerTitle = (TextView)findViewById(R.id.recycler_title_id);
    }

}

