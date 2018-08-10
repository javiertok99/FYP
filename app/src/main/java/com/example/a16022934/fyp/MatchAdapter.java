package com.example.a16022934.fyp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class MatchAdapter extends ArrayAdapter {
    private ArrayList<Player> player;
    private Context context;

    public MatchAdapter(Context context, int resource, ArrayList<Player> objects) {
        super(context, resource, objects);
        player = objects;
        this.context = context;
    }

    class ViewHolder {
        ImageView ivProfilePic;
        TextView tvName;
        TextView tvAge;
        TextView tvGender;
        Button btnViewProfile;
        ImageButton ibChat;
        RatingBar rb;
        int position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.findmatch_row, parent, false);
            view = new ViewHolder();
            view.position = position;
            view.ivProfilePic = convertView.findViewById(R.id.ivMyProfilePic);
            view.tvName = convertView.findViewById(R.id.tvMyName);
            view.tvAge = convertView.findViewById(R.id.tvAge);
            view.tvGender = convertView.findViewById(R.id.tvGender);
            view.btnViewProfile = convertView.findViewById(R.id.btnViewProfile);
            view.ibChat = convertView.findViewById(R.id.ibChat);
            view.rb = convertView.findViewById(R.id.ratingBarFindMatch);
            final Player currentPlayer = player.get(position);
            view.btnViewProfile
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getContext(), BottomNavBar.class);
                            i.putExtra("type", "otherPlayer");
                            i.putExtra("player", currentPlayer);
                            getContext().startActivity(i);
                        }
                    });
            view.ibChat
                    .setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(getContext(), BottomNavBar.class);
                            i.putExtra("type", "chat");
                            i.putExtra("class", "adapter");
                            i.putExtra("player", currentPlayer);
                            getContext().startActivity(i);
                        }
                    });
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        Player currentPlayer = player.get(position);
        view.tvName.setText(currentPlayer.getFullName());
        String age = "Age: " + currentPlayer.getAge();
        view.tvAge.setText(age);
        view.tvGender.setText(currentPlayer.getGender());
        view.ivProfilePic.setImageResource(R.drawable.alluserprofile);
        String uid = currentPlayer.getUser_id();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference user = db.collection("users").document(uid);
        user.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Player player = documentSnapshot.toObject(Player.class);
                if (player != null) {
                    String selfID = player.getSelfEvalId();
                    FirebaseFirestore fb = FirebaseFirestore.getInstance();
                    DocumentReference user = fb.collection("selfEvaluations").document(selfID);
                    user.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            SelfEvaluations self = documentSnapshot.toObject(SelfEvaluations.class);
                            if (self != null) {
                                float total = self.getBackhand() + self.getDropShot() + self.getService() + self.getFootWork() + self.getFronthand() + self.getSmashShot();
                                float avg = total / (float) 6.0;
                                view.rb.setRating(avg);
                            }
                        }
                    });

                }

            }
        });

        view.position = position;
        return convertView;

    }
}
