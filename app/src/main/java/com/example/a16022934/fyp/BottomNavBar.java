package com.example.a16022934.fyp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class BottomNavBar extends AppCompatActivity {

    public FragmentTransaction transaction;
    private TextView mTextMessage;
    private String page = "";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.search:
                    setTitle("Find Match");
                    transaction.replace(R.id.frame, new FindMatchFragment()).commit();
                    return true;
                case R.id.chat:
                    setTitle("Chat");
                    transaction.replace(R.id.frame, new ChatList()).commit();
                    return true;
                case R.id.videos:
                    setTitle("Videos");
                    transaction.replace(R.id.frame, new VideoFragment()).commit();
                    return true;
                case R.id.topPlayers:
                    setTitle("Top Players");
                    transaction.replace(R.id.frame, new TopPlayerFragment()).commit();
                    return true;
                case R.id.profile:
                    setTitle("My Profile");
                    page = "profile";
                    transaction.replace(R.id.frame, new MyProfilePageFragment()).commit();
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
        Intent i = getIntent();
        String type = i.getStringExtra("type");
        if(type != null){
            if(type.equals("signUp") || type.equals("profile")){
                setTitle("My Profile");
                transaction.replace(R.id.frame, new MyProfilePageFragment()).commit();

            }else if(type.equals("chat")){
                String file = i.getStringExtra("class");
                setTitle("Chat");
                if(file.equals("adapter")){
                    Intent intent = getIntent();
                    Player player = (Player)intent.getSerializableExtra("player");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("newSoloChat", player);
                    ChatFragment fragment = new ChatFragment();
                    fragment.setArguments(bundle);
                    transaction.replace(R.id.frame, fragment).commit();

                }else if(file.equals("list")){
                    Intent chat = getIntent();
                    OneToOne soloChat = (OneToOne) chat.getSerializableExtra("partner");
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("soloChat", soloChat);

                    ChatFragment fragment = new ChatFragment();
                    fragment.setArguments(bundle);
                    transaction.replace(R.id.frame, fragment).commit();
                }


            }else if(type.equals("otherPlayer")){
                Intent intent = getIntent();
                Player otherPlayer = (Player)intent.getSerializableExtra("player");
                Log.v("Check Other Player", otherPlayer.getFullName());
                Bundle bundle = new Bundle();
                bundle.putSerializable("player", otherPlayer);
                // set Fragmentclass Arguments
                setTitle(otherPlayer.getFullName());
                OtherPlayerProfile fragment = new OtherPlayerProfile();
                fragment.setArguments(bundle);
                transaction.replace(R.id.frame, fragment).commit();
            }
        }else{
            setTitle("Find Match");
            transaction.replace(R.id.frame, new FindMatchFragment()).commit();
        }


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
            case R.id.logOut:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setTitle("Log out?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DBHelper dbh = new DBHelper(BottomNavBar.this);
                        int uid = dbh.getId();
                        dbh.removeUserId(uid);
                        Intent intent = new Intent(getBaseContext(), LogIn.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
