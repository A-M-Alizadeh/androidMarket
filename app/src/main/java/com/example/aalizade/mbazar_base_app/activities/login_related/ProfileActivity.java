package com.example.aalizade.mbazar_base_app.activities.login_related;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.credit.UserCreditActivity;
import com.example.aalizade.mbazar_base_app.activities.orders.OrderViewActivity;
import com.example.aalizade.mbazar_base_app.activities.user.EditMemberPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.activities.user.MemberShipRequestActivity;
import com.example.aalizade.mbazar_base_app.activities.user.MyContactInfoActivity;
import com.example.aalizade.mbazar_base_app.activities.user.ShowMemberPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity {
    Toolbar toolbar;
    String TAG = "PROFILEACTIVITY";
    TextView profileUser_Username, profileUser_Name;
    Button changePassBtn, showpersonalInfoBtn, editPersonalInfoBtn, contactInfoBtn,membershipReqBtn,creditBtn,ordersBtn;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        MBZ_Token_Prefs.initTokenSharedPrefs(this);
        toolbar = (Toolbar) findViewById(R.id.profile_page_toolbar);
        setUpToolbar(toolbar);

    //textviews and buttons
        profileUser_Username = (TextView) findViewById(R.id.user_username_profile_txt_id);
        profileUser_Name = (TextView) findViewById(R.id.user_name_profile_txt_id);
        changePassBtn = (Button) findViewById(R.id.profile_pass_change_btn_id);
        showpersonalInfoBtn = (Button) findViewById(R.id.profile_show_personalInfo_btn_id);
        editPersonalInfoBtn = (Button) findViewById(R.id.profile_edit_personalInfo_btn_id);
        contactInfoBtn = (Button) findViewById(R.id.profile_contactInfo_btn_id);
        membershipReqBtn = (Button) findViewById(R.id.profile_membershipReq_btn_id);
        creditBtn = (Button) findViewById(R.id.profile_credits_btn_id);
        ordersBtn = (Button) findViewById(R.id.profile_orders_btn_id);
    //textviews and buttons

        profileUser_Name.setText(MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_FULL_NAME));
        profileUser_Username.setText(MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCOUNT_USERNAME));

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.profile_appbar_layout);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.profile_collapse_layout);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitleEnabled(true);
                    toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.MyPRDTitleApperance);
                    collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.RIGHT);
                    collapsingToolbarLayout.setTitle("پروفایل کاربری");
                    isShow = true;
                } else if (isShow) {
                    toolbar.getNavigationIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                    collapsingToolbarLayout.setTitleEnabled(false);
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });

    //button actons
        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,PassChangingActivity.class));
            }
        });
        contactInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MyContactInfoActivity.class));
            }
        });
        showpersonalInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ShowMemberPersonalInfoActivity.class));
            }
        });
        editPersonalInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, EditMemberPersonalInfoActivity.class));
            }
        });
        membershipReqBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MemberShipRequestActivity.class));
            }
        });

        creditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, UserCreditActivity.class));
            }
        });
        ordersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, OrderViewActivity.class));
            }
        });
    //button actions
    }

    public void setUpToolbar(final Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_favorite_ic_id) {
            Toast.makeText(getApplicationContext(), "Shopping Card", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
