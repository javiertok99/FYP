package com.example.a16022934.fyp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FindMatchFragment extends Fragment {

    public FindMatchFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ListView lvMatches;
        ArrayList<Player> alMatches;
        MatchBaseAdapter matchAdapter;
        View view = inflater.inflate(R.layout.fragment_find_match, container, false);
        lvMatches = view.findViewById(R.id.lvFindMatch);
        alMatches = new ArrayList<>();
        alMatches.add(new Player(R.drawable.profile,"Javier Tok",18,"Choa Chu Kang","Male"));
        alMatches.add(new Player(R.drawable.profile,"Ang Seng Lee",19,"Bukit Panjang","Male"));
        matchAdapter = new MatchBaseAdapter(getActivity(), alMatches);
        lvMatches.setAdapter(matchAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
