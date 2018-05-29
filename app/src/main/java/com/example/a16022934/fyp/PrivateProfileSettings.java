package com.example.a16022934.fyp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

        btnSave = findViewById(R.id.btnSave);
        tvPrivatePassword = findViewById(R.id.tvPrivatePassword);
        tvPrivateEmail = findViewById(R.id.tvPrivateEmail);
        tvDOB = findViewById(R.id.tvPrivateDOB);
        rgPrivateGender = findViewById(R.id.RGPrivateGender);


        DBHelper dbh = new DBHelper(PrivateProfileSettings.this);
        String uid = dbh.getUserId();

        //Get user ID
        userDoc = db.collection("users").document(uid);


        userDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currUser = documentSnapshot.toObject(Player.class);
                etPrivateMobile.setText(currUser.getPhoneNo());
                tvPrivateEmail.setText(currUser.getEmail());
                tvDOB.setText(currUser.getDateOfBirth());
            }
        });


        //Get the selected radio button by the user.


        //Save the settings
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get the new phone
                String newPhone = etPrivateMobile.getText().toString();

                //get the new date
                String newDOB = tvDOB.getText().toString();

                //get the new date
                String newGender = tvDOB.getText().toString();

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
}
