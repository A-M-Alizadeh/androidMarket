package com.example.aalizade.mbazar_base_app.activities.credit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.credit.UserCreditRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.entities.UserCreditEntity;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CreditAPIInterface;
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

public class UserCreditActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    CreditAPIInterface creditAPI;
    LinearLayout progressWrapper;
    List<UserCreditEntity> userCreditList;
    LinearLayoutManager linearLayoutManager;
    UserCreditRecyclerAdapter userCreditRecyclerAdapter;
    //    List<String> giftId;
    RecyclerView creditRecyclerListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_credit);

        creditRecyclerListView = (RecyclerView) findViewById(R.id.credit_recycler_id);
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        userCreditList = new ArrayList<>();
        userCreditRecyclerAdapter = new UserCreditRecyclerAdapter(this, userCreditList);
        creditRecyclerListView.setLayoutManager(linearLayoutManager);
        creditRecyclerListView.setAdapter(userCreditRecyclerAdapter);
        creditRecyclerListView.setHasFixedSize(true);
        creditRecyclerListView.setItemViewCacheSize(20);
        creditRecyclerListView.setDrawingCacheEnabled(true);
        creditRecyclerListView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        creditRecyclerListView.setNestedScrollingEnabled(false);
        creditAPI = RetrofitOAuthClient.getOauthClient(getApplicationContext(), findViewById(R.id.masterPage_mother_drawer_layout)).create(CreditAPIInterface.class);
        getUserCreditList(1);


    }

    public void getUserCreditList(int page) {
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            objects.add(new Object());
        }
        ArrayList<Object> param = new ArrayList<>();
        Log.v("parameters", param.toString());
        final GridRequestDataModel requestDataModel = new GridRequestDataModel(objects, param, "", false, 10, page, "", "asc");
        Log.v("publishReq:", requestDataModel.toString());
        Call<GridResponseDataModel> call = creditAPI.getUserCreditList(requestDataModel);
        ProgressBarShower.StartMyProgressBar(this, progressWrapper);
        call.enqueue(new CallbackWithRetry<GridResponseDataModel>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<GridResponseDataModel> call, Response<GridResponseDataModel> response) {
                ProgressBarShower.StopMyProgressBar(UserCreditActivity.this, progressWrapper);
                Log.v("publishResponse:", response.code() + "");
                if (response.isSuccessful()) {
                    List<RowGridModel> rowGridModel = response.body().getRows();
                    if (rowGridModel != null && creditRecyclerListView != null) {
                        Log.v("rowGridModel:", rowGridModel.size() + "");
                        Log.v("rowGridModel pub--:", rowGridModel.get(0).getCell().get(1) + "  ");
                        Log.v("rowGridModel pub--:", rowGridModel.get(0).getCell().get(2) + "  ");
                        Log.v("rowGridModel pub--:", rowGridModel.get(0).getCell().get(3) + "  ");
                        Log.v("rowGridModel pub--:", rowGridModel.get(0).getCell().get(4) + "  ");
                        Log.v("rowGridModel pub--:", rowGridModel.get(0).getCell().get(5) + "  ");
                        Log.v("rowGridModel pub--:", rowGridModel.get(0).getCell().get(6) + "  ");
                        Log.v("rowGridModel pub--:", rowGridModel.get(0).getCell().get(8) + "  ");
                        Log.v("rowGridModel pub--:", rowGridModel.get(0).getCell().get(0) + "  ");
                        for (int i = 0; i < rowGridModel.size(); i++) {
                            UserCreditEntity userCreditEntity = new UserCreditEntity(
                                    rowGridModel.get(i).getCell().get(0) + "",
                                    rowGridModel.get(i).getCell().get(2) + "",
                                    rowGridModel.get(i).getCell().get(3) + "",
                                    rowGridModel.get(i).getCell().get(3) + "",
                                    rowGridModel.get(i).getCell().get(4) + "",
                                    rowGridModel.get(i).getCell().get(5) + "",
                                    rowGridModel.get(i).getCell().get(6) + "",
                                    rowGridModel.get(i).getCell().get(8) + ""
                            );
                            userCreditList.add(userCreditEntity);
                        }
                        creditRecyclerListView.setAdapter(userCreditRecyclerAdapter);
                        userCreditRecyclerAdapter.notifyDataSetChanged();
                        creditRecyclerListView.setLayoutManager(linearLayoutManager);
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
