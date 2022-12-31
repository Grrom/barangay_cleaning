package com.example.barangay_cleaning.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.adapters.ViolationsAdapter;
import com.example.barangay_cleaning.models.Report;
import com.example.barangay_cleaning.models.Resident;

import java.util.ArrayList;

public class ResidentActivity extends AppCompatActivity {

    ArrayList<Report> violations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resident);

        RecyclerView recyclerView = findViewById(R.id.resident_violations_recyclerview);
        recyclerView.setNestedScrollingEnabled(false);


        setupViolations();

        ViolationsAdapter adapter  = new ViolationsAdapter(this, violations);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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

    private void setupViolations(){
        String name ="Littering";
        String status= "unresolved";
        int image= R.drawable.temp_profile;

        for (int i = 0; i < 10; i++){
            violations.add(new Report(image, name,status , new Resident(image, "Gab","Sins", 20, "Purok 3")));
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
