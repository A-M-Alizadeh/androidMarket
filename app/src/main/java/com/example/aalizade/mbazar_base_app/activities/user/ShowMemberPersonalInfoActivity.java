package com.example.aalizade.mbazar_base_app.activities.user;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.pager_adapters.MembeShowInfoViewPagerAdapter;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.UserRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.user.FindUserModel;
import com.example.aalizade.mbazar_base_app.network.models.user.FullUserFrontModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShowMemberPersonalInfoActivity extends AppCompatActivity {
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    ViewPager viewPager;
    MembeShowInfoViewPagerAdapter adapter;
    LinearLayout progressWrapper;
    RelativeLayout motherLayout;

    public static FullUserFrontModel generalFullUserFrontModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_member_personal_info);

        progressWrapper = (LinearLayout)findViewById(R.id.progress_wrapper_id);
        motherLayout = (RelativeLayout)findViewById(R.id.tablayout_mother_layout_id);
        final UserRetrofitAPIInterface userRetrofitAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(),motherLayout).create(UserRetrofitAPIInterface.class);
        MBZ_Token_Prefs.initTokenSharedPrefs(this);

        finduserInfos(userRetrofitAPIInterface);

    }

    public void finduserInfos(UserRetrofitAPIInterface userRetrofitAPIInterface) {//دریافت اطلاعات کاربر
        FindUserModel findUserModel = new FindUserModel();
        findUserModel.setParam(MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCOUNT_USERNAME));
        findUserModel.setUpDiscriminator("");
        Call<FullUserFrontModel> call = userRetrofitAPIInterface.findUserInfoByUsername(findUserModel);
        ProgressBarShower.StartMyProgressBar(ShowMemberPersonalInfoActivity.this,progressWrapper);
        call.enqueue(new CallbackWithRetry<FullUserFrontModel>(call,ShowMemberPersonalInfoActivity.this,progressWrapper) {
            @Override
            public void onResponse(Call<FullUserFrontModel> call, Response<FullUserFrontModel> response) {
                ProgressBarShower.StopMyProgressBar(ShowMemberPersonalInfoActivity.this,progressWrapper);
                if (response.isSuccessful()) {
                    generalFullUserFrontModel = response.body();
                    drawPageinResponse();
                } else {
                    try {
                        Log.d("This", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    public void drawPageinResponse(){
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.member_showInfo_tablayout_id);
        tabLayout.addTab(tabLayout.newTab().setText("مشخصات کانون"));
        tabLayout.addTab(tabLayout.newTab().setText("اطلاعات تماس"));
        tabLayout.addTab(tabLayout.newTab().setText("اطلاعات فردی"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager = (ViewPager) findViewById(R.id.member_showInfo_viewpager_id);
        adapter = new MembeShowInfoViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(3);//change  to 4
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tab fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/B_Yekan.ttf");
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.atextview, null);
            tv.setTypeface(custom_font);
            tabLayout.getTabAt(i).setCustomView(tv);
        }
        //tab fonts
    }
}
