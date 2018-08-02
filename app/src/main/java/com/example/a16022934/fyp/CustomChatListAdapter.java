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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class CustomChatListAdapter extends ArrayAdapter<ChatSolo> {
    private Context context;
    private ArrayList<ChatSolo> message;

    public CustomChatListAdapter(Context context, int resource, ArrayList<ChatSolo> objects) {
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
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_chat_list_row, parent, false);
            view = new CustomChatListAdapter.ViewHolder();
            view.tvChatName = convertView.findViewById(R.id.tvChatName);
            view.ivChatPic = convertView.findViewById(R.id.ivChatPic);
            final ChatSolo currentChat = message.get(position);
            convertView.setTag(view);
        }else{
            view = (CustomChatListAdapter.ViewHolder) convertView.getTag();
        }
        ChatSolo currentChat = message.get(position);
        view.tvChatName.setText(currentChat.getSenderName());

        view.position = position;
        return convertView;

    }
}

