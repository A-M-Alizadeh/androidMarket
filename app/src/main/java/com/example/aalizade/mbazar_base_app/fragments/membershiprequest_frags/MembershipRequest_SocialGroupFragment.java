package com.example.aalizade.mbazar_base_app.fragments.membershiprequest_frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.MemberShipRequestActivity;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.Value;
import com.example.aalizade.mbazar_base_app.network.models.general.ComboRequestModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MembershipRequest_SocialGroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MembershipRequest_SocialGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MembershipRequest_SocialGroupFragment extends Fragment {

    Button nextStep, previousStep;
    EditText socialGroupCode_ET;
    ArrayList<ArrayList<String>> trendComboBoxes, socialGroupSubitems;
    private static List<String> initialTrendList, emptyList;
    Spinner socialGroupTrendsSpinner, socialGroupNamesSpinner;
    ArrayAdapter<String> SocialTrendSpinnerAdapter, SocialGroupNamesAdpater;
    ComboRequestModel socialGroups;
    LinearLayout progressWrapper;
    Boolean firstTime = true;
    GeneralRetrofitAPIInterface generalAPIInterface;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MembershipRequest_SocialGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MembershipRequest_SocialGroupFragment.
     */
    public static MembershipRequest_SocialGroupFragment newInstance(String param1, String param2) {
        MembershipRequest_SocialGroupFragment fragment = new MembershipRequest_SocialGroupFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        GeneralRetrofitAPIInterface localGeneralAPIInterface = RetrofitClient.getclient(progressWrapper).create(GeneralRetrofitAPIInterface.class);
        getSocialComboMethod(localGeneralAPIInterface);//something ahs changed === from client to oathClient and maybe other things

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
        View view = inflater.inflate(R.layout.fragment_membershiprequest_social_group, container, false);
        progressWrapper = (LinearLayout) view.findViewById(R.id.progress_wrapper_id);

        trendComboBoxes = new ArrayList<>();
        socialGroupSubitems = new ArrayList<>();
        initialTrendList = new ArrayList<>();
        emptyList = new ArrayList<>();

        generalAPIInterface = RetrofitOAuthClient.getOauthClient(getContext(), progressWrapper).create(GeneralRetrofitAPIInterface.class);
        MBZ_Token_Prefs.initTokenSharedPrefs(getContext());

        nextStep = (Button) view.findViewById(R.id.step_done_btn_id);
        previousStep = (Button) view.findViewById(R.id.step_back_btn_id);

        socialGroupNamesSpinner = (Spinner) view.findViewById(R.id.SocialGroupName_spinner_id);
        socialGroupCode_ET = (EditText) view.findViewById(R.id.SocialGroupCode_ET_id);

        //trends spinner
        socialGroupTrendsSpinner = (Spinner) view.findViewById(R.id.social_group_field_spinner_id);
        SocialTrendSpinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, initialTrendList);
        SocialTrendSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        socialGroupTrendsSpinner.setAdapter(SocialTrendSpinnerAdapter);
        socialGroupTrendsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getSocialSubItems(Integer.valueOf(trendComboBoxes.get(position).get(0)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //trends spinner

//        social names spinner
        socialGroupNamesSpinner = (Spinner) view.findViewById(R.id.SocialGroupName_spinner_id);
        SocialGroupNamesAdpater = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, emptyList);
        SocialGroupNamesAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        socialGroupNamesSpinner.setAdapter(SocialGroupNamesAdpater);
        socialGroupNamesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getContext(), "Called", Toast.LENGTH_SHORT).show();
                socialGroupCode_ET.setText(socialGroupSubitems.get(position).get(2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //social names spinner


        previousStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MemberShipRequestActivity) getActivity()).backtoCallInfo();
            }
        });
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyForm()) {
                    MemberShipRequestActivity.memberCreateModel.setSocialGroup_id(socialGroupSubitems.get(socialGroupNamesSpinner.getSelectedItemPosition()).get(0));
                    ((MemberShipRequestActivity) getActivity()).thirdStepisDone();
                    socialGroupCode_ET.setText("");
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

    public Boolean verifyForm() {
        Boolean result = true;
        if (socialGroupCode_ET.getText().toString().isEmpty()) {
            socialGroupCode_ET.setError("کانونی انتخاب نشده است");
            Toast.makeText(getContext(), "کانونی انتخاب نشده است", Toast.LENGTH_SHORT).show();
            result = false;
        }
        return result;
    }

    public void getSocialComboMethod(final GeneralRetrofitAPIInterface getCombo) {
        final ComboRequestModel trendComboBox = new ComboRequestModel("trend", "", "");
        Map<String, ComboRequestModel> combos = new HashMap<>();
        combos.put("trendComboBox", trendComboBox);

        Gson gson = new Gson();
        String test = gson.toJson(combos);
        System.out.println("LOG COMBO " + test);

        Call<Map<String, ArrayList<ArrayList<String>>>> call = getCombo.getCombo(combos);
        call.enqueue(new CallbackWithRetry<Map<String, ArrayList<ArrayList<String>>>>(call, getActivity(), progressWrapper) {
            @Override
            public void onResponse(Call<Map<String, ArrayList<ArrayList<String>>>> call, Response<Map<String, ArrayList<ArrayList<String>>>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(view.getContext(), "Success Combo", Toast.LENGTH_SHORT).show();
                    initialTrendList.clear();
                    SocialTrendSpinnerAdapter.clear();
                    SocialTrendSpinnerAdapter.notifyDataSetChanged();

                    trendComboBoxes = response.body().get("trendComboBox");

                    System.out.println("TREND " + trendComboBoxes);

                    for (int i = 0; i < trendComboBoxes.size(); i++) {
                        initialTrendList.add(trendComboBoxes.get(i).get(1));
                    }
                    SocialTrendSpinnerAdapter.notifyDataSetChanged();

                } else {
                    try {
                        Log.d("Fail Combo", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    public void getSocialSubItems(Integer type) {//todo por kardan spinner baraye avvalin bar
        Object valueObj;
        valueObj = new Value(type, MembershipRequest_CallInfoFragment.cityCode);

        System.out.println("type number" + type.toString());
        socialGroups = new ComboRequestModel("socialGroup", "socialGroupOpenWithConfirm", valueObj);
        Map<String, ComboRequestModel> combos = new HashMap<>();
        combos.put("socialGroup", socialGroups);
        System.out.println("social comboreq" + new Gson().toJson(combos));

        Call<Map<String, ArrayList<ArrayList<String>>>> call = generalAPIInterface.getCombo(combos);
        call.enqueue(new CallbackWithRetry<Map<String, ArrayList<ArrayList<String>>>>(call, getActivity(), progressWrapper) {
            @Override
            public void onResponse(Call<Map<String, ArrayList<ArrayList<String>>>> call, Response<Map<String, ArrayList<ArrayList<String>>>> response) {
                System.out.println("reg response code" + response.code());
                if (response.isSuccessful()) {
                    System.out.println("socials responseBody => " + response.body().toString());
                    SocialGroupNamesAdpater.clear();
                    socialGroupSubitems.clear();
//                    Toast.makeText(view.getContext(), "Success Combo", Toast.LENGTH_SHORT).show();
                    socialGroupSubitems = response.body().get("socialGroup");

                    System.out.println("TREND 2" + socialGroupSubitems);

                    for (int i = 0; i < socialGroupSubitems.size(); i++) {
                        SocialGroupNamesAdpater.add(socialGroupSubitems.get(i).get(1));
                    }
                    SocialGroupNamesAdpater.notifyDataSetChanged();
                    if (socialGroupSubitems.size() > 0) {
                        socialGroupCode_ET.setText(socialGroupSubitems.get(0).get(2));
                    } else {
                        socialGroupCode_ET.setText("");
                    }

                    System.out.println("difference " + socialGroupSubitems.toString());

                } else {
                    Toast.makeText(getContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("Fail social reg  Combo1", response.errorBody().string());
                        Log.d("Fail social reg  Combo2", String.valueOf(response.errorBody()));
                        Log.d("Fail social reg Combo3", response.message());
                        Log.d("Fail social reg Combo4", response.errorBody().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
