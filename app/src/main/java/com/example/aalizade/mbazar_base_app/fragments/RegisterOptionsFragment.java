package com.example.aalizade.mbazar_base_app.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.MemberShipRequestActivity;
import com.example.aalizade.mbazar_base_app.activities.user.VisitMembershipRequestActivity;
import com.example.aalizade.mbazar_base_app.network.RequestServices.HasRequestForMembershipRequestService;
import com.example.aalizade.mbazar_base_app.utility.MySnackBar;
import com.example.aalizade.mbazar_base_app.network.MBZ_Token_Prefs;
import com.example.aalizade.mbazar_base_app.network.apiInterface.LoginAPIInterface;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.example.aalizade.mbazar_base_app.utility.interfaces.IResponseHandler;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterOptionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterOptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterOptionsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    //stuff
    LoginAPIInterface oauthClient;
    LinearLayout progressWrapper;
    Button memberRegisterBtn, factoyRegisterBtn, storePersonRegisterBtn;
    FrameLayout registerOptionMotherLayout;
    HasRequestForMembershipRequestService hasRequestForMembershipRequestService;
    //stuff
    //

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RegisterOptionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterOptionsFragment.
     */
    public static RegisterOptionsFragment newInstance(String param1, String param2) {
        RegisterOptionsFragment fragment = new RegisterOptionsFragment();
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
            MBZ_Token_Prefs.initTokenSharedPrefs(getContext());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regerition_options, container, false);
        initialFindViews(view);
        InitialAPI_RequestService();
        memberRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MBZ_Token_Prefs.isAuthorized()) {
                    hasRequestForMembershipRequestService.hasRequestforMembership(oauthClient, new IResponseHandler() {
                        @Override
                        public void HandleAfterResponse(Object o) {
                            String USERORMEMBERID = o.toString();
                            if (!o.toString().matches("")) {
                                Toast.makeText(getContext(), "شما قبلا درخواست داده اید", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), VisitMembershipRequestActivity.class);
                                String s = USERORMEMBERID.substring(0, USERORMEMBERID.lastIndexOf('.'));
                                intent.putExtra("USERORMEMBERID", s);
                                startActivity(intent);
                            } else {
                                startActivity(new Intent(getActivity(), MemberShipRequestActivity.class));
                            }
                        }
                    });
                } else {
                    MySnackBar.snackBarWithNoAction("لطفا ابتدا وارد شوید", registerOptionMotherLayout);
                }
            }
        });
        factoyRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temporaryOnclick();
            }
        });
        storePersonRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temporaryOnclick();
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

    public void temporaryOnclick() {
        MySnackBar.snackBarWithNoAction("در حال توسعه و پیاده سازی", registerOptionMotherLayout);
    }

    public void initialFindViews(final View view){
        progressWrapper = (LinearLayout) view.findViewById(R.id.progress_wrapper_id);
        registerOptionMotherLayout = (FrameLayout) view.findViewById(R.id.register_option_mother_layout_id);
        memberRegisterBtn = (Button) view.findViewById(R.id.real_person_register_btn_id);
        factoyRegisterBtn = (Button) view.findViewById(R.id.factory_register_btn_id);
        storePersonRegisterBtn = (Button) view.findViewById(R.id.store_register_btn_id);
    }

    public void InitialAPI_RequestService(){
        oauthClient = RetrofitOAuthClient.getOauthClient(getContext(), registerOptionMotherLayout).create(LoginAPIInterface.class);

        hasRequestForMembershipRequestService = new HasRequestForMembershipRequestService(getContext(),progressWrapper);
    }

}
