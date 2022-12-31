package com.example.barangay_cleaning.adapters;

import static android.app.PendingIntent.getActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.models.Report;
import com.example.barangay_cleaning.views.ResidentActivity;

import java.util.ArrayList;

public class ReportsAdapter  extends RecyclerView.Adapter<ReportsAdapter.MyViewHolder>{
    Context context;
    ArrayList<Report> reports;

    public ReportsAdapter(Context context, ArrayList<Report> reports){
        this.context = context;
        this.reports = reports;
    }

    @NonNull
    @Override
    public ReportsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReportsAdapter.MyViewHolder(LayoutInflater.from(context).inflate(R.layout.violation_item_row, parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsAdapter.MyViewHolder holder, int position) {
        holder.name.setText(reports.get(position).getName());
        holder.offenderName.setText(reports.get(position).getOffender().getFullName());
        holder.status.setText(reports.get(position).getStatus());
        holder.image.setImageResource(reports.get(position).getImage());

        if(reports.get(position).getStatus().equals("unresolved")){
            holder.status.setTextColor(context.getResources().getColor(R.color.red));
            holder.statusIndicator.setCardBackgroundColor(context.getResources().getColor(R.color.red));
        }else{
            holder.status.setTextColor(context.getResources().getColor(R.color.green));
            holder.statusIndicator.setCardBackgroundColor(context.getResources().getColor(R.color.green));
        }
        holder.viewButton.setOnClickListener(view->{
            Intent intent = new Intent(context, ResidentActivity.class);
            intent.putExtra("resident", reports.get(position).getOffender());
            context.startActivity(intent);
        });

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
        TextView offenderName;
        TextView status;
        Button viewButton;
        CardView statusIndicator;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.violation_image_proof);
            name = itemView.findViewById(R.id.violation_name);
            offenderName = itemView.findViewById(R.id.offender_name);
            status = itemView.findViewById(R.id.violation_status);
            viewButton = itemView.findViewById(R.id.view_button);
            statusIndicator= itemView.findViewById(R.id.status_indicator);
        }
    }
}
