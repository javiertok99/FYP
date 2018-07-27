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
    String name;
    ListView lv;
    ArrayList<ChatMsg> alMessage = new ArrayList<ChatMsg>();
    private ChatMsg message;
    CustomAdapter caMessage = null;
    private int thisTime=0;
    String msguser;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private DatabaseReference messageListRef;
    private CollectionReference nameRef;
    private Player user;

    private long time;
    Object obj;
    private String usernameToDelete = "";




    public ChatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        etMessage = (EditText) view.findViewById(R.id.editTextMessage);
        sendBtn = (FloatingActionButton) view.findViewById(R.id.sendfab);



        lv = (ListView) view.findViewById(R.id.list_of_message);
        alMessage = new ArrayList<ChatMsg>();
        caMessage = new CustomAdapter(getContext(), R.layout.msg, alMessage);
        lv.setAdapter(caMessage);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String uid = firebaseUser.getUid();

        firebaseDatabase = FirebaseDatabase.getInstance();
        nameRef = firebaseFirestore.collection("users");
        messageListRef = firebaseDatabase.getReference("messages/");

        nameRef.document(uid)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                user = task.getResult().toObject(Player.class);
                if(user != null){
                    name = user.getFullName();
                }
            }
        });
        registerForContextMenu(lv);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String msg = etMessage.getText().toString();

                time = new Date().getTime();

                ChatMsg messages = new ChatMsg(msg, name, time);
                messageListRef.push().setValue(messages);
                etMessage.setText(" ");


            }
        });

        messageListRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("ChatFragment", "onChildAdded");
                ChatMsg msg = dataSnapshot.getValue(ChatMsg.class);
                if (msg != null) {
                    msg.setMessageUser(msg.getMessageUser());
                    alMessage.add(msg);
                    caMessage.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                String selectedId = dataSnapshot.getKey();
                ChatMsg msg = dataSnapshot.getValue(ChatMsg.class);

                if (msg != null) {
                    for (int i = 0; i < alMessage.size(); i++) {
                        if (alMessage.get(i).getMessageUser().equals(selectedId)) {
                            msg.setMessageUser(selectedId);
                            alMessage.set(i, msg);
                        }
                    }

                    caMessage.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("MainActivity", "onChildRemoved()");

                String selectedId = dataSnapshot.getKey();
                ChatMsg msg = dataSnapshot.getValue(ChatMsg.class);
                if (msg != null) {
                    for (int i = 0; i < alMessage.size(); i++) {
                        if (alMessage.get(i).getMessageUser().equals(selectedId)) {
                            msg.setMessageUser(selectedId);
                            alMessage.remove(i);
                        }
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
        msguser = alMessage.get(index).getMessageUser();
        Toast.makeText(getContext(), "msg sender: " + msguser + "/ncurrent user: " + name, Toast.LENGTH_LONG).show();
        if (msguser.equals(name)) {

            if (item.getTitle() == "Delete") {

                alMessage.remove(index);
                caMessage.notifyDataSetChanged();
                String id = alMessage.get(index).getMessageUser();

                messageListRef.child(alMessage.get(index).getMessageUser()).removeValue();
            }


        } else if (msguser != name) {
            item.setVisible(false);
            Toast.makeText(getContext(), "You cannot delete other user's msg!", Toast.LENGTH_LONG).show();
        }




        return super.onContextItemSelected(item);
    }


}