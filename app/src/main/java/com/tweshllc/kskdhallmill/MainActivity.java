package com.tweshllc.kskdhallmill;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    private TabLayout mytabLayout;
    private ViewPager myViewPager;
    private TabAdapter myTabAdapter;
    private FirebaseAuth mAuth;
    private TextView profileText,appName;

    private DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileText=findViewById(R.id.profileText);  //4-10-21
        appName=findViewById(R.id.appName);  //4-10-21

        mToolBar=findViewById(R.id.myToolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mytabLayout=findViewById(R.id.tabLayout);
        myViewPager=findViewById(R.id.viewPager);
        myTabAdapter = new TabAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabAdapter);
        mytabLayout.setupWithViewPager(myViewPager);
        mAuth=FirebaseAuth.getInstance();
        userRef= FirebaseDatabase.getInstance().getReference().child("Users");

        //4-10-21
        if (mAuth.getCurrentUser()!=null){
            profileText.setVisibility(View.VISIBLE);
            appName.setTextSize(24);
            userRef.child(mAuth.getCurrentUser().getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            profileText.setText(snapshot.child("Name").getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


//        if (item.getItemId()==R.id.updateIt){
//            Intent intent  = new Intent(MainActivity.this,SetTargets.class);
//            startActivity(intent);
//        }
        if (item.getItemId()==R.id.profile){
            Intent intent  = new Intent(MainActivity.this,About.class);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.chat){
            if (mAuth.getCurrentUser()!=null){
                Intent intent  = new Intent(MainActivity.this,Messaging.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this,"Please Sign In",Toast.LENGTH_SHORT).show();
                Intent intent  = new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }

        }
        return super.onOptionsItemSelected(item);
    }
}