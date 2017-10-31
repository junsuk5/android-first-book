package com.example.screenslideexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by junsuk on 2017. 6. 28..
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mData;

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);

        mData = new ArrayList<>();
        mData.add(new ColorFragment());
        mData.add(new ItemFragment());
        mData.add(new PlusOneFragment());

    }

    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position + " 번째";
    }
}
