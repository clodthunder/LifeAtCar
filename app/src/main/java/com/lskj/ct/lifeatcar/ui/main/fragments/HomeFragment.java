package com.lskj.ct.lifeatcar.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.lskj.ct.lifeatcar.R;

/**
 * Created by thunder on 2018/2/5.
 */

public class HomeFragment extends Fragment {
    //实现类似viewpager 的广告条控件
    private ViewFlipper mViewFlipper;

    private static final HomeFragment ourInstance = new HomeFragment();

    public static HomeFragment getInstance() {
        return ourInstance;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mViewFlipper = view.findViewById(R.id.vf_banner);

        return view;
    }
}
