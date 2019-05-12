package com.example.aalizade.mbazar_base_app.activities.gift;

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
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift.PublishGiftRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CreditAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.credit.PublishGiftModel;
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

public class PublishedGiftActivity extends AppCompatActivity {
    CreditAPIInterface creditAPI;
    LinearLayout progressWrapper;
    List<PublishGiftModel> giftList;
    RecyclerView publishGiftListView;
    LinearLayoutManager linearLayoutManager;
    PublishGiftRecyclerAdapter publishGiftRecyclerAdapter;
    List<String> giftId;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.published_gift_activity);
        progressWrapper = (LinearLayout)findViewById(R.id.progress_wrapper_id);
        publishGiftListView = (RecyclerView) findViewById(R.id.published_gift_recycler_id);
        creditAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(),findViewById(R.id.masterPage_mother_drawer_layout)).create(CreditAPIInterface.class);

        giftId = getIntent().getStringArrayListExtra("giftId");
        giftList = new ArrayList<>();
        publishGiftRecyclerAdapter = new PublishGiftRecyclerAdapter(this, giftList);
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        publishGiftListView.setLayoutManager(linearLayoutManager);
//        publishGiftListView.setAdapter(publishGiftRecyclerAdapter);
        publishGiftListView.setHasFixedSize(true);
        publishGiftListView.setItemViewCacheSize(20);
        publishGiftListView.setDrawingCacheEnabled(true);
        publishGiftListView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        publishGiftListView.setNestedScrollingEnabled(false);
        getPublishedGiftList(1);

    }


    public void getPublishedGiftList(int page){
        ArrayList<Object> objects = new ArrayList<>();
        for (int i=0 ;i<12;i++){
            objects.add(new Object());
        }
        ArrayList<Object> param = new ArrayList<>();
        param.add(giftId.get(0));
        Log.v("parameters",param.toString());
        final GridRequestDataModel requestDataModel = new GridRequestDataModel(objects,param,"userGiftNotSet",false,10,page,"","asc");
        Log.v("publishReq:",requestDataModel.toString());
        Call<GridResponseDataModel> call = creditAPI.getPublishedGiftList(requestDataModel);
        ProgressBarShower.StartMyProgressBar(this,progressWrapper);
        call.enqueue(new CallbackWithRetry<GridResponseDataModel>(call,this,progressWrapper) {
            @Override
            public void onResponse(Call<GridResponseDataModel> call, Response<GridResponseDataModel> response) {
                ProgressBarShower.StopMyProgressBar(PublishedGiftActivity.this,progressWrapper);
                Log.v("publishResponse:", response.code()+"");
                if(response.isSuccessful()){
                    List<RowGridModel> rowGridModel = response.body().getRows();
                    if(rowGridModel != null && publishGiftListView != null){
                        Log.v("rowGridModel:",rowGridModel.size()+"");
                        Log.v("rowGridModel:",rowGridModel.get(0).getCell().get(10)+"  ");
                        Log.v("rowGridModel pub--:",rowGridModel.get(0).getCell().get(8)+"  ");
                        for(int i=0;i<rowGridModel.size();i++){
                            //TODO incomplete grid
                            PublishGiftModel giftRequestModel = new PublishGiftModel(
                                    rowGridModel.get(i).getCell().get(1).toString(),
                                    rowGridModel.get(i).getCell().get(2).toString(),
                                    rowGridModel.get(i).getCell().get(5).toString(),
                                    rowGridModel.get(i).getCell().get(4).toString(),
                                    rowGridModel.get(i).getCell().get(7).toString(),
                                    rowGridModel.get(i).getCell().get(8).toString(),
                                    rowGridModel.get(i).getCell().get(9).toString(),
                                    rowGridModel.get(i).getCell().get(10).toString(),
                                    rowGridModel.get(i).getCell().get(9).toString(),
                                    rowGridModel.get(i).getCell().get(10).toString(),
                                    rowGridModel.get(i).getCell().get(11).toString(),
                                    rowGridModel.get(i).getCell().get(11).toString()
                            );
                            giftList.add(giftRequestModel);
                        }

                        publishGiftListView.setAdapter(publishGiftRecyclerAdapter);
                        publishGiftRecyclerAdapter.notifyDataSetChanged();
                        publishGiftListView.setLayoutManager(linearLayoutManager);
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
