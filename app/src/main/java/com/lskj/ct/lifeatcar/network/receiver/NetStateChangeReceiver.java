package com.lskj.ct.lifeatcar.network.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;

import com.lskj.ct.lifeatcar.network.NetWorkUtil;
import com.lskj.ct.lifeatcar.network.enums.NetWorkType;
import com.lskj.ct.lifeatcar.network.observer.NetStateChangeObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thunder on 2018/2/4.
 * 网络变化广播接收者
 */

public class NetStateChangeReceiver extends BroadcastReceiver {


    public NetStateChangeReceiver() {
    }

    /**
     * 静态内部类实现的单例模式
     */
    private static class InstanceHolder {
        private static final NetStateChangeReceiver INSTANCE = new NetStateChangeReceiver();
    }

    /**
     * 管理多个网络状态变化的观察者
     */
    private List<NetStateChangeObserver> mObservers = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            NetWorkType networkType = NetWorkUtil.getNetWorkType(context);
            notifyObservers(networkType);
        }
    }

    /**
     * 注册网络监听
     */
    public static void registerReceiver(@NonNull Context context) {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(InstanceHolder.INSTANCE, intentFilter);
    }


    /**
     * 取消网络监听
     */
    public static void unregisterReceiver(@NonNull Context context) {
        context.unregisterReceiver(InstanceHolder.INSTANCE);
    }


    /**
     * 通知观察者
     *
     * @param networkType
     */
    private void notifyObservers(NetWorkType networkType) {
        if (networkType == NetWorkType.NETWORK_NO) {
            for (NetStateChangeObserver observer : mObservers) {
                observer.onNetDisconnected();
            }
        } else {
            for (NetStateChangeObserver observer : mObservers) {
                observer.onNetConnected(networkType);
            }
        }
    }

    /**
     * 注册网络变化Observer
     */
    public static void registerObserver(NetStateChangeObserver observer) {
        if (observer == null)
            return;
        if (!InstanceHolder.INSTANCE.mObservers.contains(observer)) {
            InstanceHolder.INSTANCE.mObservers.add(observer);
        }
    }

    /**
     * 取消网络变化Observer的注册
     */
    public static void unregisterObserver(NetStateChangeObserver observer) {
        if (observer == null)
            return;
        if (InstanceHolder.INSTANCE.mObservers == null)
            return;
        InstanceHolder.INSTANCE.mObservers.remove(observer);
    }
}
