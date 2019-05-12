package com.example.aalizade.mbazar_base_app.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aalizade.mbazar_base_app.fragments.showmemberpersonalinfo_frags.ShowMemberPersonalInfo_CallInfoFragment;
import com.example.aalizade.mbazar_base_app.fragments.showmemberpersonalinfo_frags.ShowMemberPersonalInfo_PersonalInfoFragment;
import com.example.aalizade.mbazar_base_app.fragments.showmemberpersonalinfo_frags.ShowMemberPersonalInfo_SocialGroupFragment;

/**
 * Created by aalizade on 12/28/2017.
 */

public class MembeShowInfoViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Fragment tab1 , tab2,tab3;

    public MembeShowInfoViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 2:
                tab2 = new ShowMemberPersonalInfo_PersonalInfoFragment();
                return tab2;
            case 1:
                tab1 = new ShowMemberPersonalInfo_CallInfoFragment();
                return tab1;
            case 0:
                tab3 = new ShowMemberPersonalInfo_SocialGroupFragment();
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
