package com.lskj.ct.lifeatcar.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.base.BaseActivity;
import com.lskj.ct.lifeatcar.network.enums.NetWorkType;
import com.lskj.ct.lifeatcar.ui.main.fragments.HomeFragment;
import com.lskj.ct.lifeatcar.ui.main.fragments.IOUFragment;
import com.lskj.ct.lifeatcar.ui.main.fragments.InsuranceFragment;
import com.lskj.ct.lifeatcar.ui.main.fragments.MineFragment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private ViewPager mViewPagerContainer;
    private MVpAdapter mVpAdapter;
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragmentLists;
    private String TAG = "MainActivity";
    //    退出记时：第一次点击back
    private long firstClickTime;

    @Override
    protected void initActionBar() {
//        设置actionbar
        ActionBar actionBar = getSupportActionBar();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customer = inflater.inflate(R.layout.view_customer_actionbar, null);
        ((TextView) customer.findViewById(R.id.ab_cus_title)).setText("车生活");
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(customer,
                new ActionBar.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT,
                        android.app.ActionBar.LayoutParams.MATCH_PARENT,
                        Gravity.CENTER));
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        mViewPagerContainer = findViewById(R.id.vp_main_container);
        mRadioGroup = findViewById(R.id.rg_main_container);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_phone_service:
                Toast.makeText(this, "电话客服", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_question:
                Toast.makeText(this, "问题咨询", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        mViewPagerContainer.setCurrentItem(0);
        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
    }

    @Override
    protected void setListener() {
        mViewPagerContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((RadioButton) mRadioGroup.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_home:
                        mViewPagerContainer.setCurrentItem(0, false);
                        break;
                    case R.id.rbtn_iou:
                        mViewPagerContainer.setCurrentItem(1, false);
                        break;
                    case R.id.rbtn_insurance:
                        mViewPagerContainer.setCurrentItem(2, false);
                        break;
                    case R.id.rbtn_mine:
                        mViewPagerContainer.setCurrentItem(3, false);
                        break;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
//        * 连续点击back按钮退出app
        if (System.currentTimeMillis() - firstClickTime > 2000) {
            firstClickTime = System.currentTimeMillis();
            Toast.makeText(this, "再次点击退出", Toast.LENGTH_SHORT).show();
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
}
