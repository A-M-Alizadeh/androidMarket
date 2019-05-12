package com.example.aalizade.mbazar_base_app.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aalizade.mbazar_base_app.fragments.membershiprequest_frags.MembershipRequest_CallInfoFragment;
import com.example.aalizade.mbazar_base_app.fragments.membershiprequest_frags.MembershipRequest_IntroducerOrgenizationFragment;
import com.example.aalizade.mbazar_base_app.fragments.membershiprequest_frags.MembershipRequest_PersonalInformationFragment;
import com.example.aalizade.mbazar_base_app.fragments.membershiprequest_frags.MembershipRequest_SocialGroupFragment;

/**
 * Created by aalizade on 12/11/2017.
 */

public class MemberRegisteritionPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Fragment tab1 , tab2 , tab3, tab4;

    public MemberRegisteritionPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new MembershipRequest_IntroducerOrgenizationFragment();
                return tab1;
            case 1:
                tab2 = new MembershipRequest_SocialGroupFragment();
                return tab2;
            case 2:
                tab3 = new MembershipRequest_CallInfoFragment();
                return tab3;
            case 3:
                 tab4 = new MembershipRequest_PersonalInformationFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
