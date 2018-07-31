package com.example.a16022934.fyp;

import android.os.Bundle;
import android.os.Handler;
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

import static com.facebook.FacebookSdk.getApplicationContext;

public class OtherPlayerProfile extends Fragment {
    private TextView name;
    private EditText etBio;
    private RatingBar publicRating;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Player currUser;
    private Ratings currRating;
    private ImageView ivProfile;
    private ProgressBar loader;

    private BarChart barChart;
    private BarData barData;

    //Create a SelfEvaluations object
    SelfEvaluations currEvaluation;

    DocumentReference selfRef;

    public OtherPlayerProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_profile_page, container, false);
        name = view.findViewById(R.id.tvMyName);
        etBio = view.findViewById(R.id.etMyBio);
        publicRating = view.findViewById(R.id.ratingBarProfile);
        ivProfile = view.findViewById(R.id.ivMyProfilePic);
        loader = view.findViewById(R.id.loading);
        barChart = view.findViewById(R.id.barChartDisplay);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1f, 0));
        entries.add(new BarEntry(2f, 1));
        entries.add(new BarEntry(3f, 2));
        entries.add(new BarEntry(4f, 3));
        entries.add(new BarEntry(5f, 4));

        BarDataSet barDataSet = new BarDataSet(entries, "Cells");

        ArrayList<String> labels = new ArrayList<>();
        labels.add(0,"Service");
        labels.add(1,"Back Hand");
        labels.add(2,"Front Hand");
        labels.add(3,"Smash Shot");
        labels.add(4,"Drop Shot");

        BarData data = new BarData(labels, barDataSet);
        barChart.setData(data);
        barChart.setTouchEnabled(false);
        barChart.setDragEnabled(false);
        barChart.setScaleEnabled(false );
        barChart.setDescription("");

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        barChart.animateY(3000);

        DBHelper dbh = new DBHelper(getActivity());
        String uid = dbh.getUserId();
        DocumentReference userDoc = db.collection("users").document(uid);
        userDoc.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                currUser = documentSnapshot.toObject(Player.class);
                String fullName = currUser.getFullName();
                name.setText(fullName);
                etBio.setText(currUser.getDescription());
                String ratingId = currUser.getRatingId();
                DocumentReference rateRef = db.collection("ratings").document(ratingId);
                rateRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        currRating = documentSnapshot.toObject(Ratings.class);

                        name.setVisibility(View.VISIBLE);
                        etBio.setVisibility(View.VISIBLE);
                        publicRating.setVisibility(View.VISIBLE);
                        ivProfile.setVisibility(View.VISIBLE);
                        loader.setVisibility(View.INVISIBLE);
                        barChart.setVisibility(View.VISIBLE);
                    }
                });
                String selfEval = currUser.getSelfEvalId();
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

                        Float averageRating = Float.parseFloat((serviceP + backHandP + frontHandP + smashShotP + dropShotP)/5.0 + "");

                        publicRating.setRating(averageRating);
                    }
                });
            }
        });
        etBio.setEnabled(false);
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

