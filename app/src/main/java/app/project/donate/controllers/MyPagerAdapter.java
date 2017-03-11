package app.project.donate.controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import app.project.donate.model.Aakash;
import app.project.donate.model.Aman;
import app.project.donate.model.Archit;
import app.project.donate.model.Arup;
import app.project.donate.model.Harsh;

/**
 * Created by ARCHIT SAMNOL on 3/3/2017.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 5;

    public MyPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show Aakash
                return Aakash.newInstance();
            case 1: // Fragment # 1 - This will show Aman
                return Aman.newInstance();
            case 2: // Fragment # 2 - This will show Archit
                return Archit.newInstance();
            case 3: // Fragment # 3 - This will show Arup
                return Arup.newInstance();
            case 4: // Fragment # 4 - This will show Harsh
                return Harsh.newInstance();
            default:
                return null;
        }
    }

}
