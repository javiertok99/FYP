package com.example.a16022934.fyp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PrivateProfileSettings extends AppCompatActivity {

    TextView tvPrivatePassword, tvPrivateEmail, tvDOB;
    EditText etPrivateMobile;
    RadioGroup rgPrivateGender;
    Button btnSave;

    //get firebase database
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    //Create player object named currUser (Object is the java class with constructors)
    Player currUser;

    //Get the user
    DocumentReference userDoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_profile_settings);

        setTitle("PRIVATE PROFILE SETTINGS");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        initialize();

        DBHelper dbh = new DBHelper(PrivateProfileSettings.this);
        String uid = dbh.getUserId();

        //Get user ID
        userDoc = db.collection("users").document(uid);


        userDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currUser = documentSnapshot.toObject(Player.class);
                String phoneNo = currUser.getPhoneNo() + "";
                etPrivateMobile.setText(phoneNo);
                tvPrivateEmail.setText(currUser.getEmail());
                tvDOB.setText(currUser.getDateOfBirth());
                RadioButton male = findViewById(R.id.rMale);
                RadioButton female = findViewById(R.id.rFemale);
                if(currUser.getGender().equalsIgnoreCase("male")){
                    male.setChecked(true);
                    female.setChecked(false);
                }else{
                    male.setChecked(false);
                    female.setChecked(true);
                }

            }
        });


        //Get the selected radio button by the user.


        //Save the settings
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the new phone
                int newPhone = Integer.parseInt(etPrivateMobile.getText().toString());

                //get the new date
                String newDOB = tvDOB.getText().toString();

                //get the new gender
                RadioButton rb = findViewById(rgPrivateGender.getCheckedRadioButtonId());
                String newGender = rb.getText().toString();

                //Update the name and bio
                userDoc.update(
                        "phoneNo", newPhone,
                        "dateOfBirth", newDOB,
                        "gender", newGender
                ).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

                finish();
                Toast.makeText(getBaseContext(), "Private Profile Settings Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initialize(){
        btnSave = findViewById(R.id.btnSave);
        tvPrivatePassword = findViewById(R.id.tvPrivatePassword);
        tvPrivateEmail = findViewById(R.id.tvPrivateEmail);
        tvDOB = findViewById(R.id.tvPrivateDOB);
        etPrivateMobile = findViewById(R.id.etPrivateMobile);
        rgPrivateGender = findViewById(R.id.RGPrivateGender);
    }
}

