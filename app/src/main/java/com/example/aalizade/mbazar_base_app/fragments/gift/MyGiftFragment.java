package com.example.aalizade.mbazar_base_app.fragments.gift;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.gift.AttachBonActivity;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift.UserBonHedieRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.entities.UserBonHedieEntity;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GiftAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.grid.GridRequestDataModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.GridResponseDataModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.RowGridModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.DoubleToString;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by gray on 10/16/17.
 */

public class MyGiftFragment extends Fragment {
    DoubleToString convert = new DoubleToString();
    private GiftAPIInterface creditAPI;
    LinearLayout progressWrapper;
    List<UserBonHedieEntity> bonList;
    FloatingActionButton attachBon;
    UserBonHedieRecyclerAdapter userBonHedieRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView bonRecycler;


    @Override
    public void onStart() {
        super.onStart();
        if(bonRecycler == null){
            getBonList(1);
        }

    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.my_gift_fragment, container, false);

        bonRecycler = (RecyclerView)view.findViewById(R.id.bon_hedie_recycler_id);
        creditAPI = RetrofitOAuthClient.getOauthClient(getContext(),view.findViewById(R.id.masterPage_mother_drawer_layout)).create(GiftAPIInterface.class);
        progressWrapper = (LinearLayout)view.findViewById(R.id.progress_wrapper_id);
        attachBon = (FloatingActionButton) view.findViewById(R.id.attach_bon);
        bonList = new ArrayList<>();
        SetRecyclerAdapter(view);
        getBonList(1);

        attachBon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyGiftFragment.this.getActivity(), AttachBonActivity.class);
                startActivity(intent);
            }
        });


        return view;

    }

    public void getBonList(int page){
        ArrayList<Object> objects = new ArrayList<>();
        for (int i=0 ;i<14;i++){
            objects.add(new Object());
        }
        ArrayList<Object> param = new ArrayList<>();
        GridRequestDataModel requestDataModel = new GridRequestDataModel(objects,param,"userGiftSet",false,10,page,"","asc");
        Log.v("gridRequestData-----: ",requestDataModel.toString());
        Call<GridResponseDataModel> call = creditAPI.getCreditList(requestDataModel);
        call.enqueue(new CallbackWithRetry<GridResponseDataModel>(call,this.getActivity(),progressWrapper) {
            @Override
            public void onResponse(Call<GridResponseDataModel> call, Response<GridResponseDataModel> response) {
                Log.v("BonListResponse: ",response.code()+"");
                if(response.isSuccessful()){
                    List<RowGridModel> rowGridModel = response.body().getRows();
                    if(rowGridModel != null & bonRecycler != null){
                        Log.v("rowGridModel:",rowGridModel.size()+"");
                        Log.v("rowGridModel:",rowGridModel.get(0).getCell().get(10)+"  ");
                        Log.v("rowGridModel ID--:",rowGridModel.get(0).getCell().get(5)+"  ");
                        for(int i=0;i<rowGridModel.size();i++){
                            UserBonHedieEntity bonHedieEntity = new UserBonHedieEntity(rowGridModel.get(i).getCell().get(5).toString(),
                                    rowGridModel.get(i).getCell().get(2)+"",
                                    rowGridModel.get(i).getCell().get(3)+"",
                                    rowGridModel.get(i).getCell().get(4)+"",
                                    convert.dTos((Double) rowGridModel.get(i).getCell().get(10)),
                                    convert.dTos((Double) rowGridModel.get(i).getCell().get(9)));
                            bonList.add(bonHedieEntity);
                        }
                        userBonHedieRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    private void SetRecyclerAdapter(View view) {
        userBonHedieRecyclerAdapter = new UserBonHedieRecyclerAdapter(this.getActivity(), bonList);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        bonRecycler.setLayoutManager(linearLayoutManager);
        bonRecycler.setAdapter(userBonHedieRecyclerAdapter);
        bonRecycler.setHasFixedSize(true);
        bonRecycler.setItemViewCacheSize(20);
        bonRecycler.setDrawingCacheEnabled(true);
        bonRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        bonRecycler.setNestedScrollingEnabled(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}

