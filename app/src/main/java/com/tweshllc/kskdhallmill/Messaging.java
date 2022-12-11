package com.tweshllc.kskdhallmill;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Messaging extends AppCompatActivity {

    private Toolbar mToolBar;
    private EditText edtMessage;
    private ImageButton btnSend;
    private DatabaseReference chatRef,userRef;
    private FirebaseAuth mAuth;
    private RecyclerView mRecyclerView;
    public static String name;

    private MessageAdapter mMessageAdapter;
    public static ArrayList<String> message,messagePosition,messageId,senderNames;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        btnSend=findViewById(R.id.btnSend);
        edtMessage=findViewById(R.id.edtMessage);
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Messaging.this));
        mMessageAdapter=new MessageAdapter(Messaging.this,message);
        mRecyclerView.setAdapter(mMessageAdapter);
        //if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //}

        mToolbar=findViewById(R.id.myToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setTitle("Community Chat");

        //for white btn
        Drawable back = getResources().getDrawable(R.drawable.ic_back);
        getSupportActionBar().setHomeAsUpIndicator(back);

        message=new ArrayList<>();
        messagePosition=new ArrayList<>();
        messageId=new ArrayList<>();
        senderNames=new ArrayList<>();

        mAuth=FirebaseAuth.getInstance();
        userRef= FirebaseDatabase.getInstance().getReference().child("Users");
        chatRef=FirebaseDatabase.getInstance().getReference().child("Chats");

        getMessages();

        userRef.child(mAuth.getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        name=snapshot.child("Name").getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtMessage.getText().toString().equals("")){
                    Toast.makeText(Messaging.this,"Enter something",Toast.LENGTH_SHORT).show();
                }
                else {
                    HashMap<String,String> map=new HashMap<>();
                    map.put("SenderId",mAuth.getCurrentUser().getUid());
                    map.put("Message",edtMessage.getText().toString());
                    map.put("Sder",name);
                    chatRef.push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //userRef.child(mAuth.getCurrentUser().getUid())
                            //.child("ChatLists").child("All").setValue(ServerValue.TIMESTAMP);
                            edtMessage.setText("");
                        }
                    });
                }
            }
        });


    }

    void getMessages()
    {
        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                if (snapshot.exists())
                {
                    if(snapshot.child("SenderId").getValue().toString().equals(mAuth.getCurrentUser().getUid()))
                    {
                        message.add(snapshot.child("Message").getValue().toString());
                        messagePosition.add("1");
                        messageId.add(snapshot.getKey());

                        mMessageAdapter.notifyItemInserted(message.size()-1);
                        mRecyclerView.smoothScrollToPosition(message.size()-1);
                    }
                    else if (!snapshot.child("SenderId").getValue().toString().equals(mAuth.getCurrentUser().getUid())){
                        message.add(snapshot.child("Message").getValue().toString());
                        messagePosition.add("0");
                        senderNames.add(snapshot.child("Sder").getValue().toString());
                        messageId.add(snapshot.getKey());
                        mMessageAdapter.notifyItemInserted(message.size()-1);
                        mRecyclerView.smoothScrollToPosition(message.size()-1);
                    }
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}