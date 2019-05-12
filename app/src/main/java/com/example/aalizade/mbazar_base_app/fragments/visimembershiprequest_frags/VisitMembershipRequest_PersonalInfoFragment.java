package com.example.aalizade.mbazar_base_app.fragments.visimembershiprequest_frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.VisitMembershipRequestActivity;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.requestform_adapter.DegreeRecyclerAdapter;
import com.example.aalizade.mbazar_base_app.network.models.user.UserEducationModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VisitMembershipRequest_PersonalInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VisitMembershipRequest_PersonalInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VisitMembershipRequest_PersonalInfoFragment extends Fragment {
    TextView user_username_TV,user_name_TV_TV,user_lastname_TV,user_fathername_TV,user_nationalcode_TV,user_shNo_TV,user_shSerial_TV,
            user_shLocation_TV,user_bornLocation_TV,user_birthdate_TV,user_gender_TV,user_jobtitle_TV,user_jobcompany_TV,
            user_marriageStatus_TV,user_interest_TV;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public VisitMembershipRequest_PersonalInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VisitMembershipRequest_PersonalInfoFragment.
     */
    public static VisitMembershipRequest_PersonalInfoFragment newInstance(String param1, String param2) {
        VisitMembershipRequest_PersonalInfoFragment fragment = new VisitMembershipRequest_PersonalInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_visitmembershiprequest_personal_info, container, false);

        user_username_TV = view.findViewById(R.id.member_info_user_username_TV_id);
        user_name_TV_TV = view.findViewById(R.id.member_info_user_name_TV_id);
        user_lastname_TV = view.findViewById(R.id.member_info_user_lastname_TV_id);
        user_fathername_TV = view.findViewById(R.id.member_info_user_fathername_TV_id);
        user_nationalcode_TV = view.findViewById(R.id.member_info_user_nationalCode_TV_id);
        user_shNo_TV = view.findViewById(R.id.member_info_user_shenasnameNo_TV_id);
        user_shSerial_TV = view.findViewById(R.id.member_info_user_shenasnameSerial_TV_id);
        user_shLocation_TV = view.findViewById(R.id.member_info_user_SHLocation_TV_id);
        user_bornLocation_TV = view.findViewById(R.id.member_info_user_bornLocation_TV_id);
        user_birthdate_TV = view.findViewById(R.id.member_info_user_birthDate_TV_id);
        user_gender_TV = view.findViewById(R.id.member_info_user_gender_TV_id);
        user_jobtitle_TV = view.findViewById(R.id.member_info_user_jobTitle_TV_id);
        user_jobcompany_TV = view.findViewById(R.id.member_info_user_jobAddress_TV_id);
        user_marriageStatus_TV = view.findViewById(R.id.member_info_user_marriage_status_TV_id);
        user_interest_TV = view.findViewById(R.id.member_info_user_interest_TV_id);

        user_username_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_username());
        user_name_TV_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_name());
        user_lastname_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_family());
        user_fathername_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_fatherName());
        user_nationalcode_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_nationalCode());
        user_shNo_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_shNo());
        user_shSerial_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_shSerialNo());
        user_shLocation_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_shLocation().getText());
        user_bornLocation_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_bornLocation().getText());
        user_birthdate_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_dateOfBorn().getYear()+"/"+
                        VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_dateOfBorn().getMonth()+"/"+
                        VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_dateOfBorn().getDay());
        user_gender_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_gender_langKey());//id
        user_jobtitle_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_jobTitle());
        user_jobcompany_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_jobCompanyName());
        user_marriageStatus_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_maritalStatus_langKey());//id
        user_interest_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_favorite());

        //        degrees.add(degree);
        final RecyclerView degreeRecycler = (RecyclerView) view.findViewById(R.id.degree_recycler_id);
        ArrayList<UserEducationModel> degrees = new ArrayList();
        degrees.addAll(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getUser_userEducationSet());
        final DegreeRecyclerAdapter degreeRecyclerAdapter = new DegreeRecyclerAdapter(view.getContext(), degrees);
        GridLayoutManager myGridLayoutManager = new GridLayoutManager(getContext(), 1);
        degreeRecycler.setLayoutManager(myGridLayoutManager);
        degreeRecycler.setAdapter(degreeRecyclerAdapter);
        degreeRecycler.setHasFixedSize(true);
        degreeRecycler.setItemViewCacheSize(20);
        degreeRecycler.setDrawingCacheEnabled(true);
        degreeRecycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        degreeRecycler.setNestedScrollingEnabled(false);
        //degree listview adapter

        
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
}
