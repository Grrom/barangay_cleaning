package com.example.barangay_cleaning.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.models.Area;

import java.util.ArrayList;

public class AreasAdapter  extends RecyclerView.Adapter<AreasAdapter.MyViewHolder>{
    Context context;
    ArrayList<Area> areas;

    public AreasAdapter(Context context, ArrayList<Area> areas){
        this.context = context;
        this.areas = areas;
    }

    @NonNull
    @Override
    public AreasAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AreasAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.area_item_row, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull AreasAdapter.MyViewHolder holder, int position) {
        holder.name.setText(areas.get(position).getName());
        holder.status.setText(areas.get(position).getStatus());
        holder.image.setImageResource(areas.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView status;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.area_image);
            name = itemView.findViewById(R.id.area_name);
            status = itemView.findViewById(R.id.area_status);
        }
    }
}
