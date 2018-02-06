package com.lskj.ct.lifeatcar.network.enums;

/**
 * Created by thunder on 2018/2/4.
 * 网络类型
 */

public enum NetWorkType {
    /**
     * WiFi
     */
    NETWORK_WIFI("WiFi"),
    /**
     * 4G
     */
    NETWORK_4G("4G"),
    /**
     * 3G
     */
    NETWORK_3G("3G"),
    /**
     * 2G
     */
    NETWORK_2G("2G"),
    /**
     * Unknown
     */
    NETWORK_UNKNOWN("Unknown"),
    /**
     * No network
     */
    NETWORK_NO("No network");

    private String desc;

    NetWorkType(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
