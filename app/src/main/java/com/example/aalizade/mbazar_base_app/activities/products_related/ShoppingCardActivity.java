package com.example.aalizade.mbazar_base_app.activities.products_related;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter.ShoppingCardActivityAdapter;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IBadgeUpdate;
import com.example.aalizade.mbazar_base_app.utility.MySnackBar;
import com.example.aalizade.mbazar_base_app.network.Globals;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartProductTypeModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShoppingCardActivity extends AppCompatActivity {

    RecyclerView cartItemsRecyclerView;
    ShoppingCardActivityAdapter cartItemsAdapter;
    TextView cartIsEmptyTxt;
    List<CartProductTypeModel> cartItemsList;
    public static TextView totalQuantityinTitle, totalPriceinTitle;
    LinearLayout progressWrapper,layoutBtn;
    CoordinatorLayout motherLayout;
    CartAPIInterface cartAPIInterface;
    Toolbar toolbar;
    ProgressBar progressBar;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onResume() {
        super.onResume();

//        if (!MBZ_Token_Prefs.isAuthorized()){
//            GlobalVariables.LocalCart = GlobalVariables.LocalUnAthourizedCart;
//        }

        if (!MBZ_Token_Prefs.isAuthorized() && GlobalVariables.LocalCart != null) {
//            Globals.CountUnAuthorizedCartItems();//todo uncomment cartmodel changed
        } else {
            Globals.updateBasketItems(cartAPIInterface, ShoppingCardActivity.this, progressWrapper, new IBadgeUpdate() {
                @Override
                public void doUpdate() {
                    setUpToolbar(toolbar);

                    //recycler
                    SetRecyclerAdapter();
                    checkItemCount();
                    //recycler

                    LinearLayout layoutBtn = (LinearLayout) findViewById(R.id.shopping_card_bottom_layout_btn_id);
                    layoutBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (MBZ_Token_Prefs.isAuthorized() && GlobalVariables.LocalCart != null) {
                                startActivity(new Intent(ShoppingCardActivity.this, CheckOutBaseActivity.class));
                            } else {
                                MySnackBar.snackBarWithNoAction("لطفا ابتدا وارد شوید", motherLayout);
                            }
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        cartItemsList.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_card);
        initialFindViews();
        InitialAPI_RequestService();
//        if (!MBZ_Token_Prefs.isAuthorized()){
//            GlobalVariables.LocalCart = GlobalVariables.LocalUnAthourizedCart;
//        }

        cartItemsList = new ArrayList<>();
        if (MBZ_Token_Prefs.isAuthorized()) {
            progressBar.setVisibility(View.VISIBLE);
            Globals.updateBasketItems(cartAPIInterface, ShoppingCardActivity.this, progressWrapper, new IBadgeUpdate() {
                @Override
                public void doUpdate() {
                    setUpToolbar(toolbar);
                    //recycler
                    SetRecyclerAdapter();
                    checkItemCount();
                    progressBar.setVisibility(View.GONE);
                    //recycler
                    LinearLayout layoutBtn = (LinearLayout) findViewById(R.id.shopping_card_bottom_layout_btn_id);
                    layoutBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (MBZ_Token_Prefs.isAuthorized()) {
                                startActivity(new Intent(ShoppingCardActivity.this, CheckOutBaseActivity.class));
                            } else {
                                MySnackBar.snackBarWithNoAction("لطفا ابتدا وارد شوید", motherLayout);
                            }
                        }
                    });
                }
            });
        } else {
            setUpToolbar(toolbar);

            //recycler
            SetRecyclerAdapter();
            checkItemCount();
            //recycler

            layoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MBZ_Token_Prefs.isAuthorized()) {
                        startActivity(new Intent(ShoppingCardActivity.this, CheckOutBaseActivity.class));
                    } else {
                        MySnackBar.snackBarWithNoAction("لطفا ابتدا وارد شوید", motherLayout);
                    }
                }
            });
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.shopping_card_activity_toolbar_menu, menu);
        return true;
    }

    public void setUpToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void SetRecyclerAdapter() {
        cartItemsList.clear();
        Integer totalPrice = 0;
        GlobalVariables.cartTotalItemsWithQuantity = 0;
        if (GlobalVariables.LocalCart != null) //check if //todo uncomment -- cartmodel changed
//            for (Map.Entry<String, HashMap<String, Set<CartProductTypeModel>>> entry : GlobalVariables.LocalCart.getCartProductTypeModelHashMap().entrySet()) {
//                for (Map.Entry<String, Set<CartProductTypeModel>> entry2 : entry.getValue().entrySet()) {
//                    for (CartProductTypeModel item : entry2.getValue()) {
//                        System.out.println("===))) " + item);
//                        cartItemsList.add(item);
//                        GlobalVariables.cartTotalItemsWithQuantity += Integer.parseInt(item.getQuantity());
////                    String s = Integer.parseInt(item.getProductType_unitPriceTaxInclude()) * Integer.parseInt(item.getQuantity());
//                        Log.d("price item", item.getProductType_unitPriceTaxInclude());
//                        totalPrice += Integer.parseInt(item.getProductType_unitPriceTaxInclude()) * Integer.parseInt(item.getQuantity());
//                        Log.d("price", String.valueOf(totalPrice));
//                    }
//                }
//            }
        totalQuantityinTitle.setText(getResources().getString(R.string.cart_items_total_quantity_string, GlobalVariables.cartTotalItemsWithQuantity));
        totalPriceinTitle.setText(String.valueOf(totalPrice));
//        Log.d("total Quantity",String.valueOf(totalQuantity));

        cartItemsAdapter = new ShoppingCardActivityAdapter(this, cartItemsList, progressWrapper);
        GridLayoutManager myGridLayoutManager = new GridLayoutManager(this, 1);
        cartItemsRecyclerView.setLayoutManager(myGridLayoutManager);
        cartItemsRecyclerView.setAdapter(cartItemsAdapter);
        cartItemsRecyclerView.setHasFixedSize(true);
        cartItemsRecyclerView.setItemViewCacheSize(20);
        cartItemsRecyclerView.setDrawingCacheEnabled(true);
        cartItemsRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        cartItemsRecyclerView.setNestedScrollingEnabled(false);
    }

    public void checkItemCount() {
        if (cartItemsAdapter.getItemCount() == 0) {
            cartIsEmptyTxt.setVisibility(View.VISIBLE);
            cartItemsRecyclerView.setVisibility(View.GONE);
        } else {
            cartIsEmptyTxt.setVisibility(View.GONE);
            cartItemsRecyclerView.setVisibility(View.VISIBLE);
        }
    }


    public void initialFindViews(){
        progressBar = (ProgressBar) findViewById(R.id.shopping_card_progressbar_id);
        toolbar = (Toolbar) findViewById(R.id.shopping_card_activity_toolbar);
        cartIsEmptyTxt = (TextView) findViewById(R.id.cart_is_empty_txt_id);
        motherLayout = (CoordinatorLayout) findViewById(R.id.beforehome_mother_drawer_layout);
        totalPriceinTitle = (TextView) findViewById(R.id.totla_price_in_title_txt_id);
        totalQuantityinTitle = (TextView) findViewById(R.id.totla_quantity_in_title_txt_id);
        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        cartItemsRecyclerView = (RecyclerView) findViewById(R.id.shopping_card_activity_recycler);
        layoutBtn = (LinearLayout) findViewById(R.id.shopping_card_bottom_layout_btn_id);
    }
    public void InitialAPI_RequestService(){
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(), progressWrapper).create(CartAPIInterface.class);

    }
}
