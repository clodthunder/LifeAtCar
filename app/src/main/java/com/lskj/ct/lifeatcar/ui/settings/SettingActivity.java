package com.lskj.ct.lifeatcar.ui.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.base.BaseActivity;
import com.lskj.ct.lifeatcar.otto.MainMsgEvent;
import com.lskj.ct.lifeatcar.otto.OttoHelper;
import com.squareup.otto.Produce;


/**
 * Created by thunder on 2018/2/14.
 */

public class SettingActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initActionBar() {
        setTitle("设置");
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OttoHelper.getInstance().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OttoHelper.getInstance().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("setting", "onPause");

    }

    @Override
    protected void onStop() {
        super.onStop();
        //提示mainactivity 选中mineFragment
        Log.d("setting", "onStop");
        OttoHelper.getInstance().post(new MainMsgEvent(4));
    }
}
