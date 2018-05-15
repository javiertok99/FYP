package com.example.a16022934.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("LOG IN PAGE");

        TextView tvCreateAcc = findViewById(R.id.tvCreateAccount);

        Button btn = findViewById(R.id.btnLogin);

        //senglee test code intent
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, BottomNavBar.class);
                startActivity(i);
            }
        });

        tvCreateAcc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), SignUp.class);
                intent.putExtra("Question","q2");
                startActivity(intent);
            }
        });
    }
}
