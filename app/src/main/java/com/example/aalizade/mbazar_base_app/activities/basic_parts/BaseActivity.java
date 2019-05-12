package com.example.aalizade.mbazar_base_app.activities.basic_parts;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.credit.UserCreditActivity;
import com.example.aalizade.mbazar_base_app.activities.gift.GiftActivity;
import com.example.aalizade.mbazar_base_app.activities.login_related.PassChangingActivity;
import com.example.aalizade.mbazar_base_app.activities.login_related.SignInLogInActivity;
import com.example.aalizade.mbazar_base_app.activities.orders.OrderViewActivity;
import com.example.aalizade.mbazar_base_app.activities.products_related.ShoppingCardActivity;
import com.example.aalizade.mbazar_base_app.activities.user.EditCustomerPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.activities.user.EditMemberPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.activities.user.MemberShipRequestActivity;
import com.example.aalizade.mbazar_base_app.activities.user.MyContactInfoActivity;
import com.example.aalizade.mbazar_base_app.activities.user.ShowMemberPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.activities.user.VisitMembershipRequestActivity;
import com.example.aalizade.mbazar_base_app.activities.user.member.MemberRequestActivity;
import com.example.aalizade.mbazar_base_app.network.RequestServices.HasRequestForMembershipRequestService;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.RequestServices.LogOutRequestService;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartModel;
import com.example.aalizade.mbazar_base_app.network.models.general.PrimitiveResponse;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by sajad on 4/26/2018.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener { // اکیتویتی جنرال که نویگیشن دراور و لوگو را در بالای صفحه به صورت دیفالت دارد
    DrawerLayout base_activity_mother_layout;                                                  //اکیتیویتی ها در صورت نیاز از این اکیتیویتی extend میکنند
    RelativeLayout mainFrame;
    NavigationView baseActivityNavigationView;
    ImageView btnDrawer, btnMbazar;
    private Activity mActivity;

    //nav view items
    TextView navUsername, signintxt;
    View headerView;
    CircleImageView headerProfilePhoto;
    Menu nav_Menu, nav_Menu_Exit, profile_Menu;
    String USERORMEMBERID = null;
    LoginAPIInterface loginAPIInterface;
    Boolean profileShow = false;
    //nav view items

    //request services
    LogOutRequestService logOutRequestService;
    HasRequestForMembershipRequestService hasRequestForMembershipRequestService;
    //request services
    IsAuthorizedBroadCastReciever isAuthorizedBroadCastReceiver;

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateNavViewAsync();
        isAuthorizedBroadCastReceiver = new IsAuthorizedBroadCastReciever(); //رجیستر کردن اکتیویتی برای دریافت برادکست
        registerReceiver(isAuthorizedBroadCastReceiver,new IntentFilter("com.example.aalizade.mbazar_base_app.ACTION_ISAUTHORIZED"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(isAuthorizedBroadCastReceiver);                  //حذف دریافت برادکست از اکتیویتی هنگام از بین رفتن اکتیویتی
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_drawer_activity);
        mActivity = this;
    }

    protected void mappingWidgets(View mainFrameView) {                     //ست کردن تک تک آیتم های موجود در نویگیشن دراور
        InitialAPI_RequestService();                                        //به صورت جنرال همه جا کار خواهد کرد
        base_activity_mother_layout = (DrawerLayout) findViewById(R.id.baseactivity_mother_drawer_layout);
        baseActivityNavigationView = (NavigationView) findViewById(R.id.baseActivity_nav_view);
        View topBarView = getLayoutInflater().inflate(R.layout.base_top_bar, null);
        base_activity_mother_layout.addView(topBarView);

        mainFrame = (RelativeLayout) topBarView.findViewById(R.id.basic_main_frame);
        mainFrame.addView(mainFrameView);

        // nav view items declaration
        MBZ_Token_Prefs.initTokenSharedPrefs(this);

        headerView = baseActivityNavigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.signed_user_name_txt_id);
        headerProfilePhoto = (CircleImageView) headerView.findViewById(R.id.header_profile_img);
        signintxt = (TextView) headerView.findViewById(R.id.login_signin_header_link_id);

        nav_Menu = baseActivityNavigationView.getMenu();
        nav_Menu.setGroupVisible(R.id.loggedMenuPart, false);

        nav_Menu_Exit = baseActivityNavigationView.getMenu();
        nav_Menu_Exit.setGroupVisible(R.id.loggedMenuPart2, false);

        profile_Menu = baseActivityNavigationView.getMenu();
        profile_Menu.setGroupVisible(R.id.profile_menu_group_id, false);

        navUsername.setOnClickListener(this);
        signintxt.setOnClickListener(this);

        setUpNavigationView();

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(10000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateNavViewAsync();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        //async
        // nav view items declaration

        //======================================================================
        btnDrawer = (ImageView) topBarView.findViewById(R.id.drawer_img_btn_id);
        btnMbazar = (ImageView) topBarView.findViewById(R.id.mbazar_top_logo_img_id);
        btnDrawer.setOnClickListener(this);
        btnMbazar.setOnClickListener(this);
        baseActivityNavigationView.bringToFront();
        baseActivityNavigationView.requestLayout();
    }

    @Override
    public void onClick(View v) {
        if (v == null) {
            throw new NullPointerException("You are referring null object. "
                    + "Please check weather you had called super class method mappingWidgets() or not");
        } else if (v == btnDrawer) {
            base_activity_mother_layout.openDrawer(GravityCompat.END);
        } else if (v == btnMbazar) {
            if (!mActivity.getClass().equals(AnewerMasterPageActivity.class)) {
                Intent intent = new Intent(mActivity, AnewerMasterPageActivity.class);
                startActivity(intent);
            }
        } else if (v == signintxt) {
            Toast.makeText(getApplicationContext(), "here", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BaseActivity.this, SignInLogInActivity.class));
        } else if (v == navUsername) {
            profileShow = !profileShow;
            profile_Menu.setGroupVisible(R.id.profile_menu_group_id, profileShow);
        }
    }

    @Override
    public void onBackPressed() {
        if (base_activity_mother_layout.isDrawerOpen(GravityCompat.END)) {
            base_activity_mother_layout.closeDrawer(GravityCompat.END);
        } else {
            super.onBackPressed();
        }
    }

    private void setUpNavigationView() {
        baseActivityNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home_item_id:
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.power_exit_gray_ic_id:
                        MBZ_Token_Prefs.cleanTokenOnExit();
                        updateNavViewAsync();
                        logOutRequestService.LogOutRequest(loginAPIInterface);
                        if (GlobalVariables.LocalCart != null) { //todo error when adding to cart after logging out ****notice****
                            GlobalVariables.LocalUnAthourizedCart = GlobalVariables.LocalCart;
                        } else {
                            GlobalVariables.LocalUnAthourizedCart = new CartModel();
                        }
                        break;
                    case R.id.member_register_menu_item_id:
                        if (MBZ_Token_Prefs.isAuthorized()) {
                            hasRequestForMembershipRequestService.hasRequestforMembership(loginAPIInterface, new IResponseHandler() {
                                @Override
                                public void HandleAfterResponse(Object o) {
                                    USERORMEMBERID = o.toString();
                                    Log.v("MEMBERResponse", o.toString());
                                    Log.v("CUSTOMERORMEMBER", String.valueOf(MBZ_Token_Prefs.getBoolean(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_IS_MEMBER)));
                                    if (MBZ_Token_Prefs.getBoolean(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_IS_MEMBER)) {
//                        Toast.makeText(getApplicationContext(), "MEMBER", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(BaseActivity.this, MemberRequestActivity.class));
                                    } else {
                                        if (!o.toString().matches("")) {
                                            Toast.makeText(getApplicationContext(), "شما قبلا درخواست داده اید", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(BaseActivity.this, VisitMembershipRequestActivity.class);
                                            String s = USERORMEMBERID.substring(0, USERORMEMBERID.lastIndexOf('.'));
                                            intent.putExtra("USERORMEMBERID", s);
                                            startActivity(intent);
                                        } else {
                                            startActivity(new Intent(BaseActivity.this, MemberShipRequestActivity.class));
                                        }
                                    }
                                }
                            });
                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(base_activity_mother_layout, "لطفا ابتدا وارد شوید", Snackbar.LENGTH_LONG)
                                    .setAction("ورود", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(new Intent(BaseActivity.this, SignInLogInActivity.class));
                                        }
                                    });
                            snackbar.show();

                        }
                        break;
                    case R.id.sat_management:
                        PackageManager pm = getApplicationContext().getPackageManager();
                        Intent intent = pm.getLaunchIntentForPackage("ir.shc.mbazar.warehouse");
                        if (intent != null) {
                            getApplicationContext().startActivity(intent);
                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(base_activity_mother_layout, "لطفا اپلبکشن سات را نصب نمایید", Snackbar.LENGTH_LONG)
                                    .setAction("نصب", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
//                                            startActivity(new Intent(AnewerMasterPageActivity.this, SignInLogInActivity.class));
                                        }
                                    });
                            snackbar.show();

                        }
                        break;
                    case R.id.profile_personal_infoChange_item_id:
                        if (MBZ_Token_Prefs.getBoolean(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_IS_MEMBER)) {
                            startActivity(new Intent(BaseActivity.this, EditMemberPersonalInfoActivity.class));
                        } else {
                            startActivity(new Intent(BaseActivity.this, EditCustomerPersonalInfoActivity.class));
                        }

                        break;
                    case R.id.profile_personal_orders_item_id:
                        startActivity(new Intent(BaseActivity.this, OrderViewActivity.class));
                        break;
                    case R.id.profile_personal_infoShow_item_id:
                        startActivity(new Intent(BaseActivity.this, ShowMemberPersonalInfoActivity.class));
                        break;
                    case R.id.profile_changePass_item_id:
                        startActivity(new Intent(BaseActivity.this, PassChangingActivity.class));
                        break;
                    case R.id.profile_personal_contactInfo_item_id:
                        startActivity(new Intent(BaseActivity.this, MyContactInfoActivity.class));
                        break;
                    case R.id.menu_department_item_id: {
                        startActivity(new Intent(BaseActivity.this, ProductsDepartmentActivity.class));
                        break;
                    }
                    case R.id.profile_personal_bon_hedie_item_id: {
                        startActivity(new Intent(BaseActivity.this, GiftActivity.class));
                        break;
                    }
                    case R.id.profile_personal_etebarat_item_id: {
                        startActivity(new Intent(BaseActivity.this, UserCreditActivity.class));
                        break;
                    }
                    case R.id.menu_basket_item_id: {
                        if (GlobalVariables.LocalCart != null)
                            startActivity(new Intent(BaseActivity.this, ShoppingCardActivity.class));
                        break;
                    }
                }
                return false;
            }
        });
    }

