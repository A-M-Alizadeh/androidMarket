package com.example.aalizade.mbazar_base_app.activities.user;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.pager_adapters.MemberInfoPagerAdapter;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.membershiprequest.FindModel;
import com.example.aalizade.mbazar_base_app.network.models.membershiprequest.MembershipRequestFrontModelUpdate;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VisitMembershipRequestActivity extends AppCompatActivity {
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    ViewPager viewPager;
    MemberInfoPagerAdapter adapter;
    public static MembershipRequestFrontModelUpdate membershipRequestFrontModelUpdate;
    LinearLayout progressWrapper;
    RelativeLayout motherLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_member_info);
        MBZ_Token_Prefs.initTokenSharedPrefs(getApplicationContext());
        membershipRequestFrontModelUpdate = new MembershipRequestFrontModelUpdate();

        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        motherLayout = (RelativeLayout) findViewById(R.id.visit_info_activity_mother_layout_id);

        //oauth member req
        final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        final LoginAPIInterface registeringAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(), motherLayout).create(LoginAPIInterface.class);

        Intent intent = getIntent();
        getMemberInfoRequest(registeringAPIInterface, viewGroup, Integer.valueOf(intent.getStringExtra("USERORMEMBERID")));

    }

    public void getMemberInfoRequest(final LoginAPIInterface getCombo, final View view, Integer id) {
        FindModel findModel = new FindModel();
        findModel.getIdList().add(id);
        findModel.setParam("");

        Call<List<MembershipRequestFrontModelUpdate>> call = getCombo.getMemberToShowRequest(findModel);
        ProgressBarShower.StartMyProgressBar(VisitMembershipRequestActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<List<MembershipRequestFrontModelUpdate>>(call, VisitMembershipRequestActivity.this, progressWrapper) {
            @Override
            public void onResponse(Call<List<MembershipRequestFrontModelUpdate>> call, Response<List<MembershipRequestFrontModelUpdate>> response) {
                ProgressBarShower.StopMyProgressBar(VisitMembershipRequestActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    membershipRequestFrontModelUpdate = response.body().get(0);

                    Log.d("Visit Member 1", response.body().toString());
                    Log.d("Visit Member 2", membershipRequestFrontModelUpdate.toString());
                    drawInResponse();
                } else {
                    Toast.makeText(view.getContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("Visit Fail", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    public void drawInResponse() {
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.member_info__tablayout_id);
        tabLayout.addTab(tabLayout.newTab().setText("معرف"));
        tabLayout.addTab(tabLayout.newTab().setText("مشخصات کانون"));
        tabLayout.addTab(tabLayout.newTab().setText("اطلاعات تماس"));
        tabLayout.addTab(tabLayout.newTab().setText("اطلاعات فردی"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.member_info_view_pager_id);
        adapter = new MemberInfoPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);

        viewPager.setCurrentItem(4);//change  to 4
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //tab fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/B_Yekan.ttf");
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.atextview, null);
            tv.setTypeface(custom_font);
            tabLayout.getTabAt(i).setCustomView(tv);
        }
        //tab fonts
    }

}
