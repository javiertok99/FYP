package com.example.a16022934.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;


public class MyProfilePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_page);

        setTitle("MY PROFILE");

        EditText etBio = findViewById(R.id.etBio);

        etBio.setEnabled(false);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_profile, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.PublicProfileSettings:
                Intent intentToPublic = new Intent(getBaseContext(), PublicProfileSettings.class);
                startActivity(intentToPublic);
                return true;
            case R.id.PrivateProfileSettings:
                Intent intentToPrivate = new Intent(getBaseContext(), PrivateProfileSettings.class);
                startActivity(intentToPrivate);
                return true;
            case R.id.AppSettings:
                Intent intentToAppSettings = new Intent(getBaseContext(), AppSettings.class);
                startActivity(intentToAppSettings);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
