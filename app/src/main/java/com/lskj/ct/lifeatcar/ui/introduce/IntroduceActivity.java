package com.lskj.ct.lifeatcar.ui.introduce;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.base.BaseActivity;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by thunder on 2018/2/4.
 */

public class IntroduceActivity extends BaseActivity {
    //
    private ViewPager mVpContainer;
    private ViewPagerAdapter viewPagerAdapter;
    private ArrayList<Integer> viewResList;
    private FragmentManager fragmentManager;
    private CirclePageIndicator indicator;


    @Override
    protected void initView() {
        setContentView(R.layout.activity_introduce);
        mVpContainer = findViewById(R.id.vp_introduce);
        indicator = findViewById(R.id.circle_pager_indicator);

    }

    @Override
    protected void initData() {
        fragmentManager = getSupportFragmentManager();
        viewResList = new ArrayList<>();
        viewResList.add(R.layout.intr_view_01);
        viewResList.add(R.layout.intr_view_02);
        viewResList.add(R.layout.intr_view_03);
        viewResList.add(R.layout.intr_view_04);

        viewPagerAdapter = new ViewPagerAdapter(fragmentManager, viewResList);
        mVpContainer.setAdapter(viewPagerAdapter);
        //绑定viewpager 和indicator
        indicator.setViewPager(mVpContainer);

    }

    @Override
    protected void setListener() {
    }
}
