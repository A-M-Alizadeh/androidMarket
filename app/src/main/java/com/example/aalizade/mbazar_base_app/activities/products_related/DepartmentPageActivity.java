package com.example.aalizade.mbazar_base_app.activities.products_related;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.basic_parts.AnewerMasterPageActivity;
import com.example.aalizade.mbazar_base_app.activities.basic_parts.ProductsDepartmentActivity;
import com.example.aalizade.mbazar_base_app.activities.credit.UserCreditActivity;
import com.example.aalizade.mbazar_base_app.activities.gift.GiftActivity;
import com.example.aalizade.mbazar_base_app.activities.login_related.PassChangingActivity;
import com.example.aalizade.mbazar_base_app.activities.login_related.ProfileActivity;
import com.example.aalizade.mbazar_base_app.activities.login_related.SignInLogInActivity;
import com.example.aalizade.mbazar_base_app.activities.orders.OrderViewActivity;
import com.example.aalizade.mbazar_base_app.activities.user.EditMemberPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.activities.user.MemberShipRequestActivity;
import com.example.aalizade.mbazar_base_app.activities.user.MyContactInfoActivity;
import com.example.aalizade.mbazar_base_app.activities.user.ShowMemberPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.activities.user.VisitMembershipRequestActivity;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.basic_pages_adapters.ProductTypesHorizontalAdapter;
import com.example.aalizade.mbazar_base_app.network.Globals;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.RequestServices.HasRequestForMembershipRequestService;
import com.example.aalizade.mbazar_base_app.network.RequestServices.LogOutRequestService;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.DepartmentAPIInterace;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentProductTypeBriefListFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentProductTypeFindFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentSidebarFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentSidebarProductCategoryFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefFrontModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.AuthorizationCheck;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.MySnackBar;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.utility.customComponents.CustomHorizontalProductRecyclerview;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IBadgeUpdate;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;
import com.example.aalizade.mbazar_base_app.utility.interfaces.UtilInterface;
import com.mikepenz.actionitembadge.library.ActionItemBadge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
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

public class DepartmentPageActivity extends AppCompatActivity {
    RecyclerView productTypesRecycler;
    Toolbar toolbar;
    DepartmentAPIInterace departmentAPIInterace;
    RelativeLayout motherLayout;
    LinearLayout progressWrapper;
//    String selectedCityId, selectedDepartmentId;
    DepartmentProductTypeBriefListFrontModel departmentProductTypeBriefListFrontModel;
    List<ProductTypeBriefFrontModel> maxDiscountList, bestSellingList, bestVendorList, bestFacilityList, newProductTypeList, recomendedList;
    List<DepartmentSidebarProductCategoryFrontModel> productTypesList;
    NestedScrollView nestedScrollView;
    LinearLayout linearLayout;
    Menu mymenu;

    TextView login_txt, registerBottomBtn, help_txt_btn, userFullName;
    CartAPIInterface cartAPIInterface;
    DrawerLayout departmentPageDrawerLayout;
    NavigationView departmentPageNavigationView;
    ViewGroup viewGroup;
    LoginAPIInterface oauthClient;
    String USERORMEMBERID = null;
    Menu nav_Menu, nav_Menu_Exit, profile_Menu;
    TextView navUsername, signintxt;
    Boolean profileShow = false;
    View headerView;
    CircleImageView headerProfilePhoto;
    ImageView bottomCenterLogoBtn;
    BannerSlider bannerSlider;

    CustomHorizontalProductRecyclerview maxDiscountProductsCustomRecycler, bestSellingProductsCustomRecycler, bestVendorsProductsCustomRecycler,
            installmentcommodityProductsCustomRecycler, newestProductsCustomRecycler, viewedsuggestedProductsCustomRecycler;
    //request services
    LogOutRequestService logOutRequestService;
    HasRequestForMembershipRequestService hasRequestForMembershipRequestService;
    AuthorizationCheck authorizationCheck;
    //request services

