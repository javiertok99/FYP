package com.example.a16022934.fyp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class BottomNavBar extends AppCompatActivity {

    private TextView mTextMessage;
    String page = "";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.search:
                    transaction.replace(R.id.container, new FindMatchFragment()).commit();
                    return true;
                case R.id.matchHistory:
                    transaction.replace(R.id.container, new MatchHistoryFragment()).commit();
                    return true;
                case R.id.videos:
                    transaction.replace(R.id.container, new VideoFragment()).commit();
                    return true;
                case R.id.topPlayers:
                    transaction.replace(R.id.container, new TopPlayerFragment()).commit();
                    return true;
                case R.id.profile:
                    page = "profile";
                    transaction.replace(R.id.container, new MyProfilePageFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav_bar);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container, new FindMatchFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu_profile, menu);
      //  if(!page.equals("profile")){
            MenuItem item = menu.findItem(R.id.PublicProfileSettings);
            item.setVisible(false);
            MenuItem item2 = menu.findItem(R.id.PrivateProfileSettings);
            item2.setVisible(false);
            MenuItem item3 = menu.findItem(R.id.AppSettings);
            item3.setVisible(false);
      //  }
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
