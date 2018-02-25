package com.lskj.ct.lifeatcar.ui.main.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.lskj.ct.lifeatcar.R;

/**
 * Created by thunder on 2018/2/5.
 */

public class HomeFragment extends Fragment {
    //实现类似viewpager 的广告条控件
    private ViewFlipper mViewFlipper;
    private static final HomeFragment ourInstance = new HomeFragment();
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    public static HomeFragment getInstance() {
        return ourInstance;
    }

    public HomeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_phone_service:
                Toast.makeText(mContext, "电话客服", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_question:
                Toast.makeText(mContext, "问题咨询", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = null;
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mViewFlipper = view.findViewById(R.id.vf_banner);

        return view;
    }

}
