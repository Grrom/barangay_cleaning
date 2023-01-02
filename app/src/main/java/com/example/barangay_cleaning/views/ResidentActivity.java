package com.example.barangay_cleaning.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.adapters.ViolationsAdapter;
import com.example.barangay_cleaning.helpers.DBHelper;
import com.example.barangay_cleaning.models.Constants;
import com.example.barangay_cleaning.models.Report;
import com.example.barangay_cleaning.models.Resident;

import java.util.ArrayList;

public class ResidentActivity extends AppCompatActivity {

    ArrayList<Report> violations = new ArrayList<>();

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

        RecyclerView recyclerView = findViewById(R.id.resident_violations_recyclerview);
        recyclerView.setNestedScrollingEnabled(false);

        setupViolations(resident);

        ViolationsAdapter adapter  = new ViolationsAdapter(this, violations);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupViolations(Resident resident){
        violations.addAll(DBHelper.getReportByResident(getApplicationContext(),resident.getId()));
//        violations.addAll(Constants.getReports(getApplicationContext()));
//        violations.removeIf(s -> s.getOffender().getId() != resident.getId());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
