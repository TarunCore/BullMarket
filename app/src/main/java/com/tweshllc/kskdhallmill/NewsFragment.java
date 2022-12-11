package com.tweshllc.kskdhallmill;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class NewsFragment extends Fragment {

    private DatabaseReference infoRef;
    private TextView newstext,announceTXT;
    private ImageView imageView1,imageView2;
    public static String newsLink,announceLink;


    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_news, container, false);


        infoRef = FirebaseDatabase.getInstance().getReference().child("Info");
        newstext = view.findViewById(R.id.newsTXT);
        announceTXT=view.findViewById(R.id.announcementTXT);
        imageView1=view.findViewById(R.id.onlineImg);
        imageView2=view.findViewById(R.id.onlineImg2);

        String t="True";

        infoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newstext.setText(snapshot.child("News").getValue().toString());
                announceTXT.setText(snapshot.child("Announcement").getValue().toString());
                announceLink=snapshot.child("AnnounceLink").getValue().toString();
                newsLink=snapshot.child("NewsLink").getValue().toString();

                Glide.with(getActivity()).load(newsLink).into(imageView1);
                Glide.with(getActivity()).load(announceLink).into(imageView2);
                if (snapshot.exists()){
                    if (snapshot.child("NewsV").getValue().toString().equals("True")){
                        imageView1.setVisibility(View.VISIBLE);
                        imageView2.setVisibility(View.VISIBLE);
                    }
                    else {
                        imageView1.setVisibility(View.GONE);
                        imageView2.setVisibility(View.GONE);
                    }

                }

                //newstext.setText(snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }



}