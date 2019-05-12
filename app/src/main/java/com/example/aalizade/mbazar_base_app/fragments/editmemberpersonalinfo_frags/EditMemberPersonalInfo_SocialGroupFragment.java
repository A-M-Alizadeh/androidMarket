package com.example.aalizade.mbazar_base_app.fragments.editmemberpersonalinfo_frags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.user.EditMemberPersonalInfoActivity;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.apiInterface.UserRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.user.UserSocialGroupModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditMemberPersonalInfo_SocialGroupFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditMemberPersonalInfo_SocialGroupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditMemberPersonalInfo_SocialGroupFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView user_socialTrend_TV, user_socialGroupName_TV;
    Button stepBack, submitFormBtn, cancelBtn;
    LinearLayout progressWrapper;
    FrameLayout motherLayout;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public EditMemberPersonalInfo_SocialGroupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditMemberPersonalInfo_SocialGroupFragment.
     */

    public static EditMemberPersonalInfo_SocialGroupFragment newInstance(String param1, String param2) {
        EditMemberPersonalInfo_SocialGroupFragment fragment = new EditMemberPersonalInfo_SocialGroupFragment();
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
        final View view = inflater.inflate(R.layout.fragment_edit_member_personal_info_social_group, container, false);
        progressWrapper = (LinearLayout)view.findViewById(R.id.progress_wrapper_id);
        motherLayout = (FrameLayout) view.findViewById(R.id.member_edit_social_frag_mother_layout_id);


        final UserRetrofitAPIInterface userRetrofitAPIInterface = RetrofitOAuthClient.getOauthClient(view.getContext(),motherLayout).create(UserRetrofitAPIInterface.class);

        user_socialTrend_TV = view.findViewById(R.id.member_info_social_group_trend_TV_id);
        user_socialGroupName_TV = view.findViewById(R.id.member_info_social_group_name_TV_id);
        cancelBtn = (Button) view.findViewById(R.id.cancel_btn_id);
        stepBack = view.findViewById(R.id.step_back_btn_id);
        submitFormBtn = view.findViewById(R.id.step_three_done_btn_id);
        if (EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUserSocialGroupList() != null) {
            List<UserSocialGroupModel> socials = new ArrayList<>();
            socials.addAll(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUserSocialGroupList());
            user_socialGroupName_TV.setText(socials.get(0).getName());
            user_socialTrend_TV.setText(socials.get(0).getTrend_name());
        } else {
            user_socialGroupName_TV.setText("هیچ کانونی ثبت نشده است");
            user_socialTrend_TV.setText("هیچ کانونی ثبت نشده است");
        }


        stepBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((EditMemberPersonalInfoActivity) getActivity()).backtoCallInfo();
            }
        });
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        submitFormBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d("User", EditMemberPersonalInfoActivity.generalFullUserFrontModel.toString());
//                Log.d("User", EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getUsername() + " " +
//                        EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getGender_langKey() + " " +
//                        EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getMaritalStatus_langKey() + " " +
//                        EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getFatherName() + " " +
//                        EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getDateOfBorn() + " " +
//                        EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getSkillList() + " ");
//                Log.d("User", EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().toString());

                updateUserPersonalInfo(userRetrofitAPIInterface, view);

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

    public void updateUserPersonalInfo(UserRetrofitAPIInterface userRetrofitAPIInterface, final View view) {
        Log.d("what", EditMemberPersonalInfoActivity.userModelUpdateForm.toString());
        EditMemberPersonalInfoActivity.userModelUpdateForm.setId(EditMemberPersonalInfoActivity.generalFullUserFrontModel.getUser().getId());
        Call<ResponseBody> call = userRetrofitAPIInterface.updateUserPersonalInfo(EditMemberPersonalInfoActivity.userModelUpdateForm);
        ProgressBarShower.StartMyProgressBar(getActivity(),progressWrapper);
        call.enqueue(new CallbackWithRetry<ResponseBody>(call,getActivity(),progressWrapper) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ProgressBarShower.StopMyProgressBar(getActivity(),progressWrapper);
                if (response.isSuccessful()) {
                    try {
                        Log.d("EditMember_f_success", response.body().string());
                        Toast.makeText(view.getContext(),"تغییرات با موفقیت انجام شد",Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(view.getContext(), "مشکل ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("EditMember_f_success", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
