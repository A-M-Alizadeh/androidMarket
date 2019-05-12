package com.example.aalizade.mbazar_base_app.fragments.showmemberpersonalinfo_frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.ShowMemberPersonalInfoActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowMemberPersonalInfo_CallInfoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowMemberPersonalInfo_CallInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowMemberPersonalInfo_CallInfoFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView user_contactCity_TV, user_contactAddress_TV, user_contacPostalCode_TV, user_contactEmail_TV, user_contactmobileNo_TV,
            user_contactPhoneNo_TV, user_contactJobAddress_TV, user_contactJobPostalCode_TV, user_contactJobPhoneNo_TV;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShowMemberPersonalInfo_CallInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShowMemberPersonalInfo_CallInfoFragment.
     */
    public static ShowMemberPersonalInfo_CallInfoFragment newInstance(String param1, String param2) {
        ShowMemberPersonalInfo_CallInfoFragment fragment = new ShowMemberPersonalInfo_CallInfoFragment();
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
        View view= inflater.inflate(R.layout.fragment_show_member_personal_info_call_info, container, false);

        user_contactCity_TV = view.findViewById(R.id.member_info_contact_City_TV_id);
        user_contactAddress_TV = view.findViewById(R.id.memberInfo_contactAddress_TV_id);
        user_contacPostalCode_TV = view.findViewById(R.id.member_iinfo_contact_postalCode_TV_id);
        user_contactEmail_TV = view.findViewById(R.id.member_iinfo_contact_emailAddress_TV_id);
        user_contactmobileNo_TV = view.findViewById(R.id.member_info_contact_mobileNo_TV_id);
        user_contactPhoneNo_TV = view.findViewById(R.id.member_iinfo_contact_phoneNo_TV_id);
        user_contactJobAddress_TV = view.findViewById(R.id.member_info_contact_jobAddress_TV_id);
        user_contactJobPostalCode_TV = view.findViewById(R.id.member_info_contact_jobPostalCode_TV_id);
        user_contactJobPhoneNo_TV = view.findViewById(R.id.member_info_contact_jobPhoneNo_TV_id);

        user_contactCity_TV.setText(ShowMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_city().getText());
        user_contactAddress_TV.setText(ShowMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_address());
        user_contacPostalCode_TV.setText(ShowMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_postalCode());
        user_contactEmail_TV.setText(ShowMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_emailAddress());
        user_contactmobileNo_TV.setText(ShowMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_mobileNo());
        user_contactPhoneNo_TV.setText(ShowMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_phoneNo());
        user_contactJobAddress_TV.setText(ShowMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobAddress());
        user_contactJobPostalCode_TV.setText(ShowMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobPostalCode());
        user_contactJobPhoneNo_TV.setText(ShowMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDefaultUserContact_jobPhoneNo());
        
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
