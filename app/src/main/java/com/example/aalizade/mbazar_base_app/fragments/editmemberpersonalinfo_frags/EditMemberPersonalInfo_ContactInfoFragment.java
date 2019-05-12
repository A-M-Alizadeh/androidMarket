package com.example.aalizade.mbazar_base_app.fragments.editmemberpersonalinfo_frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.EditMemberPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.RequestServices.AutoCompleteCitiesRequestService;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.utility.CutomAutoCompleteAdapter;
import com.example.aalizade.mbazar_base_app.utility.MobileNumberValidator;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditMemberPersonalInfo_ContactInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditMemberPersonalInfo_ContactInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditMemberPersonalInfo_ContactInfoFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    CutomAutoCompleteAdapter callCityAdapter;
    AutoCompleteTextView cityAuto_TIET;
    EditText mobileNumber_ET, userAddress_ET, userpostalCode_ET, userEmail_ET, userPhone_ET, userJobAddress_ET, userjobpostalCode_ET, userJobPhone_ET;
    private static List<AutoCompleteModel> autoCities;
    private static List<String> initialArray;
    FrameLayout motherLayout;

    //LoggedInUserModel objects
    AutoCompleteModel userDefaultUserContactCity;
    public static int cityCode;
    //LoggedInUserModel objects

    private String mParam1;
    private String mParam2;

    Button topersonalPageBtn, stepTwoDone;
    GeneralRetrofitAPIInterface getCitiesAPIInterface;
    AutoCompleteCitiesRequestService autoCompleteCitiesRequestService;
    MobileNumberValidator mobileNumberValidator;

    private OnFragmentInteractionListener mListener;

    public EditMemberPersonalInfo_ContactInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditMemberPersonalInfo_ContactInfoFragment.
     */
    public static EditMemberPersonalInfo_ContactInfoFragment newInstance(String param1, String param2) {
        EditMemberPersonalInfo_ContactInfoFragment fragment = new EditMemberPersonalInfo_ContactInfoFragment();
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
        final View view = inflater.inflate(R.layout.fragment_edit_member_personal_info_contact_info, container, false);
        mobileNumberValidator = new MobileNumberValidator();
        //client
        motherLayout = view.findViewById(R.id.edit_member_personal_info_contactinfo_mother_layout_id);
        MBZ_Token_Prefs.initTokenSharedPrefs(getContext());
        autoCompleteCitiesRequestService = new AutoCompleteCitiesRequestService(getContext(),motherLayout);
        getCitiesAPIInterface = RetrofitClient.getclient(motherLayout).create(GeneralRetrofitAPIInterface.class);
        //client


        stepTwoDone = view.findViewById(R.id.step_two_done_btn_id);
        topersonalPageBtn = view.findViewById(R.id.step_back_btn_id);

        userDefaultUserContactCity = new AutoCompleteModel();
        cityAuto_TIET = (AutoCompleteTextView) view.findViewById(R.id.edit_contactInfo_auto_tiet_id);
        mobileNumber_ET = (EditText) view.findViewById(R.id.edit_contactInfo_mobileNum_ET_id);

        userAddress_ET = (EditText) view.findViewById(R.id.edit_contactInfo_address_tiet_id);
        userpostalCode_ET = (EditText) view.findViewById(R.id.edit_contactInfo_postalCode_tiet_id);
        userEmail_ET = (EditText) view.findViewById(R.id.edit_contactInfo_email_tiet_id);
        mobileNumber_ET = (EditText) view.findViewById(R.id.edit_contactInfo_mobileNum_ET_id);
        userPhone_ET = (EditText) view.findViewById(R.id.edit_contactInfo_phonenumber_tiet_id);
        userJobAddress_ET = (EditText) view.findViewById(R.id.edit_contactInfo_workAddress_tiet_id);
        userjobpostalCode_ET = (EditText) view.findViewById(R.id.edit_contactInfo_workpostalCode_tiet_id);
        userJobPhone_ET = (EditText) view.findViewById(R.id.edit_contactInfo_workPhone_tiet_id);

        initialArray = new ArrayList<>();
        autoCities = new ArrayList<>();

        //disabling fields ----------------------------------------
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_city().getId().equals("")) {
            cityAuto_TIET.setEnabled(false);
            cityAuto_TIET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_city().getText() + " " +
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_city().getElementStr());
            userDefaultUserContactCity.setId(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_city().getId());
        }

        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_mobileNo().trim().equals("")) {
            mobileNumber_ET.setEnabled(false);
            mobileNumber_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_mobileNo());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_address().trim().equals("")) {
            userAddress_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_address());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_postalCode().trim().equals("")) {
            userpostalCode_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_postalCode());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_emailAddress().trim().equals("")) {
            userEmail_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_emailAddress());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_phoneNo().trim().equals("")) {
            userPhone_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_phoneNo());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobAddress().trim().equals("")) {
            userJobAddress_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobAddress());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobPostalCode().trim().equals("")) {
            userjobpostalCode_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobPostalCode());
        }
        if (!EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobPhoneNo().trim().equals("")) {
            userJobPhone_ET.setText(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobPhoneNo());
        }

        //disabling fields ----------------------------------------


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
                userDefaultUserContactCity.setId(autoCities.get(position).getId());
                userDefaultUserContactCity.setText(autoCities.get(position).getText());
                userDefaultUserContactCity.setElementStr(autoCities.get(position).getElementStr());
            }
        });
        //auto complete city


        topersonalPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditMemberPersonalInfoActivity) getActivity()).backtoPersonalInfo();
            }
        });
        stepTwoDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyForm()) {
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().setChildNumber(null);
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_city(userDefaultUserContactCity);
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_address(userAddress_ET.getText().toString());
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_postalCode(userpostalCode_ET.getText().toString());
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_emailAddress(userEmail_ET.getText().toString());
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_mobileNo(mobileNumber_ET.getText().toString());
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_phoneNo(userPhone_ET.getText().toString());
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_jobAddress(userJobAddress_ET.getText().toString());
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_jobPostalCode(userjobpostalCode_ET.getText().toString());
                    EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_jobPhoneNo(userJobPhone_ET.getText().toString());

                    //test
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setChildNumber(null);
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactCity(userDefaultUserContactCity);
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactAddress(userAddress_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactPostalCode(userpostalCode_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactEmailAddress(userEmail_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactMobileNo(mobileNumber_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactPhoneNo(userPhone_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactJobAddress(userJobAddress_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactJobPostalCode(userjobpostalCode_ET.getText().toString());
                    EditMemberPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactJobPhoneNo(userJobPhone_ET.getText().toString());
                    //test

                    ((EditMemberPersonalInfoActivity) getActivity()).gotoSocialGroupInfo();
                }

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

    public void populateCities(String city) {
        autoCompleteCitiesRequestService.populateCities(getCitiesAPIInterface, city, new IResponseHandler() {
            @Override
            public void HandleAfterResponse(Object o) {
                List<AutoCompleteModel> responseModel = (List<AutoCompleteModel>) o;
                initialArray.clear();
                autoCities.clear();
                callCityAdapter.clear();
                for (AutoCompleteModel temp : responseModel) {
//                    System.out.println(temp.toString());
                    autoCities.add(temp);
                    callCityAdapter.add(temp.getText() + " " + temp.getElementStr());
                }
                callCityAdapter.notifyDataSetChanged();
            }
        });
    }

    public Boolean verifyForm() {
        Boolean result = true;
        if (cityAuto_TIET.getText().toString().isEmpty() || userDefaultUserContactCity.getId() == null) {
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
