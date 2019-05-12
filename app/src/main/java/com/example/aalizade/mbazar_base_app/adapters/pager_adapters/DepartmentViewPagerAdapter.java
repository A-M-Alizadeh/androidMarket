package com.example.aalizade.mbazar_base_app.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aalizade.mbazar_base_app.fragments.DepartmentGlobalFragment;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentLevel1FrontModel;

import java.util.List;

/**
 * Created by aalizade on 12/28/2017.
 */

public class DepartmentViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    List<DepartmentLevel1FrontModel> useableDepartmentLevel1FrontModels;
//    List<Fragment> frags;

    public DepartmentViewPagerAdapter(FragmentManager fm, int NumOfTabs, List<DepartmentLevel1FrontModel> useableDepartmentLevel1FrontModels) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.useableDepartmentLevel1FrontModels = useableDepartmentLevel1FrontModels;
    }

    @Override
    public Fragment getItem(int position) {
        return new DepartmentGlobalFragment(useableDepartmentLevel1FrontModels.get(position));
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
