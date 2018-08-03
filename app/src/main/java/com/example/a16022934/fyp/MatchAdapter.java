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
import android.widget.TextView;

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
        int position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder view;
        if(convertView == null){
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
        }else{
            view = (ViewHolder) convertView.getTag();
        }
        Player currentPlayer = player.get(position);
        view.tvName.setText(currentPlayer.getFullName());
        view.tvAge.setText(currentPlayer.getAge()  +" years old");
        view.tvGender.setText("Gender: " +currentPlayer.getGender());

        view.position = position;
        return convertView;

    }
}
