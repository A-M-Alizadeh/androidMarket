package com.example.aalizade.mbazar_base_app.activities.basic_parts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.credit.UserCreditActivity;
import com.example.aalizade.mbazar_base_app.activities.gift.GiftActivity;
import com.example.aalizade.mbazar_base_app.activities.login_related.PassChangingActivity;
import com.example.aalizade.mbazar_base_app.activities.login_related.ProfileActivity;
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
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.basic_pages_adapters.HomegridSpecialplansAdapter;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.basic_pages_adapters.specialDepartmentsRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.entities.BigViewProduct;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.RequestServices.AutoCompleteCitiesRequestService;
import com.example.aalizade.mbazar_base_app.network.RequestServices.HasRequestForMembershipRequestService;
import com.example.aalizade.mbazar_base_app.network.RequestServices.LogOutRequestService;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.DepartmentAPIInterace;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentFullfrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentLevel3FrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.Empty;
import com.example.aalizade.mbazar_base_app.network.models.department.FindProductTypeByProductTypeGroupFrontTypeFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefFrontModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.AuthorizationCheck;
import com.example.aalizade.mbazar_base_app.utility.CutomAutoCompleteAdapter;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.utility.customComponents.CustomHorizontalProductRecyclerview;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;
import com.example.aalizade.mbazar_base_app.utility.interfaces.UtilInterface;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;
import uk.co.deanwild.materialshowcaseview.shape.RectangleShape;

public class AnewerMasterPageActivity extends AppCompatActivity {
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    boolean doubleBackToExitPressedOnce = false;

    RecyclerView plansGridRecycler;//, plansGridRecycler2;
    RelativeLayout focusLayout;

    TextView login_txt, registerBottomBtn, help_txt_btn, profilename;
    ImageView drawerBtn, mbazar_top_logo_Btn;
    DrawerLayout masterPagerDrawerLayout;
    NavigationView masterPageNavigationView;
    ViewGroup viewGroup;
    LinearLayout progressWrapper;
    RelativeLayout motherLayout;
    LoginAPIInterface oauthClient;
    String USERORMEMBERID = null;
    Menu nav_Menu, nav_Menu_Exit, profile_Menu;
    TextView navUsername, signintxt;
    Boolean profileShow = false;
    View headerView;

    CircleImageView headerProfilePhoto;
    CardView buyFromMbazar_card, etebar_card, nesie_aghsat_card, nesie_card, bon_card;
    View popUpCitySelectionDialogLayout;
    private AlertDialog loginDialog;
    private static AlertDialog citySelectionDialog;
    AutoCompleteTextView cityAutocompleteTV;
    GeneralRetrofitAPIInterface generalRetrofitAPIInterface;

    private static List<AutoCompleteModel> autoCities = new ArrayList<>();
    private static List<String> initialCitiesArray = new ArrayList<>();
    CutomAutoCompleteAdapter citySelectionAdapter;
    AutoCompleteModel selectedCity = new AutoCompleteModel();

    CartAPIInterface cartAPIInterface;
    TextView selectedCityShownTextView;

    //mixed
    TextView userFullName;
    DepartmentAPIInterace departmentAPIInterace;
    RecyclerView specialDepartmentsRecycler;
    DepartmentFullfrontModel departmentFullfrontModel;
    List<DepartmentLevel3FrontModel> usableIsSpecialList = new ArrayList<>();
    List<ProductTypeBriefFrontModel> specialProductsList = new ArrayList<>();
    List<ProductTypeBriefFrontModel> lifeandWorkSpecialProducts = new ArrayList<>();
    String selectedCityIdString = null;
    LinearLayout allDepartmentsLayout;
    BannerSlider bannerSlider;
    ScrollView scrollView;
    SwipeRefreshLayout swipeContainer;
    specialDepartmentsRecyclerAdapter RecyclerViewHorizontalAdapter;
    LayoutInflater inflater;
    //mixed

    //request services
    LogOutRequestService logOutRequestService;
    AutoCompleteCitiesRequestService autoCompleteCitiesRequestService;
    HasRequestForMembershipRequestService hasRequestForMembershipRequestService;
    AuthorizationCheck authorizationCheck;
    //request services
    CustomHorizontalProductRecyclerview lifeandworkproductsCustomRecycler, specialproductCustomRecycler;
    IsAuthorizedBroadCastReciever isAuthorizedBroadCastReceiver;

