package com.example.aalizade.mbazar_base_app.fragments;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductUserViewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductUserViewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductUserViewsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProductUserViewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductUserViewsFragment.
     */
    public static ProductUserViewsFragment newInstance(String param1, String param2) {
        ProductUserViewsFragment fragment = new ProductUserViewsFragment();
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
        final View view = inflater.inflate(R.layout.fragment_product_user_views, container, false);

        //fab
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.add_user_view_fab_id);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog
                final Dialog dialog = new Dialog(getContext());
                // Include dialog.xml file
                dialog.setContentView(R.layout.user_view_dialog);

                //set font
                Typeface custom_font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/B_Yekan.ttf");

                TextInputLayout user_view_name_tiet = (TextInputLayout) dialog.findViewById(R.id.user_view_dialog_tiet_layout);
                user_view_name_tiet.setTypeface(custom_font);

                TextInputLayout user_view_title_tiet = (TextInputLayout) dialog.findViewById(R.id.user_view_dialog_View_title_layout);
                user_view_title_tiet.setTypeface(custom_font);

                TextInputLayout user_view_comment_tiet = (TextInputLayout) dialog.findViewById(R.id.user_view_dialog_View_comment_layout);
                user_view_comment_tiet.setTypeface(custom_font);
                //set font

                //show inputs
                TextView inputsTxtView = (TextView) dialog.findViewById(R.id.dialog_enter_you_view_txtv_id);
                final LinearLayout inputsLayer = (LinearLayout) dialog.findViewById(R.id.user_view_inputs_layout_id);
                inputsTxtView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        inputsLayer.setVisibility(View.VISIBLE);
                    }
                });
                //show inputs

                // Set dialog title
                dialog.setTitle("نقد و بررسی کالا");
                //customize
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                Window window = dialog.getWindow();
                lp.copyFrom(window.getAttributes());
//This makes the dialog take up the full width
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(lp);
                //customize
                dialog.show();
                //dialog
                ((Button) dialog.findViewById(R.id.view_submit_btn_id)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(getContext(), "نظر با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
        //fab

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

    //custom class for recycler

    //custom class for recycler
}
