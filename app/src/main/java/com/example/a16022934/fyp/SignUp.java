package com.example.a16022934.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class SignUp extends AppCompatActivity {
    EditText etEmail;
    EditText etUser;
    EditText etPassword;
    EditText etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        etEmail = findViewById(R.id.etEmail);
        etUser = findViewById(R.id.etUser);
        etPassword = findViewById(R.id.etPw);
        etConfirmPassword = findViewById(R.id.etConfirmPw);
        setTitle("SIGN UP");

        Button btn = findViewById(R.id.btnCreate);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String Email = etEmail.getText().toString();
                String User = etUser.getText().toString();
                String Password = etPassword.getText().toString();
                String ConfirmPassword = etConfirmPassword.getText().toString();
                isEmpty();
                if(Password.equals(ConfirmPassword)){
                    Intent i = new Intent(SignUp.this, SignUp2.class);
                    i.putExtra("username",User);
                    i.putExtra("email",Email);
                    i.putExtra("password",Password);
                    startActivity(i);
                }
            }
        });
    }

    private void isEmpty(){
        String Email = etEmail.getText().toString();
        String User = etUser.getText().toString();
        String Password = etPassword.getText().toString();
        String ConfirmPassword = etConfirmPassword.getText().toString();

        if(!TextUtils.isEmpty(Email) && !TextUtils.isEmpty(User) && !TextUtils.isEmpty(Password) && !TextUtils.isEmpty(ConfirmPassword)){

        }else{
            Toast.makeText(SignUp.this,"Textfields is Empty", Toast.LENGTH_LONG).show();
        }
    }
}
