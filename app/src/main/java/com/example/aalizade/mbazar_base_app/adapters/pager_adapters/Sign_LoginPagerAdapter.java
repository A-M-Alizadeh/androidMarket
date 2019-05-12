package com.example.aalizade.mbazar_base_app.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aalizade.mbazar_base_app.fragments.LogInFragment;
import com.example.aalizade.mbazar_base_app.fragments.SignUpFragment;

/**
 * Created by gray on 10/16/17.
 */

public class Sign_LoginPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public Sign_LoginPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                LogInFragment tab1 = new LogInFragment();
                return tab1;
            case 1:
                SignUpFragment tab2 = new SignUpFragment();
//                SignUpFragment tab2 = new SignUpFragment();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
