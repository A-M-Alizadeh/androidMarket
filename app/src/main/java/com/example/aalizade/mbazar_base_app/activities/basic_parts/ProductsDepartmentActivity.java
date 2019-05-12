package com.example.aalizade.mbazar_base_app.activities.basic_parts;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.adapters.pager_adapters.DepartmentViewPagerAdapter;
import com.example.aalizade.mbazar_base_app.utility.ProgressBarShower;
import com.example.aalizade.mbazar_base_app.network.apiInterface.DepartmentAPIInterace;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentFullfrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentLevel1FrontModel;
import com.example.aalizade.mbazar_base_app.network.models.department.Empty;
import com.example.aalizade.mbazar_base_app.network.retrofit.CallbackWithRetry;
import com.example.aalizade.mbazar_base_app.network.retrofit.RetrofitClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ProductsDepartmentActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    DepartmentViewPagerAdapter adapter;
    DepartmentAPIInterace departmentApiInterface;
    LinearLayout progressWrapper;
    DepartmentFullfrontModel departmentFullfrontModel;
    List<DepartmentLevel1FrontModel> useableDepartmentLevel1FrontModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_department);

        progressWrapper = (LinearLayout) findViewById(R.id.progress_wrapper_id);
        departmentApiInterface = RetrofitClient.getclient(progressWrapper).create(DepartmentAPIInterace.class);
        useableDepartmentLevel1FrontModels = new ArrayList<>();
        fetchDepartmentsData();

    }

    public void fetchDepartmentsData() {                    //دریافت اطلاعات دپارتمان ها
        Call<DepartmentFullfrontModel> call = departmentApiInterface.fetchDeprtmentData(new Empty());
        ProgressBarShower.StartMyProgressBar(ProductsDepartmentActivity.this, progressWrapper);
        call.enqueue(new CallbackWithRetry<DepartmentFullfrontModel>(call, ProductsDepartmentActivity.this, progressWrapper) {
            @Override
            public void onResponse(Call<DepartmentFullfrontModel> call, Response<DepartmentFullfrontModel> response) {
                ProgressBarShower.StopMyProgressBar(ProductsDepartmentActivity.this, progressWrapper);
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String s = gson.toJson(response.body());
                    Log.d("Real Departments Object", s);
                    departmentFullfrontModel = new DepartmentFullfrontModel();
                    departmentFullfrontModel.setDepartmentLevel1FrontModels(response.body().getDepartmentLevel1FrontModels());
                    departmentFullfrontModel.setIsSpecialList(response.body().getIsSpecialList());
                    // fill data
                    for (int i = 0; i < departmentFullfrontModel.getDepartmentLevel1FrontModels().size(); i++) {
                        if (!departmentFullfrontModel.getDepartmentLevel1FrontModels().get(i).getIsSeprator()) {
                            useableDepartmentLevel1FrontModels.add(departmentFullfrontModel.getDepartmentLevel1FrontModels().get(i));
                        }
                    }
                    initialPage(); // نمایش صفحه درصورت دریافت اطلاعات

                } else {
                    try {
                        Log.d("MYERROR", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "مشکل دریافت اطلاعات", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void initialPage() {
        tabLayout = (TabLayout) findViewById(R.id.department_tablayout_id);
        for (int i = 0; i < useableDepartmentLevel1FrontModels.size(); i++) {
            tabLayout.addTab(tabLayout.newTab().setText(useableDepartmentLevel1FrontModels.get(i).getTitle()));
        }
        //tab fonts
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/B_Yekan.ttf");
        for (int i = tabLayout.getTabCount() - 1; i >= 0; i--) {
            //noinspection ConstantConditions
            TextView tv = (TextView) LayoutInflater.from(getApplicationContext()).inflate(R.layout.atextview, null);
            tv.setTypeface(custom_font);
            tabLayout.getTabAt(i).setCustomView(tv);
        }
        //tab fonts

        viewPager = (ViewPager) findViewById(R.id.department_viewpager_id);
        adapter = new DepartmentViewPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), useableDepartmentLevel1FrontModels);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setCurrentItem(tabLayout.getTabCount() / 2);//change  to 4

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}



