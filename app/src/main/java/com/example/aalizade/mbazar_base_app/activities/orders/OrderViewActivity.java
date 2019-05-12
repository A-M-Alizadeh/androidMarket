package com.example.aalizade.mbazar_base_app.activities.orders;

import android.content.Context;
import android.content.Intent;
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
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.orders.view.CheckoutViewRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CheckoutAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.checkout.view.CheckoutViewModel;
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

public class OrderViewActivity extends AppCompatActivity {
    CheckoutAPIInterface checkoutAPI;
    LinearLayout progressWrapper;
    List<CheckoutViewModel> orderList;
    RecyclerView orderListView;
    LinearLayoutManager linearLayoutManager;
    CheckoutViewRecyclerAdapter checkoutViewRecyclerAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout_view_activity);
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        orderListView = (RecyclerView) findViewById(R.id.checkout_view_recycler_id);
        checkoutAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(), findViewById(R.id.masterPage_mother_drawer_layout)).create(CheckoutAPIInterface.class);
        orderList = new ArrayList<>();
        checkoutViewRecyclerAdapter = new CheckoutViewRecyclerAdapter(this, orderList);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        orderListView.setLayoutManager(linearLayoutManager);
        orderListView.setAdapter(checkoutViewRecyclerAdapter);
        orderListView.setHasFixedSize(true);
        orderListView.setItemViewCacheSize(20);
        orderListView.setDrawingCacheEnabled(true);
        orderListView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        orderListView.setNestedScrollingEnabled(false);
        getPublishedGiftList(1);

        Intent intent = getIntent();
        String paymentInfo = intent.getStringExtra("info"); // نمایش موفقیت یا عدم موفقیت در انجام سفارش
        if (paymentInfo != null) {
            if (!paymentInfo.equals("")) {
                Toast.makeText(getApplicationContext(), paymentInfo, Toast.LENGTH_SHORT).show();
                paymentInfo = "";
            }
        }
    }


    public void getPublishedGiftList(int page) {
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            objects.add(new Object());
        }
        ArrayList<Object> param = new ArrayList<>();
        Log.v("parameters", param.toString());
        final GridRequestDataModel requestDataModel = new GridRequestDataModel(objects, param, "customer", false, 50, page, "", "asc");
        Log.v("publishReq:", requestDataModel.toString());
        Call<GridResponseDataModel> call = checkoutAPI.getUserCheckoutView(requestDataModel);
        ProgressBarShower.StartMyProgressBar(this, progressWrapper);
        call.enqueue(new CallbackWithRetry<GridResponseDataModel>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<GridResponseDataModel> call, Response<GridResponseDataModel> response) {
                ProgressBarShower.StopMyProgressBar(OrderViewActivity.this, progressWrapper);
                Log.v("publishResponse:", response.code() + "");
                if (response.isSuccessful()) {
                    List<RowGridModel> rowGridModel = response.body().getRows();
                    if (rowGridModel != null && orderListView != null) {
                        Log.v("rowGridModel:", rowGridModel.size() + "");
                        Log.v("rowGridModel:", rowGridModel.get(0).getCell().get(1) + "  ");
                        Log.v("rowGridModel pub--:", rowGridModel.get(0).getCell().get(2) + "  ");
                        for (int i = 0; i < rowGridModel.size(); i++) {
                            //TODO incomplete grid
                            CheckoutViewModel checkoutViewModel = new CheckoutViewModel(
                                    rowGridModel.get(i).getCell().get(0).toString(),
                                    rowGridModel.get(i).getCell().get(1).toString(),
                                    rowGridModel.get(i).getCell().get(2).toString(),
                                    rowGridModel.get(i).getCell().get(3).toString(),
                                    rowGridModel.get(i).getCell().get(4).toString()
                            );
                            orderList.add(checkoutViewModel);
                        }

                        orderListView.setAdapter(checkoutViewRecyclerAdapter);
                        checkoutViewRecyclerAdapter.notifyDataSetChanged();
                        orderListView.setLayoutManager(linearLayoutManager);
                    } else {
                        Toast.makeText(getApplicationContext(), "سفارشی وجود ندارد", Toast.LENGTH_SHORT).show();
                        Log.v("publish:", response.code() + "");
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "سفارشی وجود ندارد", Toast.LENGTH_SHORT).show();
                    Log.v("publish1:", response.code() + "");
                    finish();

                }
            }
        });
    }
}
