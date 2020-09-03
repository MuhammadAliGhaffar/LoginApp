package com.example.loginapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity{

    private TextView txt_register_login;
    private EditText edt_email_login,edt_password_login;
    private String n1,n2;

    private MainActivity obj=new MainActivity();
    private SignUpActivity obj2=new SignUpActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txt_register_login=findViewById(R.id.txt_register_login);
        edt_email_login=findViewById(R.id.edt_email_login);
        edt_password_login=findViewById(R.id.edt_password_login);

        n1=obj.getColoredSpanned("You are not a member?","#253A4B");
        n2=obj.getColoredSpanned("Register.","#F23B5F");
        txt_register_login.setText(Html.fromHtml(n1+" "+n2));

        txt_register_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                takeMetoNextActivity(LoginActivity.this,SignUpActivity.class);

            }
        });

    }
    private void takeMetoNextActivity(Context contextname, Class classname){
        Intent intent =new Intent(contextname,classname);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void loginIsPressed(View btnView){

        ParseUser.logInInBackground(edt_email_login.getText().toString(), edt_password_login.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseuser, ParseException e) {
                if(parseuser != null) {
                    if(parseuser.getBoolean("emailVerified")){
                        alertDisplayer("Login Sucessful","Welcome" + edt_email_login.getText().toString() + "!" ,false);
                    }else {
                        ParseUser.logOut();
                        alertDisplayer("Login Fail","Please verify your email first",true);
                    }
                }else{
                    ParseUser.logOut();
                    alertDisplayer("Login Fail",e.getMessage()+"Please re-try",true);

                }
            }
        });




    }
    private void alertDisplayer(String title,String message,final boolean error){
        AlertDialog.Builder builder =new AlertDialog.Builder(LoginActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                        if(!error) {
                            Intent intent =new Intent(LoginActivity.this,HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                    }
                });
        AlertDialog ok = builder.create();
        ok.show();

    }


}