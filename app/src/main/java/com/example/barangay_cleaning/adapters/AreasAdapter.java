package com.example.barangay_cleaning.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

        if(areas.get(position).getStatus().equals("unclean")){
            holder.status.setTextColor(context.getResources().getColor(R.color.red));
            holder.statusIndicator.setCardBackgroundColor(context.getResources().getColor(R.color.red));
        }else{
            holder.status.setTextColor(context.getResources().getColor(R.color.green));
            holder.statusIndicator.setCardBackgroundColor(context.getResources().getColor(R.color.green));
        }

        holder.image.setOnClickListener(view -> {
            Dialog settingsDialog = new Dialog(context);

            LayoutInflater inflater = LayoutInflater.from(context);
            View newView = (View) inflater.inflate(R.layout.image_layout, null);

            settingsDialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            settingsDialog.setContentView(newView);

            ImageView iv= (ImageView) newView.findViewById(R.id.image_popup);
            Bitmap bm=((BitmapDrawable)holder.image.getDrawable()).getBitmap();
            iv.setImageBitmap(bm);

            settingsDialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView status;
        CardView statusIndicator;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.area_image);
            name = itemView.findViewById(R.id.area_name);
            status = itemView.findViewById(R.id.area_status);
            statusIndicator = itemView.findViewById(R.id.status_indicator);
        }
    }
}
