package com.example.paymentapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class OpenActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);

        mAuth = FirebaseAuth.getInstance();

        String email = "arnabmondal203@gmail.com";
        String password = "123456";

        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(OpenActivity.this, task -> {
            startActivity(new Intent(OpenActivity.this, MainActivity.class));
            finish();
        }).addOnFailureListener(fail ->{
            startActivity(new Intent(OpenActivity.this, ErrorActivity.class));
            finish();
        });
    }
}