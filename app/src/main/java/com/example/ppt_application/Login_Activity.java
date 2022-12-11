package com.example.ppt_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login_Activity extends AppCompatActivity {

    EditText Email, password, reg_username, reg_password, reg_email, reg_password_confirm;
    Button reg_register, signup;
    TextInputLayout txtInLayoutRegPassword, txtInLayoutRegPassword_confirm;
    CheckBox rememberMe;
    TextView textView;

    public FirebaseAuth auth;
    public String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_login);


        auth = FirebaseAuth.getInstance();


        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Login_Activity.this, MainActivity.class));
            finish();
        }


        Email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        rememberMe = findViewById(R.id.rememberMe);
        signup = findViewById(R.id.button2);
        textView = findViewById(R.id.Ad);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSignUp();
            }
        });
    }


    // for admini login
    public  void admin(View view){
        Intent intent = new Intent(this, Admini.class);
        startActivity(intent);
    }


    //This is method for doing operation of check login
    String email_login, pass_login;
    public void ClickLogin(View view) {

        if (Email.getText().toString().trim().isEmpty()) {

            Snackbar snackbar = Snackbar.make(view, "Please fill out these fields",
                    Snackbar.LENGTH_LONG);
            View snackbarView = snackbar.getView();
            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
            snackbar.show();
            Email.setError("Please Fill out the Email");
        } else {
            //Here you can write the codes for checking username
            email_login = Email.getText().toString();
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
            pass_login = password.getText().toString();
        }

        if (rememberMe.isChecked()) {
            //Here you can write the codes if box is checked
        } else {
            //Here you can write the codes if box is not checked
        }

        if(email_login == null || pass_login == null){
            Toast.makeText(Login_Activity.this , "Please fill all the details", Toast.LENGTH_SHORT).show();
        }
        else{
            login2(email_login, pass_login);
        }

    }

    private void login2(String email, String pass){
        auth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(Login_Activity.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                            startActivity(new Intent(Login_Activity.this, MainActivity.class));
                            finish();
            }
        });
    }

    //The method for opening the registration page and another processes or checks for registering
    String userName = null, pass = null,confirm_pass = null ,emai = null;
    private void ClickSignUp() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.register, null);
        dialog.setView(dialogView);

        // buttons and edit texts
        reg_username = dialogView.findViewById(R.id.reg_username);
        reg_password = dialogView.findViewById(R.id.reg_password1);
        reg_email = dialogView.findViewById(R.id.reg_email);
        reg_password_confirm = dialogView.findViewById(R.id.reg_password2);
        reg_register = dialogView.findViewById(R.id.reg_register);
        txtInLayoutRegPassword = dialogView.findViewById(R.id.passw);
        txtInLayoutRegPassword_confirm = dialogView.findViewById(R.id.passw_confirm);

        // strings to get the text

        reg_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (reg_username.getText().toString().trim().isEmpty()) {

                    reg_username.setError("Please fill out this field");
                } else {
                    //Here you can write the codes for checking username
                    userName = String.valueOf(reg_username.getText());
                }

                if (reg_email.getText().toString().trim().isEmpty()) {

                    reg_email.setError("Please fill out this field");
                } else {
                    emai = reg_email.getText().toString();
                    //Here you can write the codes for checking email
                }


                if (reg_password.getText().toString().trim().isEmpty()) {
                    txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(false);
                    reg_password.setError("Please fill out this field");
                }
                else if(reg_password.getText().toString().length() < 10){
                    txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(false);
                    reg_password.setError("Password must be Atleast 10 letters Long!!");
                }
                else {
                    txtInLayoutRegPassword.setPasswordVisibilityToggleEnabled(true);
                    pass = reg_password.getText().toString();
                    //Here you can write the codes for checking password
                }

                if (reg_password_confirm.getText().toString().trim().isEmpty()) {
                    txtInLayoutRegPassword_confirm.setPasswordVisibilityToggleEnabled(false);
                    reg_password_confirm.setError("Please fill out this field");
                }
                else if(reg_password_confirm.getText().toString().length() < 10){
                    txtInLayoutRegPassword_confirm.setPasswordVisibilityToggleEnabled(false);
                    reg_password_confirm.setError("Password must be Atleast 10 letters Long!!");
                }
                else {
                    txtInLayoutRegPassword_confirm.setPasswordVisibilityToggleEnabled(true);
                    confirm_pass = reg_password_confirm.getText().toString();
                    //Here you can write the codes for checking password
                }

                if(userName == null || pass == null || emai == null || !Objects.equals(confirm_pass, pass)){
                    Toast.makeText(Login_Activity.this , "Please fill all the details Properly", Toast.LENGTH_SHORT).show();
                }
                else{
                    regis(userName, emai, confirm_pass);
                }
            }
        });
        dialog.show();
    }

    public void regis(String user, String emails, String pas){
        auth.createUserWithEmailAndPassword(emails, pas).addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Login_Activity.this , "Successfully Registered!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Login_Activity.this , "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}