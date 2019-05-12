package com.example.aalizade.mbazar_base_app.activities.orders;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.orders.ProductDescriptionRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CheckoutAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.checkout.ProductDescriptionModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.GridRequestDataModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.GridResponseDataModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.RowGridModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by sbayatani on 4/12/2018.
 */

public class DescriptionCarrierOrderActivity extends AppCompatActivity {
    CheckoutAPIInterface checkoutAPI;
    LinearLayout progressWrapper;
    List<ProductDescriptionModel> productList;
    RecyclerView productListView;
    LinearLayoutManager linearLayoutManager;
    ProductDescriptionRecyclerAdapter productDescriptionRecyclerAdapter;
    String orderId;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_description_activity);
        orderId = getIntent().getStringExtra("orderProductId");
        progressWrapper = (LinearLayout)findViewById(R.id.progress_wrapper_id);
        productListView = (RecyclerView) findViewById(R.id.product_description_recycler_id);
        checkoutAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(),findViewById(R.id.masterPage_mother_drawer_layout)).create(CheckoutAPIInterface.class);
        productList = new ArrayList<>();
        productDescriptionRecyclerAdapter = new ProductDescriptionRecyclerAdapter(this, productList);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        productListView.setLayoutManager(linearLayoutManager);
        productListView.setAdapter(productDescriptionRecyclerAdapter);
        productListView.setHasFixedSize(true);
        productListView.setItemViewCacheSize(20);
        productListView.setDrawingCacheEnabled(true);
        productListView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        productListView.setNestedScrollingEnabled(false);
        getProductList(orderId,1);
    }


    public void getProductList(String id,int page){
        ArrayList<Object> objects = new ArrayList<>();
        for (int i=0 ;i<10;i++){
            objects.add(new Object());
        }
        ArrayList<Object> param = new ArrayList<>();
        param.add(id);
        Log.v("parameters",param.toString());
        final GridRequestDataModel requestDataModel = new GridRequestDataModel(objects,param,"purchaseOrderCarrierGroup",false,10,page,"","asc");
        Log.v("publishReq:",requestDataModel.toString());
        Call<GridResponseDataModel> call = checkoutAPI.getProductDescription(requestDataModel);
        ProgressBarShower.StartMyProgressBar(this,progressWrapper);
        call.enqueue(new CallbackWithRetry<GridResponseDataModel>(call,this,progressWrapper) {
            @Override
            public void onResponse(Call<GridResponseDataModel> call, Response<GridResponseDataModel> response) {
                ProgressBarShower.StopMyProgressBar(DescriptionCarrierOrderActivity.this,progressWrapper);
                Log.v("publishResponse:", response.code()+"");
                if(response.isSuccessful()){
                    List<RowGridModel> rowGridModel = response.body().getRows();
                    if(rowGridModel != null && productListView != null){
                        Log.v("rowGridModel:",rowGridModel.size()+"");
                        Log.v("rowGridModel:",rowGridModel.get(0).getCell().get(1)+"  ");
                        Log.v("rowGridModel pub--:",rowGridModel.get(0).getCell().get(2)+"  ");
                        for(int i=0;i<rowGridModel.size();i++){
                            //TODO incomplete grid
                            //api ها آماده نیست
                            ProductDescriptionModel productDescriptionModel = new ProductDescriptionModel(
                                    rowGridModel.get(i).getCell().get(0).toString(),
                                    rowGridModel.get(i).getCell().get(1).toString(),
                                    rowGridModel.get(i).getCell().get(2).toString(),
                                    rowGridModel.get(i).getCell().get(3).toString(),
                                    rowGridModel.get(i).getCell().get(4).toString(),
                                    rowGridModel.get(i).getCell().get(5).toString(),
                                    rowGridModel.get(i).getCell().get(6).toString(),
                                    rowGridModel.get(i).getCell().get(7).toString(),
                                    rowGridModel.get(i).getCell().get(8).toString(),
                                    rowGridModel.get(i).getCell().get(9).toString()
                            );
                            productList.add(productDescriptionModel);
                        }

                        productListView.setAdapter(productDescriptionRecyclerAdapter);
                        productDescriptionRecyclerAdapter.notifyDataSetChanged();
                        productListView.setLayoutManager(linearLayoutManager);
                    }else{
                        Toast.makeText(getApplicationContext(),"بن وجود ندارد",Toast.LENGTH_SHORT).show();
                        Log.v("publish:",response.code()+"");
                        finish();
                    }
                }else {
                    Toast.makeText(getApplicationContext(),"بن وجود ندارد",Toast.LENGTH_SHORT).show();
                    Log.v("publish1:",response.code()+"");
                    finish();

                }
            }
        });
    }
}
