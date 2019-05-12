package com.example.aalizade.mbazar_base_app.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aalizade.mbazar_base_app.fragments.ProductExplanationFragment;
import com.example.aalizade.mbazar_base_app.fragments.ProductSpecificationFragment;
import com.example.aalizade.mbazar_base_app.fragments.ProductUserViewsFragment;

/**
 * Created by gray on 10/16/17.
 */

public class Spec_Exp_UsrView_PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Spec_Exp_UsrView_PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ProductUserViewsFragment tab1 = new ProductUserViewsFragment();
                return tab1;
            case 1:
                ProductSpecificationFragment tab2 = new ProductSpecificationFragment();
                return tab2;
            case 2:
                ProductExplanationFragment tab3 = new ProductExplanationFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
