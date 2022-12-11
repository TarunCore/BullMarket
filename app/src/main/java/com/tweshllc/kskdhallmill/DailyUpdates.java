package com.tweshllc.kskdhallmill;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class DailyUpdates extends Fragment {

    private ListView listview;
    private DatabaseReference updateRef;
    private ArrayAdapter mArayAdapt;
    private ArrayList<String> myArrayList;

    public DailyUpdates() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_daily_updates, container, false);

        myArrayList = new ArrayList<>();
        mArayAdapt = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,myArrayList);

        listview=view.findViewById(R.id.listView);
        listview.setAdapter(mArayAdapt);

        updateRef= FirebaseDatabase.getInstance().getReference().child("Updates");


        updateRef.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                myArrayList.add(0,snapshot.getValue().toString());
                mArayAdapt.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                mArayAdapt.notifyDataSetChanged();
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

        return view;
    }
}