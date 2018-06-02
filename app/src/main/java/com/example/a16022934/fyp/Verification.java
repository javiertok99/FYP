package com.example.a16022934.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class Verification extends AppCompatActivity {

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    EditText etCode;
    Button btnVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        etCode = findViewById(R.id.etCode);
        btnVerify = findViewById(R.id.btnVerify);
        Intent intent = getIntent();

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    }
}
