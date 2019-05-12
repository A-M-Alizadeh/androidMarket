package com.example.aalizade.mbazar_base_app.fragments.checkout_frags;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.checkout_steps_adapters.Checkout_SocialStepAdapter;
import com.example.aalizade.mbazar_base_app.network.apiInterface.CartAPIInterface;
import com.example.aalizade.mbazar_base_app.network.apiInterface.GeneralRetrofitAPIInterface;
import com.example.aalizade.mbazar_base_app.network.models.general.ComboRequestModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitOAuthClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CheckOutSocialOrganizationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CheckOutSocialOrganizationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckOutSocialOrganizationFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    static RelativeLayout motherLayout;

    private String mParam1;
    private String mParam2;
    static TextView selected_SocialGroup_name_txt, selected_SocialGroup_code_txt;
    RecyclerView socialGroupsRecycler;
    Checkout_SocialStepAdapter checkoutSocialStepAdapter;
    GeneralRetrofitAPIInterface generalRetrofitAPIInterface;
    static CartAPIInterface cartAPIInterface;
    static ArrayList<ArrayList<String>> trendComboBoxes;
    public static ArrayList<String> selectedSocialGroup;


    static Activity activity;
    static FrameLayout frameLayout;

    private OnFragmentInteractionListener mListener;

    public CheckOutSocialOrganizationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckOutSocialOrganizationFragment.
     */
    public static CheckOutSocialOrganizationFragment newInstance(String param1, String param2) {
        CheckOutSocialOrganizationFragment fragment = new CheckOutSocialOrganizationFragment();
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
        View view = inflater.inflate(R.layout.fragment_check_out_social_organization, container, false);

        //recycler
        selected_SocialGroup_name_txt = (TextView) view.findViewById(R.id.selected_socialgroup_name_txt_id);
        selected_SocialGroup_code_txt = (TextView) view.findViewById(R.id.selected_socialgroup_code_txt_id);
        socialGroupsRecycler = (RecyclerView) view.findViewById(R.id.social_groups_recycler_id);

        motherLayout = (RelativeLayout) view.findViewById(R.id.mother_layout_id);
        generalRetrofitAPIInterface = RetrofitOAuthClient.getOauthClient(getContext(), motherLayout).create(GeneralRetrofitAPIInterface.class);
        trendComboBoxes = new ArrayList<>();
        selectedSocialGroup = new ArrayList<>();//todo

        activity = getActivity();
        frameLayout = (FrameLayout) view.findViewById(R.id.social_framelayout_id) ;

        getSocialComboMethod();

        //recycler
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

    private void SetRecyclerAdapter(RecyclerView recyclerView) {
        checkoutSocialStepAdapter = new Checkout_SocialStepAdapter(getActivity(), trendComboBoxes,motherLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(checkoutSocialStepAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public static void writeSelectedSocialGroup(int position) {
        selectedSocialGroup = trendComboBoxes.get(position);
        selected_SocialGroup_name_txt.setText(trendComboBoxes.get(position).get(1));
        selected_SocialGroup_code_txt.setText(trendComboBoxes.get(position).get(2));
    }

    public void getSocialComboMethod() {
        final ComboRequestModel trendComboBox = new ComboRequestModel("socialGroup", "socialGroupForCart", "");
        Map<String, ComboRequestModel> combos = new HashMap<>();
        combos.put("socialGroup", trendComboBox);

        Gson gson = new Gson();
        String test = gson.toJson(combos);
        System.out.println("LOG COMBO " + test);

        Call<Map<String, ArrayList<ArrayList<String>>>> call = generalRetrofitAPIInterface.getCartCombo(combos);
        call.enqueue(new CallbackWithRetry<Map<String, ArrayList<ArrayList<String>>>>(call, getActivity(), motherLayout) {
            @Override
            public void onResponse(Call<Map<String, ArrayList<ArrayList<String>>>> call, Response<Map<String, ArrayList<ArrayList<String>>>> response) {
                if (response.isSuccessful()) {
                    trendComboBoxes = response.body().get("socialGroup");
                    System.out.println("COMBO " + trendComboBoxes);

                    SetRecyclerAdapter(socialGroupsRecycler);

                } else {
                    try {
                        Log.d("Fail Combo1", String.valueOf(response.errorBody().string()));
                        Log.d("Fail Combo2", response.errorBody().toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
