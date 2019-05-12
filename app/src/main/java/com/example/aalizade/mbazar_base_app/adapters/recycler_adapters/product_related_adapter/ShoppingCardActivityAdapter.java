package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.products_related.ShoppingCardActivity;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartProductTypeModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by aalizade on 11/13/2017.
 */

public class ShoppingCardActivityAdapter extends RecyclerView.Adapter<ShoppingCardActivityAdapter.NewsViewHolder> {
    private Context context;
    private List<CartProductTypeModel> products;
    private Boolean isVisible = true;
    Animation fadeIn;
    CartAPIInterface cartAPIInterface;
    Activity activity;
    LinearLayout linearLayout;
    String prd_img_url;
    RequestOptions options;


    public ShoppingCardActivityAdapter(Context context, List<CartProductTypeModel> products, LinearLayout linearLayout) {
        fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        this.context = context;
        prd_img_url = context.getString(R.string.small_cover_url);
        this.products = products;
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(context, linearLayout).create(CartAPIInterface.class);
        activity = (Activity) context;
        this.linearLayout = linearLayout;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.mbazar_128p_gray_logo)
                .override(200, 200)
                .error(R.drawable.error_getting_data_from_server_128_png);

        MBZ_Token_Prefs.initTokenSharedPrefs(context);
    }

    @Override
    public ShoppingCardActivityAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a_temp_basket_item, parent, false);
        return new ShoppingCardActivityAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShoppingCardActivityAdapter.NewsViewHolder holder, final int position) {
        final CartProductTypeModel product = products.get(position);

        String img_url = prd_img_url.replace("{id}", product.getProductType_id());
        Glide.with(context)
                .load(img_url)
                .apply(options)
                .into(holder.productImage);

        holder.product_Title.setText(product.getProductType_title());
        holder.producerNameTxt.setText(product.getProductType_vendor_name());

        //set prices
        holder.depricatedPriceWithTax.setText(product.getProductTypeQuantityPrice());
        holder.priceWithDiscountandTax.setText(String.valueOf(Integer.parseInt(product.getProductType_unitPriceTaxInclude()) * Integer.parseInt(product.getQuantity())));
        holder.unitPriceWithTaxandDFiscount.setText(product.getProductType_unitPriceTaxInclude());
        holder.depricatedUnitPriceWithTaxOnly.setText(product.getProductTypeUnitPriceTaxIncludeDiscountInclude());
        //set prices
        if (product.getDiscountTotalPercentage().equals("0.0")) {
            holder.discountLayout.setVisibility(View.GONE);
            holder.depricatedPriceWithTax.setVisibility(View.GONE);
            holder.depricatedUnitPriceWithTaxOnly.setVisibility(View.GONE);
        } else {
            holder.discountNames.setText(product.getDiscountVendorTitle() + " " + product.getDiscountMBazarTitle());
            holder.totalDiscountPercentage.setText(product.getDiscountTotalPercentage() + "%");
        }

//        productQuantityNumber = Integer.parseInt(product.getQuantity());
        holder.productQuantityTxt.setText(product.getQuantity());

        holder.deleteImgBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MBZ_Token_Prefs.isAuthorized()) {
//                    removeItemFromCart(product);
                } else {
                    products.remove(product);
                    notifyDataSetChanged();
//                    removeFromLocal(product);
//                    Globals.CountUnAuthorizedCartItems();//todo uncomment cartmodel changed
                }
            }
        });
        holder.pricemenusTrigerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                holder.expand_arrow.setRotation(holder.expand_arrow.getRotation() + 180);
                if (isVisible) {
                    holder.pricemenusLayout.setVisibility(View.GONE);
                } else {
                    holder.pricemenusLayout.setVisibility(View.VISIBLE);
                    holder.pricemenusLayout.startAnimation(fadeIn);
                }
            }
        });

        //control quantity
        holder.increaseImgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(product.getQuantity()) < 20) {
                    if (MBZ_Token_Prefs.isAuthorized()) {
//                        addItemToCart(product, Integer.parseInt(product.getQuantity()) + 1);
                    } else {
//                        product.setQuantity(String.valueOf(Integer.parseInt(product.getQuantity()) + 1));
//                        increaseLocalQuantity(product);
                        //todo update quantity and price
                        updateQuantityandList();
                        //todo update quantity and price
                        notifyDataSetChanged();
                    }
                }
            }
        });
        holder.decreaseImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.parseInt(product.getQuantity()) > 1) {
                    if (MBZ_Token_Prefs.isAuthorized()) {
//                        addItemToCart(product, Integer.parseInt(product.getQuantity()) - 1);
                    } else {
//                        product.setQuantity(String.valueOf(Integer.parseInt(product.getQuantity()) - 1));
//                        decreaseLocalQuantity(product);
                        //todo update quantity and price
                        updateQuantityandList();
                        //todo update quantity and price
                        notifyDataSetChanged();
                    }
                }
            }
        });
        //control quantity
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        //        private TextView enName;
        private TextView product_Title, depricatedPriceWithTax, priceWithDiscountandTax;
        private TextView unitPriceWithTaxandDFiscount, depricatedUnitPriceWithTaxOnly;

        private TextView producerNameTxt;
        private TextView productQuantityTxt;

        private TextView discountNames;
        private TextView totalDiscountPercentage;

        private ImageView deleteImgBtm, expand_arrow;
        private RelativeLayout pricemenusTrigerlayout, pricemenusLayout;

        private ImageView increaseImgbtn, decreaseImgBtn;
        private LinearLayout discountLayout;

        public NewsViewHolder(View itemView) {
            super(itemView);
            discountLayout = (LinearLayout) itemView.findViewById(R.id.discount_layout_id);

            productImage = (ImageView) itemView.findViewById(R.id.shopping_card_item_img_id);
//            enName=(TextView)itemView.findViewById(R.id.shopping_card_item_en_name_txt_id);
            product_Title = (TextView) itemView.findViewById(R.id.shopping_card_item_fa_name_txt_id);

            producerNameTxt = (TextView) itemView.findViewById(R.id.producer_name_txt_id);
            productQuantityTxt = (TextView) itemView.findViewById(R.id.product_quantity_txt_id);

            deleteImgBtm = (ImageView) itemView.findViewById(R.id.remove_basket_item_imgbtn_id);
            expand_arrow = (ImageView) itemView.findViewById(R.id.expand_arrow_img_id);
            pricemenusTrigerlayout = (RelativeLayout) itemView.findViewById(R.id.price_sliding_menu_triger_layout_id);
            pricemenusLayout = (RelativeLayout) itemView.findViewById(R.id.prices_liding_list_layout_id);

            discountNames = (TextView) itemView.findViewById(R.id.discount_names_txt_id);
            totalDiscountPercentage = (TextView) itemView.findViewById(R.id.discount_number_txt_id);

            //prices
            depricatedPriceWithTax = (TextView) itemView.findViewById(R.id.depricated_price_txt_id);
            priceWithDiscountandTax = (TextView) itemView.findViewById(R.id.price_withtaxanddiscount_txt_id);
            unitPriceWithTaxandDFiscount = (TextView) itemView.findViewById(R.id.shopping_card_item_price_with_tax_and_discount_txt_id);
            depricatedUnitPriceWithTaxOnly = (TextView) itemView.findViewById(R.id.shopping_card_item_price_txt_id);
            //prices

            //increase decrease tbn
            increaseImgbtn = (ImageView) itemView.findViewById(R.id.increase_quantity_img_id);
            decreaseImgBtn = (ImageView) itemView.findViewById(R.id.decrease_quantity_img_id);
            //increase decrease tbn

            //price for quantity
            depricatedPriceWithTax.setPaintFlags(depricatedPriceWithTax.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            depricatedUnitPriceWithTaxOnly.setPaintFlags(depricatedUnitPriceWithTaxOnly.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //price for quantity


        }
    }

    public void update(ArrayList<CartProductTypeModel> data) {
//        degrees.clear();
        products = data;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        products.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, products.size());
    }

    public void updateQuantityandList() {
        products.clear();
//        products.addAll(Globals.ExtractCartItemsFromMap());//todo uncomment cartmodel changed
        notifyDataSetChanged();
        ProgressBarShower.StopMyProgressBar(activity, linearLayout);

        //update actvity title
        int totalPrice = 0;
        for (int i = 0; i < products.size(); i++) {
            totalPrice += Integer.parseInt(products.get(i).getProductType_unitPriceTaxInclude()) * Integer.parseInt(products.get(i).getQuantity());
        }
        ShoppingCardActivity.totalQuantityinTitle.setText(activity.getResources().getString(R.string.cart_items_total_quantity_string, GlobalVariables.cartTotalItemsWithQuantity));
        ShoppingCardActivity.totalPriceinTitle.setText(String.valueOf(totalPrice));
        //update actvity title
    }
