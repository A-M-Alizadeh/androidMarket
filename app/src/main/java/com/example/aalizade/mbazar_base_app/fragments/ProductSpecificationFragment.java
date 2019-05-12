package com.example.aalizade.mbazar_base_app.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.products_related.Specifications_Explanation_ViewsActivity;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter.SpecificationOUTERAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProductSpecificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProductSpecificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductSpecificationFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    RecyclerView specificationRecyclerview;

    private OnFragmentInteractionListener mListener;

    public ProductSpecificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductSpecificationFragment.
     */
    public static ProductSpecificationFragment newInstance(String param1, String param2) {
        ProductSpecificationFragment fragment = new ProductSpecificationFragment();
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
        View view = inflater.inflate(R.layout.fragment_product_specification, container, false);
        specificationRecyclerview = (RecyclerView) view.findViewById(R.id.product_specification_list_recycler_id);
        SetRecyclerAdapter();
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
    private void SetRecyclerAdapter() {                             //ارسال اطلاعات مشخصات کالا به آداپتور
        SpecificationOUTERAdapter typeAdapter = new SpecificationOUTERAdapter(getContext(), Specifications_Explanation_ViewsActivity.specsWrappers);
        GridLayoutManager myGridLayoutManager = new GridLayoutManager(getContext(), 1);
        specificationRecyclerview.setLayoutManager(myGridLayoutManager);
        specificationRecyclerview.setAdapter(typeAdapter);
        specificationRecyclerview.setHasFixedSize(true);
        specificationRecyclerview.setItemViewCacheSize(20);
        specificationRecyclerview.setDrawingCacheEnabled(true);
        specificationRecyclerview.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        specificationRecyclerview.setNestedScrollingEnabled(false);
    }
    //custom class for recycler
}
