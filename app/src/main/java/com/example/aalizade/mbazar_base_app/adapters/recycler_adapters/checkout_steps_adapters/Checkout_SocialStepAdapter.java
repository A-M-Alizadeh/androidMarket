package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.checkout_steps_adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IBadgeUpdate;
import com.example.aalizade.mbazar_base_app.fragments.checkout_frags.CheckOutSocialOrganizationFragment;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Response;

public class Checkout_SocialStepAdapter extends RecyclerView.Adapter<Checkout_SocialStepAdapter.SocialViewHolder> {

    private Context context;
    private ArrayList<ArrayList<String>> socialGroupEntities;
    int selectedPosition = -1;
    CartAPIInterface cartAPIInterface;
    Activity activity;
    RelativeLayout motherLayout;

    public Checkout_SocialStepAdapter(Context context, ArrayList<ArrayList<String>>socialGroups,RelativeLayout motherlayout) {
        this.context = context;
        this.socialGroupEntities = socialGroups;
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(context, motherlayout).create(CartAPIInterface.class);
        activity = (Activity)context;
        this.motherLayout = motherlayout;
    }

    @Override
    public Checkout_SocialStepAdapter.SocialViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.checkout_social_recycler_item, parent, false);
        return new Checkout_SocialStepAdapter.SocialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Checkout_SocialStepAdapter.SocialViewHolder holder, final int position) {
        final ArrayList<String> socialGroup = socialGroupEntities.get(position);
        holder.socialGroupNameTxt.setText(socialGroup.get(1));
        holder.socialGroupCodeTxt.setText(socialGroup.get(2));

        if (selectedPosition == position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#af6b8c"));
            holder.checked_img.setVisibility(View.VISIBLE);
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.checked_img.setVisibility(View.GONE);
        }

        holder.touchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                Log.d("SocialCart",socialGroup.get(0));
                submitSelectedSocialGroup(socialGroup.get(0), new IBadgeUpdate() {
                    @Override
                    public void doUpdate() {
                        CheckOutSocialOrganizationFragment.writeSelectedSocialGroup(position);

                    }
                });
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return socialGroupEntities.size();
    }

    public class SocialViewHolder extends RecyclerView.ViewHolder {
        private TextView socialGroupNameTxt;
        private TextView socialGroupCodeTxt;
        private RelativeLayout touchLayout;
        private ImageView checked_img;

        public SocialViewHolder(View itemView) {
            super(itemView);
            socialGroupNameTxt = (TextView) itemView.findViewById(R.id.degree_list_level_txt_id);
            socialGroupCodeTxt = (TextView) itemView.findViewById(R.id.degree_list_field_txt_id);
            touchLayout = (RelativeLayout) itemView.findViewById(R.id.degree_mother_item_layout_id);
            checked_img = (ImageView) itemView.findViewById(R.id.checked_img_id);
        }
    }

    public void update(ArrayList<ArrayList<String>> data) {
//        degrees.clear();
        socialGroupEntities = data;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        socialGroupEntities.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, socialGroupEntities.size());
    }

    public void submitSelectedSocialGroup(String id , final IBadgeUpdate iBadgeUpdate) {

        System.out.println("WHAT's WRONG ???? "+id);
//        GlobalVariables.LocalCart.setSocialGroup_id(id);//todo uncomment cartmodel changed

        Gson gson = new Gson();
        String s = gson.toJson(GlobalVariables.LocalCart);
        System.out.println("WHAT's WRONG ???? "+GlobalVariables.LocalCart.getSocialGroup_code());
        System.out.println("WHAT's WRONG ???? "+s);

        Call<CartModel> call = cartAPIInterface.submitSelectedSocialGroup(GlobalVariables.LocalCart);
        call.enqueue(new CallbackWithRetry<CartModel>(call, activity,motherLayout) {
            @Override
            public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                if (response.isSuccessful()) {
                    GlobalVariables.LocalCart = response.body();
                    System.out.println("social stepdone "+GlobalVariables.LocalCart.getStepDone());
                    iBadgeUpdate.doUpdate();
                } else {
                    try {
                        Log.d("Fail Combo1", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
