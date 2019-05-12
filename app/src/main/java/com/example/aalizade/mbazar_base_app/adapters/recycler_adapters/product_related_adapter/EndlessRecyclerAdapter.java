package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.products_related.ProductMainPageActivity;
import com.example.aalizade.mbazar_base_app.activities.products_related.ProductsMainListsActivity;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IBadgeUpdate;
import com.example.aalizade.mbazar_base_app.utility.MySnackBar;
import com.example.aalizade.mbazar_base_app.network.Globals;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeBriefFrontModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aalizade on 3/17/2018.
 */

public class EndlessRecyclerAdapter extends RecyclerView.Adapter {
    String selectedCityId, selectedDepartmentId, selectedTypeId;
    private final int ITEM_VIEW_TYPE_BASIC = 0;
    private final int ITEM_VIEW_TYPE_FOOTER = 1;
    boolean value;
    private Context context;
    RequestOptions options;
    RecyclerView recyclerView;
    String prd_img_url;
    CartAPIInterface cartAPIInterface, cartAPIInterfaceWithoutToken;
    LinearLayout progressWrapper;
    Activity activity;
    private Boolean quantityChanged = false;

    private List<ProductTypeBriefFrontModel> products;

    public EndlessRecyclerAdapter(List<ProductTypeBriefFrontModel> products, RecyclerView recyclerView, Context context,
                                  String selectedCityId, String selectedDepartmentId, String selectedTypeId, LinearLayout motherLayout) {
        this.products = products;
        this.context = context;
        prd_img_url = context.getString(R.string.small_cover_url);
        this.recyclerView = recyclerView;
        this.selectedTypeId = selectedTypeId;
        this.selectedCityId = selectedCityId;
        this.selectedDepartmentId = selectedDepartmentId;
        this.progressWrapper = motherLayout;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.mbazar_128p_gray_logo)
                .override(64, 64)
                .error(R.drawable.error_getting_data_from_server_128_png);
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(context, motherLayout).create(CartAPIInterface.class);
        cartAPIInterfaceWithoutToken = RetrofitClient.getclient(motherLayout).create(CartAPIInterface.class);
        activity = (Activity) context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        if (viewType == ITEM_VIEW_TYPE_BASIC) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.products_two_grid_view_card, parent, false);

            MBZ_Token_Prefs.initTokenSharedPrefs(context);

            vh = new CardViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.progressbar, parent, false);

            vh = new ProgressViewHolder(v);
        }

        return vh;
    }

    public void refreshAdapter(boolean value, List<ProductTypeBriefFrontModel> productItems) {
        this.value = value;
        this.products = productItems;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CardViewHolder) {
            final ProductTypeBriefFrontModel singleItem = (ProductTypeBriefFrontModel) products.get(position);
            //glide

            String img_url = prd_img_url.replace("{id}", singleItem.getId().toString());
            Glide.with(context)
                    .load(img_url)
                    .apply(options)
                    .into(((CardViewHolder) holder).productImage);

//        .apply(options)
            //glide
            ((CardViewHolder) holder).faName.setText(singleItem.getTitle());
            ((CardViewHolder) holder).productPriceWithoutDiscount.setText(String.valueOf(singleItem.getUnitPriceTaxInclude()));
            ((CardViewHolder) holder).productPriceWithDiscount.setText(String.valueOf(singleItem.getUnitPriceTaxInclude()));

            if (((CardViewHolder) holder).productPriceWithoutDiscount.getText().equals(((CardViewHolder) holder).productPriceWithDiscount.getText())) {
                ((CardViewHolder) holder).discountPriceLayout.setVisibility(View.GONE);
            }

            ((CardViewHolder) holder).touchLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.startActivity(new Intent(activity, ProductMainPageActivity.class));
                }
            });

            ((CardViewHolder) holder).productImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GridLayoutManager layoutManager = ((GridLayoutManager) recyclerView.getLayoutManager());
                    int firstVisiblePosition = layoutManager.findFirstVisibleItemPosition();
                    Toast.makeText(context, String.valueOf(layoutManager.findLastCompletelyVisibleItemPosition()) + " # " + String.valueOf(getItemCount()), Toast.LENGTH_SHORT).show();
                    activity.startActivity(new Intent(activity, ProductMainPageActivity.class));
                }
            });

