package com.example.barangay_cleaning.views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.models.Resident;

public class ResidentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);


        TextView textView = findViewById(R.id.textView);

        Resident resident = (Resident) getIntent().getSerializableExtra("resident");
        getSupportActionBar().setTitle(resident.getName());
        textView.setText(resident.getName());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
