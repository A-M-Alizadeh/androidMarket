package com.example.aalizade.mbazar_base_app.fragments.visimembershiprequest_frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.VisitMembershipRequestActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VisitMembershipRequest_SocialGroupInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VisitMembershipRequest_SocialGroupInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VisitMembershipRequest_SocialGroupInfoFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    TextView user_socialTrend_TV, user_socialGroupName_TV, user_socialGroupcode_TV;


    public VisitMembershipRequest_SocialGroupInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VisitMembershipRequest_SocialGroupInfoFragment.
     */
    public static VisitMembershipRequest_SocialGroupInfoFragment newInstance(String param1, String param2) {
        VisitMembershipRequest_SocialGroupInfoFragment fragment = new VisitMembershipRequest_SocialGroupInfoFragment();
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
        View view =  inflater.inflate(R.layout.fragment_visitmembershiprequest_social_group_info, container, false);

        user_socialTrend_TV = view.findViewById(R.id.member_info_contact_socialGroupTrend_TV_id);
        user_socialGroupName_TV = view.findViewById(R.id.member_info_contact_socialGroupName_TV_id);
        user_socialGroupcode_TV = view.findViewById(R.id.member_info_contact_socialGroupCode_TV_id);

        user_socialTrend_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getSocialGroup_trend_name());
        user_socialGroupName_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getSocialGroup_name());
        user_socialGroupcode_TV.setText(VisitMembershipRequestActivity.membershipRequestFrontModelUpdate.getSocialGroup_code());

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
