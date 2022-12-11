package com.tweshllc.kskdhallmill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Set;

public class SetTargets extends AppCompatActivity {

    private Button btn1,btn2,btnStopLoss,btnNews,btnAnnounce,btnCom1,btnLink1,btnLink2;
    private EditText target1,target2,stopLoss,news,announce,edtCompany,edtLink1,edtLink2;
    private DatabaseReference targetRef,infoRef;
    private Button btnC2Tar1,btnC2Tar2,btnC2Stop,btnC3Tar1,btnC3Tar2,btnC3Stop,btnComp2,btnComp3;
    private EditText edtC2Tar1,edtC2Tar2,edtC2Stop,edtC3Tar1,edtC3Tar2,edtC3Stop,edtComp2,edtComp3;
    private Button btnNewsV,btnNewsD,btnAnnounceV,btnAnnounceD;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_targets);
        btn1=findViewById(R.id.btntarget1);
        btn2=findViewById(R.id.btntarget2);
        btnStopLoss=findViewById(R.id.btnStopLoss);
        target1=findViewById(R.id.edtTarget1);
        target2=findViewById(R.id.edtTarget2);
        stopLoss=findViewById(R.id.edtstop);
        targetRef=FirebaseDatabase.getInstance().getReference().child("Targets");
        infoRef=FirebaseDatabase.getInstance().getReference().child("Info");
        news=findViewById(R.id.edtNews);
        announce=findViewById(R.id.edtAnnounce);
        btnNews=findViewById(R.id.btnNews);
        btnAnnounce=findViewById(R.id.btnAnnounce);

        btnCom1=findViewById(R.id.btnCompany1);
        edtCompany=findViewById(R.id.edtCompany1);

        edtLink1=findViewById(R.id.edtNewsLink);
        btnLink1=findViewById(R.id.btnNewsLink);
        edtLink2=findViewById(R.id.edtAnnounceLink);
        btnLink2=findViewById(R.id.btnAnnounceLink);
        btnNewsV=findViewById(R.id.btnNewsLinkV);
        btnNewsD=findViewById(R.id.btnNewsLinkD);


        btnComp2=findViewById(R.id.btnCompany2);
        btnC2Tar1=findViewById(R.id.btnC2target1);
        btnC2Tar2=findViewById(R.id.btnC2target2);
        btnC2Stop=findViewById(R.id.btnC2StopLoss);

        edtComp2=findViewById(R.id.edtCompany2);
        edtC2Tar1=findViewById(R.id.edtC2Target1);
        edtC2Tar2=findViewById(R.id.edtC2Target2);
        edtC2Stop=findViewById(R.id.edtC2stop);

        btnComp3=findViewById(R.id.btnCompany3);
        btnC3Tar1=findViewById(R.id.btnC3target1);
        btnC3Tar2=findViewById(R.id.btnC3target2);
        btnC3Stop=findViewById(R.id.btnC3StopLoss);

        edtComp3=findViewById(R.id.edtCompany3);
        edtC3Tar1=findViewById(R.id.edtC3Target1);
        edtC3Tar2=findViewById(R.id.edtC3Target2);
        edtC3Stop=findViewById(R.id.edtC3stop);


        btnCom1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCompany.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
//                    userRef.setValue(edtValue.getText().toString());
                    targetRef.child("Company1").setValue(edtCompany.getText().toString());

                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (target1.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
//                    userRef.setValue(edtValue.getText().toString());
                    targetRef.child("Target1").setValue(target1.getText().toString());
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (target2.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
//                    userRef.setValue(edtValue.getText().toString());
                    targetRef.child("Target2_1").setValue(target2.getText().toString());
                }
            }
        });
        btnStopLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stopLoss.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
//                    userRef.setValue(edtValue.getText().toString());
                    targetRef.child("StopLoss1").setValue(stopLoss.getText().toString());
                }
            }
        });
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (news.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    infoRef.child("News").setValue(news.getText().toString());
                    //newsRef.setValue(news.getText().toString());
                }
            }
        });
        btnAnnounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (announce.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    infoRef.child("Announcement").setValue(announce.getText().toString());
                   //announceRef.setValue(announce.getText().toString());
                }
            }
        });


        btnComp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtComp2.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    targetRef.child("Company2").setValue(edtComp2.getText().toString());
                }
            }
        });
        btnComp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtComp3.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    targetRef.child("Company3").setValue(edtComp3.getText().toString());
                }
            }
        });
        btnC2Tar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtC2Tar1.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    targetRef.child("C2Target1").setValue(edtC2Tar1.getText().toString());
                }
            }
        });
        btnC3Tar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtC3Tar1.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    targetRef.child("C3Target1").setValue(edtC3Tar1.getText().toString());
                }
            }
        });
        btnC2Tar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtC2Tar2.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    targetRef.child("C2Target2").setValue(edtC2Tar2.getText().toString());
                }
            }
        });
        btnC3Tar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtC3Tar2.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    targetRef.child("C3Target2").setValue(edtC3Tar2.getText().toString());
                }
            }
        });
        btnC2Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtC2Stop.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    targetRef.child("C2StopLoss").setValue(edtC2Stop.getText().toString());
                }
            }
        });
        btnC3Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtC3Stop.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    targetRef.child("C3StopLoss").setValue(edtC3Stop.getText().toString());
                }
            }
        });

        btnLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtLink1.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    infoRef.child("NewsLink").setValue(edtLink1.getText().toString());
                }
            }
        });
        btnLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtLink2.getText().toString().equals(""))
                {
                    pushToast();
                }
                else {
                    infoRef.child("AnnounceLink").setValue(edtLink2.getText().toString());
                }
            }
        });
        btnNewsV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoRef.child("NewsV").setValue("True");
                Toast.makeText(SetTargets.this, "Visible",
                        Toast.LENGTH_SHORT).show();
            }
        });
        btnNewsD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoRef.child("NewsV").setValue("False");
                Toast.makeText(SetTargets.this, "Disabled",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void pushToast() {
        Toast.makeText(SetTargets.this, "Enter Something",
                Toast.LENGTH_SHORT).show();
    }
}