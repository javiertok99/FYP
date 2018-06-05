package com.example.a16022934.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class AppSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_settings);
        setTitle("APP SETTINGS");

        Button btnSave = findViewById(R.id.btnSave);
        Switch nightSwitch = findViewById(R.id.switchNightMode);
        Switch volumeSwitch = findViewById(R.id.switchVolume);
        Button btn2 = findViewById(R.id.button3);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(getBaseContext(), "App Settings Updated", Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_chat);
            }
        });


        nightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getBaseContext(), "Night Mode is 'ON'", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Night Mode is 'OFF'", Toast.LENGTH_SHORT).show();
                }
            }
        });

        volumeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getBaseContext(), "Volume is 'ON'", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "Volume is 'OFF'", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
