package com.example.ppt_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Admini extends AppCompatActivity {

    EditText Email, password, EmployeeId;
    CheckBox rememberMe;
    public FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_admini);

        Email = findViewById(R.id.Email);
        EmployeeId = findViewById(R.id.EmployeeId);
        password = findViewById(R.id.Password);
        rememberMe = findViewById(R.id.rememberMe);
    }


    String email_admin = null, password_admin = null;
    //This is method for doing operation of check login
    public void ClickLogin(View view) {

        if (EmployeeId.getText().toString().trim().isEmpty()) {

            Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",
                    Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
            snackbar.show();
            EmployeeId.setError("Please Fill out the Email");
        } else {
            //Here you can write the codes for checking username

        }

        if (Email.getText().toString().trim().isEmpty()) {

            Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",
                    Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
            snackbar.show();
            Email.setError("Please Fill out the Email");
        } else {
            //Here you can write the codes for checking username
            email_admin = Email.getText().toString();
        }
        if (password.getText().toString().trim().isEmpty()) {
            Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",
                    Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
            snackbar.show();
            password.setError("Password should not be empty");
        } else {
            //Here you can write the codes for checking password
            password_admin = password.getText().toString();
        }

        if(email_admin == null || password_admin == null){
            Toast.makeText(Admini.this , "Please fill all the details", Toast.LENGTH_SHORT).show();
        }
        else{
            if(Objects.equals(email_admin, "admin@lnmiit.ac.in") && Objects.equals(password_admin, "admin123")){
                Intent intent = new Intent(Admini.this, Admin_Home.class);
                startActivity(intent);
            }
        }

//        if (rememberMe.isChecked()) {
//            //Here you can write the codes if box is checked
//
//
//            if(Objects.equals(email_admin, "admin@lnmiit.ac.in") && Objects.equals(password_admin, "admin123")){
//                Intent intent = new Intent(Admini.this, Admin_home.class);
//                startActivity(intent);
//            }
//        } else {
//            //Here you can write the codes if box is not checked
//            if(Objects.equals(email_admin, "admin@lnmiit.ac.in") && Objects.equals(password_admin, "admin123")){
//                Intent intent = new Intent(Admini.this, Admin_home.class);
//                startActivity(intent);
//            login2(email_admin, password_admin);
//            }
//        }
    }

//    public void login2(String email1, String pass1){
//        auth.signInWithEmailAndPassword(email1, pass1).addOnSuccessListener(Admini.this, new OnSuccessListener<AuthResult>() {
//            @Override
//            public void onSuccess(AuthResult authResult) {
////                if (Objects.equals(email1, "admin@lnmiit.ac.in") && Objects.equals(pass1, "admin123")) {
//                    Intent intent6 = new Intent(Admini.this, Admin_home.class);
//                    startActivity(intent6);
////                }
//            }
//        });
//    }
}