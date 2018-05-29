package com.example.a16022934.fyp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity {
    private EditText etUserName;
    private EditText etPassword;
    private FirebaseUser user;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference users = db.collection("users");
    private DocumentReference userRef;
    private ArrayList<Player> alPlayer = new ArrayList<>();
    private boolean loggedIn = false;

    @Override
    protected void onStart() {
        super.onStart();
        DBHelper dbh = new DBHelper(LogIn.this);
        final String uid = dbh.getUserId();
        users.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.getId().equals(uid)) {
                            loggedIn = true;
                            Intent i = new Intent(LogIn.this, BottomNavBar.class);
                            Toast.makeText(getApplicationContext(), uid, Toast.LENGTH_LONG).show();
                            startActivity(i);
                            finish();

                        }
                    }
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUserName = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        setTitle("LOG IN PAGE");
        TextView tvCreateAcc = findViewById(R.id.tvCreateAccount);
        Button btnLogin = findViewById(R.id.btnLogin);
        setTitle("MATCH MINTON");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = etUserName.getText().toString();
                String password = etPassword.getText().toString();
                if (userName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LogIn.this, "User Name or Password not Entered", Toast.LENGTH_LONG).show();
                } else {
                    final ProgressDialog progressDialog = ProgressDialog.show(LogIn.this, "Authenticating...", "Processing...", true);
                    (mAuth.signInWithEmailAndPassword(etUserName.getText().toString(), etPassword.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user = mAuth.getCurrentUser();
                                String uid = user.getUid();
                                DBHelper dbh = new DBHelper(LogIn.this);
                                long row_affected = dbh.retainUserLogIn(uid);
                                dbh.close();
                                userRef = db.collection("users").document(uid);
                                userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        DocumentSnapshot document = task.getResult();
                                        Player user = document.toObject(Player.class);
                                        Intent i = new Intent(LogIn.this, BottomNavBar.class);
                                        i.putExtra("type", "login");
                                        startActivity(i);
                                        finish();
                                    }
                                });
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(LogIn.this, "Unable to find email, please try again", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });

        tvCreateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SignUp.class);
                intent.putExtra("Question", "q2");
                startActivity(intent);
            }
        });
    }
}
