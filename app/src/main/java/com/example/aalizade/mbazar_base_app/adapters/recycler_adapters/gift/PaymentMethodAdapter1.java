package com.example.aalizade.mbazar_base_app.adapters.recycler_adapters.gift;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aalizade.mbazar_base_app.R;
import com.example.aalizade.mbazar_base_app.network.models.general.ComboResponseModel1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sajad on 2/26/18.
 */

public class PaymentMethodAdapter1 extends ArrayAdapter<ComboResponseModel1>{
    private final LayoutInflater mInflater;
    private List<ComboResponseModel1> items = new ArrayList<>();
    private Context activity;
    private final int mResource;

    public PaymentMethodAdapter1(Context activity, ArrayList<ComboResponseModel1> items, int mResource) {
        super(activity, 0, items);
        mInflater = LayoutInflater.from(activity);
        this.items = items;
        this.activity = activity;
        this.mResource = mResource;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public ComboResponseModel1 getItem(int position) {
        return items.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(activity).inflate(R.layout.simple_textview,parent,false);

        }
        TextView textView = (TextView) view.findViewById(R.id.sample_textview);
        textView.setTextColor(Color.BLACK);
        textView.setText(items.get(position).getName());
        return view;
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        final View view = mInflater.inflate(mResource, parent, false);

        TextView satPosition = (TextView) view.findViewById(R.id.sample_textview);
        ComboResponseModel1 warehouse = items.get(position);
        satPosition.setText(warehouse.getName());
        satPosition.setTextColor(Color.BLACK);
        return view;
    }
}
