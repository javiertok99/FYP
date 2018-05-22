package com.example.a16022934.fyp;

import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


public class MyProfilePage extends AppCompatActivity {
    ImageView ivMyProfilePic;
    TextView tvMyName;
    RatingBar rtb;
    EditText etMyBio;
    BottomNavigationView navbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile_page);

        ivMyProfilePic = findViewById(R.id.ivMyProfilePic);
        tvMyName = findViewById(R.id.tvMyName);
        rtb = findViewById(R.id.ratingBar);
        etMyBio = findViewById(R.id.etMyBio);

        setTitle("MY PROFILE");

        EditText etBio = findViewById(R.id.etMyBio);
        navbar = findViewById(R.id.navBar);
        etBio.setEnabled(false);

        navbar.setSelectedItemId(R.id.profile);
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:

                        break;
                    case R.id.matchHistory:
                        break;
                    case R.id.videos:
                        break;
                    case R.id.topPlayers:

                        break;
                    case R.id.profile:
                        break;
                }
                return false;
            }
        });
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
