package com.example.aalizade.mbazar_base_app.bottom_sheets;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.product_related_adapter.VendorsFilterAdapter;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.utility.sectiond_recyclerview.AdapterSectionRecycler;
import com.example.aalizade.mbazar_base_app.utility.sectiond_recyclerview.Child;
import com.example.aalizade.mbazar_base_app.utility.sectiond_recyclerview.SectionHeader;
import com.example.aalizade.mbazar_base_app.network.apiInterface.ProductAPIInterace;
import com.example.aalizade.mbazar_base_app.network.models.product.FindByVitrinModel;
import com.example.aalizade.mbazar_base_app.network.models.product.GeneralAttributeModel;
import com.example.aalizade.mbazar_base_app.network.models.product.OtherProductTypeModel;
import com.example.aalizade.mbazar_base_app.network.models.product.OtherProductTypeWithFilterModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeLine;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeLineSidebarAttributeTitleModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeLineSidebarFindModel;
import com.example.aalizade.mbazar_base_app.network.models.product.ProductTypeLineSidebarModel;
import com.example.aalizade.mbazar_base_app.network.models.product.VendorsCustomFilterListModel;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Response;

public class ProductOtherProducersBottomSheet extends BottomSheetDialogFragment {
    ProductAPIInterace productAPIInterace;
    RelativeLayout motherLayout;
    LinearLayout progressWrapper;
    List<VendorsCustomFilterListModel> vendorsCustomFilterListModels;           //لیست عنوان اسپینر ها به همراه لیست مورد نیاز آنها
    RecyclerView filterRecyclerView;
    VendorsFilterAdapter vendorsFilterAdapter;
    Button filterOtherVendorsBtn;
    RecyclerView otherProducersRecycler;
    AdapterSectionRecycler adapterRecycler;
    public static Map<Integer, Integer> spinnersSelectedItem;
    Boolean firstTime = true;

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(final Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.product_other_producers_bottom_sheet, null);
        dialog.setContentView(contentView);
        motherLayout = (RelativeLayout) contentView.findViewById(R.id.other_vendors_top_layout_id);
        progressWrapper = (LinearLayout) contentView.findViewById(R.id.progress_wrapper_id);
        productAPIInterace = RetrofitClient.getclient(motherLayout).create(ProductAPIInterace.class);
        filterRecyclerView = (RecyclerView) contentView.findViewById(R.id.filter_recyclerview_id);
        filterOtherVendorsBtn = (Button) contentView.findViewById(R.id.filter_other_vendors_btn_id);
        //list of spinners selected item
        spinnersSelectedItem = new HashMap<>();
        //list of spinners selected item

        otherProducersRecycler = contentView.findViewById(R.id.other_producers_recyclerview_id);

        vendorsCustomFilterListModels = new ArrayList<>();
        if (firstTime){
            getfiltersData();
            firstTime = false;
        }

        //setLayout Manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        otherProducersRecycler.setLayoutManager(linearLayoutManager);
        otherProducersRecycler.setHasFixedSize(true);

        filterOtherVendorsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFiltered();
            }
        });

    }


    public void getfiltersData() {                                          //دریافت اطلاعات برای پر کردن اسپینر های فیلترها
        FindByVitrinModel findByVitrinModel = new FindByVitrinModel();
        findByVitrinModel.setId("21");//todo set product id -> GlobalVariables.selectedProductID
        findByVitrinModel.setVitrinId(GlobalVariables.selectedCity);
        Call<ProductTypeLineSidebarModel> call = productAPIInterace.getVendorFilterList(findByVitrinModel);
        ProgressBarShower.StartMyProgressBar(getActivity(), progressWrapper);
        call.enqueue(new CallbackWithRetry<ProductTypeLineSidebarModel>(call, getActivity(), motherLayout) {
            @Override
            public void onResponse(Call<ProductTypeLineSidebarModel> call, Response<ProductTypeLineSidebarModel> response) {
                ProgressBarShower.StopMyProgressBar(getActivity(), progressWrapper);
                if (response.isSuccessful()) { //todo change set to list
                    String Key;
                    List<GeneralAttributeModel> values = new ArrayList<>();
                    spinnersSelectedItem.clear();
                    vendorsCustomFilterListModels.clear();

                    values.add(new GeneralAttributeModel("-1", "انتخاب کنید"));
                    values.addAll(response.body().getVendorSet());
                    vendorsCustomFilterListModels.add(new VendorsCustomFilterListModel("عرضه کننده : ",-1, new ArrayList<GeneralAttributeModel>(values)));
                    values.clear();

                    values.add(new GeneralAttributeModel("-1", "انتخاب کنید"));
                    values.addAll(response.body().getProductTypeGuarantySet());
                    vendorsCustomFilterListModels.add(new VendorsCustomFilterListModel("گارانتی : ",-2, new ArrayList<GeneralAttributeModel>(values)));
                    values.clear();

                    //add value to count spinners
                    spinnersSelectedItem.put(0, -1);
                    spinnersSelectedItem.put(1, -1);
                    //add value to count spinners
                    //spinner counter for counting and setting the keys of soinner items
                    int spinnerCounter = 2;
                    for (ProductTypeLineSidebarAttributeTitleModel productTypeLineSidebarAttributeTitleModel : response.body().getAttributeTitleSet()) {
                        Key = productTypeLineSidebarAttributeTitleModel.getTitle();
                        values.add(new GeneralAttributeModel("-1", "انتخاب کنید"));
                        for (GeneralAttributeModel generalAttributeModel : productTypeLineSidebarAttributeTitleModel.getAttributeValueSet()) {
                            values.add(generalAttributeModel);
                        }
                        vendorsCustomFilterListModels.add(new VendorsCustomFilterListModel(Key + " :",productTypeLineSidebarAttributeTitleModel.getId(), new ArrayList<GeneralAttributeModel>(values)));
                        spinnersSelectedItem.put(spinnerCounter, -1);
                        spinnerCounter++;
                    }

                    for (int i = 0; i < vendorsCustomFilterListModels.size(); i++) {
                        System.out.println("filter pack -> " + vendorsCustomFilterListModels.get(i).toString());
                    }
                    setFiltersSpinnersAdapter();
                    System.out.println("spinners Number " + String.valueOf(spinnersSelectedItem.size()));

                } else {
                    Toast.makeText(getContext(), "اطلاعات دریافت نشد", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("Fail Combo1", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void getDataFiltered() {                 //دریافت لیست اطلاعات فیلتر شده
        ProductTypeLineSidebarFindModel productTypeLineSidebarFindModel = new ProductTypeLineSidebarFindModel();
        productTypeLineSidebarFindModel.setVitrinId(GlobalVariables.selectedCity);
        productTypeLineSidebarFindModel.setId("21");

        Set<Integer> vendorIdSet = new HashSet<>();
        Set<Integer> productTypeGuarantyIdSet = new HashSet<>();
        if (spinnersSelectedItem.get(0) != -1){
//            System.out.println("V0 list "+vendorsCustomFilterListModels.get(0).toString());
//            System.out.println("V0 Attr "+vendorsCustomFilterListModels.get(0).getAttributeModels());
//            System.out.println("Spi SelectedItem "+spinnersSelectedItem.get(0));
//            System.out.println("final1 "+vendorsCustomFilterListModels.get(0).getAttributeModels().get(spinnersSelectedItem.get(0)+1).getId());
            vendorIdSet.add(Integer.valueOf(vendorsCustomFilterListModels.get(0).getAttributeModels().get(spinnersSelectedItem.get(0)+1).getId()));
        }
        if (spinnersSelectedItem.get(1) != -1){
//            productTypeGuarantyIdSet.add(spinnersSelectedItem.get(1));
            productTypeGuarantyIdSet.add(Integer.valueOf(vendorsCustomFilterListModels.get(1).getAttributeModels().get(spinnersSelectedItem.get(1)+1).getId()));
        }
        HashMap<Integer, Set<Integer>> attributeValueIdSetHashMap = new HashMap<>();
        for (int i=2;i<spinnersSelectedItem.size();i++){
            if (spinnersSelectedItem.get(i) != -1){
//                System.out.println("3 hashkey "+vendorsCustomFilterListModels.get(i).getId());
//                System.out.println("final3 "+vendorsCustomFilterListModels.get(i).getAttributeModels().get(spinnersSelectedItem.get(i)+1).getId());
                Set<Integer> attr_codes = new HashSet<>();
                attr_codes.add(spinnersSelectedItem.get(i));
                attr_codes.add(Integer.valueOf(vendorsCustomFilterListModels.get(i).getAttributeModels().get(spinnersSelectedItem.get(i)+1).getId()));
                attributeValueIdSetHashMap.put(vendorsCustomFilterListModels.get(i).getId(),attr_codes);
            }
        }
        productTypeLineSidebarFindModel.setVendorIdSet(vendorIdSet);
        productTypeLineSidebarFindModel.setProductTypeGuarantyIdSet(productTypeGuarantyIdSet);
        productTypeLineSidebarFindModel.setAttributeValueIdSetHashMap(attributeValueIdSetHashMap);
        System.out.println("GET FILTEREDDATA: "+new Gson().toJson(productTypeLineSidebarFindModel));


        Call<OtherProductTypeWithFilterModel> call = productAPIInterace.getOtherVendors(productTypeLineSidebarFindModel);
        call.enqueue(new CallbackWithRetry<OtherProductTypeWithFilterModel>(call, getActivity(), motherLayout) {
            @Override
            public void onResponse(Call<OtherProductTypeWithFilterModel> call, Response<OtherProductTypeWithFilterModel> response) {
                if (response.isSuccessful()) { //todo change set to list
                    Log.v("otherVendors: ", new Gson().toJson(response.body()));

                    String vendorName;
                    Integer vendorId;
                    List<SectionHeader> vendorsList = new ArrayList<>();
                    List<Child> childrenList = new ArrayList<>();
                    for (OtherProductTypeModel otherProductTypeModel : response.body().getOtherModelList()) {
                        vendorName = otherProductTypeModel.getVendorName();
                        vendorId = otherProductTypeModel.getVendorId();
                        for (ProductTypeLine productTypeLine : otherProductTypeModel.getProductTypeLineList()) {
                            Child child = new Child();
                            child.setOnSale(productTypeLine.getOnSale());
                            child.setTypeLineId(productTypeLine.getId());
                            child.setUnitPericeTaxIncludeDiscountInclude(productTypeLine.getUnitPriceTaxIncludeDiscountInclude());
                            child.setGuarantyTitle(productTypeLine.getAttributeModelHashMap().get(0).getTitle());
                            child.setColorTitle(productTypeLine.getAttributeModelHashMap().get(7).getTitle());
                            childrenList.add(child);
                        }
                        vendorsList.add(new SectionHeader(vendorId, vendorName, new ArrayList<Child>(childrenList)));
                        childrenList.clear();
                    }

                    adapterRecycler = new AdapterSectionRecycler(getContext(), vendorsList);
                    otherProducersRecycler.setAdapter(adapterRecycler);
//                    vendorsFilterAdapter.notifyDataSetChanged();

                } else {
                    Toast.makeText(getContext(), "اطلاعات دریافت نشد", Toast.LENGTH_SHORT).show();
                    try {
                        Log.d("Fail Combo1", String.valueOf(response.errorBody().string()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void setFiltersSpinnersAdapter(){
        vendorsFilterAdapter = new VendorsFilterAdapter(getContext(), vendorsCustomFilterListModels);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        filterRecyclerView.setLayoutManager(linearLayoutManager2);
        filterRecyclerView.setAdapter(vendorsFilterAdapter);
        filterRecyclerView.setHasFixedSize(true);
        filterRecyclerView.setItemViewCacheSize(20);
        filterRecyclerView.setDrawingCacheEnabled(true);
        filterRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        filterRecyclerView.setNestedScrollingEnabled(false);

    }

}
