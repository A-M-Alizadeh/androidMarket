package com.example.aalizade.mbazar_base_app.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aalizade.mbazar_base_app.fragments.editmemberpersonalinfo_frags.EditMemberPersonalInfo_ContactInfoFragment;
import com.example.aalizade.mbazar_base_app.fragments.editmemberpersonalinfo_frags.EditMemberPersonalInfo_PersonalInfoFragment;
import com.example.aalizade.mbazar_base_app.fragments.editmemberpersonalinfo_frags.EditMemberPersonalInfo_SocialGroupFragment;

/**
 * Created by aalizade on 12/28/2017.
 */

public class MemberEditInfoViewPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Fragment tab1 , tab2,tab3;

    public MemberEditInfoViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 2:
                tab3 = new EditMemberPersonalInfo_PersonalInfoFragment();
                return tab3;
            case 1:
                tab2 = new EditMemberPersonalInfo_ContactInfoFragment();
                return tab2;
            case 0:
                tab1 = new EditMemberPersonalInfo_SocialGroupFragment();
                return tab1;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
