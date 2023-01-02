package com.example.barangay_cleaning.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.models.Resident;

import java.util.List;

public class CustomResidentAdapter extends ArrayAdapter<Resident> {

    LayoutInflater flater;

    public CustomResidentAdapter(Activity context, int resouceId,  List<Resident> list){

        super(context,resouceId, list);
//        flater = context.getLayoutInflater();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return rowview(convertView,position);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return rowview(convertView,position);
    }

    private View rowview(View convertView , int position){

        Resident rowItem = getItem(position);

        viewHolder holder ;
        View rowview = convertView;
        if (rowview==null) {

            holder = new viewHolder();
            flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowview = flater.inflate(R.layout.resident_item_spinner, null, false);

            holder.name = (TextView) rowview.findViewById(R.id.resident_name);
            holder.image = (ImageView) rowview.findViewById(R.id.resident_image);
            holder.address = (TextView) rowview.findViewById(R.id.resident_address);
            rowview.setTag(holder);

        }else{
            holder = (viewHolder) rowview.getTag();
        }
        holder.image.setImageResource(rowItem.getImage());
        holder.name.setText(rowItem.getFullName());
        holder.address.setText(rowItem.getAddress());

        return rowview;
    }

    private class viewHolder{
        TextView name;
        ImageView image;
        TextView address;
    }
}