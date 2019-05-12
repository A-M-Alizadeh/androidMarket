package com.example.aalizade.mbazar_base_app.network;

import android.app.Activity;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IBadgeUpdate;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartGetModel;
import com.example.aalizade.mbazar_base_app.network.models.cart.CartModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aalizade on 12/4/2017.
 */

public class Globals {
    public static void updateBasketItems(CartAPIInterface cartAPIInterface, final Activity activity, LinearLayout linearLayout, final IBadgeUpdate iBadgeUpdate) {
        CartGetModel cartGetModel = new CartGetModel();
        if (GlobalVariables.LocalCart == null) {
            String clientRevision = String.valueOf(Math.floor((Math.random() * 99999) + 10000));
            cartGetModel.setClientRevision(clientRevision);
        } else {
            cartGetModel.setClientRevision(GlobalVariables.LocalCart.getClientRevision());
        }
        cartGetModel.setVitrin_id(GlobalVariables.selectedCity);


        Call<CartModel> call = cartAPIInterface.getCartwhenLoggedIn(cartGetModel);
        Log.d("befor of resp-> ", "HEREEEEEEEEE");
        call.enqueue(new CallbackWithRetry<CartModel>(call, activity, linearLayout) {
            @Override
            public void onResponse(Call<CartModel> call, Response<CartModel> response) {
                if (response.isSuccessful()) {
                    GlobalVariables.LocalCart = null;//todo new
                    GlobalVariables.LocalCart = response.body();
                    ////////////////////////////////////////////////TODO MBG

                    System.out.println("Global GetBasket stepdone"+GlobalVariables.LocalCart.getStepDone());

                    int x = 0;
                    //todo uncomment cartmodel changed
//                    for (Map.Entry<String, HashMap<String, Set<CartProductTypeModel>>> entry : GlobalVariables.LocalCart.getCartProductTypeModelHashMap().entrySet()) {
//                        for (Map.Entry<String, Set<CartProductTypeModel>> entry2 : entry.getValue().entrySet()) {
//                            x += entry2.getValue().size();
//                        }
//                    }
                    GlobalVariables.cartItemsCount = x;
                    Log.d("resp 123123123123", String.valueOf(x));
                    iBadgeUpdate.doUpdate();
                    /////////////////////////////////////////////////TODO MBG
                    Gson gson = new Gson();
                    Log.d("server cart-> ", gson.toJson(GlobalVariables.LocalCart));
                    Log.d("end of resp-> ", "HEREEEEEEEEE");
                }
            }
        });
        Log.d("after resp-> ", "HEREEEEEEEEE");
    }

    //todo uncomment cartmodel changed
//    public static List<CartProductTypeModel> ExtractCartItemsFromMap() {
//        GlobalVariables.cartTotalItemsWithQuantity = 0;
//        List<CartProductTypeModel> cartModels = new ArrayList<>();
//        for (Map.Entry<String, HashMap<String, Set<CartProductTypeModel>>> entry : GlobalVariables.LocalCart.getCartProductTypeModelHashMap().entrySet()) {
//            for (Map.Entry<String, Set<CartProductTypeModel>> entry2 : entry.getValue().entrySet()) {
//                for (CartProductTypeModel item : entry2.getValue()) {
//                    System.out.println("===))) " + item);
//                    cartModels.add(item);
//                    GlobalVariables.cartTotalItemsWithQuantity += Integer.parseInt(item.getQuantity());
//                }
//            }
//        }
//        return cartModels;
//    }

//    public static void CountUnAuthorizedCartItems() {
//        int x = 0;
//        for (Map.Entry<String, HashMap<String, Set<CartProductTypeModel>>> entry : GlobalVariables.LocalCart.getCartProductTypeModelHashMap().entrySet()) {
//            for (Map.Entry<String, Set<CartProductTypeModel>> entry2 : entry.getValue().entrySet()) {
//                x += entry2.getValue().size();
//            }
//        }
//        GlobalVariables.cartItemsCount = x;
//    }
//
//    public static void mergeCarts(final CartModel cartModel, final CartAPIInterface cartAPIInterface, final Activity activity, final LinearLayout progressWrapper, final IBadgeUpdate iBadgeUpdate) {
//        if (MBZ_Token_Prefs.isAuthorized() && GlobalVariables.LocalUnAthourizedCart != null && GlobalVariables.LocalUnAthourizedCart.getCartProductTypeModelHashMap().size()>0) {
////            GlobalVariables.LocalUnAthourizedCart = GlobalVariables.LocalCart;
////            GlobalVariables.LocalCart = null;
//            final Context context = activity;
//            Call<CartModel> call = cartAPIInterface.addItemToCartRequest(cartModel);
//            call.enqueue(new CallbackWithRetry<CartModel>(call, activity, progressWrapper) {
//                @Override
//                public void onResponse(Call<CartModel> call, Response<CartModel> response) {
////                ProgressBarShower.StopMyProgressBar(activity, progressWrapper);
//                    if (response.isSuccessful()) {
//                        CartModel cartModel1 = response.body();
//                        Globals.updateBasketItems(cartAPIInterface, activity, progressWrapper, new IBadgeUpdate() {
//                            @Override
//                            public void doUpdate() {
//                                iBadgeUpdate.doUpdate();
//                            }
//                        });
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


}