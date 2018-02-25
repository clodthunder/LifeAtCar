package com.lskj.ct.lifeatcar.ui.account;

import android.view.View;
import android.widget.Toast;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.base.BaseActivity;
import com.lskj.ct.lifeatcar.widgets.CountDownButton;

/**
 * Created by thunder on 2018/2/21.
 */

public class ForgetActivity extends BaseActivity {
    private CountDownButton countDownButton;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    protected void initActionBar() {
        setTitle("忘记密码");
    }

    @Override
    protected void initView() {
        countDownButton = findViewById(R.id.btn_count_down);

    }

    @Override
    protected void initData() {
        countDownButton.setmClickListener(new CountDownButton.OnCdbBtnClickListener() {
            @Override
            public void OnClick(View view) {
                //点击按钮
                Toast.makeText(ForgetActivity.this, "请求发送短信", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnCancle() {

            }
        });
    }

    @Override
    protected void setListener() {

    }
}
