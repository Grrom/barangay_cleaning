package com.example.barangay_cleaning.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.models.Resident;

public class ResidentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);


        TextView name = findViewById(R.id.resident_name);
        TextView age = findViewById(R.id.resident_age);
        TextView address = findViewById(R.id.resident_address);
        ImageView image = findViewById(R.id.resident_image);

        Resident resident = (Resident) getIntent().getSerializableExtra("resident");
        getSupportActionBar().setTitle(resident.getFullName());

        name.setText(resident.getFullName());
        age.setText(String.valueOf(resident.getAge()));
        address.setText(resident.getAddress());
        image.setImageResource(resident.getImage());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
