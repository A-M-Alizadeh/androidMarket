package com.example.aalizade.mbazar_base_app.activities.products_related;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter.EndlessRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.bottom_sheets.ListOrderingBottomSheet;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.ProductAPIInterace;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefListDataFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeSearchAdvancedFrontModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.google.gson.Gson;
import com.mikepenz.actionitembadge.library.ActionItemBadge;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductsMainListsActivity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    Toolbar toolbar;
    private int colNum = 2;
    ImageView recycler_view_imageBtn;
    RecyclerView productListRecyclerView;
    ProductAPIInterace productAPIInterace;
    CoordinatorLayout motherLayout;
    List<ProductTypeBriefFrontModel> productList, tempList = new ArrayList<>();
    EndlessRecyclerAdapter producListAdapter;
    LinearLayout progressWrapper;
    //    static ProgressBar itemProgressBar;
    //    private EndlessRecyclerViewScrollListener scrollListener;
    GridLayoutManager myGridLayoutManager;
    CartAPIInterface cartAPIInterface;

    //load more data to recycler
    boolean isLoading;
    private int visibleThreshold = 5;
    int totalItemCount, lastVisibleItem;
    static Menu myMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_main_lists);
        MBZ_Token_Prefs.initTokenSharedPrefs(getApplicationContext());
        initialFindViews();
        InitialAPI_RequestService();

        productList = new ArrayList<>();
        producListAdapter = new EndlessRecyclerAdapter(productList, productListRecyclerView, ProductsMainListsActivity.this, GlobalVariables.selectedCity,
                GlobalVariables.selectedDepartment, GlobalVariables.selectedType, progressWrapper);
        productListRecyclerView.setAdapter(producListAdapter);
        myGridLayoutManager = new GridLayoutManager(this, 2);
        productListRecyclerView.setLayoutManager(myGridLayoutManager);
        productListRecyclerView.setHasFixedSize(true);
        productListRecyclerView.setItemViewCacheSize(20);
        productListRecyclerView.setDrawingCacheEnabled(true);
        productListRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        productListRecyclerView.setNestedScrollingEnabled(false);

        getVeryFirstProductList();

        productListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = myGridLayoutManager.getItemCount();
                lastVisibleItem = myGridLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
//                    getProductList();
                    isLoading = true;
                }
            }
        });

        //first time called
//        getProductList();//get prd list request for the first time
        //first time called


        setUpToolbar(toolbar);
        //changing the recycler listing type
        recycler_view_imageBtn = (ImageView) findViewById(R.id.products_list_view_image_btn_id);
        recycler_view_imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colNum == 2) {
                    recycler_view_imageBtn.setImageResource(R.drawable.products_lists_grid_gray_ic);
                    colNum = 1;
                    SetRecyclerAdapter(colNum);
                } else {
                    recycler_view_imageBtn.setImageResource(R.drawable.list_view_gray_big_row);
                    colNum = 2;
                    SetRecyclerAdapter(colNum);
                }
            }
        });
        //changing the recycler listing type

        //list_ordering_modal_bottom
        RelativeLayout orderingLayout = (RelativeLayout) findViewById(R.id.ordering_btn_layout_id);
        orderingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListOrderingBottomSheet listOrderingBottomSheet = new ListOrderingBottomSheet();
                listOrderingBottomSheet.show(getSupportFragmentManager(), listOrderingBottomSheet.getTag());
            }
        });
        //list_ordering_modal_bottom

        //list_filtering_Activity
        RelativeLayout filteringLayout = (RelativeLayout) findViewById(R.id.list_filtering_layout_id);
        filteringLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsMainListsActivity.this, ListFilteringActivity.class));
                overridePendingTransition(R.anim.filter_enter, R.anim.filter_exit);
            }
        });
        //list_filtering_Activitym

    }

    public void DrawPageInResponse() {

        //recycler
        SetRecyclerAdapter(colNum);
        //recycler                                                                              //TODO
                                                                                                //پیاده سازی اضافه شدن به لیست هنگام اسکرول به سمت پایین - پیجینیشن صفحات
//        //changing the recycler listing type
//        recycler_view_imageBtn = (ImageView) findViewById(R.id.products_list_view_image_btn_id);
//        recycler_view_imageBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (colNum == 2) {
//                    recycler_view_imageBtn.setImageResource(R.drawable.products_lists_grid_gray_ic);
//                    colNum = 1;
//                    SetRecyclerAdapter(colNum);
//                } else {
//                    recycler_view_imageBtn.setImageResource(R.drawable.list_view_gray_big_row);
//                    colNum = 2;
//                    SetRecyclerAdapter(colNum);
//                }
//            }
//        });
//        //changing the recycler listing type
//
//        //list_ordering_modal_bottom
//        RelativeLayout orderingLayout = (RelativeLayout) findViewById(R.id.ordering_btn_layout_id);
//        orderingLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ListOrderingBottomSheet listOrderingBottomSheet = new ListOrderingBottomSheet();
//                listOrderingBottomSheet.show(getSupportFragmentManager(), listOrderingBottomSheet.getTag());
//            }
//        });
//        //list_ordering_modal_bottom
//
//        //list_filtering_Activity
//        RelativeLayout filteringLayout = (RelativeLayout) findViewById(R.id.list_filtering_layout_id);
//        filteringLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(ProductsMainListsActivity.this, ListFilteringActivity.class));
//                overridePendingTransition(R.anim.filter_enter, R.anim.filter_exit);
//            }
//        });
//        //list_filtering_Activity

    }

    public void setUpToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("نام محصول");
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.products_lists_activity_toolbar_menu, menu);
        myMenu = menu;
        //you can add some logic (hide it if the count == 0)
        if (GlobalVariables.cartItemsCount > 0) {
            ActionItemBadge.update(this, menu.findItem(R.id.action_prds_lists_shoppingCard_ic_id), ContextCompat.getDrawable(this, R.drawable.basket_white_ic)
                    , ActionItemBadge.BadgeStyles.PURPLE, GlobalVariables.cartItemsCount);
        }
