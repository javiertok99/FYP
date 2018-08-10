package com.example.a16022934.fyp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class ChatFragment extends Fragment {

    EditText etMessage;
    FloatingActionButton sendBtn;

    ListView lv;
    ArrayList<ChatSolo> alMessage = new ArrayList<>();
    CustomAdapter caMessage = null;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private OneToOne chat;
    private Player player;
    private String uid;
    private long time;

    private CollectionReference nameRef;
    private DatabaseReference messageListRef;

    String receiverName;
    String senderName;
    String receiverId;
    String msguser;

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String chatType = bundle.getString("chatString");
            if (chatType != null) {
                if (chatType.equals("player")) {
                    receiverName = "";
                    receiverId = "";
                    senderName = "";
                    uid = "";
                    player = (Player) bundle.getSerializable("newSoloChat");
                    if (player != null) {

                        etMessage = (EditText) view.findViewById(R.id.editTextMessage);
                        sendBtn = (FloatingActionButton) view.findViewById(R.id.sendfab);
                        lv = (ListView) view.findViewById(R.id.list_of_message);

                        alMessage = new ArrayList<ChatSolo>();
                        caMessage = new CustomAdapter(getContext(), R.layout.msg, alMessage);

                        lv.setAdapter(caMessage);

                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseUser = firebaseAuth.getCurrentUser();
                        uid = firebaseUser.getUid();
                        receiverId = player.getUser_id();
                        receiverName = player.getFullName();

                        firebaseDatabase = FirebaseDatabase.getInstance();
                        nameRef = firebaseFirestore.collection("users");
                        nameRef.document(uid).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                Player sendPlayer = documentSnapshot.toObject(Player.class);
                                if (sendPlayer != null) {
                                    senderName = sendPlayer.getFullName();
                                }
                            }
                        });
                        messageListRef = firebaseDatabase.getReference("messages/");

                        sendBtn.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onClick(View view) {
                                receiverId = player.getUser_id();
                                String msg = etMessage.getText().toString();
                                time = new Date().getTime();
                                String key = messageListRef.push().getKey();
                                ChatSolo messages = new ChatSolo(key, senderName, receiverName, uid, receiverId, msg, time);
                                messageListRef.child(key).setValue(messages);
                                etMessage.setText(" ");
                                scrollMyListViewToBottom();
                            }
                        });

                        messageListRef.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Log.i("ChatFragment", "onChildAdded");
                                ChatSolo msg = dataSnapshot.getValue(ChatSolo.class);
                                if (msg != null) {
                                    if (msg.getReceiverId().equals(receiverId) || msg.getSenderId().equals(receiverId)) {
                                        if (msg.getReceiverId().equals(uid) || msg.getSenderId().equals(uid)) {
                                            alMessage.add(msg);
                                            caMessage.notifyDataSetChanged();
                                            scrollMyListViewToBottom();
                                        }

                                    }
                                }
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                                String selectedId = dataSnapshot.getKey();
                                ChatSolo msg = dataSnapshot.getValue(ChatSolo.class);

                                if (msg != null) {
                                    for (int i = 0; i < alMessage.size(); i++) {
//                        if (alMessage.get(i).getMessageUser().equals(selectedId)) {
//                            msg.setMessageUser(selectedId);
//                            alMessage.set(i, msg);
//                        }
                                    }

                                    caMessage.notifyDataSetChanged();
                                }

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                                Log.i("MainActivity", "onChildRemoved()");

                                String selectedId = dataSnapshot.getKey();
                                ChatSolo msg = dataSnapshot.getValue(ChatSolo.class);
                                if (msg != null) {
                                    for (int i = 0; i < alMessage.size(); i++) {
//                        if (alMessage.get(i).getMessageUser().equals(selectedId)) {
//                            msg.setMessageUser(selectedId);
//                            alMessage.remove(i);
//                        }
                                    }
                                    caMessage.notifyDataSetChanged();
                                }

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        registerForContextMenu(lv);
                    }

                } else if (chatType.equals("soloChat")) {
                    chat = (OneToOne) bundle.getSerializable("soloChat");
                    if (chat != null) {
                        receiverName = "";
                        receiverId = "";
                        senderName = "";
                        uid = "";
                        etMessage = (EditText) view.findViewById(R.id.editTextMessage);
                        sendBtn = (FloatingActionButton) view.findViewById(R.id.sendfab);
                        lv = (ListView) view.findViewById(R.id.list_of_message);

                        alMessage = new ArrayList<ChatSolo>();

                        caMessage = new CustomAdapter(getContext(), R.layout.msg, alMessage);

                        lv.setAdapter(caMessage);

                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseUser = firebaseAuth.getCurrentUser();
                        uid = firebaseUser.getUid();

                        if (uid.equals(chat.getSenderId())) {
                            senderName = chat.getSenderName();
                            receiverName = chat.getReceiverName();
                            receiverId = chat.getReceiverId();
                        } else {
                            senderName = chat.getReceiverName();
                            receiverName = chat.getSenderName();
                            receiverId = chat.getSenderId();
                        }

                        firebaseDatabase = FirebaseDatabase.getInstance();
                        nameRef = firebaseFirestore.collection("users");
                        messageListRef = firebaseDatabase.getReference("messages/");

                        sendBtn.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void onClick(View view) {
                                String msg = etMessage.getText().toString();
                                time = new Date().getTime();
                                String key = messageListRef.push().getKey();
                                ChatSolo messages = new ChatSolo(key, senderName, receiverName, uid, receiverId, msg, time);
                                messageListRef.child(key).setValue(messages);
                                etMessage.setText(" ");
                                scrollMyListViewToBottom();
                            }
                        });

                        messageListRef.addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                                Log.i("ChatFragment", "onChildAdded");
                                ChatSolo msg = dataSnapshot.getValue(ChatSolo.class);
                                if (msg != null) {
                                    if (msg.getReceiverId().equals(receiverId) || msg.getSenderId().equals(receiverId)) {
                                        if (msg.getReceiverId().equals(uid) || msg.getSenderId().equals(uid)) {
                                            alMessage.add(msg);
                                            caMessage.notifyDataSetChanged();
                                            scrollMyListViewToBottom();
                                        }

                                    }
                                }
                            }

                            @Override
                            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                                String selectedId = dataSnapshot.getKey();
                                ChatSolo msg = dataSnapshot.getValue(ChatSolo.class);

                                if (msg != null) {
                                    for (int i = 0; i < alMessage.size(); i++) {
//                        if (alMessage.get(i).getMessageUser().equals(selectedId)) {
//                            msg.setMessageUser(selectedId);
//                            alMessage.set(i, msg);
//                        }
                                    }
                                    caMessage.notifyDataSetChanged();
                                }

                            }

                            @Override
                            public void onChildRemoved(DataSnapshot dataSnapshot) {
                                Log.i("MainActivity", "onChildRemoved()");

                                String selectedId = dataSnapshot.getKey();
                                ChatSolo msg = dataSnapshot.getValue(ChatSolo.class);
                                if (msg != null) {
                                    for (int i = 0; i < alMessage.size(); i++) {
//                        if (alMessage.get(i).getMessageUser().equals(selectedId)) {
//                            msg.setMessageUser(selectedId);
//                            alMessage.remove(i);
//                        }
                                    }
                                    caMessage.notifyDataSetChanged();
                                }

                            }

                            @Override
                            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                        registerForContextMenu(lv);
                    }
                }
            }
        }
        return view;
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Are you sure you want to delete?");
        menu.add(0, v.getId(), 0, "Delete");
    }

    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        msguser = alMessage.get(index).getSenderId();
//        Toast.makeText(getContext(), "msg sender: " + msguser + "/ncurrent user: " + name, Toast.LENGTH_LONG).show();
        if (msguser.equals(uid)) {

            if (item.getTitle() == "Delete") {
                ChatSolo msgToDel = alMessage.get(index);
                messageListRef.child(msgToDel.getMsgID()).removeValue();
                alMessage.remove(index);
                caMessage.notifyDataSetChanged();
            }
        } else if (!msguser.equals(uid)) {
            item.setVisible(false);
            Toast.makeText(getContext(), "You cannot delete other user's msg!", Toast.LENGTH_LONG).show();
        }
        return super.onContextItemSelected(item);
    }

    private void scrollMyListViewToBottom() {
        lv.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                lv.setSelection(caMessage.getCount() - 1);
            }
        });
    }

}