//    public void hasRequestforMembership(final LoginAPIInterface userStuff) {
//        Call<PrimitiveResponse> call = userStuff.ifUserisMemberRequest();
//        ProgressBarShower.StartMyProgressBar(BaseActivity.this, base_activity_mother_layout);
//        call.enqueue(new CallbackWithRetry<PrimitiveResponse>(call, BaseActivity.this, base_activity_mother_layout) {
//            @Override
//            public void onResponse(Call<PrimitiveResponse> call, Response<PrimitiveResponse> response) {
//                ProgressBarShower.StopMyProgressBar(BaseActivity.this, base_activity_mother_layout);
//                if (response.isSuccessful()) {
//                    USERORMEMBERID = response.body().getResponse().toString();
//                    Object resp = response.body().getResponse();
//                    Log.v("MEMBERResponse", response.body().getResponse().toString());
//                    Log.v("CUSTOMERORMEMBER", String.valueOf(MBZ_Token_Prefs.getBoolean(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_IS_MEMBER)));
//                    if (MBZ_Token_Prefs.getBoolean(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_IS_MEMBER)) {
////                        Toast.makeText(getApplicationContext(), "MEMBER", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(BaseActivity.this, MemberRequestActivity.class));
//                    } else {
//                        if (!response.body().getResponse().toString().matches("")) {
//                            Toast.makeText(getApplicationContext(), "شما قبلا درخواست داده اید", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(BaseActivity.this, VisitMembershipRequestActivity.class);
//                            String s = USERORMEMBERID.substring(0, USERORMEMBERID.lastIndexOf('.'));
//                            intent.putExtra("USERORMEMBERID", s);
//                            startActivity(intent);
//                        } else {
//                            startActivity(new Intent(BaseActivity.this, MemberShipRequestActivity.class));
//                        }
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
//                    try {
//                        Log.d("usermember", String.valueOf(response.errorBody().string()));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//        });
//    }

    public void updateNavViewAsync() {
        if (!MBZ_Token_Prefs.isAuthorized()) {
            signintxt.setVisibility(View.VISIBLE);
            navUsername.setVisibility(View.GONE);
            nav_Menu.setGroupVisible(R.id.loggedMenuPart, false);
            nav_Menu_Exit.setGroupVisible(R.id.loggedMenuPart2, false);
            profile_Menu.setGroupVisible(R.id.profile_menu_group_id, false);
        } else {
            signintxt.setVisibility(View.GONE);
            navUsername.setVisibility(View.VISIBLE);
            navUsername.setText("پروفایل" + " " + MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_FULL_NAME));
            nav_Menu.setGroupVisible(R.id.loggedMenuPart, true);
            nav_Menu_Exit.setGroupVisible(R.id.loggedMenuPart2, true);
        }
    }

    public void InitialAPI_RequestService(){
        logOutRequestService = new LogOutRequestService(this,base_activity_mother_layout);
        hasRequestForMembershipRequestService = new HasRequestForMembershipRequestService(this,base_activity_mother_layout);

        loginAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(), base_activity_mother_layout).create(LoginAPIInterface.class);
    }

    public class IsAuthorizedBroadCastReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "IN BASE ACTIVITY", Toast.LENGTH_SHORT).show();
            updateNavViewAsync();
        }
    }
}
