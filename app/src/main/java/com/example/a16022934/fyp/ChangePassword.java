package com.example.a16022934.fyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ChangePassword extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    EditText etCurrPW, etNewPW, etConfirmNewPW;
    Button btnChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        etCurrPW = findViewById(R.id.etCurrPw);
        etNewPW = findViewById(R.id.etNewPassword);
        etConfirmNewPW = findViewById(R.id.etConfirmPassword);
        btnChange = findViewById(R.id.btnChangePw);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChangePassword.this, "Please wait...", Toast.LENGTH_SHORT).show();
                if (!TextUtils.isEmpty(etCurrPW.getText().toString()) && !TextUtils.isEmpty(etConfirmNewPW.getText().toString()) && !TextUtils.isEmpty(etNewPW.getText().toString())) {
                    if (etNewPW.getText().toString().equals(etConfirmNewPW.getText().toString())) {
                        AuthCredential credential = EmailAuthProvider
                                .getCredential(user.getEmail(), etCurrPW.getText().toString());
                        // Prompt the user to re-provide their sign-in credentials
                        user.reauthenticate(credential)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            btnChange.setEnabled(false);
                                            user.updatePassword(etNewPW.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(ChangePassword.this, "Password has been changed", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    }
                                                }
                                            });
                                        }
                                        else{
                                            Toast.makeText(ChangePassword.this, "Current password is wrong", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                });
                    } else {
                        Toast.makeText(ChangePassword.this, "New passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChangePassword.this, "Text fields cannot empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
