package com.example.a16022934.fyp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<ChatMsg> message;
    TextView tvDisplayName, tvDateTime, tvMessage;


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(layout_id, parent, false);

        tvDisplayName = (TextView) rowView.findViewById(R.id.message_user);
        tvMessage = (TextView) rowView.findViewById(R.id.message_text);
        tvDateTime = (TextView) rowView.findViewById(R.id.message_time);

        ChatMsg currentItem = message.get(position);
        String displayName = currentItem.getMessageUser();
        long dateTime = currentItem.getMessageTime();
        String message = currentItem.getMessageText();

        Date d = new Date(dateTime);

        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
        String datetoStr = format.format(d);


        tvDateTime.setText(datetoStr);
        tvDisplayName.setText(displayName);
        tvMessage.setText(message);

        return rowView;


    }

    public CustomAdapter(Context context, int resource, ArrayList<ChatMsg> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        message = objects;
    }
}

