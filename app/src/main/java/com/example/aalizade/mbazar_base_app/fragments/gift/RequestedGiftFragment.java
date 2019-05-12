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
import com.example.aalizade.mbazar_base_app.activities.gift.UserGiftRequestActivity;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift.UserGiftRequestRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.utility.DoubleToString;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GiftAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.credit.GiftRequestModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.GridRequestDataModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.GridResponseDataModel;
import com.example.aalizade.mbazar_base_app.network.models.grid.RowGridModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


/**
 * Created by gray on 10/16/17.
 */

public class RequestedGiftFragment extends Fragment {
    DoubleToString convert = new DoubleToString();
    private GiftAPIInterface creditAPI;
    LinearLayout progressWrapper;
    List<GiftRequestModel> bonList;
    UserGiftRequestRecyclerAdapter userBonHedieRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView requestGiftRecycler;
    FloatingActionButton requestGift;


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.requested_gift_fragment, container, false);

        requestGiftRecycler = (RecyclerView)view.findViewById(R.id.bon_hedie_recycler_id);
        creditAPI = RetrofitOAuthClient.getOauthClient(getContext(),view.findViewById(R.id.masterPage_mother_drawer_layout)).create(GiftAPIInterface.class);
        progressWrapper = (LinearLayout)view.findViewById(R.id.progress_wrapper_id);
        requestGift = (FloatingActionButton) view.findViewById(R.id.request_gift);
        bonList = new ArrayList<>();

        userBonHedieRecyclerAdapter = new UserGiftRequestRecyclerAdapter(this.getActivity(), bonList);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        requestGiftRecycler.setHasFixedSize(true);
        requestGiftRecycler.setItemViewCacheSize(20);
        requestGiftRecycler.setDrawingCacheEnabled(true);
        requestGiftRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        requestGiftRecycler.setNestedScrollingEnabled(false);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        getRequestGiftList(1);

        requestGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserGiftRequestActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    public void getRequestGiftList(int page){
        ArrayList<Object> objects = new ArrayList<>();
        for (int i=0 ;i<18;i++){
            objects.add(new Object());
        }
        ArrayList<Object> param = new ArrayList<>();
        final GridRequestDataModel requestDataModel = new GridRequestDataModel(objects,param,"userGiftSet",false,50,page,"","asc");
        Log.v("reqGiftRequestData---: ",requestDataModel.toString());
        Call<GridResponseDataModel> call = creditAPI.getRequestGiftList(requestDataModel);
        call.enqueue(new CallbackWithRetry<GridResponseDataModel>(call,this.getActivity(),progressWrapper) {
            @Override
            public void onResponse(Call<GridResponseDataModel> call, Response<GridResponseDataModel> response) {
                Log.v("GiftListResponse: ",response.code()+"");
                if(response.isSuccessful()){
                    List<RowGridModel> rowGridModel = response.body().getRows();
                    if(rowGridModel != null & requestGiftRecycler != null){
                        Log.v("rowGridModel:",rowGridModel.size()+"");
                        Log.v("rowGridModel:",rowGridModel.get(0).getCell().get(10)+"  ");
                        Log.v("rowGridModel ID--:",rowGridModel.get(0).getCell().get(8)+"  ");
                        for(int i=0;i<rowGridModel.size();i++){
                            GiftRequestModel giftRequestModel = new GiftRequestModel(
                                    convert.dTos((Double) rowGridModel.get(i).getCell().get(1)),
                                    rowGridModel.get(i).getCell().get(2)+"",
                                    rowGridModel.get(i).getCell().get(3)+"",
                                    rowGridModel.get(i).getCell().get(4)+"",
                                    rowGridModel.get(i).getCell().get(5)+"",
                                    rowGridModel.get(i).getCell().get(6)+"",
                                    convert.dTos((Double) rowGridModel.get(i).getCell().get(7)),
                                    convert.dTos((Double) rowGridModel.get(i).getCell().get(8)),
                                    convert.dTos((Double) rowGridModel.get(i).getCell().get(9)),
                                    rowGridModel.get(i).getCell().get(10)+"",
                                    rowGridModel.get(i).getCell().get(11)+"",
                                    rowGridModel.get(i).getCell().get(12)+"",
                                    rowGridModel.get(i).getCell().get(13)+"",
                                    rowGridModel.get(i).getCell().get(14)+"",
                                    rowGridModel.get(i).getCell().get(15)+"",
                                    rowGridModel.get(i).getCell().get(16)+"",
                                    rowGridModel.get(i).getCell().get(17)+""
                            );
                            bonList.add(giftRequestModel);
                        }
                        requestGiftRecycler.setAdapter(userBonHedieRecyclerAdapter);
                        userBonHedieRecyclerAdapter.notifyDataSetChanged();
                        requestGiftRecycler.setLayoutManager(linearLayoutManager);
                    }
                }
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }




}

