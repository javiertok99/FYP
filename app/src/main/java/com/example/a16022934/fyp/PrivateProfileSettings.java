package com.example.a16022934.fyp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class PrivateProfileSettings extends AppCompatActivity {

    TextView tvPrivatePassword, tvPrivateEmail, tvDOB, tvPrivateMobile;
    RadioGroup rgPrivateGender;
    Button btnSave;
    ImageButton ibChangePassword, ibChangeDOB, ibChangeMobile;

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
        tvPrivatePassword = findViewById(R.id.tvPasswordDisplay);
        tvPrivateEmail = findViewById(R.id.tvPrivateEmail);
        tvDOB = findViewById(R.id.tvDisplayDOB);
        ibChangePassword = findViewById(R.id.ibChangePassword);
        ibChangeMobile = findViewById(R.id.ibChangeMobile);
        ibChangeDOB = findViewById(R.id.ibCalendar);


        //get radio group object
        rgPrivateGender = findViewById(R.id.rgGender);
        initialize();

        DBHelper dbh = new DBHelper(PrivateProfileSettings.this);
        String uid = dbh.getUserId();

        //Get user ID
        userDoc = db.collection("users").document(uid);


        ibChangeDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar now = Calendar.getInstance();
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        tvDOB.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                };
                int Year = now.get(Calendar.YEAR);
                int Month = now.get(Calendar.MONTH);
                int Day = now.get(Calendar.DAY_OF_MONTH);

                // Create the Date Picker Dialog
                DatePickerDialog myDateDialog = new DatePickerDialog(PrivateProfileSettings.this,
                        myDateListener, Year, Month, Day);

                myDateDialog.show();

            }
        });
        userDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currUser = documentSnapshot.toObject(Player.class);
                String phoneNo = currUser.getPhoneNo() + "";
                tvPrivateMobile.setText(phoneNo);
                tvPrivateEmail.setText(currUser.getEmail());
                tvDOB.setText(currUser.getDateOfBirth());
                String gender = currUser.getGender();
                RadioButton rbMale = findViewById(R.id.rMale);
                RadioButton rbFemale = findViewById(R.id.rFemale);
                if (gender.equalsIgnoreCase("male")) {
                    rbMale.setChecked(true);
                    rbFemale.setChecked(false);
                } else {
                    rbMale.setChecked(false);
                    rbFemale.setChecked(true);
                }
            }
        });


        //Change Password
        ibChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ChangePassword.class);
                startActivity(intent);
            }
        });

        //Change mobile
        ibChangeMobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ChangePhoneNumber.class);
                startActivity(intent);
            }
        });


        //Save the settings
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNo = tvPrivateMobile.getText().toString();

                //get the new phone
                int newPhone = Integer.parseInt(phoneNo);

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

    private void initialize() {
        btnSave = findViewById(R.id.btnSave);
        tvPrivatePassword = findViewById(R.id.tvPasswordDisplay);
        tvPrivateEmail = findViewById(R.id.tvPrivateEmail);
        tvDOB = findViewById(R.id.tvDisplayDOB);
        tvPrivateMobile = findViewById(R.id.tvDisplayMobile);
        rgPrivateGender = findViewById(R.id.RGPrivateGender);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Hold on!");
        builder.setMessage("Changes have not been saved, are you sure you want to leave?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                PrivateProfileSettings.super.onBackPressed();
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }
}

