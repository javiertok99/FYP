package com.example.a16022934.fyp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
        ImageView ivPlayer;
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
            holder.ivPlayer = convertView.findViewById(R.id.ivPlayer);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mDisplayedValues.get(position).getName());
        holder.age.setText(mDisplayedValues.get(position).getAge() + "years old");
        holder.gender.setText("Gender: " + mDisplayedValues.get(position).getGender());
        holder.location.setText("Country: " + mDisplayedValues.get(position).getplaceOfBirth());
        holder.description.setText(mDisplayedValues.get(position).getDescription());

        if(holder.name.getText().toString().equals("Carolina María")) {
            holder.ivPlayer.setImageResource(R.drawable.carolina);
        }
        else if(holder.name.getText().toString().equals("Jan Ø. Jørgensen")){
            holder.ivPlayer.setImageResource(R.drawable.janojorgensen);
        }
        else if(holder.name.getText().toString().equals("Ratchanok Intanon")){
            holder.ivPlayer.setImageResource(R.drawable.ratchanokinatanon);
        }
        else if(holder.name.getText().toString().equals("Tai Tzu-Ying")){
            holder.ivPlayer.setImageResource(R.drawable.taitzuying);
        }
        else if(holder.name.getText().toString().equals("Vicktor Axelsen")){
            holder.ivPlayer.setImageResource(R.drawable.viktoraxelsen);
        }
        else if(holder.name.getText().toString().equals("Wang Yihan")){
            holder.ivPlayer.setImageResource(R.drawable.wangyihan);
        }
        else if(holder.name.getText().toString().equals("Chen Long")){
            holder.ivPlayer.setImageResource(R.drawable.chenlong);
        }
        else if(holder.name.getText().toString().equals("Lee Chong Wei")){
            holder.ivPlayer.setImageResource(R.drawable.leechongwei);
        }
        else if(holder.name.getText().toString().equals("Li Xuerui")){
            holder.ivPlayer.setImageResource(R.drawable.lixuerui);
        }
        else if(holder.name.getText().toString().equals("Lin Dan")){
            holder.ivPlayer.setImageResource(R.drawable.lindan);
        }
        return convertView;
    }

}