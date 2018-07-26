package com.example.a16022934.fyp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class TopPlayerFragment extends Fragment {

    private TextView tvName, tvAge, tvDescription, tvGender, tvLocation;
    private ImageView ivProfile;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static final String TAG = "TopPlayerFragment";

    ArrayList<TopPlayers> alTops;

    public TopPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("TopPlayers");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // TODO: Task 4 - Get the latest value from the dataSnapshot and display on the UI using the EditText message
                // This method will get fired everytime the "message" value updates in the realtime database. We're getting our data back as a DataSnapshot
                TopPlayers list = dataSnapshot.getValue(TopPlayers.class);
                if (list != null) {
                    tvName.setText(list.getName());
                    tvAge.setText(list.getAge());
                    tvDescription.setText(list.getDescription());
                    tvLocation.setText(list.getLocation());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // This method will be invoked if there is any error
                Log.e(TAG, "Database error occurred", databaseError.toException());
            }
        });
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_player, container, false);
//        ListView lvTopPlayers;
//        lvTopPlayers = view.findViewById(R.id.lvTopPlayers);
//        TopPlayerBaseAdapter tpAdapter;
//        TopPlayers new1 = new TopPlayers(R.drawable.profile, tvName.getText().toString(), tvGender.getText().toString(), Integer.parseInt(tvAge.getText().toString()), tvLocation.getText().toString(),tvDescription.getText().toString());
//        alTops.add(new1);
//tpAdapter = new TopPlayerBaseAdapter(getActivity(), alTops);
//        lvTopPlayers.setAdapter(tpAdapter);
//        tpAdapter.notifyDataSetChanged();
        return view;

    }

}
