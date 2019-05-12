package com.example.aalizade.mbazar_base_app.activities.products_related;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.pager_adapters.MyCustomPagerAdapter;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter.ProductBriefDescriptionAdapter;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter.vendorsOtherConditionsAdapter;
import com.example.aalizade.mbazar_base_app.bottom_sheets.ProductOtherProducersBottomSheet;
import com.example.aalizade.mbazar_base_app.entities.BriefSpecificationEntity;
import com.example.aalizade.mbazar_base_app.network.apiInterface.ProductAPIInterace;
import com.example.aalizade.mbazar_base_app.network.models.product.FindByVitrinModel;
import com.example.aalizade.mbazar_base_app.network.models.product.GeneralAttributeModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeFullFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeLine;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeSearchRealatedFrontModel;
import com.example.aalizade.mbazar_base_app.network.models.product.RelatedProductTypeFullPageModel;
import com.example.aalizade.mbazar_base_app.network.models.product.VendorOtherConditionsCustomModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.customComponents.CustomHorizontalProductRecyclerview;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.rd.PageIndicatorView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProductMainPageActivity extends AppCompatActivity {

    Toolbar toolbar;
    ProductBriefDescriptionAdapter briefDescriptionAdapter;
    String TAG = "PRODUCTMAINACTIVITY";
    int color = 1;
    RecyclerView optionsRecycler,otherConditionsRecycler;
    vendorsOtherConditionsAdapter vendorsOtherConditionsAdapter;
    ProductAPIInterace productAPIInterace;
    CoordinatorLayout motherLayout;
    ViewPager viewPager;
    PageIndicatorView pageIndicatorView;
    TextView moreTextView;
    Button otherVendorsBtn, userViewsBtn, SpecitficationButton, checkAndReviewBtn,otherConditionsBtn;
    ProductTypeFullFrontModel productTypeFullFrontModel;
    TextView prd_name_txt, prd_transport_option_txt, prd_currentPrice_txt, prd_depricatedPrice_txt, prd_rate_num_txt, brief_describtion_txt;
    SimpleRatingBar prd_rate_bar;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    List<ProductTypeBriefFrontModel> accessoryProductsList, relatedProductsList, RelatedBasketProducsList;
    View vendorsOtherConditionDialogLayout,vendorsOtherConditionLayoutTitle;
    AlertDialog vendorsOtherConditionsDialog;
    List<VendorOtherConditionsCustomModel> otherConditionsList;

    CustomHorizontalProductRecyclerview prd_byproducts_customRecycler,prd_related_products_customRecycler,prd_related_product_baskets_customRecycler;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main_page);
        initialFindViews();
        InitialAPI_RequestService();

        setUpToolbar(toolbar);

        accessoryProductsList = new ArrayList<>();
        relatedProductsList = new ArrayList<>();
        RelatedBasketProducsList = new ArrayList<>();
        otherConditionsList = new ArrayList<>();

        getProductData();
        getBottomProductsLists();

    }

    public void setUpToolbar(final Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        final Toolbar toolbar1 = toolbar;
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toolbar.getMenu().getItem(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
//                toolbar.getMenu().getItem(1).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                toolbar.getMenu().findItem(R.id.action_shopping_card_ic_id).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_main_page_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite_ic_id) {
            Toast.makeText(getApplicationContext(), "Shopping Card", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void GoToRelatedTab(View view) {         //رفتن به تب مرتبط با کلیک روی دکمه های موجود در صفحه - مشخصات/نظرات/...
        Intent intent = new Intent(ProductMainPageActivity.this, Specifications_Explanation_ViewsActivity.class);
        if (view.getTag().toString().equals("read_more_tag")) {
            intent.putExtra("tab_item", "2");
        } else if (view.getTag().toString().equals("spefics_tag")) {
            intent.putExtra("tab_item", "1");
        } else if (view.getTag().toString().equals("user_views_tag")) {
            intent.putExtra("tab_item", "0");
        }
        startActivity(intent);
    }

    public void initSpecifications() {  //فقط ایندکس های 0 7 استفاده شده اند
        optionsRecycler = (RecyclerView) findViewById(R.id.brief_specifications_recycler_id);
        ArrayList<BriefSpecificationEntity> briefSpecificationEntities = new ArrayList();
        briefSpecificationEntities.add(new BriefSpecificationEntity("عرضه کننده", productTypeFullFrontModel.getVendor_name()));

        System.out.println("filter hashmap" + productTypeFullFrontModel.getFilterModelHashMap().toString());

        Iterator iter = productTypeFullFrontModel.getFilterModelHashMap().get(0).getAttributeModelSet().iterator();
        GeneralAttributeModel generalAttributeModel = (GeneralAttributeModel) iter.next();
        briefSpecificationEntities.add(new BriefSpecificationEntity(productTypeFullFrontModel.getFilterModelHashMap().get(0).getTitle(), generalAttributeModel.getTitle()));

        Iterator iter2 = productTypeFullFrontModel.getFilterModelHashMap().get(7).getAttributeModelSet().iterator();
        GeneralAttributeModel generalAttributeModel2 = (GeneralAttributeModel) iter2.next();
        briefSpecificationEntities.add(new BriefSpecificationEntity(productTypeFullFrontModel.getFilterModelHashMap().get(7).getTitle(), generalAttributeModel2.getTitle()));

        briefDescriptionAdapter = new ProductBriefDescriptionAdapter(getApplicationContext(), briefSpecificationEntities);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        optionsRecycler.setLayoutManager(linearLayoutManager);
        optionsRecycler.setAdapter(briefDescriptionAdapter);
        optionsRecycler.setHasFixedSize(true);
        optionsRecycler.setItemViewCacheSize(20);
        optionsRecycler.setDrawingCacheEnabled(true);
        optionsRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        optionsRecycler.setNestedScrollingEnabled(false);
    }

    public void DrawPageInResponse() {
        final int size = productTypeFullFrontModel.getSliderModel().getHashedPathList().size();

        final List<String> imageUrls = new ArrayList<>();
        for (int i = 0; i < size; i++) {        //آدرس تصاویر هر کالا که در پیجر نمایش داده میشود
            imageUrls.add(getString(R.string.prd_image_pager_url) + productTypeFullFrontModel.getSliderModel().getHashedPathList().get(i) + "/");
        }
        final MyCustomPagerAdapter myCustomPagerAdapter = new MyCustomPagerAdapter(ProductMainPageActivity.this, imageUrls);
        viewPager.setAdapter(myCustomPagerAdapter);

        pageIndicatorView.setViewPager(viewPager);

        //depricated text price
        prd_depricatedPrice_txt.setPaintFlags(prd_depricatedPrice_txt.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        moreTextView.setPaintFlags(moreTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        prd_name_txt.setText(productTypeFullFrontModel.getProduct_title());
        prd_currentPrice_txt.setText(productTypeFullFrontModel.getThisProductType().getUnitPriceTaxIncludeDiscountInclude());
        prd_depricatedPrice_txt.setText(productTypeFullFrontModel.getThisProductType().getUnitPriceTaxInclude());
        prd_rate_num_txt.setText(getResources().getString(R.string.product_rate_out_out_5, productTypeFullFrontModel.getRate()));
        if (!productTypeFullFrontModel.getRate().toString().equals(""))
            prd_rate_bar.setRating(Float.valueOf(productTypeFullFrontModel.getRate()));
//        brief_describtion_txt.setText(productTypeFullFrontModel.disasdasd());
        //depricated text price

        final Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "بازار اعضا - http://www.mbazar.net"); // Simple text and URL to share
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "http://www.mbazar.net/shop/809/#/product/2"); // Simple text and URL to share
        sendIntent.setType("text/plain");
        ImageButton shareBtn = (ImageButton) findViewById(R.id.prd_share_btn_id);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(sendIntent);
            }
        });

        final ImageButton likeBtn = (ImageButton) findViewById(R.id.prd_like_btn_id);
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (color == 1) {
                    likeBtn.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                    color++;
                } else {
                    likeBtn.setColorFilter(Color.DKGRAY, PorterDuff.Mode.SRC_IN);
                    color--;
                }
            }
        });

        //add to cart button

        otherVendorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {           // سایر عرضه کنندگان محصول
                Bundle bundle = new Bundle();
                bundle.putString("product_id", productTypeFullFrontModel.getProduct_id());
                                                                                                    //دیگر نیازی به فرگمنت افزودن به سبد خرید در این قسمت نیست
//                BottomSheetDialogFragment bottomSheetDialogFragment = new AddToCartBottomSheet();//todo review this with masoud
//                bottomSheetDialogFragment.setArguments(bundle);
//                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());

                BottomSheetDialogFragment bottomSheetDialogFragment = new ProductOtherProducersBottomSheet();
                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
            }
        });
        //add to cart button

        checkAndReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductMainPageActivity.this, WebViewActivity.class));
            }
        });
        //check and review in webview btn

        //init brief specs recyclerview
        initSpecifications(); //todo get data out of hashmap model
        //init brief specs recyclerview

        //upperClass
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {        //عملیات مربوط به جمع شدن قسمت بالایی لایوت
                if (scrollRange == -1) {                                                        //تغییر رنگ آیتم های منو هنگام اسکرول کردن به بالاترین سطح
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitleEnabled(true);
                    toolbar.getNavigationIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    toolbar.getOverflowIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    toolbar.getMenu().getItem(0).getIcon().setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
                    collapsingToolbarLayout.setCollapsedTitleGravity(Gravity.RIGHT);
                    collapsingToolbarLayout.setTitle(((TextView) (findViewById(R.id.perisan_name_txt_id))).getText());
                    isShow = true;
                } else if (isShow) {
                    toolbar.getNavigationIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                    toolbar.getOverflowIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                    toolbar.getMenu().getItem(0).getIcon().setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
                    collapsingToolbarLayout.setTitleEnabled(false);
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
        //upperClass

        //other conditions list dialog
        LayoutInflater inflater = LayoutInflater.from(this);
        vendorsOtherConditionDialogLayout = inflater.inflate(R.layout.vendor_other_condition_layout, null);
        vendorsOtherConditionLayoutTitle = inflater.inflate(R.layout.vendor_other_condition_dialog_title, null);
        AlertDialog.Builder otherConditionsAlert = new AlertDialog.Builder(this);
        otherConditionsAlert.setView(vendorsOtherConditionDialogLayout);
        otherConditionsAlert.setCustomTitle(vendorsOtherConditionLayoutTitle);
        otherConditionsAlert.setCancelable(false);
        vendorsOtherConditionsDialog = otherConditionsAlert.create();
        vendorsOtherConditionsDialog.setCanceledOnTouchOutside(true);
        vendorsOtherConditionsDialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        otherConditionsRecycler = (RecyclerView)vendorsOtherConditionDialogLayout.findViewById(R.id.vendor_other_conditions_recycler_id);
        setVendorOtherConditionsAdapter();
//        //other conditions list dialog
        otherConditionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vendorsOtherConditionsDialog.show();
            }
        });
    }

    //requests
    public void getProductData() { //گرفتن اطلاعات کلی محصول
        FindByVitrinModel findByVitrinModel = new FindByVitrinModel();
        findByVitrinModel.setId("21");//todo set product id -> GlobalVariables.selectedProductID
        findByVitrinModel.setVitrinId(GlobalVariables.selectedCity);
        System.out.println("prd full model-> " + findByVitrinModel.toString());
        Call<ProductTypeFullFrontModel> call = productAPIInterace.getProductData(findByVitrinModel);
        call.enqueue(new CallbackWithRetry<ProductTypeFullFrontModel>(call, ProductMainPageActivity.this, motherLayout) {
            @Override
            public void onResponse(Call<ProductTypeFullFrontModel> call, Response<ProductTypeFullFrontModel> response) {
                if (response.isSuccessful()) {
                    productTypeFullFrontModel = response.body();
//                    Log.d("Got prd full model", productTypeFullFrontModel.toString());
//                    Log.d("product Attributes", new Gson().toJson(productTypeFullFrontModel.getAttributeGroupList()));

                    for (ProductTypeLine productTypeLine : productTypeFullFrontModel.getOtherProductTypeList()){
                        VendorOtherConditionsCustomModel customModel = new VendorOtherConditionsCustomModel();
                        customModel.setProductId(productTypeLine.getId());
                        customModel.setPrice(productTypeLine.getUnitPriceTaxIncludeDiscountInclude());
                        customModel.setGuarantyName(productTypeLine.getAttributeModelHashMap().get(0).getTitle());
                        customModel.setGuarantyCode(productTypeLine.getAttributeModelHashMap().get(0).getId());
                        customModel.setColorName(productTypeLine.getAttributeModelHashMap().get(7).getTitle());
                        customModel.setColorCode(productTypeLine.getAttributeModelHashMap().get(7).getId());
                        otherConditionsList.add(customModel);
                    }
                    DrawPageInResponse();
                } else {
                    Toast.makeText(getApplicationContext(), "اطلاعات دریافت نشد", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("Fail Combo1", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void getBottomProductsLists() {      //دریافت لیست محصولات نمایش داده شده در پایین صفحه
        ProductTypeSearchRealatedFrontModel productTypeSearchRealatedFrontModel = new ProductTypeSearchRealatedFrontModel();
        productTypeSearchRealatedFrontModel.setProductTypeId("21");
        productTypeSearchRealatedFrontModel.setVitrinId(GlobalVariables.selectedCity);
        productTypeSearchRealatedFrontModel.setPage("0");
        productTypeSearchRealatedFrontModel.setRows("10");

        Call<RelatedProductTypeFullPageModel> call = productAPIInterace.getProductPageRelatedProducts(productTypeSearchRealatedFrontModel);
        call.enqueue(new CallbackWithRetry<RelatedProductTypeFullPageModel>(call, ProductMainPageActivity.this, motherLayout) {
            @Override
            public void onResponse(Call<RelatedProductTypeFullPageModel> call, Response<RelatedProductTypeFullPageModel> response) {
                if (response.isSuccessful()) {

                    accessoryProductsList = response.body().getAccessories().getProductTypeFrontModelList();
                    relatedProductsList = response.body().getRelatedProducts().getProductTypeFrontModelList();
                    RelatedBasketProducsList = response.body().getIsBasketRelatedProducts().getProductTypeFrontModelList();

                    prd_byproducts_customRecycler.configAdapter("محصولات جانبی",accessoryProductsList);
                    prd_related_products_customRecycler.configAdapter("محصولات مرتبط",relatedProductsList);
                    prd_related_product_baskets_customRecycler.configAdapter("سبدهای کالایی مرتبط",RelatedBasketProducsList);

                } else {
                    Toast.makeText(getApplicationContext(), "اطلاعات دریافت نشد", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("Fail Combo1", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public void setVendorOtherConditionsAdapter(){          //سایر شرایط عرضه کننده
        vendorsOtherConditionsAdapter = new vendorsOtherConditionsAdapter(getApplicationContext(), otherConditionsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        otherConditionsRecycler.setLayoutManager(linearLayoutManager);
        otherConditionsRecycler.setAdapter(vendorsOtherConditionsAdapter);
        otherConditionsRecycler.setHasFixedSize(true);
        otherConditionsRecycler.setItemViewCacheSize(20);
        otherConditionsRecycler.setDrawingCacheEnabled(true);
        otherConditionsRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        otherConditionsRecycler.setNestedScrollingEnabled(false);
        vendorsOtherConditionsAdapter.notifyDataSetChanged();
    }


    public void initialFindViews(){
        toolbar = (Toolbar) findViewById(R.id.product_main_page_toolbar);
        motherLayout = (CoordinatorLayout) findViewById(R.id.product_page_mother_layout_id);
        appBarLayout = (AppBarLayout) findViewById(R.id.prd_main_home_appbar_layout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.prd_main_home_collapse_layout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);

        moreTextView = (TextView) findViewById(R.id.describe_more_txt_id);
        otherVendorsBtn = (Button) findViewById(R.id.other_vendors_btn_id);
        otherConditionsBtn = (Button) findViewById(R.id.other_conditions_btn_id);
        //goto spec actvity
        userViewsBtn = (Button) findViewById(R.id.user_views_btn_id);
        SpecitficationButton = (Button) findViewById(R.id.specifications_btn_id);
        //goto spec actvity
        //check and review in webview btn
        checkAndReviewBtn = (Button) findViewById(R.id.check_and_review_product_btn_id);

        //textViews
        prd_name_txt = (TextView) findViewById(R.id.perisan_name_txt_id);
        prd_transport_option_txt = (TextView) findViewById(R.id.transport_cond_option_txt_id);
        prd_currentPrice_txt = (TextView) findViewById(R.id.currentprice_txt_id);
        prd_depricatedPrice_txt = (TextView) findViewById(R.id.depricatedprice_txt_id);
        prd_rate_bar = (SimpleRatingBar) findViewById(R.id.prd_rateBar_id);
        prd_rate_num_txt = (TextView) findViewById(R.id.rate_num_txt_id);
        brief_describtion_txt = (TextView) findViewById(R.id.describe_txt_id);
        //textViews

        prd_byproducts_customRecycler = (CustomHorizontalProductRecyclerview)findViewById(R.id.prd_byproducts_customRecycler_id);
        prd_related_products_customRecycler = (CustomHorizontalProductRecyclerview)findViewById(R.id.prd_related_products_customRecycler_id);
        prd_related_product_baskets_customRecycler = (CustomHorizontalProductRecyclerview)findViewById(R.id.prd_related_product_baskets_customRecycler_id);
    }
    public void InitialAPI_RequestService(){
        productAPIInterace = RetrofitClient.getclient(motherLayout).create(ProductAPIInterace.class);
    }

}


