package com.tweshllc.kskdhallmill;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Company4 extends Fragment {

    private TextView target1,target2,stoploss,company1, dateTarget,date1,date2,date3,time1,time2,time3;
    private DatabaseReference targetRef;

    public Company4() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_company4, container, false);

        targetRef= FirebaseDatabase.getInstance().getReference().child("Targets");
        target1=view.findViewById(R.id.C1T1TXT);
        target2=view.findViewById(R.id.C1T2TXT);
        stoploss=view.findViewById(R.id.C1SlTXT);
        company1=view.findViewById(R.id.C1NameTXT);
        targetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                company1.setText(snapshot.child("Company1").getValue().toString());
                target1.setText(snapshot.child("Target1").getValue().toString());
                target2.setText(snapshot.child("Target2_1").getValue().toString());
                stoploss.setText(snapshot.child("StopLoss1").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }
}