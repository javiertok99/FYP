package com.example.a16022934.fyp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class OtherPlayerProfile extends Fragment {
    private TextView name;
    private EditText etBio;
    private RatingBar publicRating;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Player otherPlayer;
    private Ratings currRating;
    private ImageView ivProfile;
    private ProgressBar loader;

    private BarChart barChart;
    private BarData barData;

    private String fullName;
    private String ratingId;
    private String selfEval;

    //Create a SelfEvaluations object
    SelfEvaluations currEvaluation;

    DocumentReference selfRef;

    public OtherPlayerProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_other_player_profile, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            Toast.makeText(getContext(), "I got past bundle", Toast.LENGTH_SHORT).show();
            otherPlayer = (Player) bundle.getSerializable("player");

            name = view.findViewById(R.id.tvOtherName);
            etBio = view.findViewById(R.id.etOtherBio);
            publicRating = view.findViewById(R.id.ratingOther);
            ivProfile = view.findViewById(R.id.ivOtherProfilePic);
            loader = view.findViewById(R.id.loadingOther);
            barChart = view.findViewById(R.id.barChartDisplayOther);

            if (otherPlayer != null) {
                Toast.makeText(getContext(), "IT WENT IN HERE", Toast.LENGTH_SHORT).show();
                fullName = otherPlayer.getFullName();
                name.setText(fullName);
                etBio.setText(otherPlayer.getDescription());
                ratingId = otherPlayer.getRatingId();
                selfEval = otherPlayer.getSelfEvalId();

                selfRef = db.collection("selfEvaluations").document(selfEval);
                selfRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        currEvaluation = documentSnapshot.toObject(SelfEvaluations.class);
                        int serviceP = currEvaluation.getService();
                        int backHandP = currEvaluation.getBackhand();
                        int frontHandP = currEvaluation.getFronthand();
                        int smashShotP = currEvaluation.getSmashShot();
                        int dropShotP = currEvaluation.getDropShot();
                        int footWorkP = currEvaluation.getFootWork();

                        Float serviceFloat = Float.parseFloat(serviceP + "");
                        Float backHandFloat = Float.parseFloat(backHandP + "");
                        Float frontHandFloat = Float.parseFloat(frontHandP + "");
                        Float smashShotFloat = Float.parseFloat(smashShotP + "");
                        Float dropShotFloat = Float.parseFloat(dropShotP + "");
                        Float footWorkFloat = Float.parseFloat(footWorkP + "");

                        Float averageRating = Float.parseFloat((serviceP + backHandP + frontHandP + smashShotP + dropShotP + footWorkP) / 6.0 + "");

                        publicRating.setRating(averageRating);

                        ArrayList<BarEntry> entries = new ArrayList<>();
                        entries.add(new BarEntry(serviceFloat, 0));
                        entries.add(new BarEntry(backHandFloat, 1));
                        entries.add(new BarEntry(frontHandFloat, 2));
                        entries.add(new BarEntry(smashShotFloat, 3));
                        entries.add(new BarEntry(dropShotFloat, 4));
                        entries.add(new BarEntry(footWorkFloat, 5));

                        BarDataSet barDataSet = new BarDataSet(entries, "labels");

                        ArrayList<String> labels = new ArrayList<>();
                        labels.add("Service");
                        labels.add("Back Hand");
                        labels.add("Front Hand");
                        labels.add("Smash Shot");
                        labels.add("Drop Shot");
                        labels.add("Foot Work");

                        barChart.getXAxis().setLabelsToSkip(0);


                        BarData data = new BarData(labels, barDataSet);

                        barDataSet.setBarSpacePercent(0.5f);

                        barChart.setData(data);
                        barChart.setTouchEnabled(false);
                        barChart.setDragEnabled(false);
                        barChart.setScaleEnabled(true);
                        barChart.setDescription("");

                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        barChart.animateY(3000);

                        name.setVisibility(View.VISIBLE);
                        etBio.setVisibility(View.VISIBLE);
                        publicRating.setVisibility(View.VISIBLE);
                        ivProfile.setVisibility(View.VISIBLE);
                        loader.setVisibility(View.INVISIBLE);
                        barChart.setVisibility(View.VISIBLE);
                    }
                });
                etBio.setEnabled(false);
            }
        }
        return view;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu_profile, menu);
    }
}

