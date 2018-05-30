package com.example.a16022934.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

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
    Button btnSave;
    TextView settingsDescription;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_3);
        initialize();
        Intent i = getIntent();
        final String email = i.getStringExtra("email");
        final String uid = i.getStringExtra("uid");
        final String fullName = i.getStringExtra("fullName");
        final int phoneNo = Integer.parseInt(i.getStringExtra("phone"));
        final String dateOfBirth = i.getStringExtra("dob");
        final String gender = i.getStringExtra("gender");
        final String bioText = i.getStringExtra("bio");

        setTitle("SIGN UP");

        btnSave = findViewById(R.id.btnSave);
//Service
        service.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (service.getRating() == 1) {
                    settingsDescription.setText("Serving - 1STAR \nHave little to no experience in serving.");
                } else if (service.getRating() == 2) {
                    settingsDescription.setText("Serving - 2STAR \nAble to deliver some successful serve.");
                } else if (service.getRating() == 3) {
                    settingsDescription.setText("Serving - 3STAR \nAble to deliver successful serve most of the times.");
                } else if (service.getRating() == 4) {
                    settingsDescription.setText("Serving - 4STAR \nAble to serve with little failure rate");
                } else if (service.getRating() == 5) {
                    settingsDescription.setText("Serving - 5STAR \nAble to serve with professionally.");
                } else {
                    settingsDescription.setText("Description will update\naccording to rating bar value");
                }
            }
        });
        //BackHand
        backHand.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (backHand.getRating() == 1) {
                    settingsDescription.setText("Back Hand - 1STAR \nHave little to no experience in back hand");
                } else if (backHand.getRating() == 2) {
                    settingsDescription.setText("Back Hand - 2STAR \nAble to deliver some successful back hand.\n");
                } else if (backHand.getRating() == 3) {
                    settingsDescription.setText("Back Hand - 3STAR \nAble to deliver successful back hand most of the times.\n");
                } else if (backHand.getRating() == 4) {
                    settingsDescription.setText("Back Hand - 4STAR \nAble to back hand with little failure rate\n");
                } else if (backHand.getRating() == 5) {
                    settingsDescription.setText("Back Hand - 5STAR \nAble to back hand professionally.");
                } else {
                    settingsDescription.setText("Description will update\naccording to rating bar value");
                }
            }
        });
        //FrontHand
        frontHand.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (frontHand.getRating() == 1) {
                    settingsDescription.setText("Front Hand - 1STAR \nHave little to no experience in front hand.");
                } else if (frontHand.getRating() == 2) {
                    settingsDescription.setText("Front Hand - 2STAR \nAble to deliver some successful front hand.");
                } else if (frontHand.getRating() == 3) {
                    settingsDescription.setText("Front Hand - 3STAR \nAble to deliver successful front hand most of the times.");
                } else if (frontHand.getRating() == 4) {
                    settingsDescription.setText("Front Hand - 4STAR \nAble to front hand with little failure rate.");
                } else if (frontHand.getRating() == 5) {
                    settingsDescription.setText("Front Hand - 5STAR \nAble to front hand professionally.");
                } else {
                    settingsDescription.setText("Description will update\naccording to rating bar value");
                }
            }
        });
        //SmashShot
        smashShot.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (smashShot.getRating() == 1) {
                    settingsDescription.setText("Smash Shot - 1STAR \nHave little to no experience in smash shot.");
                } else if (smashShot.getRating() == 2) {
                    settingsDescription.setText("Smash Shot - 2STAR \nAble to deliver some successful smash shot.");
                } else if (smashShot.getRating() == 3) {
                    settingsDescription.setText("Smash Shot - 3STAR \nAble to deliver successful smash shot most of the times.");
                } else if (smashShot.getRating() == 4) {
                    settingsDescription.setText("Smash Shot - 4STAR \nAble to smash shot with little failure rate.");
                } else if (smashShot.getRating() == 5) {
                    settingsDescription.setText("Smash Shot - 5STAR \nAble to smash shot professionally.");
                } else {
                    settingsDescription.setText("Description will update\naccording to rating bar value");
                }
            }
        });
        //DropShot
        dropShot.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (dropShot.getRating() == 1) {
                    settingsDescription.setText("Smash Shot - 1STAR \nHave little to no experience in drop shot.");
                } else if (dropShot.getRating() == 2) {
                    settingsDescription.setText("Smash Shot - 2STAR \nAble to deliver some successful drop shot.");
                } else if (dropShot.getRating() == 3) {
                    settingsDescription.setText("Smash Shot - 3STAR \nAble to deliver successful drop shot most of the times.");
                } else if (dropShot.getRating() == 4) {
                    settingsDescription.setText("Smash Shot - 4STAR \nAble to drop shot with little failure rate.");
                } else if (dropShot.getRating() == 5) {
                    settingsDescription.setText("Smash Shot - 5STAR \nAble to drop shot professionally.");
                } else {
                    settingsDescription.setText("Description will update\naccording to rating bar value");
                }
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            Button btn = findViewById(R.id.btnSave);

            @Override


            public void onClick(View view) {
                DocumentReference newUserRef = db.collection("users").document(uid);
                DocumentReference newRateRef = db.collection("ratings").document();
                DocumentReference newEvalRef = db.collection("selfEvaluations").document();
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
                selfEval.put("user_id", uid);
                newEvalRef.set(selfEval);
                //Rating
                Map<String, Object> rating = new HashMap<>();
                rating.put("score", 5);
                rating.put("user_id", uid);
                newRateRef.set(rating);
                //User
                Map<String, Object> player = new HashMap<>();
                player.put("gender", gender);
                player.put("dateOfBirth", dateOfBirth);
                player.put("description", bioText);
                player.put("email", email);
                player.put("fullName", fullName);
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
        settingsDescription = findViewById(R.id.tvSkillDescription);
    }

}

