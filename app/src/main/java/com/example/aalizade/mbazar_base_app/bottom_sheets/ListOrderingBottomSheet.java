package com.example.aalizade.mbazar_base_app.bottom_sheets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.aalizade.mbazar_base_app.R;

public class ListOrderingBottomSheet extends BottomSheetDialogFragment {
    RadioButton mostSellerRadio,mostExpensiveRadio,cheapestRadio,RisingRadio,FallingRadio;
    LinearLayout mostSellerLayout,mostExpensiveLayout,cheapestLayout,RisingLayout,FallingLayout;
    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.activity_list_ordering_bottom_sheet, null);
        dialog.setContentView(contentView);

        LinearLayout mostSellerLayout = (LinearLayout)dialog.findViewById(R.id.most_seller_layout_id);
        LinearLayout mostExpensiveLayout = (LinearLayout)dialog.findViewById(R.id.most_expensive_layout_id);
        LinearLayout cheapestLayout = (LinearLayout)dialog.findViewById(R.id.cheapest_layout_id);
        LinearLayout RisingLayout = (LinearLayout)dialog.findViewById(R.id.rising_layout_id);
        LinearLayout FallingLayout = (LinearLayout)dialog.findViewById(R.id.falling_layout_id);

        final RadioButton cheapestRadio = (RadioButton)dialog.findViewById(R.id.cheapest_radio_id);
        final RadioButton mostExpensiveRadio = (RadioButton)dialog.findViewById(R.id.most_expensive_radio_id);
        final RadioButton mostSellerRadio = (RadioButton)dialog.findViewById(R.id.most_seller_radio_id);
        final RadioButton RisingRadio = (RadioButton)dialog.findViewById(R.id.rising_radio_id);
        final RadioButton FallingRadio = (RadioButton)dialog.findViewById(R.id.falling_radio_id);

        mostSellerLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostSellerRadio.setChecked(true);
                cheapestRadio.setChecked(false);
                mostExpensiveRadio.setChecked(false);
                RisingRadio.setChecked(false);
                FallingRadio.setChecked(false);
            }
        });
        cheapestLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostSellerRadio.setChecked(false);
                cheapestRadio.setChecked(true);
                mostExpensiveRadio.setChecked(false);
                RisingRadio.setChecked(false);
                FallingRadio.setChecked(false);
            }
        });
        mostExpensiveLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostSellerRadio.setChecked(false);
                cheapestRadio.setChecked(false);
                mostExpensiveRadio.setChecked(true);
                RisingRadio.setChecked(false);
                FallingRadio.setChecked(false);
            }
        });
        RisingLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostSellerRadio.setChecked(false);
                cheapestRadio.setChecked(false);
                mostExpensiveRadio.setChecked(false);
                RisingRadio.setChecked(true);
                FallingRadio.setChecked(false);
            }
        });
        FallingLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mostSellerRadio.setChecked(false);
                cheapestRadio.setChecked(false);
                mostExpensiveRadio.setChecked(false);
                RisingRadio.setChecked(false);
                FallingRadio.setChecked(true);
            }
        });


    }
}
