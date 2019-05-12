package com.example.aalizade.mbazar_base_app.bottom_sheets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.network.apiInterface.ProductAPIInterace;
import com.example.aalizade.mbazar_base_app.network.models.product.FindByVitrinModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeLineSidebarModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by aalizade on 11/13/2017.
 */

public class AddToCartBottomSheet extends BottomSheetDialogFragment {
    ProductAPIInterace productAPIInterace;
    LinearLayout linearLayout;
    String productId;
    ProductTypeLineSidebarModel productTypeLineSidebarModel;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.add_to_card_bottom_sheet, null);
        dialog.setContentView(contentView);
        productId = getArguments().getString("product_id");

        linearLayout = (LinearLayout) contentView.findViewById(R.id.bottom_sheet_mother_layout_id);
        productAPIInterace = RetrofitClient.getclient(linearLayout).create(ProductAPIInterace.class);
        productTypeLineSidebarModel = new ProductTypeLineSidebarModel();
        getvendor_guranty_sets();

        LinearLayout buy_bsh_btn = (LinearLayout) dialog.findViewById(R.id.but_bsh_bottom_button_layout_id);
        buy_bsh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"با موفقیت به سبد خرید اضافه شد",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }


    public void getvendor_guranty_sets() {
        FindByVitrinModel findByVitrinModel = new FindByVitrinModel();
        findByVitrinModel.setVitrinId(GlobalVariables.selectedCity);
        findByVitrinModel.setId(productId);
        Call<ProductTypeLineSidebarModel> call = productAPIInterace.getVendorFilterList(findByVitrinModel);
        call.enqueue(new CallbackWithRetry<ProductTypeLineSidebarModel>(call, getActivity(), linearLayout) {
            @Override
            public void onResponse(Call<ProductTypeLineSidebarModel> call, Response<ProductTypeLineSidebarModel> response) {
                if (response.isSuccessful()) {
                    System.out.println("product ven-guar sets " + response.body().toString());
                    productTypeLineSidebarModel = response.body();//todo continue this
                } else {
                    try {
                        Log.d("Fail addCart BtmSht", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
