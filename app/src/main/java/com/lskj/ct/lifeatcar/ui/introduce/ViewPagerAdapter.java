package com.lskj.ct.lifeatcar.ui.introduce;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by thunder on 2018/2/5.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Integer> viewResList;

    public ViewPagerAdapter(FragmentManager fm, ArrayList<Integer> viewResList) {
        super(fm);
        this.viewResList = viewResList;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle mBundle = new Bundle();
        mBundle.putInt(IntroduceFragment.KEY_LAYOUT_ID,
                viewResList.get(position));
        return IntroduceFragment.getInstance(mBundle);
    }

    @Override
    public int getCount() {
        return viewResList.size();
    }
}
