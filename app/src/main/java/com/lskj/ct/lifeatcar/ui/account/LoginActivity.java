package com.lskj.ct.lifeatcar.ui.account;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.base.BaseActivity;

/**
 * Created by thunder on 2018/2/14.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTvForget;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initActionBar() {
        setTitle("登录");
    }

    @Override
    protected void initView() {
        mTvForget = findViewById(R.id.tv_login_forget);
        mTvForget.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_forget:
                Intent intent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
                break;
        }
    }
}
