package com.example.aalizade.mbazar_base_app.activities.products_related;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.bottom_sheets.CheckOutLastStepBottomSheet;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IBadgeUpdate;
import com.example.aalizade.mbazar_base_app.fragments.checkout_frags.CheckOutAddressFragment;
import com.example.aalizade.mbazar_base_app.fragments.checkout_frags.CheckOutPreviewAndPayFragment;
import com.example.aalizade.mbazar_base_app.fragments.checkout_frags.CheckOutSocialOrganizationFragment;
import com.example.aalizade.mbazar_base_app.fragments.checkout_frags.CheckOutTransportationFragment;
import com.example.aalizade.mbazar_base_app.network.Globals;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CheckOutBaseActivity extends AppCompatActivity {
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    Toolbar toolbar;
    //    FragmentTransaction ft;
    String step;
    Fragment fragment;
    TextView textView;
    static CartAPIInterface cartAPIInterface;
    static LinearLayout linearLayout;
    static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_base);

        cartAPIInterface = RetrofitOAuthClient.getOauthClient(getApplicationContext(), linearLayout).create(CartAPIInterface.class);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout_id);
        activity = this;


        textView = (TextView) findViewById(R.id.bottom_layout_txt_id);
        toolbar = (Toolbar) findViewById(R.id.checkOut_toolbar);
        setUpToolbar(toolbar);

        //fragments
        fragment = new CheckOutSocialOrganizationFragment();
        replaceFragment(fragment, "1");
        textView.setText("نشانی و توضیحات تحویل");          //نمایش متن لایه ی پایینی برای تعین استپ بعدی

        RelativeLayout bottomBtnLayout = (RelativeLayout) findViewById(R.id.bottom_Btn_layout_id);
        bottomBtnLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step = fragment.getTag();
                switch (step) {
                    case "1":
                        if (CheckOutSocialOrganizationFragment.selectedSocialGroup.size() > 0) {
                            Globals.updateBasketItems(cartAPIInterface, activity, linearLayout, new IBadgeUpdate() {
                                @Override
                                public void doUpdate() {
                                    fragment = new CheckOutAddressFragment();
                                    replaceFragment(fragment, "2");
                                    textView.setText("انتخاب حامل");
                                }
                            });

                        }
                        break;
                    case "2":
//                        CheckOutAddressFragment.submitSelectedAddress(CheckOutBaseActivity.this, new IBadgeUpdate() {//todo uncomment cartmodel changed
//                            @Override
//                            public void doUpdate() {
//                                if (CheckOutAddressFragment.addressesStringList.size() > 0 && CheckOutAddressFragment.is_Adr_Submited) {
//                                    Globals.updateBasketItems(cartAPIInterface, activity, linearLayout, new IBadgeUpdate() {
//                                        @Override
//                                        public void doUpdate() {
//                                            System.out.println("sdasdfsdf" + GlobalVariables.LocalCart.getStepDone());
//                                            fragment = new CheckOutTransportationFragment();
//                                            replaceFragment(fragment, "3");
//                                            textView.setText("پیش نمایش و پرداخت");
//                                        }
//                                    });
//                                }
//                            }
//                        });
                        break;
                    case "3":
                        CheckOutTransportationFragment.updateCarrier(new IBadgeUpdate() {
                            @Override
                            public void doUpdate() {
                                Globals.updateBasketItems(cartAPIInterface, activity, linearLayout, new IBadgeUpdate() {
                                    @Override
                                    public void doUpdate() {
                                        fragment = new CheckOutPreviewAndPayFragment();
                                        replaceFragment(fragment, "4");
                                        textView.setText("پرداخت");
                                    }
                                });
                            }
                        });


                        break;
                    case "4": {
                        Globals.updateBasketItems(cartAPIInterface, activity, linearLayout, new IBadgeUpdate() {
                            @Override
                            public void doUpdate() {
                                BottomSheetDialogFragment bottomSheetDialogFragment = new CheckOutLastStepBottomSheet();
                                bottomSheetDialogFragment.show(getSupportFragmentManager(), bottomSheetDialogFragment.getTag());
                            }
                        });
                        break;
                    }
                }

            }
        });
        //fragments
    }

    public void setUpToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                step = fragment.getTag();
                switch (step) {
                    case "1":
                        finish();
                        break;
                    case "2":
                        fragment = new CheckOutSocialOrganizationFragment();
                        replaceFragment(fragment, "1");
                        textView.setText("نشانی و توضیحات تحویل");
                        break;
                    case "3":
                        fragment = new CheckOutAddressFragment();
                        replaceFragment(fragment, "2");
                        textView.setText("انتخاب حامل");
                        break;
                    case "4":
                        fragment = new CheckOutTransportationFragment();
                        replaceFragment(fragment, "3");
                        textView.setText("پیش نمایش و پرداخت");
                        break;
                    default:
                        finish();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        } else {
//            super.onBackPressed();
//        }
        step = fragment.getTag();
        switch (step) {
            case "1":
                finish();
                break;
            case "2":
                fragment = new CheckOutSocialOrganizationFragment();
                replaceFragment(fragment, "1");
                textView.setText("نشانی و توضیحات تحویل");
                break;
            case "3":
                fragment = new CheckOutAddressFragment();
                replaceFragment(fragment, "2");
                textView.setText("انتخاب حامل");
                break;
            case "4":
                fragment = new CheckOutTransportationFragment();
                replaceFragment(fragment, "3");
                textView.setText("پیش نمایش و پرداخت");
                break;
            default:
                finish();
        }
    }

    private void replaceFragment(Fragment fragment, String tag) {
//        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
//        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
//        if (!fragmentPopped){
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.checkout_base_placeholder, fragment, tag);
        ft.addToBackStack(null);
        ft.commit();
//        }
    }


}
