package com.lskj.ct.lifeatcar.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by thunder on 2018/2/5.
 */

public class MVpAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;

    public MVpAdapter(FragmentManager fm) {
        super(fm);
    }

    public MVpAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
