package com.example.a16022934.fyp;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class MatchBaseAdapter extends BaseAdapter {
    private ArrayList<Player> mOriginalValues; // Original Values
    private ArrayList<Player> mDisplayedValues;    // Values to be displayed
    LayoutInflater inflater;

    public MatchBaseAdapter(Context context, ArrayList<Player> mProductArrayList) {
        this.mOriginalValues = mProductArrayList;
        this.mDisplayedValues = mProductArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDisplayedValues.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        ConstraintLayout llContainer;
        ImageView ivProfilePic;
        TextView tvName;
        TextView tvAge;
        TextView tvGender;
        TextView tvLocation;
        Button btnViewProfile;
        ImageButton ibChat;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.findmatch_row, null);
            holder.llContainer = convertView.findViewById(R.id.llContainer);
            holder.ivProfilePic = convertView.findViewById(R.id.ivMyProfilePic);
            holder.tvName = convertView.findViewById(R.id.tvMyName);
            holder.tvAge = convertView.findViewById(R.id.tvAge);
            holder.tvGender = convertView.findViewById(R.id.tvGender);
            holder.tvLocation = convertView.findViewById(R.id.tvLocation);
            holder.btnViewProfile = convertView.findViewById(R.id.btnViewProfile);
            holder.ibChat = convertView.findViewById(R.id.ibChat);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        String fullName = mDisplayedValues.get(position).getFirstName() + " " + mDisplayedValues.get(position).getLastName();
        holder.tvName.setText(fullName);
        holder.tvGender.setText(mDisplayedValues.get(position).getGender());
        String year = mDisplayedValues.get(position).getDateOfBirth().substring(6,9);
        String age = Calendar.getInstance().get(Calendar.YEAR) - Integer.parseInt(year) + "";
        holder.tvAge.setText(age);
        holder.ivProfilePic.getLayoutParams().height = 250;
        holder.ivProfilePic.getLayoutParams().width = 250;
        holder.btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = mDisplayedValues.get(position);
                Intent i = new Intent(v.getContext(), MyProfilePage.class);
                i.putExtra("player", player);
                v.getContext().startActivity(i);

            }
        });
        holder.ibChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Player player = mDisplayedValues.get(position);
                Intent i = new Intent(v.getContext(), Chat.class);
                i.putExtra("player", player);
                v.getContext().startActivity(i);
            }
        });
        final ViewHolder finalHolder = holder;

        return convertView;
    }

    public int getRawId(Context context, String name) {
        return context.getResources().getIdentifier(name, "raw", context.getPackageName());
    }
}