    @Override
    protected void onStart() {
        super.onStart();
        MBZ_Token_Prefs.initTokenSharedPrefs(getApplicationContext());
        updateNavViewAsync();
        isAuthorizedBroadCastReceiver = new IsAuthorizedBroadCastReciever(); // ثبت کردن اکتیویتی برای دریافت برادکست اگاهی از تمام شدن توکن
        registerReceiver(isAuthorizedBroadCastReceiver, new IntentFilter("com.example.aalizade.mbazar_base_app.ACTION_ISAUTHORIZED"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(isAuthorizedBroadCastReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoggedInUserName();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anewer_master_page);
        MBZ_Token_Prefs.initTokenSharedPrefs(this);

        initialFindViews();
        InitialAPI_RequestService();
        InitialCitySelectionDoalog();
        setUpNavigationView();
        masterPageNavigationView.bringToFront();
        masterPageNavigationView.requestLayout();
        setMenuCounter();
        //        sendBroadcast(new Intent("com.example.aalizade.mbazar_base_app.ACTION_ISAUTHORIZED"));

        //mixed
        showLoggedInUserName();
        Set_Special_Departments_Horizontal_List(specialDepartmentsRecycler);
        fetchDepartmentsSpecialData();
        getSpecialProducts();
        getSLifeandWork();
        //mixed

        //all departments
        allDepartmentsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnewerMasterPageActivity.this, ProductsDepartmentActivity.class);
                startActivity(intent);
            }
        });
        //all departments

        //slider
        focusLayout.requestFocus(); //بنر ها میتوانند شامل url باشند
        List<Banner> banners = new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.slogan_banner));
        banners.add(new DrawableBanner(R.drawable.mbazar_eid_banner));
        banners.add(new DrawableBanner(R.drawable.plan16));
        banners.add(new DrawableBanner(R.drawable.plan15));
        banners.add(new DrawableBanner(R.drawable.samsung_banner));
        bannerSlider.setBanners(banners);
        bannerSlider.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(AnewerMasterPageActivity.this, "Banner with position " + String.valueOf(position) + " clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        //slider

        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorizationCheck.DoIfNotAuthorized(new UtilInterface() {
                    @Override
                    public void HandleAfterResponse() {
                        Intent intent = new Intent(AnewerMasterPageActivity.this, SignInLogInActivity.class);
                        intent.putExtra("signOrlog", "0"); // برای رفتن به تب ورود یا ثبت نام در اکیتویتی مقصد
                        startActivity(intent);
                    }
                });
            }
        });

        registerBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorizationCheck.DoIfNotAuthorized(new UtilInterface() {
                    @Override
                    public void HandleAfterResponse() {
                        Intent intent = new Intent(AnewerMasterPageActivity.this, SignInLogInActivity.class);
                        intent.putExtra("signOrlog", "1");
                        startActivity(intent);
                    }
                });

            }
        });
        //nav test
        drawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                masterPagerDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
        //nav test

        //upperClass header
        profilename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnewerMasterPageActivity.this, SignInLogInActivity.class));
//                HomePageActivity.this.finish();
            }
        });

        //upperClass header // ابتدا برای چک کردن لاگین بودن و تغییر محتویات نویگیشن دراور از این متد استفاده میشد
