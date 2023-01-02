package com.example.barangay_cleaning.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barangay_cleaning.R;
import com.example.barangay_cleaning.adapters.CustomResidentAdapter;
import com.example.barangay_cleaning.helpers.DBHelper;
import com.example.barangay_cleaning.models.Constants;
import com.example.barangay_cleaning.models.Resident;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class ReportActivity extends AppCompatActivity {


    ImageView imagePicker ;
    String imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getSupportActionBar().setTitle("File a report");

        Spinner residentPicker = findViewById(R.id.pick_resident);
        Button submit = findViewById(R.id.submit_report);
        EditText violationName = findViewById(R.id.violation_name);

        imagePicker = findViewById(R.id.pick_image);

        imagePicker.setOnClickListener(view->{
            ImagePicker.with(this)
                    .crop()
                    .compress(100)
                    .start();
        });


        CustomResidentAdapter adapter =new  CustomResidentAdapter(this,R.layout.resident_item_spinner, Constants.getResidents());

        residentPicker.setAdapter(adapter);

        submit.setOnClickListener(view->{
            if(imageUri == null){
                Toast.makeText(this, "Insert Image", Toast.LENGTH_SHORT).show();
                return;
            }

            if(violationName.getText().toString().isEmpty()){
                Toast.makeText(this, "Insert Violation Name", Toast.LENGTH_SHORT).show();
                return;
            }

            Resident selected  =(Resident)residentPicker.getSelectedItem();

            DBHelper.SerializedReport newReport  = new DBHelper.SerializedReport(selected.getId(), imageUri, violationName.getText().toString(), "unresolved");

            DBHelper.insertReport(getApplicationContext() ,newReport);
            onBackPressed();
            Toast.makeText(this, "Your report has been submitted.",Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            imageUri = data.getDataString();
            imagePicker.setImageURI(android.net.Uri.parse(imageUri));
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
