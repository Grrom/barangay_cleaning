package com.example.barangay_cleaning.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barangay_cleaning.R;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getSupportActionBar().setTitle("File a report");
    }
}
