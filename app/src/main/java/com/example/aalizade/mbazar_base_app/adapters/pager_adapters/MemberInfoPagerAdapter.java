package com.example.aalizade.mbazar_base_app.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.aalizade.mbazar_base_app.fragments.visimembershiprequest_frags.VisitMembershipRequest_CallInfoFragment;
import com.example.aalizade.mbazar_base_app.fragments.visimembershiprequest_frags.VisitMembershipRequest_IntroducerOrganizationInfoFragment;
import com.example.aalizade.mbazar_base_app.fragments.visimembershiprequest_frags.VisitMembershipRequest_PersonalInfoFragment;
import com.example.aalizade.mbazar_base_app.fragments.visimembershiprequest_frags.VisitMembershipRequest_SocialGroupInfoFragment;

/**
 * Created by aalizade on 12/11/2017.
 */

public class MemberInfoPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Fragment tab1 , tab2 , tab3, tab4;

    public MemberInfoPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new VisitMembershipRequest_IntroducerOrganizationInfoFragment();
                return tab1;
            case 1:
                tab2 = new VisitMembershipRequest_SocialGroupInfoFragment();
                return tab2;
            case 2:
                tab3 = new VisitMembershipRequest_CallInfoFragment();
                return tab3;
            case 3:
                 tab4 = new VisitMembershipRequest_PersonalInfoFragment();
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
