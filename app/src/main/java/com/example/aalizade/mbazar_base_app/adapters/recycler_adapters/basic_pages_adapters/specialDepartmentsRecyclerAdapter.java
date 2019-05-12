package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.basic_pages_adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.basic_parts.AnewerMasterPageActivity;
import com.example.aalizade.mbazar_base_app.activities.products_related.DepartmentPageActivity;
import com.example.aalizade.mbazar_base_app.activities.basic_parts.ProductsDepartmentActivity;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentLevel3FrontModel;

import java.util.List;

/**
 * Created by aalizade on 10/25/2017.
 */

public class specialDepartmentsRecyclerAdapter extends RecyclerView.Adapter<specialDepartmentsRecyclerAdapter.DepartmentSpeciialPlansViewHolder> {

    private Context context;
    private List<DepartmentLevel3FrontModel> options;
    String selectedCityId;
    LinearLayout progressWrapper;

    public specialDepartmentsRecyclerAdapter(Context context, List<DepartmentLevel3FrontModel> options, String selectedCityId, LinearLayout progressWrapper) {
        this.context = context;
        this.options = options;
        this.selectedCityId = selectedCityId;
        this.progressWrapper = progressWrapper;
    }

    @Override
    public DepartmentSpeciialPlansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.master_page_plan_item_layout, parent, false); //iconic_show_option_item
        return new DepartmentSpeciialPlansViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DepartmentSpeciialPlansViewHolder holder, final int position) {
        final DepartmentLevel3FrontModel singleItem = options.get(position);
        Drawable pic = context.getResources().getDrawable(R.drawable.samsung);

        holder.dep_img.setImageDrawable(pic);
        holder.dep_title.setText(singleItem.getTitle());
        holder.dep_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (GlobalVariables.selectedCity == null && singleItem.getId() == -2000) {
                    Intent intent = new Intent(context, ProductsDepartmentActivity.class);
                    context.startActivity(intent);
                } else if (GlobalVariables.selectedCity == null) {
//                    MySnackBar.snackBarWithNoAction("ابتدا ویترین مورد نظر را انتخاب کنید",progressWrapper);
                    AnewerMasterPageActivity.ShowCitySelecteionDialog();
                } else {
                    //check if it's link todo
                    Intent intent = new Intent(context, DepartmentPageActivity.class);
//                intent.putExtra("cityId",selectedCityId);
                    intent.putExtra("cityId", GlobalVariables.selectedCity);//global
                    intent.putExtra("departmentId", String.valueOf(singleItem.getId()));
                    GlobalVariables.selectedDepartment = String.valueOf(singleItem.getId());//global
                    context.startActivity(intent);

                }

            }
        });
//        holder.iconImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ProductMainPageActivity.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return options.size();
    }

    public class DepartmentSpeciialPlansViewHolder extends RecyclerView.ViewHolder {
        private ImageView dep_img;
        private TextView dep_title;

        public DepartmentSpeciialPlansViewHolder(View itemView) {
            super(itemView);
            dep_img=(ImageView)itemView.findViewById(R.id.plan_img_id);
            dep_title = (TextView) itemView.findViewById(R.id.plan_name_txt_id);
        }
    }
}
