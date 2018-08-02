package com.example.a16022934.fyp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatList extends Fragment {

    ListView lvChatList;
    ArrayList<ChatSolo> alChats;
    CustomChatListAdapter caChatList;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    CollectionReference nameRef;
    DatabaseReference messageListRef;
    String uid;
    Boolean contain = false;

    public ChatList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat_list, container, false);
        lvChatList = view.findViewById(R.id.ChatList);
        alChats = new ArrayList<>();
        caChatList = new CustomChatListAdapter(getContext(), R.layout.activity_chat_list_row, alChats);
        lvChatList.setAdapter(caChatList);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseUser.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        nameRef = firestore.collection("users");
        messageListRef = firebaseDatabase.getReference("messages/");

        messageListRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatSolo msg = dataSnapshot.getValue(ChatSolo.class);
                Log.v("senderid", msg.getSenderId());
                Log.v("uid", uid);

                if (msg.getReceiverId().equals(uid)) {
                    if (alChats.size() > 0) {
                        for (int i = 0; i < alChats.size(); i++) {
                            if(!alChats.get(i).getSenderId().equals(uid) || !alChats.get(i).getReceiverId().equals(uid)){
                                alChats.add(msg);
                            }
                        }
                    } else {
                        alChats.add(msg);
                    }
                }
                Log.v("alsize", alChats.size() + "");
                caChatList.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        lvChatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent toChat = new Intent(getActivity(), BottomNavBar.class);
                toChat.putExtra("type", "chat");
                toChat.putExtra("partner", alChats.get(i));
                startActivity(toChat);
            }
        });

        return view;
    }

}
