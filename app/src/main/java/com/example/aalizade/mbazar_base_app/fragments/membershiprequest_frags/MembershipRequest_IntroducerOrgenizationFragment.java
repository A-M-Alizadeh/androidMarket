package com.example.aalizade.mbazar_base_app.fragments.membershiprequest_frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.MemberShipRequestActivity;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MembershipRequest_IntroducerOrgenizationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MembershipRequest_IntroducerOrgenizationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MembershipRequest_IntroducerOrgenizationFragment extends Fragment {
    Button previousStep,submitBtn,cancelBtn;
    TextInputEditText introducerUsername_ET,introducernactionaCode_ET,introducername_ET,introducerfamilyName_ET;
    LinearLayout progressWrapper;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MembershipRequest_IntroducerOrgenizationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MembershipRequest_IntroducerOrgenizationFragment.
     */
    public static MembershipRequest_IntroducerOrgenizationFragment newInstance(String param1, String param2) {
        MembershipRequest_IntroducerOrgenizationFragment fragment = new MembershipRequest_IntroducerOrgenizationFragment();
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
        final View view = inflater.inflate(R.layout.fragment_membershiprequest_introducer_orgenization, container, false);
        progressWrapper = (LinearLayout)view.findViewById(R.id.progress_wrapper_id);

        introducerUsername_ET = (TextInputEditText)view.findViewById(R.id.user_introducer_username_Teit_id);
        introducernactionaCode_ET = (TextInputEditText)view.findViewById(R.id.user_introducer_nationalCode_Teit_id);
        introducername_ET = (TextInputEditText)view.findViewById(R.id.user_introducer_name_Teit_id);
        introducerfamilyName_ET = (TextInputEditText)view.findViewById(R.id.user_introducer_familyname_Teit_id);

        submitBtn = (Button)view.findViewById(R.id.register_form_submit_btn_id);
        cancelBtn = (Button)view.findViewById(R.id.register_cancel_btn_id);
        previousStep = (Button)view.findViewById(R.id.step_back_btn_id);

        previousStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MemberShipRequestActivity)getActivity()).backtoSocialGroupInfo();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberShipRequestActivity.memberCreateModel.setIntroducer_username(introducerUsername_ET.getText().toString());
                MemberShipRequestActivity.memberCreateModel.setIntroducer_nationalCode(introducernactionaCode_ET.getText().toString());
                MemberShipRequestActivity.memberCreateModel.setIntroducer_name(introducername_ET.getText().toString());
                MemberShipRequestActivity.memberCreateModel.setIntroducer_family(introducerfamilyName_ET.getText().toString());

                Gson gsonObj = new Gson();
                String jsonStr = gsonObj.toJson(MemberShipRequestActivity.memberCreateModel);
                System.out.println("JSON "+jsonStr);

                final LoginAPIInterface registeringAPIInterface = RetrofitOAuthClient.getOauthClient(getContext(),view).create(LoginAPIInterface.class);
                getSocialComboMethod(registeringAPIInterface,view);

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

    public void getSocialComboMethod(final LoginAPIInterface loginAPIInterface,final View view) {
        Call<ResponseBody> call = loginAPIInterface.RegisterMember(MemberShipRequestActivity.memberCreateModel);

        Gson gson = new Gson();
        String sss = gson.toJson(MemberShipRequestActivity.memberCreateModel);
        System.out.println("introduce combo register ---->"+sss);

        call.enqueue(new CallbackWithRetry<ResponseBody>(call,getActivity(),progressWrapper) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(view.getContext(), "درخواست عضویت با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else {
                    Toast.makeText(view.getContext(), "مشکل ارتباط با سرور Combo", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("Fail Combo", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