    //    static Menu myMenu;
    static Activity activity;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onStart() {
        super.onStart();
        MBZ_Token_Prefs.initTokenSharedPrefs(getApplicationContext());
        updateNavViewAsync();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.shopping_card_toolbar_ic_id:
                if (GlobalVariables.LocalCart != null || GlobalVariables.cartItemsCount > 0)
                    startActivity(new Intent(this, ShoppingCardActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_page);
        MBZ_Token_Prefs.initTokenSharedPrefs(getApplicationContext());

        initialFindViews();
        InitialAPI_RequestService();

        activity = this;

        Intent intent = getIntent();
//        selectedCityId = intent.getStringExtra("cityId");
//        selectedDepartmentId = intent.getStringExtra("departmentId");

        maxDiscountList = new ArrayList<>();
        bestSellingList = new ArrayList<>();
        bestVendorList = new ArrayList<>();
        bestFacilityList = new ArrayList<>();
        newProductTypeList = new ArrayList<>();
        recomendedList = new ArrayList<>();
        productTypesList = new ArrayList<>();

        //mixed
        userFullName = (TextView) findViewById(R.id.user_fullname_txt_id);
        showLoggedInUserName();
        viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        departmentPageDrawerLayout = (DrawerLayout) findViewById(R.id.departmentpage_mother_drawer_layout);
        departmentPageNavigationView = (NavigationView) findViewById(R.id.departmentpage_nav_view_id);
        headerView = departmentPageNavigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.signed_user_name_txt_id);
        headerProfilePhoto = (CircleImageView) headerView.findViewById(R.id.header_profile_img);
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
                    startActivity(new Intent(DepartmentPageActivity.this, ProfileActivity.class));
                }
            }
        });
        //mixed
        setMenuCounter(R.id.menu_basket_item_id, 10);

        getAllListsRequest();
        getProductTypes();

        setUpToolbar(toolbar);
        setUpNavigationView();

        //slider
        List<Banner> banners = new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.pic1));
        banners.add(new DrawableBanner(R.drawable.pic2));
        banners.add(new DrawableBanner(R.drawable.pic1));
        banners.add(new DrawableBanner(R.drawable.pic2));
        banners.add(new DrawableBanner(R.drawable.pic1));
        bannerSlider.setBanners(banners);
        bannerSlider.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(DepartmentPageActivity.this, "Banner with position " + String.valueOf(position) + " clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        //slider


        //mixed
        View headerview = departmentPageNavigationView.getHeaderView(0);
        TextView profilename = (TextView) headerview.findViewById(R.id.login_signin_header_link_id);

        profilename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DepartmentPageActivity.this, SignInLogInActivity.class));
