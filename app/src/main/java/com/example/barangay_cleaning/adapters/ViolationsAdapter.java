package com.example.barangay_cleaning.adapters;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.models.Report;

import java.util.ArrayList;

public class ViolationsAdapter  extends RecyclerView.Adapter<ViolationsAdapter.MyViewHolder>{
    Context context;
    ArrayList<Report> reports;

    public ViolationsAdapter(Context context, ArrayList<Report> reports){
        this.context = context;
        this.reports = reports;
    }

    @NonNull
    @Override
    public ViolationsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViolationsAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.violation_item_row, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViolationsAdapter.MyViewHolder holder, int position) {
        holder.name.setText(reports.get(position).getName());
        holder.status.setText(reports.get(position).getStatus());
        holder.image.setImageURI(reports.get(position).getImage());

        if(reports.get(position).getStatus().equalsIgnoreCase("unresolved")){
            holder.status.setTextColor(context.getResources().getColor(R.color.red));
            holder.statusIndicator.setCardBackgroundColor(context.getResources().getColor(R.color.red));
        }else{
            holder.status.setTextColor(context.getResources().getColor(R.color.green));
            holder.statusIndicator.setCardBackgroundColor(context.getResources().getColor(R.color.green));
        }

        holder.offenderDetails.setVisibility(View.GONE);

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
        return reports.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView status;
        CardView statusIndicator;
        LinearLayout offenderDetails;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.violation_image_proof);
            name = itemView.findViewById(R.id.violation_name);
            status = itemView.findViewById(R.id.violation_status);
            statusIndicator= itemView.findViewById(R.id.status_indicator);
            offenderDetails = itemView.findViewById(R.id.offender_details);
        }
    }
}
