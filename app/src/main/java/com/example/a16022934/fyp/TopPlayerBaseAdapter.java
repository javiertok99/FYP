package com.example.a16022934.fyp;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TopPlayerBaseAdapter extends BaseAdapter {
    private ArrayList<TopPlayers> mOriginalValues; // Original Values
    private ArrayList<TopPlayers> mDisplayedValues;    // Values to be displayed
    LayoutInflater inflater;

    public TopPlayerBaseAdapter(Context context, ArrayList<TopPlayers> mProductArrayList) {
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
        ConstraintLayout llcontainer;
        TextView name;
        TextView age;
        TextView gender;
        TextView location;
        TextView description;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.top_player_row, null);
            holder.llcontainer = convertView.findViewById(R.id.llContainer);
            holder.name = convertView.findViewById(R.id.tvMyName);
            holder.age = convertView.findViewById(R.id.tvAge);
            holder.gender = convertView.findViewById(R.id.tvGender);
            holder.location = convertView.findViewById(R.id.tvLocation);
            holder.description = convertView.findViewById(R.id.tvDescription);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText("Name: " + mDisplayedValues.get(position).getName());
        holder.age.setText("Age: " + mDisplayedValues.get(position).getAge());
        holder.gender.setText("Gender: " + mDisplayedValues.get(position).getGender());
        holder.location.setText("From: " + mDisplayedValues.get(position).getplaceOfBirth());
        holder.description.setText(mDisplayedValues.get(position).getDescription());

        final ViewHolder finalHolder = holder;

        return convertView;
    }

}