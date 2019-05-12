package com.example.aalizade.mbazar_base_app.activities.gift;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.basic_parts.BaseActivity;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift.GiftPagerAdapter;

import de.hdodenhof.circleimageview.CircleImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class GiftActivity extends BaseActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    View activityLayout;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //== main layout for this activity ================================
        activityLayout = getLayoutInflater().inflate(R.layout.activity_gift,null);
        super.mappingWidgets(activityLayout);
        //=================================================================
//        setContentView(R.layout.activity_gift);
        initialFindViews();

        //related to tabs
        tabLayout.addTab(tabLayout.newTab().setText("درخواست های بن"));
        tabLayout.addTab(tabLayout.newTab().setText("بن های من"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(Color.WHITE, R.color.colorAccent);

        final GiftPagerAdapter adapter = new GiftPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        //related to tabs

        //tab fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/B_Yekan.ttf");
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(this).inflate(R.layout.atextview, null);
            tv.setTypeface(custom_font);
            tabLayout.getTabAt(i).setCustomView(tv);

        }
    }

    public void initialFindViews(){
        tabLayout = (TabLayout) activityLayout.findViewById(R.id.sign_log_tablayout_id);
        viewPager = (ViewPager) activityLayout.findViewById(R.id.sign_log_view_pager_id);
    }



}




