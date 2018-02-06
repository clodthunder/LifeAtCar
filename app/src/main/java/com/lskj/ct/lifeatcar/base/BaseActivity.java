package com.lskj.ct.lifeatcar.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lskj.ct.lifeatcar.network.enums.NetWorkType;
import com.lskj.ct.lifeatcar.network.observer.NetStateChangeObserver;
import com.lskj.ct.lifeatcar.network.receiver.NetStateChangeReceiver;

/**
 * Created by thunder on 2018/2/4.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements NetStateChangeObserver {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        setListener();
    }

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
