package com.lskj.ct.lifeatcar.ui.account;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.base.BaseActivity;
import com.lskj.ct.lifeatcar.widgets.CountDownButton;

/**
 * Created by thunder on 2018/2/14.
 */

public class RegisterActivity extends BaseActivity
        implements View.OnClickListener {
    private ActionBar actionBar;
    private AlertDialog mRegDialog;
    private LayoutInflater inflater;
    private CountDownButton countDownButton;
    private TextView mTvRules;
    private WebView mWebView;
    private Button mRegisterBtn;

    @Override
    protected void initView() {
        countDownButton = findViewById(R.id.btn_count_down);
        mTvRules = findViewById(R.id.tv_service_rules);
        mRegisterBtn = findViewById(R.id.btn_register);
        mRegisterBtn.setEnabled(false);
        mRegisterBtn.getBackground().setAlpha(100);
        mRegisterBtn.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initActionBar() {
        setTitle("注册");
    }


    @Override
    protected void initData() {
        countDownButton.setmClickListener(new CountDownButton.OnCdbBtnClickListener() {
            @Override
            public void OnClick(View view) {
                //点击按钮
                Toast.makeText(RegisterActivity.this, "请求发送短信", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnCancle() {

            }
        });
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //加载本地html
        View regDialogView = inflater.inflate(R.layout.dialog_register, null);
        regDialogView.findViewById(R.id.btn_agree_rules).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRegDialog != null && mRegDialog.isShowing()) {
                    mRegDialog.dismiss();
                    //修改注册按钮为可用
                    mRegisterBtn.getBackground().setAlpha(255);
                    mRegisterBtn.setEnabled(true);
                }
            }
        });
        mRegDialog = new AlertDialog.Builder(this)
                .setView(regDialogView)
                .setCancelable(true)
                .create();
        mWebView = regDialogView.findViewById(R.id.wv_register_rule);
        mWebView.loadUrl("file:///android_asset/html/register.html");
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.getSettings().setSupportZoom(false);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setDefaultFontSize(16);


        SpannableStringBuilder ssb =
                new SpannableStringBuilder(getResources().getString(R.string.str_register_rules));

        ForegroundColorSpan colorSpan2
                = new ForegroundColorSpan(Color.parseColor("#1A9BFC"));

        ClickableSpan ruleClickSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                if (mRegDialog != null && !mRegDialog.isShowing())
                    mRegDialog.show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置下划线
                ds.setUnderlineText(false);
            }
        };
        ssb.setSpan(colorSpan2, 3, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.setSpan(ruleClickSpan, 3, 12, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mTvRules.setMovementMethod(LinkMovementMethod.getInstance());
        mTvRules.setText(ssb);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                Toast.makeText(this, "请求网络注册操作", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
