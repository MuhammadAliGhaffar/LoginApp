package com.example.loginapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseInstallation;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private String n1,n2;
    TextView txtRegister1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseInstallation.getCurrentInstallation().saveInBackground();

        txtRegister1=findViewById(R.id.txtRegister1);

        n1=getColoredSpanned("You are not a member?","#253A4B");
        n2=getColoredSpanned("Register.","#F23B5F");

        txtRegister1.setText(Html.fromHtml(n1+" "+n2));


        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takeMetoNextActivity(MainActivity.this,LoginActivity.class);


            }
        });





    }
    public String getColoredSpanned(String text, String color) {
        String input = "<font color=" + color + ">" + text + "</font>";
        return input;
    }
    private void takeMetoNextActivity(Context contextname,Class classname){
        Intent intent =new Intent(contextname,classname);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




}