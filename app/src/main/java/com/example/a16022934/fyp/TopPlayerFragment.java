package com.example.a16022934.fyp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class TopPlayerFragment extends Fragment {
    public TopPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_player, container, false);
        ListView lvTopPlayers;
        lvTopPlayers = view.findViewById(R.id.lvTopPlayers);
        ArrayList<TopPlayers> alTops;
        TopPlayerBaseAdapter tpAdapter;
        alTops = new ArrayList<>();
        alTops.add(new TopPlayers(R.drawable.profile, "Lin Dan", "Male", 34, "China"));
        tpAdapter = new TopPlayerBaseAdapter(getActivity(), alTops);
        lvTopPlayers.setAdapter(tpAdapter);
        return view;

    }

}
