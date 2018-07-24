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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyProfilePageFragment extends Fragment {
    private TextView name;
    private EditText etBio;
    private RatingBar publicRating;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Player currUser;
    private Ratings currRating;
    private ImageView ivProfile;
    private ProgressBar loader;

    public MyProfilePageFragment() {
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
                        publicRating.setRating(currRating.getScore());

                        name.setVisibility(View.VISIBLE);
                        etBio.setVisibility(View.VISIBLE);
                        publicRating.setVisibility(View.VISIBLE);
                        ivProfile.setVisibility(View.VISIBLE);
                        loader.setVisibility(View.INVISIBLE);
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

