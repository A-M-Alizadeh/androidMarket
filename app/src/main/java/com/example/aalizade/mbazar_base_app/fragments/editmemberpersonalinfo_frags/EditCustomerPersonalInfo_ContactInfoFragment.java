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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.EditCustomerPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.network.RequestServices.AutoCompleteCitiesRequestService;
import com.example.aalizade.mbazar_base_app.utility.CutomAutoCompleteAdapter;
import com.example.aalizade.mbazar_base_app.utility.MobileNumberValidator;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.UserRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.AutoCompleteModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditCustomerPersonalInfo_ContactInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditCustomerPersonalInfo_ContactInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditCustomerPersonalInfo_ContactInfoFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    LinearLayout progressWrapper;

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

    Button topersonalPageBtn, stepTwoDone, reject;
    AutoCompleteCitiesRequestService autoCompleteCitiesRequestService;
    GeneralRetrofitAPIInterface getCitiesAPIInterface;
    MobileNumberValidator mobileNumberValidator;

    private OnFragmentInteractionListener mListener;

    public EditCustomerPersonalInfo_ContactInfoFragment() {
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
    public static EditCustomerPersonalInfo_ContactInfoFragment newInstance(String param1, String param2) {
        EditCustomerPersonalInfo_ContactInfoFragment fragment = new EditCustomerPersonalInfo_ContactInfoFragment();
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
        final View view = inflater.inflate(R.layout.fragment_edit_customer_personal_info_contact_info, container, false);
        mobileNumberValidator = new MobileNumberValidator();

        motherLayout = (FrameLayout) view.findViewById(R.id.customer_edit_contact_frag_mother_layout_id);
        progressWrapper = (LinearLayout) view.findViewById(R.id.progress_wrapper_id);
        autoCompleteCitiesRequestService = new AutoCompleteCitiesRequestService(getContext(), progressWrapper);
        //client
        MBZ_Token_Prefs.initTokenSharedPrefs(getContext());
        getCitiesAPIInterface = RetrofitClient.getclient(progressWrapper).create(GeneralRetrofitAPIInterface.class);
        //client

        final UserRetrofitAPIInterface userRetrofitAPIInterface = RetrofitOAuthClient.getOauthClient(view.getContext(), motherLayout).create(UserRetrofitAPIInterface.class);
        stepTwoDone = view.findViewById(R.id.step_three_done_btn_id);
        topersonalPageBtn = view.findViewById(R.id.step_back_btn_id);
        reject = view.findViewById(R.id.cancel_btn_id);


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
        if (!EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_city().getId().equals("")) {
//            cityAuto_TIET.setEnabled(false);
            cityAuto_TIET.setText(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_city().getText() + " " +
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_city().getElementStr());
            userDefaultUserContactCity.setId(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_city().getId());
        }

        if (!EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_mobileNo().trim().equals("")) {
            mobileNumber_ET.setText(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_mobileNo());
        }
        if (!EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_address().trim().equals("")) {
            userAddress_ET.setText(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_address());
        }
        if (!EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_postalCode().trim().equals("")) {
            userpostalCode_ET.setText(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_postalCode());
        }
        if (!EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_emailAddress().trim().equals("")) {
            userEmail_ET.setText(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_emailAddress());
        }
        if (!EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_phoneNo().trim().equals("")) {
            userPhone_ET.setText(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_phoneNo());
        }
        if (!EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobAddress().trim().equals("")) {
            userJobAddress_ET.setText(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobAddress());
        }
        if (!EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobPostalCode().trim().equals("")) {
            userjobpostalCode_ET.setText(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobPostalCode());
        }
        if (!EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobPhoneNo().trim().equals("")) {
            userJobPhone_ET.setText(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobPhoneNo());
        }
        //disabling fields ----------------------------------------


        //auto complete city
        callCityAdapter = new CutomAutoCompleteAdapter(getContext(), R.layout.autocomplete_row,R.id.autocomplete_txt_id, initialArray);
        cityAuto_TIET.setAdapter(callCityAdapter);
        cityAuto_TIET.setDropDownHeight(500);
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
                ((EditCustomerPersonalInfoActivity) getActivity()).backtoPersonalInfo();
            }
        });
        stepTwoDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyForm()) {
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().setChildNumber(null);
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_city(userDefaultUserContactCity);
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_address(userAddress_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_postalCode(userpostalCode_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_emailAddress(userEmail_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_mobileNo(mobileNumber_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_phoneNo(userPhone_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_jobAddress(userJobAddress_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_jobPostalCode(userjobpostalCode_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().setDefaultUserContact_jobPhoneNo(userJobPhone_ET.getText().toString());

                    //test
                    EditCustomerPersonalInfoActivity.userModelUpdateForm.setChildNumber(null);
                    EditCustomerPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactCity(userDefaultUserContactCity);
                    EditCustomerPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactAddress(userAddress_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactPostalCode(userpostalCode_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactEmailAddress(userEmail_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactMobileNo(mobileNumber_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactPhoneNo(userPhone_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactJobAddress(userJobAddress_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactJobPostalCode(userjobpostalCode_ET.getText().toString());
                    EditCustomerPersonalInfoActivity.userModelUpdateForm.setDefaultUserContactJobPhoneNo(userJobPhone_ET.getText().toString());
                    //test

//                    ((EditCustomerPersonalInfoActivity) getActivity()).gotoSocialGroupInfo();
                    updateUserPersonalInfo(userRetrofitAPIInterface, view);
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

    public void updateUserPersonalInfo(UserRetrofitAPIInterface userRetrofitAPIInterface, final View view) {
        Log.d("what", EditCustomerPersonalInfoActivity.userModelUpdateForm.toString());
        EditCustomerPersonalInfoActivity.userModelUpdateForm.setId(EditCustomerPersonalInfoActivity.generalFullUserFrontModel.getUser().getId());
        Call<ResponseBody> call = userRetrofitAPIInterface.updateUserPersonalInfo(EditCustomerPersonalInfoActivity.userModelUpdateForm);
        ProgressBarShower.StartMyProgressBar(getActivity(), progressWrapper);
        call.enqueue(new CallbackWithRetry<ResponseBody>(call, getActivity(), progressWrapper) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressBarShower.StopMyProgressBar(getActivity(), progressWrapper);
                if (response.isSuccessful()) {
                    try {
                        Log.d("what", response.body().string());
                        Toast.makeText(view.getContext(), "تغییرات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(view.getContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("what", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
