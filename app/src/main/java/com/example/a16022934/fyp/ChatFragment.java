package com.example.a16022934.fyp;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;


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
    private ChatSolo chat;
    private String uid;
    private long time;

    private CollectionReference nameRef;
    private DatabaseReference messageListRef;

    String receiverName;
    String senderName;
    String receiverId;
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
            chat = (ChatSolo) bundle.getSerializable("soloChat");
            if (chat != null) {

                etMessage = (EditText) view.findViewById(R.id.editTextMessage);
                sendBtn = (FloatingActionButton) view.findViewById(R.id.sendfab);
                lv = (ListView) view.findViewById(R.id.list_of_message);

                alMessage = new ArrayList<ChatSolo>();
                caMessage = new CustomAdapter(getContext(), R.layout.msg, alMessage);

                lv.setAdapter(caMessage);

                firebaseAuth = FirebaseAuth.getInstance();
                firebaseUser = firebaseAuth.getCurrentUser();
                uid = firebaseUser.getUid();

                if(uid.equals(chat.getSenderId())){
                    senderName = chat.getSenderName();
                    receiverName = chat.getReceiverName();
                    receiverId = chat.getReceiverId();
                }else{
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
                        ChatSolo messages = new ChatSolo(senderName, receiverName, uid, receiverId, msg, time);
                        messageListRef.push().setValue(messages);
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
                            if(msg.getReceiverId().equals(receiverId) || msg.getSenderId().equals(receiverId)){
                                if(msg.getReceiverId().equals(uid) || msg.getSenderId().equals(uid)){
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

        return view;
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select Option");
        menu.add(0, v.getId(), 0, "Delete");
        menu.add(0, v.getId(), 0, "Copy");
    }

    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
//        msguser = alMessage.get(index).getMessageUser();
//        Toast.makeText(getContext(), "msg sender: " + msguser + "/ncurrent user: " + name, Toast.LENGTH_LONG).show();
//        if (msguser.equals(senderName)) {
//
//            if (item.getTitle() == "Delete") {
//
//                alMessage.remove(index);
//                caMessage.notifyDataSetChanged();
//                  String id = alMessage.get(index).getMessageUser();
//
//                  messageListRef.child(alMessage.get(index).getMessageUser()).removeValue();
//            }
//
//
//        } else if (msguser != senderName) {
//            item.setVisible(false);
//            Toast.makeText(getContext(), "You cannot delete other user's msg!", Toast.LENGTH_LONG).show();
//        }


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