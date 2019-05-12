package com.example.aalizade.mbazar_base_app.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.department_hierarchy_adapter.DepartmentsOUTERAdapter;
import com.example.aalizade.mbazar_base_app.network.models.DepartmentCustomModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentLevel1FrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentLevel3FrontModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DepartmentGlobalFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DepartmentGlobalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DepartmentGlobalFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private RecyclerView recyclerView;
    private DepartmentsOUTERAdapter departmentsOUTERAdapter;
    DepartmentLevel1FrontModel departmentLevel1FrontModel;
    List<DepartmentCustomModel> departmentCustomModels;
    RelativeLayout relativeLayout;

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    View view;

    public DepartmentGlobalFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public DepartmentGlobalFragment(DepartmentLevel1FrontModel departmentLevel1FrontModel) { //گرفتن لیست مورد نیاز در کانستراکتور فرگمنت
        this.departmentLevel1FrontModel = departmentLevel1FrontModel;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DepartmentGlobalFragment.
     */
    public static DepartmentGlobalFragment newInstance(String param1, String param2) {
        DepartmentGlobalFragment fragment = new DepartmentGlobalFragment();
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
        view = inflater.inflate(R.layout.fragment_department_global, container, false);

        //load image here
        ImageView department_imageView = (ImageView) view.findViewById(R.id.department_imgView_id);
        String imageURL = getString(R.string.small_cover_url);
        imageURL = imageURL.replace("{id}", String.valueOf(departmentLevel1FrontModel.getId()));
        Glide.with(view.getContext()).load(imageURL).into(department_imageView);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.wrapper_relative_layout_id);
        departmentCustomModels = new ArrayList<>();//todo use this custom list and model for department hierarchy
        setData();

        departmentsOUTERAdapter = new DepartmentsOUTERAdapter(getContext(), departmentCustomModels,relativeLayout);
        recyclerView.setAdapter(departmentsOUTERAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);


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

    private void setData() {                        //دسته بندی اطلاعات لیست دپارتمان ها در هر فرگمنت
        String key;                                 //تیتر دسته بندی ها و زیر شاخه های آنها
        List<DepartmentLevel3FrontModel> values = new ArrayList<>();
        //for column 1
        for (int j = 0; j < departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex1FrontModels().size(); j++) {
            //level2 names
            key = departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex1FrontModels().get(j).getTitle();
            System.out.println("KEY-> " + j + " " + departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex1FrontModels().get(j).getTitle());
            //level3 names
            for (int k = 0; k < departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex1FrontModels().get(j).getDepartmentLevel3FrontModels().size(); k++) {
                if (!departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex1FrontModels().get(j).getDepartmentLevel3FrontModels().get(k).getIsSeprator()) {
                    values.add(departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex1FrontModels().get(j).getDepartmentLevel3FrontModels().get(k));
                    System.out.println("VALUE-> " + k + " " + departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex1FrontModels().get(j).getDepartmentLevel3FrontModels().get(k).toString());
                }
            }
            departmentCustomModels.add(new DepartmentCustomModel(key, new ArrayList<DepartmentLevel3FrontModel>(values)));
            values.clear();
        }

        values.clear();

        for (int j = 0; j < departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex2FrontModels().size(); j++) {
            //level2 names
            key = departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex2FrontModels().get(j).getTitle();
            System.out.println("KEY-> " + j + " " + departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex2FrontModels().get(j).getTitle());
            //level3 names
            for (int k = 0; k < departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex2FrontModels().get(j).getDepartmentLevel3FrontModels().size(); k++) {
                if (!departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex2FrontModels().get(j).getDepartmentLevel3FrontModels().get(k).getIsSeprator()) {
                    values.add(departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex2FrontModels().get(j).getDepartmentLevel3FrontModels().get(k));
                    System.out.println("VALUE-> " + k + " " + departmentLevel1FrontModel.getDepartmentLevel2ColumnIndex2FrontModels().get(j).getDepartmentLevel3FrontModels().get(k).getTitle());
                }
            }
            departmentCustomModels.add(new DepartmentCustomModel(key, new ArrayList<DepartmentLevel3FrontModel>(values)));
            values.clear();
        }

    }

}
