package com.example.aalizade.mbazar_base_app.utility.sectiond_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;

import java.util.List;

/**
 * Created by aalizade on 3/5/2018.
 */

public class AdapterSectionRecycler extends SectionRecyclerViewAdapter<SectionHeader, Child, AdapterSectionRecycler.SectionViewHolder, AdapterSectionRecycler.ChildViewHolder> {

    Context context;
    int last_selected_section_number = -1, last_selected_item_number = -1;
    RadioButton lastCheckedRadioBtn;

    public AdapterSectionRecycler(Context context, List<SectionHeader> sectionItemList) {
        super(context, sectionItemList);
        this.context = context;
    }

    @Override
    public SectionViewHolder onCreateSectionViewHolder(ViewGroup sectionViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a_section_header, sectionViewGroup, false);
        return new SectionViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.a_section_child, childViewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int sectionPosition, SectionHeader section) {
        sectionViewHolder.name.setText(section.vendorName);
    }

    @Override
    public void onBindChildViewHolder(final ChildViewHolder childViewHolder, final int sectionNumber, final int itemNumber, Child child) {
        childViewHolder.name.setText(child.getGuarantyTitle()+" - "+child.getColorTitle()+" - "+child.getUnitPericeTaxIncludeDiscountInclude());
        childViewHolder.selectedRBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, String.valueOf(sectionNumber) + " -> " + String.valueOf(itemNumber), Toast.LENGTH_SHORT).show();
                if (sectionNumber == last_selected_section_number && itemNumber == last_selected_item_number) {

                } else {
                    if (lastCheckedRadioBtn != null)
                        lastCheckedRadioBtn.setChecked(false);
//                childViewHolder.selectedRBtn.setChecked(true);
                    lastCheckedRadioBtn = childViewHolder.selectedRBtn;
                    last_selected_item_number = itemNumber;
                    last_selected_section_number = sectionNumber;
                }
            }
        });
    }


    public class SectionViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public SectionViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.section);
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        RadioButton selectedRBtn;

        public ChildViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.child);
            selectedRBtn = (RadioButton) itemView.findViewById(R.id.producers_items_radio_btn_id);
        }
    }
}
