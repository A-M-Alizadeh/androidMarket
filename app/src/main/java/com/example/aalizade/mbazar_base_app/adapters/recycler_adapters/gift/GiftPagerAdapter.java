package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aalizade.mbazar_base_app.fragments.gift.MyGiftFragment;
import com.example.aalizade.mbazar_base_app.fragments.gift.RequestedGiftFragment;

/**
 * Created by gray on 10/16/17.
 */

public class GiftPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public GiftPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                RequestedGiftFragment tab1 = new RequestedGiftFragment();
                return tab1;
            case 1:
                MyGiftFragment tab2 = new MyGiftFragment();
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
