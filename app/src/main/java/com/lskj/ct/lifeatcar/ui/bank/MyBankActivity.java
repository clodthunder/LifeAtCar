package com.lskj.ct.lifeatcar.ui.bank;

import android.widget.Toast;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.base.BaseActivity;

/**
 * Created by thunder on 2018/2/14.
 */

public class MyBankActivity extends BaseActivity {
    @Override
    protected void initActionBar() {
        setTitle("我的银行卡");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_banks;
    }

    @Override
    protected void initView() {
        Toast.makeText(this, "mybank", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }
}