//        Thread t = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    while (!isInterrupted()) {
//                        Thread.sleep(10000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                showLoggedInUserName();//to hide full name if unauthorized
//                                updateNavViewAsync();
////                                Toast.makeText(getApplicationContext(),"THREAD RUNNING",Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        };
//        t.start();
        //async

        //click on username to go to profile
        navUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileShow = !profileShow;
                profile_Menu.setGroupVisible(R.id.profile_menu_group_id, profileShow);
            }
        });
        headerProfilePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MBZ_Token_Prefs.isAuthorized()) {
                    startActivity(new Intent(AnewerMasterPageActivity.this, ProfileActivity.class));
                }
            }
        });

        //plans grid recycler ---------
        Set_Grid_Plan_Recycler(plansGridRecycler);
        //plans grid recycler ---------

        buyFromMbazar_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                citySelectionDialog.show();

            }
        });
        etebar_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorizationCheck.DoIfAuthorized(new UtilInterface() {
                    @Override
                    public void HandleAfterResponse() {
                        startActivity(new Intent(AnewerMasterPageActivity.this, UserCreditActivity.class));
                    }
                });
            }
        });

        bon_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorizationCheck.DoIfAuthorized(new UtilInterface() {
                    @Override
                    public void HandleAfterResponse() {
                        if (MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_ROLE_ID) != null) {
                            changeCurrentUserId(MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_ROLE_ID));
                        }
                        startActivity(new Intent(AnewerMasterPageActivity.this, GiftActivity.class));
                    }
                });
            }
        });

        help_txt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guideTour();
            }
        });

        //sweap to refresh
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchDepartmentsSpecialData();//برای لود دوباره دیتا هنگام سویپ کردن به سمت پایین
                getSpecialProducts();          // حالت async فعال نیست تا لودیگ هنگام گرفتن دیتا ناپدید شود
                getSLifeandWork();
                swipeContainer.setRefreshing(false);
            }
        });
        swipeContainer.setColorSchemeResources(R.color.colorAccent);
        //sweap to refresh

    }


    @Override
    public void onBackPressed() {       //درصورت باز بودن نویگیشن بسته میشود در غیر این صورت خروج
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        if (masterPagerDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            masterPagerDrawerLayout.closeDrawer(GravityCompat.START);
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "برای خروج دکمه ی بازگشت را دوبار بزنید", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void showLoggedInUserName() { //نمایش اسم کاربر در لایوت پایینی و جایگزینی با ورود/ثبتنام
        if (MBZ_Token_Prefs.isAuthorized()) {
            userFullName.setText(MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_FULL_NAME));
            userFullName.setVisibility(View.VISIBLE);
            registerBottomBtn.setVisibility(View.GONE);
            login_txt.setVisibility(View.GONE);
        } else {
            userFullName.setText("");
            userFullName.setVisibility(View.GONE);
            registerBottomBtn.setVisibility(View.VISIBLE);
            login_txt.setVisibility(View.VISIBLE);
        }
    }

    //TODO=====sajad========
    public void changeCurrentUserId(String id) {        //برای تشخیص نوع کاربر که مشتری یا عضو و .. است
        Call<ResponseBody> call = oauthClient.changeCurrentUserPosition(id);
        ProgressBarShower.StartMyProgressBar(AnewerMasterPageActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<ResponseBody>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressBarShower.StopMyProgressBar(AnewerMasterPageActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    try {
                        Log.v("changecurrentID: ", response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    //========sajad=========
    //    private void setMenuCounter(@IdRes int itemId) {}     //برای ست کردن عدد بج روی آیتم منو

    private void setMenuCounter() {                             // ست کردن عدد روی آیتم منو
        TextView gallery = (TextView) MenuItemCompat.getActionView(masterPageNavigationView.getMenu().
                findItem(R.id.menu_basket_item_id));
        gallery.setGravity(Gravity.CENTER_VERTICAL);
        gallery.setTypeface(null, Typeface.BOLD);
        gallery.setTextColor(getResources().getColor(R.color.colorAccent));
        if (MBZ_Token_Prefs.isAuthorized() && GlobalVariables.LocalCart != null) {
            if (GlobalVariables.cartItemsCount > 0) {
                gallery.setText(String.valueOf(GlobalVariables.cartItemsCount));
            }
        }
//        gallery.setText("99");                            // ست کردن عدد روی آیتم نویگیشن منو
//        TextView view = (TextView) masterPageNavigationView.getMenu().findItem(itemId).getActionView();
//        view.setText(String.valueOf(count));
    }

    private void setUpNavigationView() {                    // انجام تمام تنظیمات نویگیشن دراور -
        MBZ_Token_Prefs.initTokenSharedPrefs(this);
        navUsername = (TextView) headerView.findViewById(R.id.signed_user_name_txt_id);
        headerProfilePhoto = (CircleImageView) headerView.findViewById(R.id.header_profile_img);
        signintxt = (TextView) headerView.findViewById(R.id.login_signin_header_link_id);

        nav_Menu = masterPageNavigationView.getMenu();
        nav_Menu.setGroupVisible(R.id.loggedMenuPart, false);

        nav_Menu_Exit = masterPageNavigationView.getMenu();
        nav_Menu_Exit.setGroupVisible(R.id.loggedMenuPart2, false);

        profile_Menu = masterPageNavigationView.getMenu();
        profile_Menu.setGroupVisible(R.id.profile_menu_group_id, false);

        masterPageNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home_item_id:
                        Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.power_exit_gray_ic_id:
                        MBZ_Token_Prefs.cleanTokenOnExit();
                        updateNavViewAsync();
                        logOutRequestService.LogOutRequest(oauthClient);
                        if (GlobalVariables.LocalCart != null) { //todo error when adding to cart after logging out ****notice****
                            GlobalVariables.LocalUnAthourizedCart = GlobalVariables.LocalCart;
                        } else {
                            GlobalVariables.LocalUnAthourizedCart = new CartModel();
                        }
                        showLoggedInUserName();
                        break;
                    case R.id.member_register_menu_item_id:
                        if (MBZ_Token_Prefs.isAuthorized()) {
                            hasRequestForMembershipRequestService.hasRequestforMembership(oauthClient, new IResponseHandler() {//بررسی اینکه آیا قبلا درخواست عضویت داده شده است
                                @Override
                                public void HandleAfterResponse(Object o) {
                                    USERORMEMBERID = o.toString();
                                    Log.v("MEMBERResponse", o.toString());
                                    Log.v("CUSTOMERORMEMBER", String.valueOf(MBZ_Token_Prefs.getBoolean(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_IS_MEMBER)));
                                    if (MBZ_Token_Prefs.getBoolean(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_USER_IS_MEMBER)) {
                                        startActivity(new Intent(AnewerMasterPageActivity.this, MemberRequestActivity.class));
                                    } else {
                                        if (!o.toString().matches("")) {
                                            Toast.makeText(getApplicationContext(), "شما قبلا درخواست داده اید", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(AnewerMasterPageActivity.this, VisitMembershipRequestActivity.class);
                                            String s = USERORMEMBERID.substring(0, USERORMEMBERID.lastIndexOf('.'));
                                            intent.putExtra("USERORMEMBERID", s);
                                            startActivity(intent);
                                        } else {
                                            startActivity(new Intent(AnewerMasterPageActivity.this, MemberShipRequestActivity.class));
                                        }
                                    }
                                }
                            });
                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(motherLayout, "لطفا ابتدا وارد شوید", Snackbar.LENGTH_LONG)
                                    .setAction("ورود", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(new Intent(AnewerMasterPageActivity.this, SignInLogInActivity.class));
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
                                    .make(motherLayout, "لطفا اپلبکشن سات را نصب نمایید", Snackbar.LENGTH_LONG)
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
                            startActivity(new Intent(AnewerMasterPageActivity.this, EditMemberPersonalInfoActivity.class));
                        } else {
                            startActivity(new Intent(AnewerMasterPageActivity.this, EditCustomerPersonalInfoActivity.class));
                        }

                        break;
                    case R.id.profile_personal_orders_item_id:
                        startActivity(new Intent(AnewerMasterPageActivity.this, OrderViewActivity.class));
                        break;
                    case R.id.profile_personal_infoShow_item_id:
                        startActivity(new Intent(AnewerMasterPageActivity.this, ShowMemberPersonalInfoActivity.class));
                        break;
                    case R.id.profile_changePass_item_id:
                        startActivity(new Intent(AnewerMasterPageActivity.this, PassChangingActivity.class));
                        break;
                    case R.id.profile_personal_contactInfo_item_id:
                        startActivity(new Intent(AnewerMasterPageActivity.this, MyContactInfoActivity.class));
                        break;
                    case R.id.menu_department_item_id: {
                        startActivity(new Intent(AnewerMasterPageActivity.this, ProductsDepartmentActivity.class));
                        break;
                    }
                    case R.id.profile_personal_bon_hedie_item_id: {
                        startActivity(new Intent(AnewerMasterPageActivity.this, GiftActivity.class));
                        break;
                    }
                    case R.id.profile_personal_etebarat_item_id: {
                        startActivity(new Intent(AnewerMasterPageActivity.this, UserCreditActivity.class));
                        break;
                    }
                    case R.id.menu_basket_item_id: {
                        if (GlobalVariables.LocalCart != null)
                            startActivity(new Intent(AnewerMasterPageActivity.this, ShoppingCardActivity.class));
                        break;
                    }
                }
                return false;
            }
        });
    }

    private void Set_Grid_Plan_Recycler(RecyclerView recyclerView) { //دیتا استاتیک است
        Drawable pic = getResources().getDrawable(R.drawable.plan7);
        Drawable pic2 = getResources().getDrawable(R.drawable.plan12);
        Drawable pic3 = getResources().getDrawable(R.drawable.plan10);
        Drawable pic4 = getResources().getDrawable(R.drawable.plan8);
        Drawable pic5 = getResources().getDrawable(R.drawable.plan9);
        Drawable pic6 = getResources().getDrawable(R.drawable.plan13);

        List<BigViewProduct> plans = new ArrayList<>();

        BigViewProduct bigViewProduct = new BigViewProduct();
        bigViewProduct.setFa_name("صدور انواع بیمه نامه");
        bigViewProduct.setProductImage(pic6);

        BigViewProduct bigViewProduct2 = new BigViewProduct();
        bigViewProduct2.setFa_name("طرح جهیزیه آسان");
        bigViewProduct2.setProductImage(pic5);

        BigViewProduct bigViewProduct3 = new BigViewProduct();
        bigViewProduct3.setFa_name("طرح ویژه آستان قدس رضوی");
        bigViewProduct3.setProductImage(pic2);

        BigViewProduct bigViewProduct4 = new BigViewProduct();
        bigViewProduct4.setFa_name("فروش اقساط محصولات سایپا");
        bigViewProduct4.setProductImage(pic3);

        BigViewProduct bigViewProduct5 = new BigViewProduct();
        bigViewProduct5.setFa_name("تولیدات کانون های کار وزندگی");
        bigViewProduct5.setProductImage(pic4);

        BigViewProduct bigViewProduct6 = new BigViewProduct();
        bigViewProduct6.setFa_name("طرح یارانا");
        bigViewProduct6.setProductImage(pic);

        plans.add(bigViewProduct);
        plans.add(bigViewProduct2);
        plans.add(bigViewProduct3);
        plans.add(bigViewProduct4);
        plans.add(bigViewProduct5);
        plans.add(bigViewProduct6);
//

        Log.d("horizontal", " Called");
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager RecyclerViewLayoutManager;
        HomegridSpecialplansAdapter RecyclerViewHorizontalAdapter;
        LinearLayoutManager HorizontalLayout;
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);
        RecyclerViewHorizontalAdapter = new HomegridSpecialplansAdapter(this, plans);
        HorizontalLayout = new LinearLayoutManager(AnewerMasterPageActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);
    }

    public void populateCities(String city) {                                       //استفاده از متد جنرال برای api شهرها
        autoCompleteCitiesRequestService.populateCities(generalRetrofitAPIInterface, city, new IResponseHandler() {
            @Override
            public void HandleAfterResponse(Object o) { //todo come here city selection problem
                initialCitiesArray.clear();
                citySelectionAdapter.clear();
                List<AutoCompleteModel> responseModel = (List<AutoCompleteModel>) o;
                autoCities.clear();
//                System.out.println("city in response->  " + responseModel.toString());
                for (AutoCompleteModel temp : responseModel) {
                    System.out.println(temp.toString());
                    autoCities.add(temp);
                    citySelectionAdapter.add(temp.getText() + " " + temp.getElementStr());
                }

//                System.out.println("city in list-> " + autoCities.toString());//درخواست نام شهر در صورت تایپ بیش از 2 حرف
//                for (int i = 0; i < autoCities.size(); i++) {
//                    System.out.println("city " + i + " :" + autoCities.get(i).getText());
//                    citySelectionAdapter.add(autoCities.get(i).getText() + " " + autoCities.get(i).getElementStr());
//                }
//                System.out.println("city adapter count-> " + String.valueOf(citySelectionAdapter.getCount()));
                citySelectionAdapter.notifyDataSetChanged();
            }
        });
    }

    public void guideTour() {
        scrollView.fullScroll(View.FOCUS_UP);
        scrollView.smoothScrollTo(0, 0);
        scrollView.requestFocus();
        // sequence example
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(100); // half second between each showcase view
        config.setShape(new RectangleShape(100, 100));
        config.setMaskColor(Color.argb(200, 66, 66, 66));
//        config.setDismissTextColor(R.color.white);

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);
        sequence.setConfig(config);
//        sequence.addSequenceItem(msv1.build());
        sequence.addSequenceItem(buyFromMbazar_card,
                "با انتخاب این گزینه میتوانید از بازار اعضا خرید کنید", "فهمیدم");
        sequence.addSequenceItem(etebar_card,
                "برای برخورداری از امکانات بازار اعضا ابتدا باید عضو شوید", "فهمیدم");
        sequence.addSequenceItem(bon_card,
                "برای برخورداری از امکانات بازار اعضا ابتدا باید عضو شوید", "فهمیدم");
        sequence.start();
    }

    public void fetchDepartmentsSpecialData() { //دریافت اطلاعات دپارتمان های ویژه
        Call<DepartmentFullfrontModel> call = departmentAPIInterace.fetchDeprtmentData(new Empty());
        ProgressBarShower.StartMyProgressBar(AnewerMasterPageActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<DepartmentFullfrontModel>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<DepartmentFullfrontModel> call, Response<DepartmentFullfrontModel> response) {
                ProgressBarShower.StopMyProgressBar(AnewerMasterPageActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    usableIsSpecialList.clear();

                    departmentFullfrontModel = new DepartmentFullfrontModel();
                    departmentFullfrontModel.setDepartmentLevel1FrontModels(response.body().getDepartmentLevel1FrontModels());
                    departmentFullfrontModel.setIsSpecialList(response.body().getIsSpecialList());

                    for (int i = 0; i < departmentFullfrontModel.getIsSpecialList().size(); i++) {
                        if (!departmentFullfrontModel.getIsSpecialList().get(i).getDepartmentReferenceTypeEnum().equals("")) {
                            usableIsSpecialList.add(departmentFullfrontModel.getIsSpecialList().get(i));
                        }
                        Log.d("special department: ", new Gson().toJson(departmentFullfrontModel.getIsSpecialList().get(i)));
                    }
                    RecyclerViewHorizontalAdapter.notifyDataSetChanged();

                } else {
                    try {
                        Log.d("MYERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "اطلاعات دریافت نشد", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getSpecialProducts() { //دریافت اطلاعات محصولات ویژه
        FindProductTypeByProductTypeGroupFrontTypeFrontModel sendOfferModel = new FindProductTypeByProductTypeGroupFrontTypeFrontModel();
//        sendOfferModel.setVitrin_id(selectedCityId);
        sendOfferModel.setVitrinId(GlobalVariables.selectedCity);//global
        sendOfferModel.setPage("0");        // هنوز نحوه ی گرفتن تعداد و صفحات و اینکه آیا داینامیک است مطرح نشده است
        sendOfferModel.setRows("8");
        List<String> typeList = new ArrayList<>();
        typeList.add("SPECIAL");
        sendOfferModel.setFrontTypeList(typeList);

        Call<Map<String, List<ProductTypeBriefFrontModel>>> call = departmentAPIInterace.getOfferedProducts(sendOfferModel);
        ProgressBarShower.StartMyProgressBar(AnewerMasterPageActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<Map<String, List<ProductTypeBriefFrontModel>>>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<Map<String, List<ProductTypeBriefFrontModel>>> call, Response<Map<String, List<ProductTypeBriefFrontModel>>> response) {
                ProgressBarShower.StopMyProgressBar(AnewerMasterPageActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    specialProductsList = response.body().get("SPECIAL");
                    for (int i = 0; i < specialProductsList.size(); i++) {
                        System.out.println("spec " + i + specialProductsList.get(i).getTitle().toString());
                    }
                    specialproductCustomRecycler.configAdapter("محصولات ویژه", specialProductsList);
                } else {
                    try {
                        Log.d("MYERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "اطلاعات دریافت نشد", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getSLifeandWork() { //دریافت اطلاعات محصولات کار و زندگی
        FindProductTypeByProductTypeGroupFrontTypeFrontModel sendOfferModel = new FindProductTypeByProductTypeGroupFrontTypeFrontModel();
        sendOfferModel.setVitrinId(GlobalVariables.selectedCity);
        sendOfferModel.setPage("0");        // هنوز نحوه ی گرفتن تعداد و صفحات و اینکه آیا داینامیک است مطرح نشده است
        sendOfferModel.setRows("8");
        List<String> typeList = new ArrayList<>();
        typeList.add("WORK");
        sendOfferModel.setFrontTypeList(typeList);

        Call<Map<String, List<ProductTypeBriefFrontModel>>> call = departmentAPIInterace.getOfferedProducts(sendOfferModel);
        ProgressBarShower.StartMyProgressBar(AnewerMasterPageActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<Map<String, List<ProductTypeBriefFrontModel>>>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<Map<String, List<ProductTypeBriefFrontModel>>> call, Response<Map<String, List<ProductTypeBriefFrontModel>>> response) {
                ProgressBarShower.StopMyProgressBar(AnewerMasterPageActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    lifeandWorkSpecialProducts = response.body().get("WORK");
                    if (!lifeandWorkSpecialProducts.isEmpty())
                        for (int i = 0; i < lifeandWorkSpecialProducts.size(); i++) {
                            System.out.println("WORK " + i + lifeandWorkSpecialProducts.get(i).getTitle().toString());
                        }
                    lifeandworkproductsCustomRecycler.configAdapter("محصولات کار و زندگی", lifeandWorkSpecialProducts);
                } else {
                    try {
                        Log.d("MYERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "اطلاعات دریافت نشد", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Set_Special_Departments_Horizontal_List(RecyclerView recyclerView) {       //آداپتر دپارتمان های ویژه
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager RecyclerViewLayoutManager;
        LinearLayoutManager HorizontalLayout;
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);

        RecyclerViewHorizontalAdapter = new specialDepartmentsRecyclerAdapter(this, usableIsSpecialList, GlobalVariables.selectedCity, progressWrapper);
        HorizontalLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(RecyclerViewHorizontalAdapter);

    }

    public static void ShowCitySelecteionDialog() {
        citySelectionDialog.show();
    }

    public void updateNavViewAsync() {                  //نمایش/عدم نمایش آیتم های مربوط به زمانیکه کاربر لاگین است.
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

    public void initialFindViews() {                //متدی صرفا برای خارج کردن findview از متد oncreate
        allDepartmentsLayout = (LinearLayout) findViewById(R.id.all_departments_layout_id);         // میتوانیم از کتابخانه butterknife استفاده کنیم
        scrollView = (ScrollView) findViewById(R.id.masterpage_scrollview_id);
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        registerBottomBtn = (TextView) findViewById(R.id.register_bottom_btn_id);
        login_txt = (TextView) findViewById(R.id.loging_txt_id);
        help_txt_btn = (TextView) findViewById(R.id.help_txt_btn_id);
        selectedCityShownTextView = (TextView) findViewById(R.id.selected_city_name_txt_id);
        masterPageNavigationView = (NavigationView) findViewById(R.id.masterPage_nav_view);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swip_refresh_layout_id);
        userFullName = (TextView) findViewById(R.id.user_fullname_txt_id);

        specialDepartmentsRecycler = (RecyclerView) findViewById(R.id.departments_horizontal_recycler_id);
        motherLayout = (RelativeLayout) findViewById(R.id.demo_page_container_layout_id);
        focusLayout = (RelativeLayout) findViewById(R.id.top_part_layout_id);
        bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);
        buyFromMbazar_card = (CardView) findViewById(R.id.masterpage_buy_from_mbazar_layout_id);
        etebar_card = (CardView) findViewById(R.id.masterpage_etebar_layout_id);
        nesie_aghsat_card = (CardView) findViewById(R.id.masterpage_nesie_aghsat_layout_id);
        nesie_card = (CardView) findViewById(R.id.masterpage_nesie_layout_id);
        bon_card = (CardView) findViewById(R.id.masterpage_bon_hedie_layout_id);
        drawerBtn = (ImageView) findViewById(R.id.drawer_img_btn_id);
        mbazar_top_logo_Btn = (ImageView) findViewById(R.id.mbazar_top_logo_img_id);
        masterPagerDrawerLayout = (DrawerLayout) findViewById(R.id.masterPage_mother_drawer_layout);
        viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        headerView = masterPageNavigationView.getHeaderView(0);
        profilename = (TextView) headerView.findViewById(R.id.login_signin_header_link_id);
        plansGridRecycler = (RecyclerView) findViewById(R.id.grid_plans_recycler_id);
        inflater = LayoutInflater.from(this);

        lifeandworkproductsCustomRecycler = (CustomHorizontalProductRecyclerview) findViewById(R.id.lifeandwork_products_recycler_id);
        specialproductCustomRecycler = (CustomHorizontalProductRecyclerview) findViewById(R.id.special_products_recycler_id);
    }

    public void InitialAPI_RequestService() {           //متدی برای خارج کردن interface ها و service ها
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(), motherLayout).create(CartAPIInterface.class);
        departmentAPIInterace = RetrofitClient.getclient(motherLayout).create(DepartmentAPIInterace.class);
        generalRetrofitAPIInterface = RetrofitClient.getclient(motherLayout).create(GeneralRetrofitAPIInterface.class);
        oauthClient = RetrofitOAuthClient.getOauthClient(getApplicationContext(), motherLayout).create(LoginAPIInterface.class);

        logOutRequestService = new LogOutRequestService(this, progressWrapper);
        autoCompleteCitiesRequestService = new AutoCompleteCitiesRequestService(this, progressWrapper);
        hasRequestForMembershipRequestService = new HasRequestForMembershipRequestService(this, progressWrapper);
        authorizationCheck = new AuthorizationCheck(this, motherLayout);
    }

    public void InitialCitySelectionDoalog() {         // تمام کار های مربوط به دیالوگ انتخاب شهر
        popUpCitySelectionDialogLayout = inflater.inflate(R.layout.select_city_dialog_layout, null);
        View popUpSelectCityDialogLayoutTitle = inflater.inflate(R.layout.select_city_dialog_title, null);
        AlertDialog.Builder cityAlert = new AlertDialog.Builder(this);
        cityAlert.setView(popUpCitySelectionDialogLayout);
        cityAlert.setCustomTitle(popUpSelectCityDialogLayoutTitle);
        cityAlert.setCancelable(false);
        citySelectionDialog = cityAlert.create();
        citySelectionDialog.setCanceledOnTouchOutside(true);
        citySelectionDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;

        //auto complete city
        cityAutocompleteTV = (AutoCompleteTextView) popUpCitySelectionDialogLayout.findViewById(R.id.autocomplete_complete_city_view_id);
        citySelectionAdapter = new CutomAutoCompleteAdapter(this, R.layout.autocomplete_row,R.id.autocomplete_txt_id, initialCitiesArray);
        cityAutocompleteTV.setAdapter(citySelectionAdapter);
        cityAutocompleteTV.setDropDownHeight(400);

        cityAutocompleteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cityAutocompleteTV.showDropDown();
            }
        });
        cityAutocompleteTV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 2) {//todo come here city problem is revealed
                    populateCities(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        if (!GlobalVariables.selectedCityNAME.equals("")) {
            selectedCityShownTextView.setText(GlobalVariables.selectedCityNAME);
            selectedCityShownTextView.setVisibility(View.VISIBLE);
        } else {
            selectedCityShownTextView.setVisibility(View.GONE);
        }

        cityAutocompleteTV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!autoCities.isEmpty()) {
                    selectedCity.setId(autoCities.get(position).getId());
                    selectedCity.setText(autoCities.get(position).getText());
                    selectedCity.setElementStr(autoCities.get(position).getElementStr());
                }

                Log.d("SELECTEDCITY", selectedCity.toString());
                Log.d("SELECTEDCITYYY", position + "");
                citySelectionDialog.dismiss();
                cityAutocompleteTV.setText("");         //پاک کردن اتوکامپلیت تکست
                GlobalVariables.selectedCity = selectedCity.getId();//global
                GlobalVariables.selectedCityNAME = selectedCity.getText();//global
                selectedCityIdString = selectedCity.getId();
                //mixed
//                Globals.updateBasketItems(cartAPIInterface, AnewerMasterPageActivity.this, progressWrapper, new IBadgeUpdate() { //این متد ها باید تغییر پیدا کنند
//                    @Override
//                    public void doUpdate() {
                fetchDepartmentsSpecialData();//todo make this work
                getSpecialProducts();
                getSLifeandWork();
//                        setMenuCounter();//todo show number in nav
//                    }
//                });
                selectedCityShownTextView.setText(selectedCity.getText()); //نمایش شهر انتخاب شده در کاردویو خرید از بازار اعضا
                selectedCityShownTextView.setVisibility(View.VISIBLE);
                //mixed
            }
        });
        //auto complete city
    }

    public void InitialLoginDialog() { // دیالوگ ثبت نام - درحال حاضر کاربردی ندارد
        View popUpLoginDialogLayout = inflater.inflate(R.layout.master_page_login_dialog, null);
        View popUpLoginDialogLayoutTitle = inflater.inflate(R.layout.master_page_login_dialog_title, null);
        AlertDialog.Builder loginAlert = new AlertDialog.Builder(this);
        loginAlert.setView(popUpLoginDialogLayout);
        loginAlert.setCustomTitle(popUpLoginDialogLayoutTitle);
        loginAlert.setCancelable(false);
        loginDialog = loginAlert.create();
        loginDialog.setCanceledOnTouchOutside(true);
        loginDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
    }

    public class IsAuthorizedBroadCastReciever extends BroadcastReceiver { // پیاده سازی کلاس برادکست رسیور
        @Override
        // باید در هر کلاس که نیاز به دریافت برادکست است نوشته شود
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "IN ACTIVITY", Toast.LENGTH_SHORT).show();
            showLoggedInUserName();
            updateNavViewAsync();
        }
    }

}
