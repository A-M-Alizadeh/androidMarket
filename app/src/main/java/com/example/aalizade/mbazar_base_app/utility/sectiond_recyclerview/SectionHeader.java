package com.example.aalizade.mbazar_base_app.utility.sectiond_recyclerview;

import com.intrusoft.sectionedrecyclerview.Section;

import java.util.List;

/**
 * Created by aalizade on 3/5/2018.
 */

public class SectionHeader implements Section<Child> {

    List<Child> childList;
    String vendorName;
    Integer vendorId;

    public SectionHeader(Integer vendorId , String vendorName, List<Child> childList) {
        this.childList = childList;
        this.vendorName = vendorName;
        this.vendorId = vendorId;
    }

    @Override
    public String toString() {
        return "SectionHeader{" +
                "childList=" + childList +
                ", vendorName='" + vendorName + '\'' +
                ", vendorId=" + vendorId +
                '}';
    }

    @Override
    public List<Child> getChildItems() {
        return childList;
    }

    public String getSectionText() {
        return vendorName;
    }
}
