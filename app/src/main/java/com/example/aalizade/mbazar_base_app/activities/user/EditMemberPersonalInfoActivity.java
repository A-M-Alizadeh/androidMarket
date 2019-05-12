package com.example.aalizade.mbazar_base_app.activities.user;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.pager_adapters.MemberEditInfoViewPagerAdapter;
import com.example.aalizade.mbazar_base_app.adapters.pager_adapters.NonSwipeableViewPager;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.UserRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.user.FindUserModel;
import com.example.aalizade.mbazar_base_app.network.models.user.FullUserFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.user.UserModelUpdateForm;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class EditMemberPersonalInfoActivity extends AppCompatActivity {
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    NonSwipeableViewPager viewPager;
    MemberEditInfoViewPagerAdapter adapter;
    LinearLayout progressWrapper;
    RelativeLayout motherLayout;
    public static FullUserFrontModel generalFullUserFrontModel;
    public static UserModelUpdateForm userModelUpdateForm;
    public  static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_member_personal_info);

        progressWrapper = (LinearLayout)findViewById(R.id.progress_wrapper_id);
        motherLayout = (RelativeLayout)findViewById(R.id.edit_member_personal_info_mother_layout_id);
        generalFullUserFrontModel = new FullUserFrontModel();
        userModelUpdateForm = new UserModelUpdateForm();

        final UserRetrofitAPIInterface userRetrofitAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(),motherLayout).create(UserRetrofitAPIInterface.class);
        MBZ_Token_Prefs.initTokenSharedPrefs(getApplicationContext());
//        memberCreateModel = new MemberCreateModel();

        activity = this;

        RegisterFindByUsername(userRetrofitAPIInterface);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("OnDestroy", "------> Destroyed");
    }

    //steps done
    public void FirstStepisDone() {
        viewPager.setCurrentItem(1);
    }

    public void backtoPersonalInfo() {
        viewPager.setCurrentItem(2);
    }

    public void backtoCallInfo() {
        viewPager.setCurrentItem(1);
    }

    public void gotoSocialGroupInfo() {
        viewPager.setCurrentItem(0);
    }
    //steps back

    public void RegisterFindByUsername(UserRetrofitAPIInterface userRetrofitAPIInterface) {
        FindUserModel findUserModel = new FindUserModel();
        findUserModel.setParam(MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCOUNT_USERNAME));
        findUserModel.setUpDiscriminator("");

        Call<FullUserFrontModel> call = userRetrofitAPIInterface.findUserInfoByUsername(findUserModel);
        ProgressBarShower.StartMyProgressBar(EditMemberPersonalInfoActivity.this,progressWrapper);
        call.enqueue(new CallbackWithRetry<FullUserFrontModel>(call,EditMemberPersonalInfoActivity.this,progressWrapper) {
            @Override
            public void onResponse(Call<FullUserFrontModel> call, Response<FullUserFrontModel> response) {
                ProgressBarShower.StopMyProgressBar(EditMemberPersonalInfoActivity.this,progressWrapper);
                if (response.isSuccessful()) {
                    generalFullUserFrontModel = response.body();
                    Log.d("FOUND", generalFullUserFrontModel.getUser().getUsername());
                    drawPageStuff();
                } else {
                    Toast.makeText(getApplicationContext(), "اطلاعات کاربر دریافت نشد", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void drawPageStuff(){
        final TabLayout tabLayout = (TabLayout) findViewById(R.id.member_editPersonalInfo_tablayout_id);
        tabLayout.addTab(tabLayout.newTab().setText("مشخصات کانون"));
        tabLayout.addTab(tabLayout.newTab().setText("اطلاعات تماس"));
        tabLayout.addTab(tabLayout.newTab().setText("اطلاعات فردی"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
        viewPager = (NonSwipeableViewPager) findViewById(R.id.member_editPersonalInfo_view_pager_id);
        adapter = new MemberEditInfoViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);

        viewPager.setCurrentItem(2);//change  to 4
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //related to tabs
        //selected tab
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        //selected tab

        //tab fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/B_Yekan.ttf");
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.atextview, null);
            tv.setTypeface(custom_font);
            tabLayout.getTabAt(i).setCustomView(tv);
        }
        //tab fonts
    }

    public static Activity GETACTIVIY(){
        return activity;
    }

}
