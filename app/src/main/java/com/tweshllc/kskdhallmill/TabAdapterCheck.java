package com.tweshllc.kskdhallmill;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabAdapterCheck extends FragmentPagerAdapter {
    public TabAdapterCheck(@NonNull FragmentManager fm) {
        super(fm);
    }

    //TO ADD NEW STYLE FRAGMENT SWAP
    //1. Enable below code
    //2. Change margin top from 55dp to 10dp in all fragment,
    //         CardView Height in Final fragment to 435dp, and CircleIndicator marginTop to 394dp
    //3. Appbar visibility gone
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:return new CompanyOne();
            case 1:return new Companytwo();
            case 2:return new Company3();
            //case 3:return new Company4();
            //case 4:return new TradeFragment();
            default:return  null;
        }
    }


    @Override
    public int getCount() {
        return 3;
    }


    //TO ADD OLD STYLE FRAGMENT SWAP
    //1. Enable below code
    //2. Change margin top from 10dp to 55dp in all fragment,
    //         CardView Height in Final fragment from 435dp to 480dp, and CircleIndicator from 394dp marginTop to 439dp
    //Appbar visibility visible
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0:return "Stock 1";
            case 1:return "Stock 2";
            case 2:return "Stock 3";
            //case 3:return "Stock 4";
            //case 3:return "Stock 4";
            //case 4:return "Try";
            default:return null;
        }
    }
}
