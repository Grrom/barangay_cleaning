package com.example.barangay_cleaning.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.adapters.CustomResidentAdapter;
import com.example.barangay_cleaning.models.Constants;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class ReportActivity extends AppCompatActivity {


    ImageView imagePicker ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getSupportActionBar().setTitle("File a report");

        imagePicker = findViewById(R.id.pick_image);

        imagePicker.setOnClickListener(view->{
            ImagePicker.with(this)
                    .crop()
                    .compress(100)
                    .start();
        });

        Spinner residentPicker = findViewById(R.id.pick_resident);

        CustomResidentAdapter adapter =new  CustomResidentAdapter(this,R.layout.resident_item_spinner, Constants.getResidents());

        residentPicker.setAdapter(adapter);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            imagePicker.setImageURI(android.net.Uri.parse(data.getDataString()));
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
