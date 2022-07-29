package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView banner;
    private EditText editTextEmail, editTextPassword;
    private Button registerUser,verifyMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        mAuth = FirebaseAuth.getInstance();
        verifyMobile = findViewById(R.id.verifyMobile);
        verifyMobile.setOnClickListener(this);

        banner = findViewById(R.id.banner);
        banner.setOnClickListener(this);
        registerUser = findViewById(R.id.register1);
        registerUser.setOnClickListener(this);

        editTextEmail = findViewById(R.id.email1);
        editTextPassword = findViewById(R.id.password1);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.register1:
                registerUser();
                break;

            case R.id.b1:
                startActivity(new Intent(this,otp.class));
                break;
            case R.id.verifyMobile:
                startActivity(new Intent(this,phoneNumber.class));
        }
    }

    private void registerUser () {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter valid email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("password is required");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Minimum password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
//                            com.example.login.User user = new com.example.login.User(fullName, age, email);
//
//                            FirebaseDatabase.getInstance().getReference("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                        @Override
//                                        public void onComplete(@NonNull Task<Void> task) {
//                                            if (task.isSuccessful()) {
//                                                Toast.makeText(RegisterActivity.this, "User has been registered successfully!!!", Toast.LENGTH_SHORT).show();
//                                            } else {
//                                                Toast.makeText(RegisterActivity.this, "Failed to Register! Try Again", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }
//                                    });
                            Toast.makeText(RegisterActivity.this, "User Registered Successfully!!!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Failed to Register!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


//    private void initiateotp ()
//    {
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                phonenumber,        // Phone number to verify
//                60,                 // Timeout duration
//                TimeUnit.SECONDS,   // Unit of timeout
//                this,               // Activity (for callback binding)
//                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                    @Override
//                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//                        otpid = s;
//                    }
//
//                    @Override
//                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//                        signInWithPhoneAuthCredential(phoneAuthCredential);
//                    }
//
//                    @Override
//                    public void onVerificationFailed(FirebaseException e) {
//                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });        // OnVerificationStateChangedCallbacks
//
//    }
//
//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Toast.makeText(RegisterActivity.this, "Phone number Verified!!!", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Signin Code Error", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });

}
