package com.example.loginapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {

    private TextView txtUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ParseUser user =new ParseUser();


        txtUser=findViewById(R.id.txtUser);
        txtUser.setText("Hi "+user.getUsername());



    }
}