package com.mlabs.bbm.firstandroidapp;

import android.text.method.PasswordTransformationMethod;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginScreen extends AppCompatActivity {

    DBAdapter DBAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        DBAdapter = new DBAdapter(this);
        DBAdapter = DBAdapter.open();


        final EditText email = (EditText) findViewById(R.id.txtFname);
        final EditText password = (EditText) findViewById(R.id.lblPass);
        Button login = (Button) findViewById(R.id.btnLogin);
        final Button btnShow = (Button) findViewById(R.id.btnShow);

        final TextView SignUp = (TextView) findViewById(R.id.lblSignUp);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!validateEmail(email.getText().toString())&&!isValidUser(email.getText().toString())) {
                    email.setError("Invalid Email/Username");
                    email.requestFocus();
                } else if (!validatePassword(password.getText().toString())) {
                    password.setError("Invalid Password");
                    password.requestFocus();
                } else {
                    String User = email.getText().toString();
                    String Pass = password.getText().toString();
                    String passdb = DBAdapter.getSingleEntry(User);

                    if (Pass.equals(passdb)){
                        Intent intent = new Intent(LoginScreen.this,MainActivity.class );
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginScreen.this, "Username/Password is Incorrect.", Toast.LENGTH_SHORT).show();
                    }

                }

            }

            //Return true if password is valid and false if password is invalid.
            private boolean validatePassword(String password) {
                if (password != null && password.length() >= 8) {
                    return true;
                } else {
                    return false;
                }
            }

            //Return true if password is valid and false if password is invalid.
            private boolean validateEmail(String email) {
                String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                Pattern pattern = Pattern.compile(emailPattern);
                Matcher matcher = pattern.matcher(email);

                return matcher.matches();
            }

            public boolean isValidUser(String user) {
                String pat = "^[a-z0-9_-]{3,15}$";
                Pattern pattern = Pattern.compile(pat);
                Matcher matcher = pattern.matcher(user);
                return matcher.matches();
            }


        });


        btnShow.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int cursor = password.getSelectionStart();
                boolean res = false;
                int x = motionEvent.getAction();

                switch(x){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("LoginScreen.java","ACTION_DOWN");
                        password.setTransformationMethod(null);
                        password.setSelection(cursor);
                        res = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("LoginScreen.java","ACTION_UP");
                        password.setTransformationMethod(new PasswordTransformationMethod());
                        password.setSelection(cursor);
                        break;
                }
                return res;
            }

        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginScreen.this,RegistrationScreen.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected  void onPause(){
        super.onPause();
        finish();
    }

}
