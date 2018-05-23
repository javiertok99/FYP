package com.example.a16022934.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class PublicProfileSettings extends AppCompatActivity {

    EditText settingsName, settingsBio;
    RatingBar settingService, settingBackHand, settingFrontHand, settingSmashShot, settingDropShot;
    TextView settingsDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        setTitle("PUBLIC PROFILE SETTINGS");
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        Intent intentReceived = getIntent();

        Button btnSave = findViewById(R.id.btnSave);
        settingsName = findViewById(R.id.etSettingsName);
        settingsBio = findViewById(R.id.etSettingsBio);
        settingService = findViewById(R.id.ratingService);
        settingBackHand = findViewById(R.id.ratingBackHand);
        settingFrontHand = findViewById(R.id.ratingFrontHand);
        settingSmashShot = findViewById(R.id.ratingSmashShot);
        settingDropShot = findViewById(R.id.ratingDropShot);
        settingsDescription = findViewById(R.id.tvSkillDescription);

        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(getBaseContext(),"Public Profile Settings Updated",Toast.LENGTH_SHORT).show();
            }
        });

        //Service
        settingService.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(settingService.getRating() == 1) {
                    settingsDescription.setText("Serving - 1STAR \nHave little to no experience in serving.");
                }
                else if(settingService.getRating() == 2) {
                    settingsDescription.setText("Serving - 2STAR \nAble to deliver some successful serve.");
                }
                else if(settingService.getRating() == 3) {
                    settingsDescription.setText("Serving - 3STAR \nAble to deliver successful serve most of the times.");
                }
                else if(settingService.getRating() == 4) {
                    settingsDescription.setText("Serving - 4STAR \nAble to serve with little failure rate");
                }
                else if(settingService.getRating() == 5) {
                    settingsDescription.setText("Serving - 5STAR \nAble to serve with professionally.");
                }
                else{
                    settingsDescription.setText("Description will update\naccording to rating bar value");
                }
            }
        });
        //BackHand
        settingBackHand.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(settingBackHand.getRating() == 1) {
                    settingsDescription.setText("Back Hand - 1STAR \nHave little to no experience in back hand");
                }
                else if(settingBackHand.getRating() == 2) {
                    settingsDescription.setText("Back Hand - 2STAR \nAble to deliver some successful back hand.\n");
                }
                else if(settingBackHand.getRating() == 3) {
                    settingsDescription.setText("Back Hand - 3STAR \nAble to deliver successful back hand most of the times.\n");
                }
                else if(settingBackHand.getRating() == 4) {
                    settingsDescription.setText("Back Hand - 4STAR \nAble to back hand with little failure rate\n");
                }
                else if(settingBackHand.getRating() == 5) {
                    settingsDescription.setText("Back Hand - 5STAR \nAble to back hand professionally.");
                }
                else{
                    settingsDescription.setText("Description will update\naccording to rating bar value");
                }
            }
        });
        //FrontHand
        settingFrontHand.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(settingFrontHand.getRating() == 1) {
                    settingsDescription.setText("Front Hand - 1STAR \nHave little to no experience in front hand.");
                }
                else if(settingFrontHand.getRating() == 2) {
                    settingsDescription.setText("Front Hand - 2STAR \nAble to deliver some successful front hand.");
                }
                else if(settingFrontHand.getRating() == 3) {
                    settingsDescription.setText("Front Hand - 3STAR \nAble to deliver successful front hand most of the times.");
                }
                else if(settingFrontHand.getRating() == 4) {
                    settingsDescription.setText("Front Hand - 4STAR \nAble to front hand with little failure rate.");
                }
                else if(settingFrontHand.getRating() == 5) {
                    settingsDescription.setText("Front Hand - 5STAR \nAble to front hand professionally.");
                }
                else{
                    settingsDescription.setText("Description will update\naccording to rating bar value");
                }
            }
        });
        //SmashShot
        settingSmashShot.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(settingSmashShot.getRating() == 1) {
                    settingsDescription.setText("Smash Shot - 1STAR \nHave little to no experience in smash shot.");
                }
                else if(settingSmashShot.getRating() == 2) {
                    settingsDescription.setText("Smash Shot - 2STAR \nAble to deliver some successful smash shot.");
                }
                else if(settingSmashShot.getRating() == 3) {
                    settingsDescription.setText("Smash Shot - 3STAR \nAble to deliver successful smash shot most of the times.");
                }
                else if(settingSmashShot.getRating() == 4) {
                    settingsDescription.setText("Smash Shot - 4STAR \nAble to smash shot with little failure rate.");
                }
                else if(settingSmashShot.getRating() == 5) {
                    settingsDescription.setText("Smash Shot - 5STAR \nAble to smash shot professionally.");
                }
                else{
                    settingsDescription.setText("Description will update\naccording to rating bar value");
                }
            }
        });
        //DropShot
        settingDropShot.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(settingDropShot.getRating() == 1) {
                    settingsDescription.setText("Smash Shot - 1STAR \nHave little to no experience in drop shot.");
                }
                else if(settingDropShot.getRating() == 2) {
                    settingsDescription.setText("Smash Shot - 2STAR \nAble to deliver some successful drop shot.");
                }
                else if(settingDropShot.getRating() == 3) {
                    settingsDescription.setText("Smash Shot - 3STAR \nAble to deliver successful drop shot most of the times.");
                }
                else if(settingDropShot.getRating() == 4) {
                    settingsDescription.setText("Smash Shot - 4STAR \nAble to drop shot with little failure rate.");
                }
                else if(settingDropShot.getRating() == 5) {
                    settingsDescription.setText("Smash Shot - 5STAR \nAble to drop shot professionally.");
                }
                else{
                    settingsDescription.setText("Description will update\naccording to rating bar value");
                }
            }
        });
    }
}
