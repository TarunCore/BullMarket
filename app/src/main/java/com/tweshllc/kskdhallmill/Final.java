package com.tweshllc.kskdhallmill;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class Final extends Fragment {


    private TabLayout mytabLayout;
    private ViewPager myViewPager;
    private TabAdapterCheck myTabAdapter;
    Timer timer;
    Handler handler;
    private CircleIndicator indi;

    public Final() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_final, container, false);
        indi=view.findViewById(R.id.circleIndicator);

        mytabLayout=view.findViewById(R.id.tabTMP);
        myViewPager=view.findViewById(R.id.viewPageTMP);
        myTabAdapter = new TabAdapterCheck(getChildFragmentManager());
        myViewPager.setAdapter(myTabAdapter);
        mytabLayout.setupWithViewPager(myViewPager);
        indi.setViewPager(myViewPager);


        handler=new Handler();
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int i=myViewPager.getCurrentItem();
                        if(i==2){
                            i=0;
                            myViewPager.setCurrentItem(i,true);
                        }
                        else {
                            i++;
                            myViewPager.setCurrentItem(i,true);
                        }
                    }
                });
            }
        },5000,5000);


        return view;
    }


}