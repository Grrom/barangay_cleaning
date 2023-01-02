package com.example.barangay_cleaning.views;



import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.barangay_cleaning.MainActivity;
import com.example.barangay_cleaning.R;


public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);


        login.setOnClickListener(view->{
            String usernameText = username.getText().toString();
            String passwordText = password.getText().toString();

            if(usernameText.isEmpty()|| passwordText.isEmpty()){
                Toast.makeText(this, "Fill all the fields",Toast.LENGTH_SHORT).show();
                return;
            }

            if(!usernameText.equals("kyle")){
                username.setText("");
                Toast.makeText(this, "User not found.",Toast.LENGTH_SHORT).show();
                return;
            }

            if(!passwordText.equals("moraga")){
                password.setText("");
                Toast.makeText(this, "Wrong Password.",Toast.LENGTH_SHORT).show();
                return;
            }

            startActivity(new Intent(this, MainActivity.class));
        });
    }
}