package com.example.aalizade.mbazar_base_app.activities.products_related;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter.FilterTypeAdapter;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter.FilterTypeItemAdapter;
import com.example.aalizade.mbazar_base_app.utility.ProductTypeDataGenerator;
import com.example.aalizade.mbazar_base_app.utility.ProductTypeItemDataGenerator;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ListFilteringActivity extends AppCompatActivity {
    RecyclerView typeRecyclerView, typeItemsRecyclerView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_filtering);

        toolbar = (Toolbar) findViewById(R.id.filtering_page_toolbar_id);
        setUpToolbar(toolbar);

        //type recycler
        typeRecyclerView = (RecyclerView) findViewById(R.id.filter_types_recycler_id);
        SetRecyclerAdapter(typeRecyclerView);
        //type recycler
        //type recycler
        typeItemsRecyclerView = (RecyclerView) findViewById(R.id.filter_types_items_recycler_id);
        SetRecyclerAdapter_Items(typeItemsRecyclerView);
        //type recycler

    }

    public void setUpToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.list_enter, R.anim.list_exit);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.list_enter, R.anim.list_exit);
    }

    private void SetRecyclerAdapter(RecyclerView recyclerView) {
        FilterTypeAdapter typeAdapter = new FilterTypeAdapter(this, new ProductTypeDataGenerator(this).getData(this));
        GridLayoutManager myGridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(myGridLayoutManager);
        recyclerView.setAdapter(typeAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
    }

    private void SetRecyclerAdapter_Items(RecyclerView recyclerView) {
        FilterTypeItemAdapter typeAdapter = new FilterTypeItemAdapter(this, new ProductTypeItemDataGenerator(this).getData(this));
        GridLayoutManager myGridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(myGridLayoutManager);
        recyclerView.setAdapter(typeAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
    }


}
