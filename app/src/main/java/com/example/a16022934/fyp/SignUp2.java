package com.example.a16022934.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUp2 extends AppCompatActivity {

    EditText etFirstName;
    EditText etLastName;
    EditText etPhoneNo;
    EditText etDateOfBirth;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_2);
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        etDateOfBirth = findViewById(R.id.etDoB);
        rgGender = findViewById(R.id.rgGender);

        Intent i = getIntent();
        final String username = i.getStringExtra("username");
        final String password = i.getStringExtra("password");
        final String email = i.getStringExtra("email");

        setTitle("SIGN UP");

        Button btn = findViewById(R.id.btnNext);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isEmpty();
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String phone = etPhoneNo.getText().toString();
                String dob = etDateOfBirth.getText().toString();
                RadioButton rb = findViewById(rgGender.getCheckedRadioButtonId());
                String gender = rb.getText().toString();
                Intent i = new Intent(SignUp2.this, SignUp3.class);
                i.putExtra("username", username);
                i.putExtra("password", password);
                i.putExtra("email", email);
                i.putExtra("firstName", firstName);
                i.putExtra("lastName", lastName);
                i.putExtra("phone", phone);
                i.putExtra("dob", dob);
                i.putExtra("gender", gender);

                startActivity(i);
            }
        });
    }
    private void isEmpty(){
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String phone = etPhoneNo.getText().toString();
        String dob = etDateOfBirth.getText().toString();

        if(!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(phone) && !TextUtils.isEmpty(dob)){

        }else{
            Toast.makeText(SignUp2.this,"Textfields is Empty", Toast.LENGTH_LONG).show();
        }
    }
}

