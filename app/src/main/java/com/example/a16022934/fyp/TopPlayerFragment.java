package com.example.a16022934.fyp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class TopPlayerFragment extends Fragment {

    ArrayList<TopPlayers> alTop = new ArrayList<>();
    private FirebaseFirestore database;
    private DatabaseReference myRef;
    private static final String TAG = "TopPlayerFragment";
    private ListView lvTopPlayers;
    private ProgressBar loader;

    public TopPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        database = FirebaseFirestore.getInstance();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_player, container, false);
        final ListView lvTopPlayers;
        lvTopPlayers = view.findViewById(R.id.lvTopPlayers);
        loader = view.findViewById(R.id.tpLoader);
        lvTopPlayers.setVisibility(View.INVISIBLE);
        loader.setVisibility(View.VISIBLE);
        final TopPlayerBaseAdapter tpAdapter;
        tpAdapter = new TopPlayerBaseAdapter(getActivity(), alTop);
        database.collection("TopPlayers")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        TopPlayers user = document.toObject(TopPlayers.class);
                        alTop.add(user);
                        loader.setVisibility(View.INVISIBLE);
                        lvTopPlayers.setVisibility(View.VISIBLE);
                    }
                    tpAdapter.notifyDataSetChanged();
                }
            }
        });

        lvTopPlayers.setAdapter(tpAdapter);
        tpAdapter.notifyDataSetChanged();
        return view;

    }

}
