package com.lskj.ct.lifeatcar.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lskj.ct.lifeatcar.R;

/**
 * Created by thunder on 2018/2/6.
 * 白条
 */

public class IOUFragment extends Fragment {


    private static final IOUFragment ourInstance = new IOUFragment();

    public static IOUFragment getInstance() {
        return ourInstance;
    }

    public IOUFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_iou, container, false);
        return view;
    }
}
