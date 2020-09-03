package com.example.loginapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private TextView txt_login;
    private EditText edt_username_SignUp,edt_email_SignUp,edt_password_SignUp;

    private String n1,n2;

    private MainActivity obj1=new MainActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        txt_login=findViewById(R.id.txt_login);
        edt_username_SignUp=findViewById(R.id.edt_username_SignUp);
        edt_email_SignUp=findViewById(R.id.edt_email_SignUp);
        edt_password_SignUp=findViewById(R.id.edt_password_SignUp);

        n1=obj1.getColoredSpanned("You already have account?","#253A4B");
        n2=obj1.getColoredSpanned("Login.","#F23B5F");
        txt_login.setText(Html.fromHtml(n1+" "+n2));


    }
    public void signupIsPressed(View BtnView){
        Toast.makeText(this,"Sign Up is Tapped",Toast.LENGTH_SHORT).show();

        try{
            //Sign up with parse
            ParseUser user =new ParseUser();

            user.setUsername(edt_username_SignUp.getText().toString());
            user.setEmail(edt_email_SignUp.getText().toString());
            user.setPassword(edt_password_SignUp.getText().toString());

            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null){
                        ParseUser.logOut();
                        alertDisplayer("Account Created Successfully","Please verify your email before Login",false);
                    }
                    else{
                        alertDisplayer("Error Account Creation failed","Account could not be created"+":"+e.getMessage(),true);
                    }
                }
            });

        } catch (Exception e){
            e.printStackTrace();
        }



    }

    private void alertDisplayer(String title,String message,final boolean error){
        AlertDialog.Builder builder =new AlertDialog.Builder(SignUpActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        dialog.cancel();
                        if(!error) {
                            Intent intent =new Intent(SignUpActivity.this,LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                    }
                });
        AlertDialog ok = builder.create();
        ok.show();

    }
    public EditText getEdt_username_SignUp() {
        return edt_username_SignUp;
    }


}