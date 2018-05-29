package com.example.a16022934.fyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {
    EditText etEmail;
    EditText etPassword;
    EditText etConfirmPassword;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPw);
        etConfirmPassword = findViewById(R.id.etConfirmPw);
        setTitle("SIGN UP");
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        Button btn = findViewById(R.id.btnCreate);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassword.getText().toString().equals(etConfirmPassword.getText().toString()) && checkIfEmpty()) {
                    (firebaseAuth.createUserWithEmailAndPassword(etEmail.getText().toString().trim(), etPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_LONG).show();
                                String uid = user.getUid();
                                DBHelper dbh = new DBHelper(SignUp.this);
                                dbh.retainUserLogIn(uid);
                                dbh.close();
                                String email = etEmail.getText().toString();
                                Intent i = new Intent(SignUp.this, SignUp2.class);
                                i.putExtra("uid", uid);
                                i.putExtra("email", email);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                }else{
                    if(TextUtils.isEmpty(etEmail.getText().toString())){
                        Toast.makeText(SignUp.this, "Email not Entered", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(SignUp.this,"Passwords are not the same", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

    public boolean checkIfEmpty(){
        if(TextUtils.isEmpty(etEmail.getText().toString()) &&
        TextUtils.isEmpty(etPassword.getText().toString()) &&
        TextUtils.isEmpty(etConfirmPassword.getText().toString())){
            return false;
        }else{
            return true;
        }
    }


}
