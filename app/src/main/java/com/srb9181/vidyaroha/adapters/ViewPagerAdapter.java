package com.srb9181.vidyaroha.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.srb9181.vidyaroha.fragments.Greetings;
import com.srb9181.vidyaroha.fragments.TakePhoto;

/**
 * Created by Rudra on 8/12/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int noOfTab;

    public ViewPagerAdapter(FragmentManager fm, int num) {
        super(fm);
        noOfTab = num;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Greetings();
            case 1:
                return new TakePhoto();
        }
        return null;
    }

    @Override
    public int getCount() {
        return noOfTab;
    }
}
