package com.example.aalizade.mbazar_base_app.bottom_sheets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.checkout_steps_adapters.CheckoutPaymentOptionsTypeOUTERAdapter;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IBadgeUpdate;
import com.example.aalizade.mbazar_base_app.fragments.checkout_frags.CheckOutPreviewAndPayFragment;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.cart.CustomListItemForAdapter;
import com.example.aalizade.mbazar_base_app.network.models.cart.CustomPaymentOptionsModel;
import com.example.aalizade.mbazar_base_app.network.models.cart.PaymentSystemCartByMemberModel;
import com.example.aalizade.mbazar_base_app.network.models.cart.PaymentSystemCartModel;
import com.example.aalizade.mbazar_base_app.network.models.department.Empty;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class CheckOutLastStepBottomSheet extends BottomSheetDialogFragment {
    RecyclerView optionsRecycler;
    ProgressBar progressBar;
    CartAPIInterface cartAPIInterface;
    LinearLayout linearLayout;
    List<CustomPaymentOptionsModel> customPaymentOptionsModels;
    List<CustomListItemForAdapter> justOptionsList;
    RelativeLayout finalPriceLayout;
    Button PaymentgatewayBtn,paymentBtn;
    TextView finalPriceToPayTxt;
    HashMap<String,String> paymentSystemHashMap;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        final View contentView = View.inflate(getContext(), R.layout.checkout_laststep_bottomsheet, null);
        linearLayout = contentView.findViewById(R.id.linear_layout_id);
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(getContext(), linearLayout).create(CartAPIInterface.class);

        finalPriceLayout = (RelativeLayout) contentView.findViewById(R.id.final_price_payment_layout_id);
        optionsRecycler = (RecyclerView) contentView.findViewById(R.id.optionsRecycler);
        PaymentgatewayBtn = (Button) contentView.findViewById(R.id.Payment_gateway_btn_id);
        progressBar = (ProgressBar) contentView.findViewById(R.id.progressbar_id);
        finalPriceToPayTxt = (TextView) contentView.findViewById(R.id.final_price_btmsheet_txt_id);

        customPaymentOptionsModels = new ArrayList<>();
        justOptionsList = new ArrayList<>();
        paymentSystemHashMap = new HashMap<>();

        paymentBtn = (Button) contentView.findViewById(R.id.payment);
//        paymentBtn.setOnClickListener(new View.OnClickListener() {//todo uncomment cartmodel changed
//            @Override
//            public void onClick(View v) {
//                Log.v("LAST CART TO SEND",GlobalVariables.LocalCart.toString());
//                Log.v("LAST SEND STEP DONE",GlobalVariables.LocalCart.getStepDone());
//                Intent intent = new Intent(getActivity(), PaymentActivity.class);//todo hazine haml
//                RedirectModel redirectModel = new RedirectModel(GlobalVariables.LocalCart.getVitrin_id(),GlobalVariables.LocalCart.getClientRevision(),paymentSystemHashMap,"0");
//                intent.putExtra("redirectURL",redirectModel);
//                startActivity(intent);
//                getActivity().finish();//finish last activity
//            }
//        });

        getPaymentOptions(new IBadgeUpdate() {
            @Override
            public void doUpdate() {
                CheckoutPaymentOptionsTypeOUTERAdapter newsAdapter = new CheckoutPaymentOptionsTypeOUTERAdapter(getContext(), customPaymentOptionsModels);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                optionsRecycler.setLayoutManager(linearLayoutManager);
                optionsRecycler.setAdapter(newsAdapter);
                optionsRecycler.setHasFixedSize(true);
                optionsRecycler.setItemViewCacheSize(20);
                optionsRecycler.setDrawingCacheEnabled(true);
                optionsRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
                optionsRecycler.setNestedScrollingEnabled(false);

                progressBar.setVisibility(View.GONE);

                dialog.setContentView(contentView);
            }
        });

        finalPriceToPayTxt.setText(getContext().getResources().getString(R.string.final_price_to_pay_string,CheckOutPreviewAndPayFragment.total_price_to_pay));

//        PaymentgatewayBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                finalPriceToPayTxt.setText(getContext().getResources().getString(R.string.final_price_to_pay_string,CheckOutPreviewAndPayFragment.total_price_to_pay));
////                if (isVisible){
////                    finalPriceLayout.setVisibility(View.GONE);
////                }else {
////                    finalPriceLayout.setVisibility(View.VISIBLE);
////                }
//////                if (isVisible ? finalPriceLayout.setVisibility(View.GONE ) : finalPriceLayout.setVisibility(View.VISIBLE))
////                isVisible = !isVisible;
//            }
//        });
    }

    public void getPaymentOptions(final IBadgeUpdate iBadgeUpdate) {
        Call<List<PaymentSystemCartByMemberModel>> call = cartAPIInterface.paymentOptions(new Empty());
        call.enqueue(new CallbackWithRetry<List<PaymentSystemCartByMemberModel>>(call, getActivity(), linearLayout) {
            @Override
            public void onResponse(Call<List<PaymentSystemCartByMemberModel>> call, Response<List<PaymentSystemCartByMemberModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Integer i;
                        for (PaymentSystemCartByMemberModel outerList : response.body()) {
                            i = 0;
                            List<PaymentSystemCartModel> eachPaymentSystemCartModels = new ArrayList<>();
                            customPaymentOptionsModels.add(new CustomPaymentOptionsModel(outerList.getKey().toString(), eachPaymentSystemCartModels));
                            for (PaymentSystemCartModel innderList : outerList.getPaymentSystemCartModelSet()) {
                                eachPaymentSystemCartModels.add(innderList);
                                paymentSystemHashMap.put(innderList.getId(),"0");
                                justOptionsList.add(new CustomListItemForAdapter(i, innderList));
                                i++;
                            }
                            i = 0;
                        }
                    }

                    for (int i = 0; i < customPaymentOptionsModels.size(); i++) {
                        System.out.println("OPTION " + i + " ->  " + customPaymentOptionsModels.get(i).toString());
                    }
                    for (int i = 0; i < justOptionsList.size(); i++) {
                        System.out.println("JUST OPTION " + i + " ->  " + justOptionsList.get(i).toString());
                    }

                    System.out.println("Key -> "+paymentSystemHashMap.keySet().toString());
                    System.out.println("Val -> "+paymentSystemHashMap.values().toString());

                    iBadgeUpdate.doUpdate();
                } else {
                    try {
                        Log.d("MYERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