//
//    public void addItemToCart(final CartProductTypeModel singleItem, int quantity) { //todo uncomment -- cart model has been changed
//        //find item
//        if (MBZ_Token_Prefs.isAuthorized()) {
//            CartProductTypeModel cartProductTypeModel = new CartProductTypeModel();
//            cartProductTypeModel.setProductType_id(singleItem.getProductType_id());
//            Set<CartProductTypeModel> cartProductTypeModels = new HashSet<>();
//            cartProductTypeModels.add(cartProductTypeModel);
//            cartProductTypeModel.setQuantity(String.valueOf(Integer.valueOf(quantity)));
//            //             `
//            // -------------------------create hashmaps
//            HashMap<String, Set<CartProductTypeModel>> integerSetHashMap = new HashMap<>();
//            integerSetHashMap.put("0", cartProductTypeModels);
//            //-------------------------create hashmaps
//            HashMap<String, HashMap<String, Set<CartProductTypeModel>>> integerHashMapHashMap = new HashMap<>();
//            integerHashMapHashMap.put(String.valueOf(singleItem.getProductType_vendor_id()), integerSetHashMap);
//
//            GlobalVariables.LocalCart.getCartProductTypeModelHashMap().clear();
//            GlobalVariables.LocalCart.setCartProductTypeModelHashMap(integerHashMapHashMap);
//
//            //find item
//
//            Call<CartModel> call = cartAPIInterface.addItemToCartRequest(GlobalVariables.LocalCart);
//            ProgressBarShower.StartMyProgressBar(activity, linearLayout);
//            call.enqueue(new CallbackWithRetry<CartModel>(call, activity, linearLayout) {
//                @Override
//                public void onResponse(Call<CartModel> call, Response<CartModel> response) {
////                ProgressBarShower.StopMyProgressBar(activity, progressWrapper);
//                    if (response.isSuccessful()) {
//                        CartModel cartModel1 = response.body();
////                    MySnackBar.snackBarWithNoAction("به سبد خرید اضافه شد",linearLayout);
//
//                        Globals.updateBasketItems(cartAPIInterface, activity, linearLayout, new IBadgeUpdate() {
//                            @Override
//                            public void doUpdate() {
//                                updateQuantityandList();
//                            }
//                        });
//
//                    } else {
//                        try {
//                            Log.d("MYERROR", response.errorBody().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Toast.makeText(context, "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }
//
//
//    public void removeItemFromCart(final CartProductTypeModel singleItem) {
//        //find item
//        if (MBZ_Token_Prefs.isAuthorized()) {
//
//            CartDeleteModel cartDeleteModel = new CartDeleteModel();
//            cartDeleteModel.setProductTypeId(singleItem.getProductType_id());
//            cartDeleteModel.setVitrinId(GlobalVariables.selectedCity);
//            cartDeleteModel.setClientRevision(GlobalVariables.LocalCart.getClientRevision());
//            cartDeleteModel.setHasCart(true);
//
//            Gson gson = new Gson();
//            Log.d("delete model", cartDeleteModel.toString());
//
//            Call<ResponseBody> call = cartAPIInterface.removeItemFromCartRequest(cartDeleteModel);
//            ProgressBarShower.StartMyProgressBar(activity, linearLayout);
//            call.enqueue(new CallbackWithRetry<ResponseBody>(call, activity, linearLayout) {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                ProgressBarShower.StopMyProgressBar(activity, progressWrapper);
//                    if (response.isSuccessful()) {
////                        CartDeleteModel cartModel1 = response.body();
//                        try {
//                            Log.d("resp delete", response.body().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        MySnackBar.snackBarWithNoAction("از سبد خرید حذف شد", linearLayout);
//                        Globals.updateBasketItems(cartAPIInterface, activity, linearLayout, new IBadgeUpdate() {
//                            @Override
//                            public void doUpdate() {
//                                updateQuantityandList();
//                                Log.d("resp deleteqwwqqwqwqwqw", GlobalVariables.cartItemsCount.toString());
//                            }
//                        });
//
//                    } else {
//                        try {
//                            Log.d("MYERROR", response.errorBody().string());
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        Toast.makeText(context, "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }
//    }
//
//    public void removeFromLocal(final CartProductTypeModel singleItem) {
//        if (GlobalVariables.LocalCart != null) {
//            for (Map.Entry<String, HashMap<String, Set<CartProductTypeModel>>> entry : GlobalVariables.LocalCart.getCartProductTypeModelHashMap().entrySet()) {
//                for (Map.Entry<String, Set<CartProductTypeModel>> entry2 : entry.getValue().entrySet()) {
//                    for (CartProductTypeModel entry3 : entry2.getValue()) {
//                        if (entry3.getProductType_id().equals(singleItem.getProductType_id())) {
//                            entry2.getValue().remove(entry3);
//                            //update price and number
//                            MySnackBar.snackBarWithNoAction("از سبد خرید حذف شد", linearLayout);
//
//                            updateQuantityandList();
//
////                            });
//                            //update price and number todo
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public void decreaseLocalQuantity(final CartProductTypeModel singleItem) {
//        if (GlobalVariables.LocalCart != null) {
//            for (Map.Entry<String, HashMap<String, Set<CartProductTypeModel>>> entry : GlobalVariables.LocalCart.getCartProductTypeModelHashMap().entrySet()) {
//                for (Map.Entry<String, Set<CartProductTypeModel>> entry2 : entry.getValue().entrySet()) {
//                    for (CartProductTypeModel entry3 : entry2.getValue()) {
//                        if (entry3.getProductType_id().equals(singleItem.getProductType_id())) {
//                            entry3.setQuantity(String.valueOf(Integer.valueOf(entry3.getQuantity()) - 1));
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public void increaseLocalQuantity(final CartProductTypeModel singleItem) {
//        if (GlobalVariables.LocalCart != null) {
//            for (Map.Entry<String, HashMap<String, Set<CartProductTypeModel>>> entry : GlobalVariables.LocalCart.getCartProductTypeModelHashMap().entrySet()) {
//                for (Map.Entry<String, Set<CartProductTypeModel>> entry2 : entry.getValue().entrySet()) {
//                    for (CartProductTypeModel entry3 : entry2.getValue()) {
//                        if (entry3.getProductType_id().equals(singleItem.getProductType_id())) {
//                            entry3.setQuantity(String.valueOf(Integer.valueOf(entry3.getQuantity()) + 1));
//                        }
//                    }
//                }
//            }
//        }
//    }
}
