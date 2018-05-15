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
import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MatchHistoryFragment extends Fragment {

    public MatchHistoryFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ListView lvMatchHistory;
        ArrayList<MatchHistory> alMatches;
        HistoryBaseAdapter matchAdapter;
        View view = inflater.inflate(R.layout.match_history, container, false);
        lvMatchHistory = view.findViewById(R.id.lvMatchHistory);
        alMatches = new ArrayList<>();

        alMatches.add(new MatchHistory("Javier Tok", "Ang Seng Lee", "Daniel Ng", "Jacob Potato", "16/5/2018", "2359", "Choa Chu Kang" ));
        alMatches.add(new MatchHistory("Javier Tok", "Ang Seng Lee", "16/5/2018", "2359", "Choa Chu Kang" ));
        matchAdapter = new HistoryBaseAdapter(getActivity(), alMatches);
        lvMatchHistory.setAdapter(matchAdapter);

        // Inflate the layout for this fragment
        return view;
    }

}
