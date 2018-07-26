package com.example.a16022934.fyp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class AutoLogin extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference users = db.collection("users");
    private Boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_login);

        DBHelper dbh = new DBHelper(AutoLogin.this);
        final String uid = dbh.getUserId();
        users.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.v("HELP", "I CAME HERE");
                        if (document.getId().equals(uid)) {
                            loggedIn = true;
                            Intent i = new Intent(AutoLogin.this, BottomNavBar.class);
                            startActivity(i);
                            finish();

                        }
                    }
                    if (!loggedIn) {
                        Intent i = new Intent(AutoLogin.this, LogIn.class);
                        startActivity(i);
                        finish();
                    }
                    

                }
            }
        });
    }
}
