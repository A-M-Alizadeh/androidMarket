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
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.orders.OrderCarrierRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CheckoutAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.checkout.OrderCarrierModel;
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

public class CarrierOrderActivity extends AppCompatActivity {

    CheckoutAPIInterface checkoutAPI;
    LinearLayout progressWrapper;
    List<OrderCarrierModel> carrierList;
    RecyclerView carrierListView;
    LinearLayoutManager linearLayoutManager;
    OrderCarrierRecyclerAdapter orderCarrierRecyclerAdapter;
    String orderId;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_carrier_activity);
        orderId = getIntent().getStringExtra("orderID");
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        carrierListView = (RecyclerView) findViewById(R.id.carrier_order_recycler_id);
        checkoutAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(), findViewById(R.id.masterPage_mother_drawer_layout)).create(CheckoutAPIInterface.class);
        carrierList = new ArrayList<>();
        orderCarrierRecyclerAdapter = new OrderCarrierRecyclerAdapter(this, carrierList);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        carrierListView.setLayoutManager(linearLayoutManager);
        carrierListView.setAdapter(orderCarrierRecyclerAdapter);
        carrierListView.setHasFixedSize(true);
        carrierListView.setItemViewCacheSize(20);
        carrierListView.setDrawingCacheEnabled(true);
        carrierListView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        carrierListView.setNestedScrollingEnabled(false);

        getCarrierList(orderId, 1);
    }

    public void getCarrierList(String id, int page) {
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            objects.add(new Object());
        }
        ArrayList<Object> param = new ArrayList<>();
        param.add(id);
        Log.v("parameters", param.toString());
        final GridRequestDataModel requestDataModel = new GridRequestDataModel(objects, param, "purchaseOrder", false, 10, page, "", "asc");
        Log.v("publishReq:", requestDataModel.toString());
        Call<GridResponseDataModel> call = checkoutAPI.getOrderCarrier(requestDataModel);
        ProgressBarShower.StartMyProgressBar(this, progressWrapper);
        call.enqueue(new CallbackWithRetry<GridResponseDataModel>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<GridResponseDataModel> call, Response<GridResponseDataModel> response) {
                ProgressBarShower.StopMyProgressBar(CarrierOrderActivity.this, progressWrapper);
                Log.v("publishResponse:", response.code() + "");
                if (response.isSuccessful()) {
                    List<RowGridModel> rowGridModel = response.body().getRows();
                    if (rowGridModel != null && carrierListView != null) {
                        Log.v("rowGridModel:", rowGridModel.size() + "");
                        Log.v("rowGridModel:", rowGridModel.get(0).getCell().get(1) + "  ");
                        Log.v("rowGridModel pub--:", rowGridModel.get(0).getCell().get(2) + "  ");
                        for (int i = 0; i < rowGridModel.size(); i++) {
                            //TODO incomplete grid
                            OrderCarrierModel orderCarrierModel = new OrderCarrierModel(
                                    rowGridModel.get(i).getCell().get(0).toString(),
                                    rowGridModel.get(i).getCell().get(1).toString(),
                                    rowGridModel.get(i).getCell().get(2).toString(),
                                    rowGridModel.get(i).getCell().get(3).toString(),
                                    rowGridModel.get(i).getCell().get(4).toString(),
                                    rowGridModel.get(i).getCell().get(5).toString(),
                                    rowGridModel.get(i).getCell().get(6).toString(),
                                    rowGridModel.get(i).getCell().get(7).toString(),
                                    rowGridModel.get(i).getCell().get(8).toString(),
                                    rowGridModel.get(i).getCell().get(9).toString(),
                                    rowGridModel.get(i).getCell().get(10).toString(),
                                    rowGridModel.get(i).getCell().get(11).toString()
                            );
                            carrierList.add(orderCarrierModel);
                        }
                        carrierListView.setAdapter(orderCarrierRecyclerAdapter);
                        orderCarrierRecyclerAdapter.notifyDataSetChanged();
                        carrierListView.setLayoutManager(linearLayoutManager);

                    } else {
                        Toast.makeText(getApplicationContext(), "بن وجود ندارد", Toast.LENGTH_SHORT).show();
                        Log.v("publish:", response.code() + "");
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "بن وجود ندارد", Toast.LENGTH_SHORT).show();
                    Log.v("publish1:", response.code() + "");
                    finish();

                }
            }
        });
    }

}
