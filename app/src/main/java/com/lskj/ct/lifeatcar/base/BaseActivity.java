package com.lskj.ct.lifeatcar.base;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.network.enums.NetWorkType;
import com.lskj.ct.lifeatcar.network.observer.NetStateChangeObserver;
import com.lskj.ct.lifeatcar.network.receiver.NetStateChangeReceiver;

/**
 * Created by thunder on 2018/2/4.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements NetStateChangeObserver {
    private ActionBar mActionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customer = inflater.inflate(R.layout.view_customer_ab, null);
            //actionBar 显示放回按钮 和自定义view
            mActionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP,
                    ActionBar.DISPLAY_SHOW_CUSTOM);
            mActionBar.setCustomView(customer,
                    new ActionBar.LayoutParams(android.app.ActionBar.LayoutParams.WRAP_CONTENT,
                            android.app.ActionBar.LayoutParams.MATCH_PARENT,
                            Gravity.CENTER));
//            去除actionbar 底部的阴影
            mActionBar.setElevation(0);
            mActionBar.setHomeButtonEnabled(true);

        }
        initView();
        initActionBar();
        initData();
        setListener();
    }

    /**
     * @param title        actionbar title
     * @param color        actionbar 的北京颜色
     * @param isShowTitile 是否显示title
     */
    protected void changeActionBarState(String title, int color, boolean isShowTitile) {
        setTitle(title == null ? "" : title);
        mActionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(color)));
        shouldShowTitle(isShowTitile);
    }

    /**
     * 设置居中的标题
     *
     * @param title
     */
    protected void setTitle(String title) {
        View view = mActionBar.getCustomView();
        TextView tvTitle = view.findViewById(R.id.tv_ab_title);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText(title);
    }

    /**
     * 设置是否显示标题
     */
    protected void shouldShowTitle(Boolean shouldShow) {
        View view
                = mActionBar.getCustomView();
        TextView mTitle = view.findViewById(R.id.tv_ab_title);
        mTitle.setVisibility(shouldShow ? View.VISIBLE : View.INVISIBLE);
    }


    /**
     * 获取baseactivity 中的
     *
     * @return
     */
    protected ActionBar getMineActionBar() {
        return mActionBar;
    }

    /**
     * 初始化view
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化actionBar
     */
    protected abstract void initActionBar();

    /**
     * 初始化view 组件
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置监听
     */
    protected abstract void setListener();


    @Override
    protected void onResume() {
        super.onResume();
        //如果activity 重写了needRegisterNetworkChangeObserver
        // 并且返回的是true 则给当前的activity注册观察者
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.registerObserver(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.unregisterObserver(this);
        }
    }

    /**
     * 是否需要注册网络变化的Observer,如果不需要监听网络变化,
     * 则返回false;否则返回true.默认返回false
     */
    protected boolean needRegisterNetworkChangeObserver() {
        return false;
    }

    /**
     * 网络断开回调处理的地方
     */
    @Override
    public void onNetDisconnected() {

    }

    /**
     * 网络连接成功 回调处理的地方
     *
     * @param networkType
     */
    @Override
    public void onNetConnected(NetWorkType networkType) {

    }
}
