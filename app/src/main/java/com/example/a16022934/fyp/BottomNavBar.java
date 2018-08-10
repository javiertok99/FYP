package com.example.a16022934.fyp;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
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
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BottomNavBar extends AppCompatActivity {

    public FragmentTransaction transaction;
    private TextView mTextMessage;
    private String page = "";
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = firebaseAuth.getCurrentUser();
    private String uid = user.getUid();
    private Boolean chatFragment = false;
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
        if (type != null) {
            if (type.equals("signUp") || type.equals("profile")) {
                chatFragment = true;
                setTitle("My Profile");
                transaction.replace(R.id.frame, new MyProfilePageFragment()).commit();

            } else if (type.equals("chat")) {
                String file = i.getStringExtra("class");
                if (file.equals("adapter")) {
                    chatFragment = false;
                    Intent intent = getIntent();
                    Player player = (Player) intent.getSerializableExtra("player");
                    setTitle(player.getFullName());
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("newSoloChat", player);
                    bundle.putString("chatString", "player");
                    ChatFragment fragment = new ChatFragment();
                    fragment.setArguments(bundle);
                    navigation.setSelectedItemId(R.id.chat);
                    transaction.replace(R.id.frame, fragment).commit();
                } else if (file.equals("list")) {
                    chatFragment = false;
                    Intent chat = getIntent();
                    OneToOne soloChat = (OneToOne) chat.getSerializableExtra("partner");
                    if (uid.equals(soloChat.getSenderId())) {
                        setTitle(soloChat.getReceiverName());
                    } else {
                        setTitle(soloChat.getSenderName());
                    }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("soloChat", soloChat);
                    bundle.putString("chatString", "soloChat");

                    ChatFragment fragment = new ChatFragment();
                    fragment.setArguments(bundle);
                    navigation.setSelectedItemId(R.id.chat);
                    transaction.replace(R.id.frame, fragment).commit();
                }
            } else if (type.equals("otherPlayer")) {
                chatFragment = true;
                Intent intent = getIntent();
                Player otherPlayer = (Player) intent.getSerializableExtra("player");
                Log.v("Check Other Player", otherPlayer.getFullName());
                Bundle bundle = new Bundle();
                bundle.putSerializable("player", otherPlayer);
                // set Fragmentclass Arguments
                setTitle(otherPlayer.getFullName());
                OtherPlayerProfile fragment = new OtherPlayerProfile();
                fragment.setArguments(bundle);
                transaction.replace(R.id.frame, fragment).commit();
            }
        } else {
            chatFragment = true;
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
