package com.example.aalizade.mbazar_base_app.activities.products_related;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.pager_adapters.Spec_Exp_UsrView_PagerAdapter;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.apiInterface.ProductAPIInterace;
import com.example.aalizade.mbazar_base_app.network.models.attributes.AttributeValueFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.attributes.FullAttributeGroupFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.attributes.FullAttributeTitleFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.FindByVitrinModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductSpecificationListCustomModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeFullFrontModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Specifications_Explanation_ViewsActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    Toolbar toolbar;
    ProductAPIInterace productAPIInterace;
    RelativeLayout relativeLayout;
    ProductTypeFullFrontModel productTypeFullFrontModel;
    LinearLayout progressWrapper;
    public static List<ProductSpecificationListCustomModel> specsWrappers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specifications__explanation__views);

        relativeLayout = (RelativeLayout) findViewById(R.id.prd_specification_main_layout_id);
        productAPIInterace = RetrofitClient.getclient(relativeLayout).create(ProductAPIInterace.class);
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        toolbar = (Toolbar) findViewById(R.id.spec_exp_views_page_toolbar_id);

        setUpToolbar(toolbar);
        specsWrappers = new ArrayList<>();          //از این لیست به صورت استاتیک در فرگمنت مشخصات استفاده میشود
        getProductData();

    }

    public void DrawPageInResponse() {
        //related to tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.Specific_Explain_Views_tablayout_id);
        tabLayout.addTab(tabLayout.newTab().setText("نظرات"));
        tabLayout.addTab(tabLayout.newTab().setText("مشخصات"));
        tabLayout.addTab(tabLayout.newTab().setText("توضیحات"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.Specific_Explain_Views_view_pager_id);
        final Spec_Exp_UsrView_PagerAdapter adapter = new Spec_Exp_UsrView_PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //related to tabs

        //set selected tab
        Intent intent = getIntent();
        viewPager.setCurrentItem(Integer.parseInt(intent.getStringExtra("tab_item")));
        //set selected tab

        //tab fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/B_Yekan.ttf");
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.atextview, null);
            tv.setTypeface(custom_font);
            tabLayout.getTabAt(i).setCustomView(tv);
        }
    }
    //tab fonts

    //toolbar set
    public void setUpToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    //toolbar set

    //get product data again
    public void getProductData() {                                  // اطلاعات کلی مربوط به محصول
        FindByVitrinModel findByVitrinModel = new FindByVitrinModel();
        findByVitrinModel.setId("21");//todo set product id -> GlobalVariables.selectedProductID
        findByVitrinModel.setVitrinId(GlobalVariables.selectedCity);
        System.out.println("prd full model-> " + findByVitrinModel.toString());
        Call<ProductTypeFullFrontModel> call = productAPIInterace.getProductData(findByVitrinModel);
        ProgressBarShower.StartMyProgressBar(this, progressWrapper);
        call.enqueue(new CallbackWithRetry<ProductTypeFullFrontModel>(call, Specifications_Explanation_ViewsActivity.this, relativeLayout) {

            @Override
            public void onResponse(Call<ProductTypeFullFrontModel> call, Response<ProductTypeFullFrontModel> response) {
                ProgressBarShower.StopMyProgressBar(Specifications_Explanation_ViewsActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    productTypeFullFrontModel = response.body();
                    String key;
                    List<AttributeValueFrontModel> values = new ArrayList();
                    for (FullAttributeGroupFrontModel fullAttributeGroupFrontModel : productTypeFullFrontModel.getAttributeGroupList()) {
                        key = fullAttributeGroupFrontModel.getTitle();
                        for (FullAttributeTitleFrontModel fullAttributeTitleFrontModel : fullAttributeGroupFrontModel.getAttributeTitleModelSet()) {
                            for (AttributeValueFrontModel attributeValueFrontModel : fullAttributeTitleFrontModel.getAttributeValueModelSet()) {
                                values.add(attributeValueFrontModel);
                            }
                        }
                        specsWrappers.add(new ProductSpecificationListCustomModel(key, new ArrayList<AttributeValueFrontModel>(values)));
                        key = "";
                        values.clear();
                    }

                    DrawPageInResponse();
                } else {
                    Toast.makeText(getApplicationContext(), "اطلاعات دریافت نشد", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("Fail Combo1", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    //get product data again

}