// else {
//            ActionItemBadge.hide(menu.findItem(R.id.action_prds_lists_shoppingCard_ic_id));
//        }
//        //If you want to add your ActionItem programmatically you can do this too. You do the following:
//        new ActionItemBadgeAdder().act(this).menu(menu).title(R.string.sample_2).itemDetails(0, SAMPLE2_ID, 1).showAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS).add(bigStyle, 1);
        return true;
    }

    public static void UPDATE_CART_NUMBER(Activity activity) {
        if (GlobalVariables.cartItemsCount > 0) {
            ActionItemBadge.update(activity, myMenu.findItem(R.id.action_prds_lists_shoppingCard_ic_id), ContextCompat.getDrawable(activity, R.drawable.basket_white_ic)
                    , ActionItemBadge.BadgeStyles.PURPLE, GlobalVariables.cartItemsCount);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        myMenu = menu;
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_prds_lists_shoppingCard_ic_id: {
//                Toast.makeText(getApplicationContext(),"working",Toast.LENGTH_SHORT).show();
//                Globals.updateBasketItems(cartAPIInterface, ProductsMainListsActivity.this, progressWrapper, new IBadgeUpdate() {
//                    @Override
//                    public void doUpdate() {
                if (GlobalVariables.LocalCart != null)
                    startActivity(new Intent(ProductsMainListsActivity.this, ShoppingCardActivity.class));
//                    }
//                });
                break;
            }
//                return true;
            case R.id.prds_lists_text_search_ic_id: {
                Toast.makeText(getApplicationContext(), "asdfasdf", Toast.LENGTH_SHORT).show();
                break;
            }

//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void SetRecyclerAdapter(int colNum) {
        myGridLayoutManager = new GridLayoutManager(this, colNum);
        productListRecyclerView.setLayoutManager(myGridLayoutManager);
        productListRecyclerView.setHasFixedSize(true);
        productListRecyclerView.setItemViewCacheSize(20);
        productListRecyclerView.setDrawingCacheEnabled(true);
        productListRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        productListRecyclerView.setNestedScrollingEnabled(false);
        //recyclerview
    }

    //on start or resum

    @Override
    protected void onStart() {
        super.onStart();
        SetRecyclerAdapter(2);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    //getting data requests
    public void getVeryFirstProductList() {

//        Log.d("IDS", "City->"+selectedCityId +
//                " " +"Dep->"+ selectedDepartmentId + " " +"Type->"+ selectedTypeId);

        Log.d("IDS2", "City->" + GlobalVariables.selectedCity +
                " " + "Dep->" + GlobalVariables.selectedDepartment + " " + "Type->" + GlobalVariables.selectedType);
        ProductTypeSearchAdvancedFrontModel productTypeSearchAdvancedFrontModel = new ProductTypeSearchAdvancedFrontModel();
        productTypeSearchAdvancedFrontModel.setDepartmentId(GlobalVariables.selectedDepartment);//8 global
        productTypeSearchAdvancedFrontModel.setProductCategoryId(GlobalVariables.selectedType);//4 global
        productTypeSearchAdvancedFrontModel.setVitrinId(GlobalVariables.selectedCity);//809 global

        productTypeSearchAdvancedFrontModel.setPage("0");
        productTypeSearchAdvancedFrontModel.setRows("10");
        System.out.println("get prd list req Model: " + new Gson().toJson(productTypeSearchAdvancedFrontModel));

        Call<ProductTypeBriefListDataFrontModel> call = productAPIInterace.getProductListRequest(productTypeSearchAdvancedFrontModel);
//        ProgressBarShower.StartMyProgressBar(this, progressWrapper);
//        itemProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new CallbackWithRetry<ProductTypeBriefListDataFrontModel>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<ProductTypeBriefListDataFrontModel> call, Response<ProductTypeBriefListDataFrontModel> response) {
//                ProgressBarShower.StopMyProgressBar(ProductsMainListsActivity.this, progressWrapper);
//                itemProgressBar.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    ProductTypeBriefListDataFrontModel productTypeBriefListDataFrontModel = response.body();

                    for (int i = 0; i < productTypeBriefListDataFrontModel.getProductTypeFrontModelList().size(); i++) {
                        productList.add(productTypeBriefListDataFrontModel.getProductTypeFrontModelList().get(i));
                        System.out.println(i + " " + productTypeBriefListDataFrontModel.getProductTypeFrontModelList().get(i).toString());
                    }

                    producListAdapter.notifyDataSetChanged();
//                    producListAdapter.refreshAdapter(true,productList);

                } else {
                    try {
                        Log.d("product list error", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }


    public void getProductList() {

        ProductTypeSearchAdvancedFrontModel productTypeSearchAdvancedFrontModel = new ProductTypeSearchAdvancedFrontModel();
        productTypeSearchAdvancedFrontModel.setDepartmentId(GlobalVariables.selectedDepartment);//8 global
        productTypeSearchAdvancedFrontModel.setProductCategoryId(GlobalVariables.selectedType);//4 global
        productTypeSearchAdvancedFrontModel.setVitrinId(GlobalVariables.selectedCity);//809 global
        productTypeSearchAdvancedFrontModel.setPage("0");
        productTypeSearchAdvancedFrontModel.setRows("10");

//        ProductTypeSearchAdvancedFrontModel productTypeSearchAdvancedFrontModel = new ProductTypeSearchAdvancedFrontModel();
//        productTypeSearchAdvancedFrontModel.setDepartmentId("8");
//        productTypeSearchAdvancedFrontModel.setProductCategoryId("4");
//        productTypeSearchAdvancedFrontModel.setVitrin_id("809");
//        productTypeSearchAdvancedFrontModel.setPage("0");
//        productTypeSearchAdvancedFrontModel.setRows("10");

        Call<ProductTypeBriefListDataFrontModel> call = productAPIInterace.getProductListRequest(productTypeSearchAdvancedFrontModel);
//        ProgressBarShower.StartMyProgressBar(this, progressWrapper);
//        itemProgressBar.setVisibility(View.VISIBLE);
        call.enqueue(new CallbackWithRetry<ProductTypeBriefListDataFrontModel>(call, this, progressWrapper) {
            @Override
            public void onResponse(Call<ProductTypeBriefListDataFrontModel> call, Response<ProductTypeBriefListDataFrontModel> response) {
//                ProgressBarShower.StopMyProgressBar(ProductsMainListsActivity.this, progressWrapper);
//                itemProgressBar.setVisibility(View.GONE);

                tempList.add(null);
                producListAdapter.notifyItemInserted(tempList.size() - 1);
                Log.d("SIZE", "cardItem " + productList.size() + "; cardTotal " + tempList.size());

                if (response.isSuccessful()) {
                    ProductTypeBriefListDataFrontModel productTypeBriefListDataFrontModel = response.body();

                    for (int i = 0; i < productTypeBriefListDataFrontModel.getProductTypeFrontModelList().size(); i++) {
                        productList.add(productTypeBriefListDataFrontModel.getProductTypeFrontModelList().get(i));
                        System.out.println(i + " " + productTypeBriefListDataFrontModel.getProductTypeFrontModelList().get(i).toString());
                    }

//                    itemProgressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tempList.remove(tempList.size() - 1);
                            producListAdapter.notifyItemRemoved(tempList.size());
                            int Total = productList.size();
                            int start = tempList.size();
                            int end = start + 20;
                            int size = (Total > end) ? end : Total;

                            for (int i = start; i < size; i++) {
                                tempList.add(productList.get(i));
                            }
                            isLoading = (Total == size);
                            producListAdapter.refreshAdapter(isLoading, tempList);
//                            itemProgressBar.setVisibility(View.GONE);
                        }
                    }, 1000);
//                    DrawPageInResponse();

                } else {
                    try {
                        Log.d("product list error", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    //progress bar
//    public static void startProgressBar(){
//        itemProgressBar.setVisibility(View.VISIBLE);
//    }
//    public static void StopProgressBar(){
//        itemProgressBar.setVisibility(View.GONE);
//    }


    public void initialFindViews() {
        motherLayout = (CoordinatorLayout) findViewById(R.id.prd_list_mother_drawer_layout);
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        productListRecyclerView = (RecyclerView) findViewById(R.id.produs_lists_recycler_id);
        toolbar = (Toolbar) findViewById(R.id.product_lists_toolbar);
//        itemProgressBar = (ProgressBar) findViewById(R.id.item_progress_bar);

    }

    public void InitialAPI_RequestService() {
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(), progressWrapper).create(CartAPIInterface.class);
        productAPIInterace = RetrofitClient.getclient(progressWrapper).create(ProductAPIInterace.class);
    }
}