//            ((CardViewHolder) holder).addToCartBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (MBZ_Token_Prefs.isAuthorized()) {
//                        CartProductTypeModel cartProductTypeModel = new CartProductTypeModel();
//                        cartProductTypeModel.setProductType_id(singleItem.getId());//todo
//                        Set<CartProductTypeModel> cartProductTypeModels = new HashSet<>();
//                        cartProductTypeModels.add(cartProductTypeModel);
//                        cartProductTypeModel.setQuantity("1");
//                        for (Map.Entry<String, HashMap<String, Set<CartProductTypeModel>>> entry : GlobalVariables.LocalCart.getCartProductTypeModelHashMap().entrySet()) {
//                            for (Map.Entry<String, Set<CartProductTypeModel>> entry2 : entry.getValue().entrySet()) {
//                                for (CartProductTypeModel entry3 : entry2.getValue()) {
//                                    if (entry3.getProductType_id().equals(singleItem.getId())) {
//                                        cartProductTypeModel.setQuantity(String.valueOf(Integer.valueOf(entry3.getQuantity()) + 1));
//                                    }
//                                }
//                            }
//                        }
//
//                        // -------------------------create hashmaps
//                        HashMap<String, Set<CartProductTypeModel>> integerSetHashMap = new HashMap<>();
//                        integerSetHashMap.put("0", cartProductTypeModels);
//                        //-------------------------create hashmaps
//                        HashMap<String, HashMap<String, Set<CartProductTypeModel>>> integerHashMapHashMap = new HashMap<>();
//                        integerHashMapHashMap.put(String.valueOf(singleItem.getVendor_id()), integerSetHashMap);
//
//                        Gson gson = new Gson();
//                        System.out.println("____)))) " + gson.toJson(integerHashMapHashMap));
//
//                        GlobalVariables.LocalCart.getCartProductTypeModelHashMap().clear();
//                        GlobalVariables.LocalCart.setCartProductTypeModelHashMap(integerHashMapHashMap);
//                        addItemToCart(GlobalVariables.LocalCart);
//                        //***************************************************************************new method
//                    } else {
//                        //not authorized
//                        if (GlobalVariables.LocalUnAthourizedSENTCart == null) {
//                            //todo unauthorized
//                            GlobalVariables.LocalUnAthourizedSENTCart = new CustomBaghBaghuModel();
//                            String clientRevision = String.valueOf(Math.floor((Math.random() * 99999) + 10000));
//                            GlobalVariables.LocalUnAthourizedSENTCart.setClientRevision(clientRevision.substring(0, clientRevision.lastIndexOf(".")));
//                            GlobalVariables.LocalUnAthourizedSENTCart.setVitrin_id(GlobalVariables.selectedCity);
//                            GlobalVariables.LocalUnAthourizedSENTCart.setId("");
//                            //todo unauthorized
//                        }
////                        CustomBaghBaghuModel customBaghBaghuModel = new CustomBaghBaghuModel();
////                        adsfasdfdsf
////                        if (GlobalVariables.LocalUnAthourizedCart == null) {
////                            String clientRevision = String.valueOf(Math.floor((Math.random() * 99999) + 10000));
////                            customBaghBaghuModel.setClientRevision(clientRevision.substring(0, clientRevision.lastIndexOf(".")));
////                        } else {
////                            customBaghBaghuModel.setClientRevision(GlobalVariables.LocalUnAthourizedCart.getClientRevision());
////                        }
//
//
//                        CartBaghuBriefModel cartBaghuBriefModel = new CartBaghuBriefModel();
//                        cartBaghuBriefModel.setProductType_id(singleItem.getId());//todo custom model is not acceptable
//                        cartBaghuBriefModel.setQuantity("1");
//
//                        Set<CartBaghuBriefModel> BAGHUBRIEFSET = new HashSet<>();
//                        BAGHUBRIEFSET.add(cartBaghuBriefModel);
//
//                        Set<String> vendorIDList = new HashSet<>();
//
//                        if (GlobalVariables.LocalCart != null) {
//                            for (Map.Entry<String, HashMap<String, Set<CartProductTypeModel>>> entry : GlobalVariables.LocalCart.getCartProductTypeModelHashMap().entrySet()) {
//                                for (Map.Entry<String, Set<CartProductTypeModel>> entry2 : entry.getValue().entrySet()) {
//                                    for (CartProductTypeModel entry3 : entry2.getValue()) {
//                                        vendorIDList.add(entry3.getProductType_vendor_id());
//                                        if (entry3.getProductType_id().equals(singleItem.getId())) {
//                                            cartBaghuBriefModel.setQuantity(String.valueOf(Integer.valueOf(entry3.getQuantity()) + 1));
//                                            quantityChanged = true;
//                                        }
//                                    }
//                                }
//                            }
//                        }
//
//                        // -------------------------create hashmaps
//                        HashMap<String, Set<CartBaghuBriefModel>> integerSetHashMap = new HashMap<>();
//                        integerSetHashMap.put("0", BAGHUBRIEFSET);
//                        //-------------------------create hashmaps
//                        HashMap<String, HashMap<String, Set<CartBaghuBriefModel>>> integerHashMapHashMap = new HashMap<>();
//                        integerHashMapHashMap.put(String.valueOf(singleItem.getVendor_id()), integerSetHashMap);
//
//
//                        //adding data
////                        for (int i = 0; i < vendorIDList.size(); i++) {
////                        }
//                        System.out.println("VENDORS => "+vendorIDList.toString());
//
//                        if (!vendorIDList.contains(String.valueOf(singleItem.getVendor_id()))){
//                            GlobalVariables.LocalUnAthourizedSENTCart.getCartProductTypeModelHashMap().put(String.valueOf(singleItem.getVendor_id()), integerSetHashMap);
//                            System.out.println("Shamel Nist");
//                        }
//                        else if (vendorIDList.contains(String.valueOf(singleItem.getVendor_id())) && !quantityChanged){
//                            GlobalVariables.LocalUnAthourizedSENTCart.getCartProductTypeModelHashMap().get(String.valueOf(singleItem.getVendor_id())).get("0").add(cartBaghuBriefModel);
//                            System.out.println("Shamel Hast - Tedad Nist");
//                        }
//                        else if (quantityChanged){
//                            System.out.println("TEDAD");
//                            System.out.println("ITEM "+cartBaghuBriefModel.toString());
//                            GlobalVariables.LocalUnAthourizedSENTCart.getCartProductTypeModelHashMap().get(String.valueOf(singleItem.getVendor_id())).get("0").remove(cartBaghuBriefModel);
//                            GlobalVariables.LocalUnAthourizedSENTCart.getCartProductTypeModelHashMap().get(String.valueOf(singleItem.getVendor_id())).get("0").add(cartBaghuBriefModel);
////                            GlobalVariables.LocalUnAthourizedSENTCart.getCartProductTypeModelHashMap().put(String.valueOf(singleItem.getVendor_id()), integerSetHashMap);
//                        }
//                        quantityChanged = false;
//                        //adding data
//
////                        if (GlobalVariables.LocalUnAthourizedSENTCart != null){
////                            System.out.println("(BEFORE): " + new Gson().toJson(GlobalVariables.LocalUnAthourizedSENTCart.getCartProductTypeModelHashMap()));
////                            GlobalVariables.LocalUnAthourizedSENTCart.getCartProductTypeModelHashMap().get(0).dsfads
////                            System.out.println("(AFTER): " + new Gson().toJson(GlobalVariables.LocalUnAthourizedSENTCart.getCartProductTypeModelHashMap()));
////                        }
//
//
//                        Gson gson = new Gson();
//                        System.out.println("(SENT): " + gson.toJson(GlobalVariables.LocalUnAthourizedSENTCart));
//
//                        addItemToCartWithoutToken(GlobalVariables.LocalUnAthourizedSENTCart);
//                        //not authorized
//                    }
//
//                }
//            });

        } else {
            if (!value) {
                ((ProgressViewHolder) holder).progressBar.setVisibility(View.VISIBLE);
                ((ProgressViewHolder) holder).progressBar.setIndeterminate(true);
            } else {
                ((ProgressViewHolder) holder).progressBar.setVisibility(View.GONE);
            }
        }


    }

    @Override
    public int getItemViewType(int position) {
        return products.get(position) != null ? ITEM_VIEW_TYPE_BASIC : ITEM_VIEW_TYPE_FOOTER;
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView faName;
        private TextView productPriceWithoutDiscount;
        private TextView productPriceWithDiscount;
        private Button addToCartBtn;
        private RelativeLayout touchLayout;
        LinearLayout discountPriceLayout;

        public CardViewHolder(View itemView) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.card_image_id);
            faName = (TextView) itemView.findViewById(R.id.card_persian_title_id);
            productPriceWithoutDiscount = (TextView) itemView.findViewById(R.id.prd_price_without_discount_txt_id);
            productPriceWithoutDiscount.setPaintFlags(productPriceWithoutDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            productPriceWithDiscount = (TextView) itemView.findViewById(R.id.prd_price_with_discount_txt_id);
            addToCartBtn = (Button) itemView.findViewById(R.id.item_add_to_cart_btn_id);
            touchLayout = (RelativeLayout) itemView.findViewById(R.id.root_layout_id);
            discountPriceLayout = (LinearLayout) itemView.findViewById(R.id.price_without_discount_layout_id);
        }
    }

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        }
    }


    public void addItemToCart(final CartModel cartModel) {
        Call<CartModel> call = cartAPIInterface.addItemToCartRequest(cartModel);
//        ProgressBarShower.StartMyProgressBar(activity, progressWrapper);
        call.enqueue(new CallbackWithRetry<CartModel>(call, activity, progressWrapper) {
            @Override
            public void onResponse(Call<CartModel> call, Response<CartModel> response) {
//                ProgressBarShower.StopMyProgressBar(activity, progressWrapper);
                if (response.isSuccessful()) {
                    CartModel cartModel1 = response.body();
                    System.out.println("resp----> " + cartModel);
                    Gson gson = new Gson();
                    System.out.println("resp==> " + gson.toJson(cartModel1));
                    MySnackBar.snackBarWithNoAction("به سبد خرید اضافه شد", progressWrapper);
//                    MySnackBar.snackBarWithNoAction("به سبد خرید اضافه شد",progressWrapper);

                    Globals.updateBasketItems(cartAPIInterface, activity, progressWrapper, new IBadgeUpdate() {
                        @Override
                        public void doUpdate() {
                            ProductsMainListsActivity.UPDATE_CART_NUMBER(activity);
                        }
                    });
                } else {
                    try {
                        Log.d("MYERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(context, "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    public void addItemToCartWithoutToken(final CustomBaghBaghuModel customBaghBaghuModel) {
//        Call<CartModel> call = cartAPIInterfaceWithoutToken.addItemToCartRequestWithoutToken(customBaghBaghuModel);
//        call.enqueue(new CallbackWithRetry<CartModel>(call, activity, progressWrapper) {
//            @Override
//            public void onResponse(Call<CartModel> call, Response<CartModel> response) {
//                if (response.isSuccessful()) {
//                    GlobalVariables.LocalCart = response.body();
//                    System.out.println("(GOTTEN): " + new Gson().toJson(GlobalVariables.LocalCart));
//                    MySnackBar.snackBarWithNoAction("به سبد خرید اضافه شد", progressWrapper);
//                    //todo update badge
//                    Globals.CountUnAuthorizedCartItems();
//                    ProductsMainListsActivity.UPDATE_CART_NUMBER(activity);
//
//                } else {
//                    try {
//                        Log.d("RECIEVED ERROR", response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Toast.makeText(context, "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
}
