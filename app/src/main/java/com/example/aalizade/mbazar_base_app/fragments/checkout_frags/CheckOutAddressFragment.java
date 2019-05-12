package com.example.aalizade.mbazar_base_app.fragments.checkout_frags;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.MyContactInfoActivity;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheckOutAddressFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckOutAddressFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckOutAddressFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static Activity activity;
    static LinearLayout linearLayout;

    private String mParam1;
    private String mParam2;
    static Spinner addressSpinner;
    static EditText reciverName, reciverMobile, basketDescription;
    static FrameLayout motherLayout;
    static ArrayList<ArrayList<String>> addresses;
    Button editOrAddAddress;
    public static List<String> addressesStringList;

    GeneralRetrofitAPIInterface generalRetrofitAPIInterface;
    static CartAPIInterface cartAPIInterface;

    private OnFragmentInteractionListener mListener;

    public CheckOutAddressFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckOutAddressFragment.
     */
    public static CheckOutAddressFragment newInstance(String param1, String param2) {
        CheckOutAddressFragment fragment = new CheckOutAddressFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MBZ_Token_Prefs.initTokenSharedPrefs(getContext());
        View view = inflater.inflate(R.layout.fragment_check_out_address, container, false);
        addresses = new ArrayList<>();
        addressesStringList = new ArrayList<>();

        activity = getActivity();
        linearLayout = (LinearLayout)view.findViewById(R.id.step_indicator_id);
        motherLayout = (FrameLayout) view.findViewById(R.id.mother_layout_id);
        generalRetrofitAPIInterface = RetrofitOAuthClient.getOauthClient(getContext(), motherLayout).create(GeneralRetrofitAPIInterface.class);
        cartAPIInterface = RetrofitOAuthClient.getOauthClient(getContext(), motherLayout).create(CartAPIInterface.class);
        //address adapter
//        getAddressComboMethod();//todo uncomment cartmodel changed

        editOrAddAddress = (Button) view.findViewById(R.id.checkout_edit_address_btn_id);
        addressSpinner = (Spinner) view.findViewById(R.id.checkout_reciever_address_spinner_id);
        reciverName = (EditText) view.findViewById(R.id.checkout_reciever_name_et_id);
        reciverMobile = (EditText) view.findViewById(R.id.checkout_reciever_mibilenum_et_id);
        basketDescription = (EditText) view.findViewById(R.id.checkout_reciever_purchase_info_et_id);

//        reciverName.setText(MBZ_Token_Prefs.getString(MBZ_Token_Prefs.com_example_aalizade_mbazar_base_app_FULL_NAME));

        editOrAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyContactInfoActivity.class));
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void setAddressAdapter(ArrayList<ArrayList<String>> addresses) {
        for (int i = 0; i < addresses.size(); i++) {
            addressesStringList.add(addresses.get(i).get(1));
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, addressesStringList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        addressSpinner.setAdapter(dataAdapter);
        //address adapter
    }

//    public void getAddressComboMethod() {//todo uncomment cartmodel changed
//        String value = GlobalVariables.LocalCart.getVitrin_id();
//        final ComboRequestModel trendComboBox = new ComboRequestModel("userContact", "wUserContact", Integer.valueOf(value));
//        Map<String, ComboRequestModel> combos = new HashMap<>();
//        combos.put("carrierAddress", trendComboBox);
//
//        Gson gson = new Gson();
//        String test = gson.toJson(combos);
//        System.out.println("LOG COMBO " + test);
//
//        Call<Map<String, ArrayList<ArrayList<String>>>> call = generalRetrofitAPIInterface.getCartCombo(combos);
//        call.enqueue(new CallbackWithRetry<Map<String, ArrayList<ArrayList<String>>>>(call, getActivity(), motherLayout) {
//            @Override
//            public void onResponse(Call<Map<String, ArrayList<ArrayList<String>>>> call, Response<Map<String, ArrayList<ArrayList<String>>>> response) {
//                if (response.isSuccessful()) {
//                    System.out.println("QQQQQQQQQQ}}}} " + response.body().toString());
//                    addresses = response.body().get("carrierAddress");
//                    setAddressAdapter(addresses);
//                } else {
//                    try {
//                        Log.d("Fail Combo1", String.valueOf(response.errorBody().string()));
//                        Log.d("Fail Combo2", response.errorBody().toString());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//    }
//
//    public static void submitSelectedAddress(Activity activity, final IBadgeUpdate iBadgeUpdate) {
//        if (!reciverName.getText().toString().equals("") && !reciverMobile.getText().toString().equals("") && addresses.get(addressSpinner.getSelectedItemPosition()).get(0)!=null) {
//            GlobalVariables.LocalCart.setReciverName(reciverName.getText().toString());
//            GlobalVariables.LocalCart.setReciverMobileNo(reciverMobile.getText().toString());
//            GlobalVariables.LocalCart.setDescription(basketDescription.getText().toString());
//            GlobalVariables.LocalCart.setUserContact_id(addresses.get(addressSpinner.getSelectedItemPosition()).get(0));
//
//            Call<CartModel> call = cartAPIInterface.submitSelectedAddress(GlobalVariables.LocalCart);
//            call.enqueue(new CallbackWithRetry<CartModel>(call, activity, motherLayout) {
//                @Override
//                public void onResponse(Call<CartModel> call, Response<CartModel> response) {
//                    if (response.isSuccessful()) {
//                        GlobalVariables.LocalCart = response.body();
//                        System.out.println("address stepdone "+GlobalVariables.LocalCart.getStepDone());
//                        is_Adr_Submited = true;
//                        iBadgeUpdate.doUpdate();
//                    } else {
//                        try {
//                            Log.d("Fail Combo1", String.valueOf(response.errorBody().string()));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
//        }else {
//            MySnackBar.snackBarWithNoAction("فیلد های خالی را پر کنید",motherLayout);
//        }
//    }

}
