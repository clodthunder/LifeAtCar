package com.lskj.ct.lifeatcar.app;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.lskj.ct.lifeatcar.BuildConfig;
import com.lskj.ct.lifeatcar.log.FakeCrashLibrary;
import com.lskj.ct.lifeatcar.network.receiver.NetStateChangeReceiver;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

import timber.log.Timber;

/**
 * Created by thunder on 2018/2/3.
 */

public class LifeApp extends Application {
    public static Application app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
//        init timer log framework
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }

        //debug
        Stetho.initializeWithDefaults(this);
        //注册网络广播
        NetStateChangeReceiver.registerReceiver(this);
        //初始化热更新
        initSophix();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        //取消网络广播
        NetStateChangeReceiver.unregisterReceiver(this);

    }

    /**
     * A tree which logs important information for crash reporting.
     * 待实现
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, @NonNull String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);
            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }

    /**
     * 初始化阿里云的热更新
     * 注意versionname 必须是1.0.0 有两个点 的格式，否则可能出现问题
     */
    private void initSophix() {
        String appVersion;
        try {
            appVersion = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0";
        }
        // initialize最好放在attachBaseContext最前面，初始化直接在Application类里面，切勿封装到其他类
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后调用killProcessSafely自杀，以此加快应用补丁，详见1.3.2.3
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
        // queryAndLoadNewPatch不可放在attachBaseContext 中，否则无网络权限，建议放在后面任意时刻，如onCreate中
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}
