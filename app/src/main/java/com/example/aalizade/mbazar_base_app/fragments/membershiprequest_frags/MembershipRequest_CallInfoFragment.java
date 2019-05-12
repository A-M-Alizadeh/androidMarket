package com.example.aalizade.mbazar_base_app.fragments.membershiprequest_frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.MemberShipRequestActivity;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.RequestServices.AutoCompleteCitiesRequestService;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.utility.CutomAutoCompleteAdapter;
import com.example.aalizade.mbazar_base_app.utility.MobileNumberValidator;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MembershipRequest_CallInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MembershipRequest_CallInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MembershipRequest_CallInfoFragment extends Fragment {

    CutomAutoCompleteAdapter callCityAdapter;
    Button nextStep, previousStep;
    AutoCompleteTextView cityAuto_TIET;
    EditText mobileNumber_ET, userAddress_ET, userpostalCode_ET, userEmail_ET, userPhone_ET, userJobAddress_ET, userjobpostalCode_ET, userJobPhone_ET;
    private static List<AutoCompleteModel> autoCities;
    private static List<String> initialArray;

    //LoggedInUserModel objects
    AutoCompleteModel userDefaultUserContactCity;
    public static int cityCode;
    LinearLayout progressWrapper;
    AutoCompleteCitiesRequestService autoCompleteCitiesRequestService;
    GeneralRetrofitAPIInterface generalAPIInterface;
    MobileNumberValidator mobileNumberValidator;
    //LoggedInUserModel objects

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MembershipRequest_CallInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MembershipRequest_CallInfoFragment.
     */
    public static MembershipRequest_CallInfoFragment newInstance(String param1, String param2) {
        MembershipRequest_CallInfoFragment fragment = new MembershipRequest_CallInfoFragment();
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
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_membershiprequest_call_info, container, false);
        progressWrapper = (LinearLayout) view.findViewById(R.id.progress_wrapper_id);

        mobileNumberValidator = new MobileNumberValidator();
        autoCompleteCitiesRequestService = new AutoCompleteCitiesRequestService(getContext(),progressWrapper);
        userDefaultUserContactCity = new AutoCompleteModel();
        nextStep = (Button) view.findViewById(R.id.step_done_btn_id);
        previousStep = (Button) view.findViewById(R.id.step_back_btn_id);
        cityAuto_TIET = (AutoCompleteTextView) view.findViewById(R.id.call_city_auto_tiet_id);
        mobileNumber_ET = (EditText) view.findViewById(R.id.call_city_mobileNum_ET_id);

        userAddress_ET = (EditText) view.findViewById(R.id.call_city_address_tiet_id);
        userpostalCode_ET = (EditText) view.findViewById(R.id.call_city_postalCode_tiet_id);
        userEmail_ET = (EditText) view.findViewById(R.id.call_city_email_tiet_id);
//        mobileNumber_ET = (EditText) view.findViewById(R.id.call_city_mobileNum_ET_id);
        userPhone_ET = (EditText) view.findViewById(R.id.call_city_phonenumber_tiet_id);
        userJobAddress_ET = (EditText) view.findViewById(R.id.call_city_workAddress_tiet_id);
        userjobpostalCode_ET = (EditText) view.findViewById(R.id.call_city_workpostalCode_tiet_id);
        userJobPhone_ET = (EditText) view.findViewById(R.id.call_city_workPhone_tiet_id);

        initialArray = new ArrayList<>();
        autoCities = new ArrayList<>();


        //client
        generalAPIInterface = RetrofitClient.getclient(progressWrapper).create(GeneralRetrofitAPIInterface.class);
        MBZ_Token_Prefs.initTokenSharedPrefs(getContext());
        //client

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyForm()) {
                    MemberShipRequestActivity.memberCreateModel.setUser_childNumber(null);
                    MemberShipRequestActivity.memberCreateModel.setUser_defaultUserContact_city(userDefaultUserContactCity);
                    MemberShipRequestActivity.memberCreateModel.setUser_defaultUserContact_address(userAddress_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_defaultUserContact_postalCode(userpostalCode_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_defaultUserContact_emailAddress(userEmail_ET.getText().toString());

                    MemberShipRequestActivity.memberCreateModel.setUser_defaultUserContact_mobileNo(mobileNumber_ET.getText().toString());

                    MemberShipRequestActivity.memberCreateModel.setUser_defaultUserContact_phoneNo(userPhone_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_defaultUserContact_jobAddress(userJobAddress_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_defaultUserContact_jobPostalCode(userjobpostalCode_ET.getText().toString());
                    MemberShipRequestActivity.memberCreateModel.setUser_defaultUserContact_jobPhoneNo(userJobPhone_ET.getText().toString());

                    ((MemberShipRequestActivity) getActivity()).secondStepisDone();
                }
            }
        });
        previousStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MemberShipRequestActivity) getActivity()).backtoPersonalInfo();
            }
        });

        //auto complete city
        callCityAdapter = new CutomAutoCompleteAdapter(getContext(), R.layout.autocomplete_row,R.id.autocomplete_txt_id, initialArray);
        cityAuto_TIET.setAdapter(callCityAdapter);
        cityAuto_TIET.setDropDownHeight(300);
        cityAuto_TIET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                cityAuto_TIET.showDropDown();
            }
        });
        cityAuto_TIET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                shenasnameTown_ET.showDropDown();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() >= 2) {
                    populateCities(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        cityAuto_TIET.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cityCode = Integer.valueOf(autoCities.get(position).getId());
                System.out.println("selected City reg"+autoCities.toString() + " #code "+ cityCode);
                userDefaultUserContactCity.setId(autoCities.get(position).getId());
                userDefaultUserContactCity.setText(autoCities.get(position).getText());
                userDefaultUserContactCity.setElementStr(autoCities.get(position).getElementStr());
            }
        });
        //auto complete city

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

    public void populateCities(String city) {
        autoCompleteCitiesRequestService.populateCities(generalAPIInterface, city, new IResponseHandler() {
            @Override
            public void HandleAfterResponse(Object o) {
                List<AutoCompleteModel> responseModel = (List<AutoCompleteModel>) o;
                initialArray.clear();
                autoCities.clear();
                callCityAdapter.clear();
                for (AutoCompleteModel temp : responseModel) {
                    System.out.println(temp.toString());
                    autoCities.add(temp);
                    callCityAdapter.add(temp.getText() + " " + temp.getElementStr());
                }
                callCityAdapter.notifyDataSetChanged();
            }
        });
    }

    public Boolean verifyForm() {
        Boolean result = true;
        if (cityAuto_TIET.getText().toString().isEmpty()) {
            cityAuto_TIET.setError("لطفا این فیلد را پر کنید");
            result = false;
        }
        if (mobileNumber_ET.getText().toString().isEmpty()) {
            mobileNumber_ET.setError("لطفا این فیلد را پر کنید");
            result = false;
        } else if (!mobileNumberValidator.validate(mobileNumber_ET.getText().toString().trim())) {
            mobileNumber_ET.setError("شماره موبایل نامعتبر است");
            result = false;
        }
        return result;
    }


}
