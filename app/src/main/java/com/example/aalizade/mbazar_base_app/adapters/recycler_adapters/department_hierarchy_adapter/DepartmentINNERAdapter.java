package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.department_hierarchy_adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.products_related.DepartmentPageActivity;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.utility.MySnackBar;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentLevel3FrontModel;

import java.util.List;


/**
 * Created by aalizade on 11/13/2017.
 */

public class DepartmentINNERAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<DepartmentLevel3FrontModel> options;
    Activity activity;
    RelativeLayout relativeLayout;

    public DepartmentINNERAdapter(Context context, List<DepartmentLevel3FrontModel> options,RelativeLayout relativeLayout) {
        this.context = context;
        this.options = options;
        activity = (Activity) context;
        this.relativeLayout = relativeLayout;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.departments_subitem_layout, parent, false);
        return new OptionViewHoler(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final DepartmentLevel3FrontModel item = options.get(position);
        OptionViewHoler optionViewHoler = (OptionViewHoler) holder;
        optionViewHoler.subItemTitle.setText(item.getTitle());
        optionViewHoler.touchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalVariables.selectedCity == null){
                    MySnackBar.snackBarWithNoAction("ابتدا ویترین مورد نظر را انتخاب کنید",relativeLayout);
                }else {
                    System.out.println("department selected: "+item.toString());
                    System.out.println("department selected id: "+item.getId());
                    Intent intent = new Intent(context, DepartmentPageActivity.class);
                    intent.putExtra("cityId", GlobalVariables.selectedCity);
                    intent.putExtra("departmentId",item.getId());
                    GlobalVariables.selectedDepartment = String.valueOf(item.getId());
                    context.startActivity(intent);
                    activity.finish();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class OptionViewHoler extends RecyclerView.ViewHolder {

        private TextView subItemTitle;
        private LinearLayout touchLayout;

        public OptionViewHoler(View itemView) {
            super(itemView);
            subItemTitle = (TextView) itemView.findViewById(R.id.subitem_title_txt_id);
            touchLayout = (LinearLayout) itemView.findViewById(R.id.touch_layout_id);
        }
    }

}
