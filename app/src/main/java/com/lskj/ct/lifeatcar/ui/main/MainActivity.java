package com.lskj.ct.lifeatcar.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.base.BaseActivity;
import com.lskj.ct.lifeatcar.network.enums.NetWorkType;
import com.lskj.ct.lifeatcar.otto.OttoHelper;
import com.lskj.ct.lifeatcar.otto.MainMsgEvent;
import com.lskj.ct.lifeatcar.ui.main.fragments.HomeFragment;
import com.lskj.ct.lifeatcar.ui.main.fragments.IOUFragment;
import com.lskj.ct.lifeatcar.ui.main.fragments.InsuranceFragment;
import com.lskj.ct.lifeatcar.ui.main.fragments.MineFragment;
import com.lskj.ct.lifeatcar.utils.BottomNavigationViewHelper;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ViewPager mViewPagerContainer;
    private MVpAdapter mVpAdapter;
    private BottomNavigationView mNavView;
    private List<Fragment> mFragmentLists;
    private String TAG = "MainActivity";
    //    退出记时：第一次点击back
    private long firstClickTime;
    private ActionBar actionBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initActionBar() {
        actionBar = getMineActionBar();
//        不显示返回图标
        actionBar.setDisplayOptions(
                ActionBar.DISPLAY_SHOW_CUSTOM,
                ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP);
        //设置actionBar 的居中标题
        setTitle("车生活");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        OttoHelper.getInstance().register(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
//        if (mViewPagerState != null) {
//            mViewPagerContainer.onRestoreInstanceState(mViewPagerState);
//            mViewPagerState = null;
//        }
    }

    @Override
    protected void initView() {
        mViewPagerContainer = findViewById(R.id.vp_main_container);
        mNavView = findViewById(R.id.bnmv_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //解决optionMenu 不显示图标的问题
        try {
            Class<?> classZ = Class.forName("android.support.v7.view.menu.MenuBuilder");
            try {
                Method method = classZ.getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                try {
                    //调用该方法  传入的值是 true
                    method.invoke(menu, true);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    protected void initData() {
        mFragmentLists = new ArrayList<>();
        mFragmentLists.add(HomeFragment.getInstance());
        mFragmentLists.add(IOUFragment.getInstance());
        mFragmentLists.add(InsuranceFragment.getInstance());
        mFragmentLists.add(MineFragment.getInstance());

        //设置adapter
        mVpAdapter = new MVpAdapter(getSupportFragmentManager(), mFragmentLists);
        mViewPagerContainer.setAdapter(mVpAdapter);
        mViewPagerContainer.setOffscreenPageLimit(3);
        //默认选中第一项
        BottomNavigationViewHelper.disableShiftMode(mNavView);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void setListener() {
        mViewPagerContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mNavView.getMenu().getItem(position).setChecked(true);
                switch (position) {
                    case 0:
                        changeActionBarState("车生活", R.color.colorPrimary, true);
                        break;
                    case 1:
                        changeActionBarState("广发白条", R.color.colorPrimary, true);
                        break;
                    case 2:
                        changeActionBarState("车险", R.color.colorPrimary, true);
                        break;
                    case 3:
                        changeActionBarState(null, R.color.orange_fd9827, false);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_main_home:
                        mViewPagerContainer.setCurrentItem(0, false);
                        break;
                    case R.id.menu_main_intr:
                        mViewPagerContainer.setCurrentItem(1, false);
                        break;
                    case R.id.menu_main_car:
                        mViewPagerContainer.setCurrentItem(2, false);
                        break;
                    case R.id.menu_main_mine:
                        mViewPagerContainer.setCurrentItem(3, false);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }


    @Override
    public void onBackPressed() {
//        * 连续点击back按钮退出app
        long clickTime = System.currentTimeMillis();
        if (clickTime - firstClickTime > 2000) {
            firstClickTime = clickTime;
            Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
            OttoHelper.getInstance().post(onMainMsgEvent());
        } else {
            System.exit(0);
        }
    }

    @Override
    protected boolean needRegisterNetworkChangeObserver() {
        return true;
    }

    @Override
    public void onNetDisconnected() {
        final Snackbar snackbar =
                Snackbar.make(findViewById(R.id.cdl_main),
                        "网络断开", Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        view.setBackgroundColor(Color.RED);
        TextView msgTc = view.findViewById(R.id.snackbar_text);
        msgTc.setTextColor(Color.WHITE);
        msgTc.setGravity(Gravity.CENTER);
        msgTc.setTextSize(16);
        snackbar.setAction("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.GREEN);
        snackbar.show();
    }

    @Override
    public void onNetConnected(NetWorkType networkType) {
    }

    @Produce
    public MainMsgEvent onMainMsgEvent() {
        return new MainMsgEvent(3);
    }

    @Subscribe
    public void onResiverMsg(MainMsgEvent event) {
        Toast.makeText(this, "" + event.position, Toast.LENGTH_SHORT).show();
    }

    @Override

    protected void onDestroy() {
        OttoHelper.getInstance().unregister(this);
        super.onDestroy();
        Log.d(TAG, "onDestroy: unregister");

    }
}
