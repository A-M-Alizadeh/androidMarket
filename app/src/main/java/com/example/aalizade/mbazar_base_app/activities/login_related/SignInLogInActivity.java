package com.example.aalizade.mbazar_base_app.activities.login_related;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.pager_adapters.Sign_LoginPagerAdapter;
import com.example.aalizade.mbazar_base_app.fragments.SignUpFragment;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SignInLogInActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_log_in);

        Intent intent = getIntent();
        String signOrlog = intent.getStringExtra("signOrlog");

        //related to tabs
        tabLayout = (TabLayout) findViewById(R.id.sign_log_tablayout_id);
        tabLayout.addTab(tabLayout.newTab().setText("ورود"));
        tabLayout.addTab(tabLayout.newTab().setText("ثبت نام"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.sign_log_view_pager_id);
        final Sign_LoginPagerAdapter adapter = new Sign_LoginPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        if (signOrlog!= null && signOrlog.matches("1")){    //انتقال به صفحه ثبت نام اگر مقداری به صورت اینتنت ارسال شده باشد
            viewPager.setCurrentItem(1);
        }
        //related to tabs

        //tab fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/B_Yekan.ttf");
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv=(TextView)LayoutInflater.from(this).inflate(R.layout.atextview,null);
            tv.setTypeface(custom_font);
            tabLayout.getTabAt(i).setCustomView(tv);

        }
        //tab fonts
    }

    @Override
    public void onBackPressed() {                       //درصورت ارسال شدن کد تایید محتوای صفحه تغییر میکند - اگر دکمه بازگشت کلیک شود به صفحه قبلی میرویم
        if (viewPager.getCurrentItem() == 1 && SignUpFragment.gonnaReceiveVerifyCodeLayout.getVisibility() == View.VISIBLE) {
            SignUpFragment.gonnaReceiveVerifyCodeLayout.setVisibility(View.GONE);
            SignUpFragment.firstPartLayout.setVisibility(View.VISIBLE);

            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
            SignUpFragment.firstPartLayout.startAnimation(animation);
            SignUpFragment.gonnaReceiveVerifyCodeLayout.startAnimation(animation2);
        } else {
            super.onBackPressed();
        }
    }
}
