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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HistoryBaseAdapter extends BaseAdapter {
    private ArrayList<MatchHistory> mOriginalValues; // Original Values
    private ArrayList<MatchHistory> mDisplayedValues;    // Values to be displayed
    LayoutInflater inflater;

    public HistoryBaseAdapter(Context context, ArrayList<MatchHistory> mProductArrayList) {
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
        TextView tvPlayer1;
        TextView tvPlayer2;
        TextView tvPlayer3;
        TextView tvPlayer4;
        TextView tvLocation;
        TextView tvTime;
        TextView tvDate;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.matchhistory_row, null);
            holder.llContainer = convertView.findViewById(R.id.llContainer);
            holder.tvPlayer1 = convertView.findViewById(R.id.tvPlayer1);
            holder.tvPlayer2 = convertView.findViewById(R.id.tvPlayer2);
            holder.tvPlayer3 = convertView.findViewById(R.id.tvPlayer3);
            holder.tvPlayer4 = convertView.findViewById(R.id.tvPlayer4);
            holder.tvLocation = convertView.findViewById(R.id.tvLocation);
            holder.tvDate = convertView.findViewById(R.id.tvDate);
            holder.tvTime = convertView.findViewById(R.id.tvTime);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvPlayer1.setText(mDisplayedValues.get(position).getPlayer1());
        holder.tvPlayer2.setText(mDisplayedValues.get(position).getPlayer2());
        holder.tvPlayer3.setText(mDisplayedValues.get(position).getPlayer3());
        holder.tvPlayer4.setText(mDisplayedValues.get(position).getPlayer4());
        holder.tvDate.setText(mDisplayedValues.get(position).getDateOfMatch());
        holder.tvTime.setText(mDisplayedValues.get(position).getTimeOfMatch());
        holder.tvLocation.setText(mDisplayedValues.get(position).getLocation());

        final ViewHolder finalHolder = holder;

        return convertView;
    }

}