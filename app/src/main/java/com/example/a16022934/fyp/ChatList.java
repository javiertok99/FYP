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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatList extends Fragment {

    ListView lvChatList;
    ArrayList<String> alChats;
    ArrayList<OneToOne> alIds;
    CustomChatListAdapter caChatList;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    CollectionReference nameRef;
    DatabaseReference messageListRef;
    String uid;
    String senderName;
    String receiverName;
    Boolean done = false;

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
        alIds = new ArrayList<>();
        caChatList = new CustomChatListAdapter(getContext(), R.layout.activity_chat_list_row, alIds);
        lvChatList.setAdapter(caChatList);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        uid = firebaseUser.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        nameRef = firestore.collection("users");
        nameRef.document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Player player = documentSnapshot.toObject(Player.class);
                if (player != null) {
                    senderName = player.getFullName();
                }
            }
        });

        messageListRef = firebaseDatabase.getReference("messages/");

        messageListRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatSolo msg = dataSnapshot.getValue(ChatSolo.class);
                ArrayList<String> items = new ArrayList<>();
                if (msg != null) {
                    alIds.add(new OneToOne(msg.getSenderName(), msg.getReceiverName(), msg.getSenderId(), msg.getReceiverId()));
                    if (alIds.size() > 1) {
                        for (int i = 0; i < alIds.size(); i++) {
                            for (int j = i + 1; j < alIds.size(); j++) {
                                if (!alIds.get(i).getReceiverId().equals(uid) && !alIds.get(i).getSenderId().equals(uid)) {
                                    alIds.remove(i);
                                }
                                if (alIds.size() > j) {
                                    if ((alIds.get(i).getSenderName().equals(alIds.get(j).getSenderName())
                                            && alIds.get(i).getReceiverName().equals(alIds.get(j).getReceiverName())) ||
                                            (alIds.get(i).getReceiverName().equals(alIds.get(j).getSenderName())
                                                    && alIds.get(i).getSenderName().equals(alIds.get(j).getReceiverName()))) {
                                        alIds.remove(j);
                                    }
                                }
                            }
                        }
                    }
                }
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
                toChat.putExtra("class", "list");
                toChat.putExtra("partner", alIds.get(i));
                startActivity(toChat);
            }
        });

        return view;
    }

    public boolean hasDuplicatesInArrayList(ArrayList<ChatSolo> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).getReceiverName().equals(list.get(j).getReceiverName())) {
                    return true;
                }
            }
        }
        return false;
    }

}
