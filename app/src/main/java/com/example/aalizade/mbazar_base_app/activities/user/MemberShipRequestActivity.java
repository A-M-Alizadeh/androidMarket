package com.example.aalizade.mbazar_base_app.activities.user;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.pager_adapters.MemberRegisteritionPagerAdapter;
import com.example.aalizade.mbazar_base_app.adapters.pager_adapters.NonSwipeableViewPager;
import com.example.aalizade.mbazar_base_app.fragments.membershiprequest_frags.MembershipRequest_PersonalInformationFragment;
import com.example.aalizade.mbazar_base_app.network.models.membershiprequest.MembershipRequestFrontModelCreate;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MemberShipRequestActivity extends AppCompatActivity {
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public static Boolean step1 = false;
    public static MembershipRequestFrontModelCreate memberCreateModel;
//    String userName;

    NonSwipeableViewPager viewPager;
    MemberRegisteritionPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_request);

        memberCreateModel = new MembershipRequestFrontModelCreate();
        memberCreateModel.setUser_username(String.valueOf(getIntent().getStringExtra("user_username")));

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.member_registerition_tablayout_id);
        tabLayout.addTab(tabLayout.newTab().setText("معرف"));
        tabLayout.addTab(tabLayout.newTab().setText("مشخصات کانون"));
        tabLayout.addTab(tabLayout.newTab().setText("اطلاعات تماس"));
        tabLayout.addTab(tabLayout.newTab().setText("اطلاعات فردی"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
        viewPager = (NonSwipeableViewPager) findViewById(R.id.member_registerition_view_pager_id);
        adapter = new MemberRegisteritionPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);

        viewPager.setCurrentItem(4);//change  to 4
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //related to tabs

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
////        if (viewPager.getCurrentItem() != 3) {
//            viewPager.setCurrentItem(3);
////        }else{
////            this.finish();
////        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("OnDestroy", "------> Destroyed");
        MembershipRequest_PersonalInformationFragment.firstEnter = true;
    }

    //step done
    public void FirstStepisDone() {
        System.out.println("Until Step one" + memberCreateModel.toString());
        viewPager.setCurrentItem(2);
    }

    public void secondStepisDone() {
        System.out.println("Until Step two" + memberCreateModel.toString());
        viewPager.setCurrentItem(1);
    }

    public void thirdStepisDone() {
        System.out.println("Until Step three" + memberCreateModel.toString());
        viewPager.setCurrentItem(0);
    }

    //step done

    //step back
    public void backtoPersonalInfo() {
        viewPager.setCurrentItem(3);
    }

    public void backtoCallInfo() {
        viewPager.setCurrentItem(2);
    }

    public void backtoSocialGroupInfo() {
        viewPager.setCurrentItem(1);
    }
    //step back


}
