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

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Company3 extends Fragment {


    private TextView C3target1,C3target2,C3stoploss,company3,dateTarget,date1,date2,date3,time1,time2,time3;;
    private DatabaseReference targetRef;

    public Company3() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_company3, container, false);
        targetRef = FirebaseDatabase.getInstance().getReference().child("Targets");
        C3target1=view.findViewById(R.id.C3target1TXT);
        C3target2=view.findViewById(R.id.C3target2TXT);
        C3stoploss=view.findViewById(R.id.C3stopLossTXT);
        company3=view.findViewById(R.id.companyName3);

        dateTarget=view.findViewById(R.id.targetDate);
        date1=view.findViewById(R.id.date1);
        date2=view.findViewById(R.id.date2);
        date3=view.findViewById(R.id.date3);

        time1=view.findViewById(R.id.time1);
        time2=view.findViewById(R.id.time2);
        time3=view.findViewById(R.id.time3);

        Calendar calendar=Calendar.getInstance();
        Calendar calendar2=Calendar.getInstance();

        targetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                company3.setText(snapshot.child("Company3").getValue().toString());
                C3target1.setText(snapshot.child("C3Target1").getValue().toString());
                C3target2.setText(snapshot.child("C3Target2").getValue().toString());
                C3stoploss.setText(snapshot.child("C3StopLoss").getValue().toString());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yyyy");
        String date= simpleDateFormat.format(calendar.getTime());
        dateTarget.setText(date);
        date1.setText(date);
        date2.setText(date);
        date3.setText(date);

        SimpleDateFormat simpleDateFormat2= new SimpleDateFormat("hh:mm a");
        String time= simpleDateFormat2.format(calendar2.getTime());
        time1.setText(time);
        time2.setText(time);
        time3.setText(time);
        return view;
    }
}