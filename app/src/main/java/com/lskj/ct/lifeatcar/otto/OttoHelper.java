package com.lskj.ct.lifeatcar.otto;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by thunder on 2018/2/17.
 */

public class OttoHelper {
    private Bus bus = new Bus();

    public static OttoHelper getInstance() {
        return OttoHelperHolder.busHelper;
    }

    private static class OttoHelperHolder {
        private static final OttoHelper busHelper = new OttoHelper();
    }


    public OttoHelper() {
        //任何线程上都可以使用
        bus = new Bus(ThreadEnforcer.ANY);
    }

    public void register(Object object) {
        bus.register(object);
    }

    public void unregister(Object object) {
        bus.unregister(object);
    }

    public void post(Object object) {
        bus.post(object);
    }
}