//                HomePageActivity.this.finish();
            }
        });

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
                    startActivity(new Intent(DepartmentPageActivity.this, ProfileActivity.class));
                }
            }
        });
        //mixed
        //select toolbar item
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.action_universal_search_ic_id) {
                    startActivity(new Intent(DepartmentPageActivity.this, UniversalSearchActivity.class));
                }
                if (item.getItemId() == R.id.shopping_card_toolbar_ic_id) {
                    startActivity(new Intent(DepartmentPageActivity.this, ShoppingCardActivity.class));
                }
                return false;
            }
        });
        //select toolbar item

        //mixed
        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorizationCheck.DoIfNotAuthorized(new UtilInterface() {
                    @Override
                    public void HandleAfterResponse() {
                        Intent intent = new Intent(DepartmentPageActivity.this, SignInLogInActivity.class);
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
                        Intent intent = new Intent(DepartmentPageActivity.this, SignInLogInActivity.class);
                        intent.putExtra("signOrlog", "1");
                        startActivity(intent);
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

        bottomCenterLogoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //mixed

    }


    public void showLoggedInUserName() {
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

    public void setUpToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(DepartmentPageActivity.this, departmentPageDrawerLayout, toolbar, 0, 0);
        departmentPageDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    //
    private void setUpNavigationView() {                    //تنظیمات کلی نویگیشن دراور
        MBZ_Token_Prefs.initTokenSharedPrefs(this);
        headerView = departmentPageNavigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.signed_user_name_txt_id);
        headerProfilePhoto = (CircleImageView) headerView.findViewById(R.id.header_profile_img);
        signintxt = (TextView) headerView.findViewById(R.id.login_signin_header_link_id);

        nav_Menu = departmentPageNavigationView.getMenu();
        nav_Menu.setGroupVisible(R.id.loggedMenuPart, false);

        nav_Menu_Exit = departmentPageNavigationView.getMenu();
        nav_Menu_Exit.setGroupVisible(R.id.loggedMenuPart2, false);

        profile_Menu = departmentPageNavigationView.getMenu();
        profile_Menu.setGroupVisible(R.id.profile_menu_group_id, false);


        departmentPageNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
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
//                        MBZ_Token_Prefs.setTokenValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_EXPIRES_IN, "0");
//                        MBZ_Token_Prefs.setTokenEncryptedValues(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_ACCESS_TOKEN, "");
                        if (GlobalVariables.LocalCart != null) { //todo error when adding to cart after logging out ****notice****
                            GlobalVariables.LocalUnAthourizedCart = GlobalVariables.LocalCart;
                        } else {
                            GlobalVariables.LocalUnAthourizedCart = new CartModel();
                        }
                        showLoggedInUserName();
                        break;
                    case R.id.member_register_menu_item_id:
                        if (MBZ_Token_Prefs.isAuthorized()) {
                            hasRequestForMembershipRequestService.hasRequestforMembership(oauthClient, new IResponseHandler() {
                                @Override
                                public void HandleAfterResponse(Object o) {
                                    USERORMEMBERID = o.toString();
                                    Log.d("MEMBER Response", o.toString());
                                    if (!o.toString().matches("")) {
                                        Toast.makeText(getApplicationContext(), "شما قبلا درخواست داده اید", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(DepartmentPageActivity.this, VisitMembershipRequestActivity.class);
                                        String s = USERORMEMBERID.substring(0, USERORMEMBERID.lastIndexOf('.'));
                                        intent.putExtra("USERORMEMBERID", s);
                                        startActivity(intent);
                                    } else {
                                        startActivity(new Intent(DepartmentPageActivity.this, MemberShipRequestActivity.class));
                                    }
                                }
                            });
                        } else {
                            Snackbar snackbar = Snackbar
                                    .make(departmentPageDrawerLayout, "لطفا ابتدا وارد شوید", Snackbar.LENGTH_LONG)
                                    .setAction("ورود", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            startActivity(new Intent(DepartmentPageActivity.this, SignInLogInActivity.class));
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
                                    .make(departmentPageDrawerLayout, "لطفا اپلبکشن سات را نصب نمایید", Snackbar.LENGTH_LONG)
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
                        startActivity(new Intent(DepartmentPageActivity.this, EditMemberPersonalInfoActivity.class));
                        break;
                    case R.id.profile_personal_orders_item_id:
                        startActivity(new Intent(DepartmentPageActivity.this, OrderViewActivity.class));
                        break;
                    case R.id.profile_personal_infoShow_item_id:
                        startActivity(new Intent(DepartmentPageActivity.this, ShowMemberPersonalInfoActivity.class));
                        break;
                    case R.id.profile_changePass_item_id:
                        startActivity(new Intent(DepartmentPageActivity.this, PassChangingActivity.class));
                        break;
                    case R.id.profile_personal_contactInfo_item_id:
                        startActivity(new Intent(DepartmentPageActivity.this, MyContactInfoActivity.class));
                        break;
                    case R.id.menu_department_item_id: {
                        startActivity(new Intent(DepartmentPageActivity.this, ProductsDepartmentActivity.class));
                        break;
                    }
                    case R.id.profile_personal_bon_hedie_item_id: {
                        startActivity(new Intent(DepartmentPageActivity.this, GiftActivity.class));
                        break;
                    }
                    case R.id.profile_personal_etebarat_item_id: {
                        startActivity(new Intent(DepartmentPageActivity.this, UserCreditActivity.class));
                        break;
                    }
                    case R.id.menu_basket_item_id: {
                        Globals.updateBasketItems(cartAPIInterface, activity, linearLayout, new IBadgeUpdate() {
                            @Override
                            public void doUpdate() {
                                if (GlobalVariables.LocalCart != null)
                                    startActivity(new Intent(DepartmentPageActivity.this, ShoppingCardActivity.class));
                            }
                        });


                        break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (this.departmentPageDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.departmentPageDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void setMenuCounter(@IdRes int itemId, int count) { // درجای دیگری کامنت شده است و از روش دیگری استفاده شده - بدون ورودی

        TextView gallery = (TextView) MenuItemCompat.getActionView(departmentPageNavigationView.getMenu().
                findItem(R.id.menu_basket_item_id));

        gallery.setGravity(Gravity.CENTER_VERTICAL);
        gallery.setTypeface(null, Typeface.BOLD);
        gallery.setTextColor(getResources().getColor(R.color.colorAccent));
        if (MBZ_Token_Prefs.isAuthorized() && GlobalVariables.LocalCart != null) {
            if (GlobalVariables.cartItemsCount > 0) {
                gallery.setText(String.valueOf(GlobalVariables.cartItemsCount));
            }
        }

//        TextView view = (TextView) masterPageNavigationView.getMenu().findItem(itemId).getActionView();
//        view.setText(String.valueOf(count));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_page_toolbar_menu, menu);

        mymenu = menu;
        if (GlobalVariables.cartItemsCount != null) {
            if (GlobalVariables.cartItemsCount > 0) {
                ActionItemBadge.update(this, menu.findItem(R.id.shopping_card_toolbar_ic_id), ContextCompat.getDrawable(this, R.drawable.basket_white_ic)
                        , ActionItemBadge.BadgeStyles.PURPLE, GlobalVariables.cartItemsCount);
            }
        }

        return true;
    }

    private void Set_ProductTypes_Horizontal_Adapter(RecyclerView recyclerView) {
        recyclerView.setNestedScrollingEnabled(false);
        RecyclerView.LayoutManager RecyclerViewLayoutManager;
        ProductTypesHorizontalAdapter productTypesHorizontalAdapter;
        LinearLayoutManager HorizontalLayout;
        RecyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(RecyclerViewLayoutManager);

        productTypesHorizontalAdapter = new ProductTypesHorizontalAdapter(this, productTypesList, GlobalVariables.selectedCity, GlobalVariables.selectedDepartment);
        HorizontalLayout = new LinearLayoutManager(DepartmentPageActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);
        recyclerView.setAdapter(productTypesHorizontalAdapter);

        productTypesHorizontalAdapter.notifyDataSetChanged();
    }

    public void getAllListsRequest() {
//        Log.d("intent values", selectedCityId + "$" + selectedDepartmentId);
        DepartmentProductTypeFindFrontModel departmentProductTypeFindFrontModel = new DepartmentProductTypeFindFrontModel();
//        departmentProductTypeFindFrontModel.setVitrin_id(selectedCityId);
        departmentProductTypeFindFrontModel.setVitrinId(GlobalVariables.selectedCity);//globals
        departmentProductTypeFindFrontModel.setPage("0");
        departmentProductTypeFindFrontModel.setRows("10");
//        departmentProductTypeFindFrontModel.setDepartmentId(selectedDepartmentId);
        departmentProductTypeFindFrontModel.setDepartmentId(GlobalVariables.selectedDepartment);//globals
        departmentProductTypeFindFrontModel.setProductCategoryIdSet(Collections.<String>emptySet());
        departmentProductTypeFindFrontModel.setVendorIdSet(Collections.<String>emptySet());

        Call<DepartmentProductTypeBriefListFrontModel> call = departmentAPIInterace.getDepartmentAllPageLists(departmentProductTypeFindFrontModel);
        ProgressBarShower.StartMyProgressBar(DepartmentPageActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<DepartmentProductTypeBriefListFrontModel>(call, DepartmentPageActivity.this, progressWrapper) {
            @Override
            public void onResponse(Call<DepartmentProductTypeBriefListFrontModel> call, Response<DepartmentProductTypeBriefListFrontModel> response) {
                ProgressBarShower.StopMyProgressBar(DepartmentPageActivity.this, progressWrapper);
                if (response.isSuccessful()) {

                    departmentProductTypeBriefListFrontModel = response.body();

                    if (response.body().getBestFacilityList() != null) {
                        bestFacilityList = response.body().getBestFacilityList().getProductTypeFrontModelList();
                    }
                    if (response.body().getBestSellingList() != null) {
                        bestSellingList = response.body().getBestSellingList().getProductTypeFrontModelList();
                    }
                    if (response.body().getBestVendorList() != null) {
                        bestVendorList = response.body().getBestVendorList().getProductTypeFrontModelList();
                    }
                    if (response.body().getMaxDiscountList() != null) {
                        maxDiscountList = response.body().getMaxDiscountList().getProductTypeFrontModelList();
                    }
                    if (response.body().getNewProductTypeList() != null) {
                        newProductTypeList = response.body().getNewProductTypeList().getProductTypeFrontModelList();
                    }
                    if (response.body().getRecomendedList() != null) {
                        recomendedList = response.body().getRecomendedList().getProductTypeFrontModelList();
                    }


                    Log.d("bestFacilityList", bestFacilityList.toString());
                    Log.d("bestSellingList", bestSellingList.toString());
                    Log.d("bestVendorList", bestVendorList.toString());
                    Log.d("maxDiscountList", maxDiscountList.toString());
                    Log.d("newProductTypeList", newProductTypeList.toString());
                    Log.d("recomendedList", recomendedList.toString());

                    maxDiscountProductsCustomRecycler.configAdapter("محصولات با بیشترین تخفیف", bestFacilityList);
                    bestSellingProductsCustomRecycler.configAdapter("پرفـروش ترین ها", bestSellingList);
                    bestVendorsProductsCustomRecycler.configAdapter("بهترین فروشنده ها", bestVendorList);
                    installmentcommodityProductsCustomRecycler.configAdapter("محصولات اقساطی", maxDiscountList);
                    newestProductsCustomRecycler.configAdapter("محصولات جدید", newProductTypeList);
                    viewedsuggestedProductsCustomRecycler.configAdapter("محصولات بازدید شده / پیشنهادی", recomendedList);


                } else {
                    try {
                        Log.d("List Daryaft Nashod ==>", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "لیستهای اطلاعات دریافت نشد", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getProductTypes() { // دریافت نوع های کالایی
        DepartmentSidebarFrontModel departmentSidebarFrontModel = new DepartmentSidebarFrontModel();
        departmentSidebarFrontModel.setDepartmentId(GlobalVariables.selectedDepartment);
        departmentSidebarFrontModel.setVitrinId(GlobalVariables.selectedCity);

        Call<DepartmentSidebarFrontModel> call = departmentAPIInterace.getsideProductTypes(departmentSidebarFrontModel);
        ProgressBarShower.StartMyProgressBar(DepartmentPageActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<DepartmentSidebarFrontModel>(call, DepartmentPageActivity.this, progressWrapper) {
            @Override
            public void onResponse(Call<DepartmentSidebarFrontModel> call, Response<DepartmentSidebarFrontModel> response) {
                ProgressBarShower.StopMyProgressBar(DepartmentPageActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    productTypesList = response.body().getProductCategoyIdList();
                    Set_ProductTypes_Horizontal_Adapter(productTypesRecycler);
                } else {
                    try {
                        Log.d("MYERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "نوع محصولات دریافت نشد", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void guideTour() {
        nestedScrollView.fullScroll(View.FOCUS_UP);
        nestedScrollView.smoothScrollTo(0, 0);
        productTypesRecycler.requestFocus();
        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(100); // half second between each showcase view
        config.setShape(new RectangleShape(100, 100));
        config.setMaskColor(Color.argb(200, 66, 66, 66));

        MaterialShowcaseSequence sequence = new MaterialShowcaseSequence(this);
        sequence.setConfig(config);
        sequence.addSequenceItem(productTypesRecycler,
                "نوع محصولات را میتوانید از این قسمت انتخاب کنید", "فهمیدم");
        sequence.start();
    }

    //mixed

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

    public void InitialAPI_RequestService() {
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(), motherLayout).create(CartAPIInterface.class);
        oauthClient = RetrofitOAuthClient.getOauthClient(getApplicationContext(), motherLayout).create(LoginAPIInterface.class);

        logOutRequestService = new LogOutRequestService(this, progressWrapper);
        hasRequestForMembershipRequestService = new HasRequestForMembershipRequestService(this, progressWrapper);
        authorizationCheck = new AuthorizationCheck(this,motherLayout);
    }

    public void initialFindViews() {
        motherLayout = (RelativeLayout) findViewById(R.id.mother_layout_id);
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        departmentAPIInterace = RetrofitClient.getclient(motherLayout).create(DepartmentAPIInterace.class);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view_id);
        registerBottomBtn = (TextView) findViewById(R.id.register_bottom_btn_id);
        login_txt = (TextView) findViewById(R.id.loging_txt_id);
        help_txt_btn = (TextView) findViewById(R.id.help_txt_btn_id);
        bottomCenterLogoBtn = (ImageView) findViewById(R.id.logo_bottom_center_img_btn_id);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout_id);

        maxDiscountProductsCustomRecycler = (CustomHorizontalProductRecyclerview) findViewById(R.id.maxDiscount_Products_customrecycler_view_id);
        bestSellingProductsCustomRecycler = (CustomHorizontalProductRecyclerview) findViewById(R.id.best_selling_Products_customrecycler_view_id);
        bestVendorsProductsCustomRecycler = (CustomHorizontalProductRecyclerview) findViewById(R.id.Best_vendors_customrecycler_txt_id);
        installmentcommodityProductsCustomRecycler = (CustomHorizontalProductRecyclerview) findViewById(R.id.installment_commodity_customrecycler_txt_id);
        newestProductsCustomRecycler = (CustomHorizontalProductRecyclerview) findViewById(R.id.newest_products_customrecycler_txt_id);
        viewedsuggestedProductsCustomRecycler = (CustomHorizontalProductRecyclerview) findViewById(R.id.viewed_suggested_products_customrecycler_view_id);
        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);
        productTypesRecycler = (RecyclerView) findViewById(R.id.productTypes_recycler_id);
    }

    public class IsAuthorizedBroadCastReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            Toast.makeText(context, "IN ACTIVITY", Toast.LENGTH_SHORT).show();
            showLoggedInUserName();
            updateNavViewAsync();
        }
    }
}
