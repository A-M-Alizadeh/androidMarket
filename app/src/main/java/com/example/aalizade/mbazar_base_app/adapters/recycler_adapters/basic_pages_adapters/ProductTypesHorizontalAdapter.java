package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.basic_pages_adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.activities.products_related.ProductsMainListsActivity;
import com.example.aalizade.mbazar_base_app.utility.GlobalVariables;
import com.example.aalizade.mbazar_base_app.network.models.department.DepartmentSidebarProductCategoryFrontModel;

import java.util.List;

/**
 * Created by aalizade on 10/25/2017.
 */

public class ProductTypesHorizontalAdapter extends RecyclerView.Adapter<ProductTypesHorizontalAdapter.typeViewHolder> {

    private Context context;
    private List<DepartmentSidebarProductCategoryFrontModel> types;
    private String cityId;
    private String departmentId;

    public ProductTypesHorizontalAdapter(Context context, List<DepartmentSidebarProductCategoryFrontModel> plans,String cityId,String departmentId){
        this.context = context;
        this.types = plans;
        this.cityId = cityId;
        this.departmentId = departmentId;
    }
    @Override
    public typeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_types_horizontal_item_designed,parent,false);
        return new typeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(typeViewHolder holder, final int position) {
        final DepartmentSidebarProductCategoryFrontModel singleItem = types.get(position);
        holder.title.setText(singleItem.getTitle());
        holder.touchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsMainListsActivity.class);
//                intent.putExtra("cityId",cityId);
//                intent.putExtra("departmentId",departmentId);
//                intent.putExtra("typeId",String.valueOf(singleItem.getId()));

                intent.putExtra("cityId", GlobalVariables.selectedCity);//global
                intent.putExtra("departmentId",GlobalVariables.selectedCity);//global
                intent.putExtra("typeId",String.valueOf(singleItem.getId()));//global
                GlobalVariables.selectedType = String.valueOf(singleItem.getId());//global


                context.startActivity(intent);
            }
        });
//        switch (position){
//            case 0:holder.cardView.setBackgroundResource(R.color.after_buy_btn_color);break;
//            case 1:holder.cardView.setBackgroundResource(R.color.dark_gradient_color);break;
//            case 2:holder.cardView.setBackgroundResource(R.color.dark_yellow);break;
//            case 3:holder.cardView.setBackgroundResource(R.color.dark_blue);break;
//            default:{
//                Random r = new Random();
//                int red=r.nextInt(255 - 0 + 1)+0;
//                int green=r.nextInt(255 - 0 + 1)+0;
//                int blue=r.nextInt(255 - 0 + 1)+0;
//                GradientDrawable draw = new GradientDrawable();
//                draw.setColor(Color.rgb(red,green,blue));
//                holder.cardView.setBackground(draw);
//                break;
//            }
//        }
    }

    @Override
    public int getItemCount() {
        return types.size();
    }

    public class typeViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private CardView cardView;
        private RelativeLayout touchLayout;

        public typeViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.type_name_txt_id);
            cardView=(CardView) itemView.findViewById(R.id.card_id);
            touchLayout=(RelativeLayout) itemView.findViewById(R.id.touch_layout_id);
        }
    }
}
