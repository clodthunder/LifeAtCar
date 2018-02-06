package com.lskj.ct.lifeatcar.ui.introduce;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.common.MConstant;
import com.lskj.ct.lifeatcar.ui.main.MainActivity;
import com.lskj.ct.lifeatcar.utils.MSharedPreUtil;

/**
 * Created by thunder on 2018/2/3.
 * app 启动的第一个页面
 */

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        //发送校验产品版本 这里使用的是热更新
//        mTHandler.sendMessageDelayed(,);
        //判断是否是第一次启动 读取sharedPrefences
        reqReadPermi();
    }

    /**
     * 读取本地数据判断是否是第一次启动
     */
    private void reqReadPermi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MConstant.REQ_READ_PERMI);
            } else {
                toActivity();
            }
        } else {
            toActivity();
        }
    }

    private void toActivity() {
        Intent mIntent = null;
        if (MSharedPreUtil.getBoolean(MConstant.KEY_IS_FIRST_USER, true)) {
            mIntent = new Intent(LoadingActivity.this, IntroduceActivity.class);
            //写入第一次启动数据
            reqWritePermi();
        } else {
            mIntent = new Intent(LoadingActivity.this, MainActivity.class);
        }
        startActivity(mIntent);
        finish();
    }

    /**
     * 写入第一次启动的标识
     */
    private void reqWritePermi() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MConstant.REQ_WRITE_PERMI);
            } else {
                MSharedPreUtil.putBoolean(MConstant.KEY_IS_FIRST_USER, false);
            }
        } else {
            MSharedPreUtil.putBoolean(MConstant.KEY_IS_FIRST_USER, false);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissions.length > 0) {
            switch (requestCode) {
                case MConstant.REQ_READ_PERMI:
                    boolean isGrant = true;
                    for (int cGrant : grantResults) {
                        if (cGrant != PackageManager.PERMISSION_GRANTED) {
                            isGrant = false;
                        }
                    }
                    if (isGrant)
                        toActivity();
                    break;
                case MConstant.REQ_WRITE_PERMI:
                    boolean isGrant2 = true;
                    for (int cGrant : grantResults) {
                        if (cGrant != PackageManager.PERMISSION_GRANTED) {
                            isGrant2 = false;
                        }
                    }
                    if (isGrant2)
                        MSharedPreUtil.putBoolean(MConstant.KEY_IS_FIRST_USER, false);
                    break;
            }
        }
    }
}
