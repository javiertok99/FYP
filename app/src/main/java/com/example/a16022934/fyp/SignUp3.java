package com.example.a16022934.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp3 extends AppCompatActivity {
    RatingBar service;
    RatingBar frontHand;
    RatingBar backHand;
    RatingBar dropShot;
    RatingBar smashShot;
    EditText bio;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    boolean evalSuccess = false;
    boolean rateSuccess = false;
    boolean userSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_3);
        initialize();
        Intent i = getIntent();
        final String username = i.getStringExtra("username");
        final String password = i.getStringExtra("password");
        final String email = i.getStringExtra("email");
        final String firstName = i.getStringExtra("firstName");
        final String lastName = i.getStringExtra("lastName");
        final int phoneNo = Integer.parseInt(i.getStringExtra("phone"));
        final String dateOfBirth = i.getStringExtra("dob");
        final String gender = i.getStringExtra("gender");
        final String bioText = bio.getText().toString();

        setTitle("SIGN UP");

        Button btn = findViewById(R.id.btnDone);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference newUserRef = db.collection("users").document();
                DocumentReference newRateRef = db.collection("ratings").document();
                DocumentReference newEvalRef = db.collection("selfEvaluations").document();
                String userId = newUserRef.getId();
                String rateId = newRateRef.getId();
                String evalId = newEvalRef.getId();
                //Service
                float serving = service.getRating();
                float front = frontHand.getRating();
                float back = backHand.getRating();
                float smash = dropShot.getRating();
                float drop = smashShot.getRating();
                Map<String, Object> selfEval = new HashMap<>();
                selfEval.put("backhand", back);
                selfEval.put("dropShot", drop);
                selfEval.put("fronthand", front);
                selfEval.put("service", serving);
                selfEval.put("smashShot", smash);
                selfEval.put("user_id", userId);
                newEvalRef.set(selfEval);
                //Rating
                Map<String, Object> rating = new HashMap<>();
                rating.put("score", 5);
                rating.put("user_id", userId);
                newRateRef.set(rating);
                //User
                Map<String, Object> player = new HashMap<>();
                player.put("gender", gender);
                player.put("username", username);
                player.put("dateOfBirth", dateOfBirth);
                player.put("description", bioText);
                player.put("email", email);
                player.put("firstName", firstName);
                player.put("lastName", lastName);
                player.put("password", password);
                player.put("phoneNo", phoneNo);
                player.put("ratingId", rateId);
                player.put("selfEvalId", evalId);
                newUserRef.set(player);

                Intent i = new Intent(SignUp3.this, BottomNavBar.class);
                i.putExtra("type", "signUp");
                startActivity(i);
                finish();
            }
        });
    }

    private void initialize() {
        service = findViewById(R.id.ratingService);
        frontHand = findViewById(R.id.ratingFrontHand);
        backHand = findViewById(R.id.ratingBackHand);
        dropShot = findViewById(R.id.ratingDropShot);
        smashShot = findViewById(R.id.ratingSmashShot);
        bio = findViewById(R.id.etOtherBio);
    }

}

