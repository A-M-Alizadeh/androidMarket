package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.requestform_adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.models.user.UserEducationModel;

import java.util.ArrayList;
import java.util.List;

public class DegreeRecyclerAdapter extends RecyclerView.Adapter<DegreeRecyclerAdapter.NewsViewHolder> {

    private Context context;
    private List<UserEducationModel> degrees;

    public DegreeRecyclerAdapter(Context context, List<UserEducationModel> degrees){
        this.context = context;
        this.degrees = degrees;
    }
    @Override
    public DegreeRecyclerAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.degree_list_item,parent,false);
        return new DegreeRecyclerAdapter.NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DegreeRecyclerAdapter.NewsViewHolder holder, final int position) {
        UserEducationModel degree=degrees.get(position);
        holder.degreeLevelTxt.setText(degree.getCertificateLevel_langKey());
        holder.degreeFieldTxt.setText(degree.getCertificateField());
        holder.touchLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"CLICKED",Toast.LENGTH_SHORT).show();
                //test
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);
                // set title
                alertDialogBuilder.setTitle("حذف مدرک تحصیلی");
                // set dialog message
                alertDialogBuilder
                        .setMessage("آیا میخواهید مدرک را حذف کنید؟")
                        .setCancelable(false)
                        .setPositiveButton("بله",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity
                                removeAt(position);
                            }
                        })
                        .setNegativeButton("خیر",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                // show it
                alertDialog.show();
                //test

                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return degrees.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        private TextView degreeLevelTxt;
        private TextView degreeFieldTxt;
        LinearLayout touchLayout;

        public NewsViewHolder(View itemView) {
            super(itemView);
            degreeLevelTxt=(TextView)itemView.findViewById(R.id.degree_list_level_txt_id);
            degreeFieldTxt=(TextView)itemView.findViewById(R.id.degree_list_field_txt_id);
            touchLayout=(LinearLayout) itemView.findViewById(R.id.degree_mother_item_layout_id);
        }
    }

    public void update(ArrayList<UserEducationModel> data) {
//        degrees.clear();
        degrees = data;
        notifyDataSetChanged();
    }

    public void removeAt(int position) {
        degrees.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, degrees.size());
    }

}