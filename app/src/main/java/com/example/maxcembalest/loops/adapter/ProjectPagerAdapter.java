package com.example.maxcembalest.loops.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.maxcembalest.loops.fragments.MyLoopsFragment;
import com.example.maxcembalest.loops.fragments.SharedLoopsFragment;

/**
 * Created by Chase on 12/12/16.
 */

public class ProjectPagerAdapter extends FragmentPagerAdapter {

    public ProjectPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MyLoopsFragment();
                break;
            case 1:
                fragment = new SharedLoopsFragment();
                break;
            default:
                fragment = new MyLoopsFragment();
                break;
        }

        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "My Loops";
            case 1:
                return "Shared With Me";
        }

        return "My Loops";
    }

    @Override
    public int getCount() {
        return 2;
    }
}
