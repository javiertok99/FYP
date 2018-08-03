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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CustomChatListAdapter extends ArrayAdapter<OneToOne> {
    private Context context;
    private ArrayList<OneToOne> message;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference userRef = db.collection("users");

    public CustomChatListAdapter(Context context, int resource, ArrayList<OneToOne> objects) {
        super(context, resource, objects);
        message = objects;
        this.context = context;
    }

    class ViewHolder {
        ImageView ivChatPic;
        TextView tvChatName;
        int position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final CustomChatListAdapter.ViewHolder view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_chat_list_row, parent, false);
            view = new CustomChatListAdapter.ViewHolder();
            view.tvChatName = convertView.findViewById(R.id.tvChatName);
            view.ivChatPic = convertView.findViewById(R.id.ivChatPic);
            final String currentChat = message.get(position).getSenderName();
            convertView.setTag(view);
        } else {
            view = (CustomChatListAdapter.ViewHolder) convertView.getTag();
        }
        OneToOne current = message.get(position);
        if(current.getSenderId().equals(user.getUid())){
            view.tvChatName.setText(current.getReceiverName());
        }else{
            view.tvChatName.setText(current.getSenderName());
        }


        view.position = position;
        return convertView;

    }
}

