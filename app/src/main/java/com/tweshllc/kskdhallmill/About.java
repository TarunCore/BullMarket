package com.tweshllc.kskdhallmill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class About extends AppCompatActivity {

    private ImageView yt,tele,insta;
    private TextView username,role,textDev;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef,infoRef;
    private Button logout;
    private Toolbar mToolbar;
    public static String instaLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        mToolbar=findViewById(R.id.aboutToolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //for white btn
        //Drawable back = getResources().getDrawable(R.drawable.ic_back);
        //getSupportActionBar().setHomeAsUpIndicator(back);
        textDev=findViewById(R.id.textView6);
        yt=findViewById(R.id.yt);
        tele=findViewById(R.id.btnTele);
        insta=findViewById(R.id.btnInsta);
        username=findViewById(R.id.username);
        logout=findViewById(R.id.btnLogout);
        role=findViewById(R.id.role);
        mAuth=FirebaseAuth.getInstance();
        userRef= FirebaseDatabase.getInstance().getReference().child("Users");
        infoRef = FirebaseDatabase.getInstance().getReference().child("Info");

        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://youtube.com/channel/UC7ASVNWjdqxWTuWp4PWLkSw");
            }
        });
        tele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://t.me/gs1998");
            }
        });

        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        instaLink=snapshot.child("InstaLink").getValue().toString();
                        //Toast.makeText(About.this, instaLink, Toast.LENGTH_SHORT).show();
                        gotoUrl(instaLink);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        textDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if (mAuth.getCurrentUser()!=null)
        {
            userRef.child(mAuth.getCurrentUser().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            username.setText(snapshot.child("Name").getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
            logout.setVisibility(View.VISIBLE);
            role.setVisibility(View.VISIBLE);
        }
        else {
            logout.setVisibility(View.GONE);
            role.setVisibility(View.INVISIBLE);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(About.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void gotoUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
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