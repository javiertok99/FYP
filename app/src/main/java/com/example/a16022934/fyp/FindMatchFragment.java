package com.example.a16022934.fyp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FindMatchFragment extends Fragment {
    ArrayList<Player> alMatches;
    DBHelper dbh;
    String uid;
    private ProgressBar loader;
    public FindMatchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final ListView lvMatches;
        final MatchAdapter matchAdapter;
        View view = inflater.inflate(R.layout.fragment_find_match, container, false);
        lvMatches = view.findViewById(R.id.lvFindMatch);
        loader = view.findViewById(R.id.loader);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference users = db.collection("users");
        alMatches = new ArrayList<>();
        dbh = new DBHelper(getActivity());
        uid = dbh.getUserId();
        matchAdapter = new MatchAdapter(getActivity(), R.layout.findmatch_row, alMatches);
        users.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {
                        Player user = document.toObject(Player.class);
                        if(!document.getId().equals(uid)) {
                            alMatches.add(user);
                            loader.setVisibility(View.INVISIBLE);
                            lvMatches.setVisibility(View.VISIBLE);
                        }
                    }
                    matchAdapter.notifyDataSetChanged();
                }
            }
        });



        lvMatches.setAdapter(matchAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
