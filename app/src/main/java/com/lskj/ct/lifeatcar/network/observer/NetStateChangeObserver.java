package com.lskj.ct.lifeatcar.network.observer;

import com.lskj.ct.lifeatcar.network.enums.NetWorkType;

/**
 * Created by thunder on 2018/2/4.
 * 定义网络变化观察者
 */

public interface NetStateChangeObserver {

    void onNetDisconnected();

    void onNetConnected(NetWorkType networkType);
}
