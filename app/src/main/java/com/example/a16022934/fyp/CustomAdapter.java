package com.example.a16022934.fyp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<ChatSolo> message;
    TextView tvDisplayName, tvDateTime, tvMessage;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = firebaseAuth.getCurrentUser();
    private String uid = user.getUid();

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ChatSolo currentItem = message.get(position);
        View rowView;
        if(currentItem.getSenderId().equals(uid)){
            rowView = inflater.inflate(R.layout.lv_chat_send, parent, false);
        }else{
            rowView = inflater.inflate(R.layout.lv_chat_receive, parent, false);
        }
        tvDisplayName = (TextView) rowView.findViewById(R.id.message_user);
        tvMessage = (TextView) rowView.findViewById(R.id.message_text);
        tvDateTime = (TextView) rowView.findViewById(R.id.message_time);

        String displayName = currentItem.getSenderName();
        long dateTime = currentItem.getTimeStamp();
        String message = currentItem.getMsg();

        Date d = new Date(dateTime);

        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
        String datetoStr = format.format(d);

        tvDateTime.setText(datetoStr);
        tvDisplayName.setText(displayName);
        tvMessage.setText(message);

        return rowView;


    }

    public CustomAdapter(Context context, int resource, ArrayList<ChatSolo> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        message = objects;
    }
}

