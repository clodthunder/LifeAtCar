package com.lskj.ct.lifeatcar.ui.main.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lskj.ct.lifeatcar.R;
import com.lskj.ct.lifeatcar.app.LifeApp;
import com.lskj.ct.lifeatcar.model.UserModel;
import com.lskj.ct.lifeatcar.ui.bank.MyBankActivity;
import com.lskj.ct.lifeatcar.ui.cars.MyCarsActivity;
import com.lskj.ct.lifeatcar.ui.discount.DiscountCardActivity;
import com.lskj.ct.lifeatcar.ui.account.LoginActivity;
import com.lskj.ct.lifeatcar.ui.account.RegisterActivity;
import com.lskj.ct.lifeatcar.ui.mine.PersonalActivity;
import com.lskj.ct.lifeatcar.ui.news.MyNewsActivity;
import com.lskj.ct.lifeatcar.ui.oil.MyOilActivity;
import com.lskj.ct.lifeatcar.ui.settings.SettingActivity;
import com.lskj.ct.lifeatcar.widgets.CircleImageView;
import com.squareup.picasso.Picasso;

/**
 * Created by thunder on 2018/2/6.
 */

public class MineFragment extends Fragment implements View.OnClickListener {
    private static MineFragment instance = new MineFragment();
    private Context mContext;
    private UserModel mUser;
    //是否显示用户信息
    private boolean isShowUserMsg = false;


    public static MineFragment getInstance() {
        return instance;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mUser = LifeApp.getInstance().getUser();
        if (mUser != null) {
            isShowUserMsg = true;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.mine_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_mine_setting:
                Intent mIntent = new Intent(mContext, SettingActivity.class);
                mContext.startActivity(mIntent);
                break;
        }
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
//       点击头像进入
        CircleImageView circleImageView = view.findViewById(R.id.img_mine_head);
        circleImageView.setOnClickListener(this);
        view.findViewById(R.id.rl_mine_discount).setOnClickListener(this);
        view.findViewById(R.id.rl_mine_oil).setOnClickListener(this);
        view.findViewById(R.id.rl_mine_bank).setOnClickListener(this);
        view.findViewById(R.id.rl_mine_cars).setOnClickListener(this);
        view.findViewById(R.id.rl_mine_news).setOnClickListener(this);
//      根据用户是否登录显示界面
        RelativeLayout rlLoginOrRegister = view.findViewById(R.id.rl_login_OrRegister);
        LinearLayout llUserMsg = view.findViewById(R.id.ll_user_msg);
        if (isShowUserMsg) {
            llUserMsg.setVisibility(View.VISIBLE);
            rlLoginOrRegister.setVisibility(View.INVISIBLE);
            //todo 处理显示用户信息
            Picasso.with(mContext).load(mUser.getHeaderIcon()).into(circleImageView);
            //设置用户的nickName
            ((TextView) view.findViewById(R.id.tv_nickName)).setText(mUser.getNickName());
            //加油卡剩余金额
//            ((TextView) view.findViewById(R.id.tv_left_quota)).setText(mUser);
//            白条可用额度
//            ((TextView) view.findViewById(R.id.tv_max_quota)).setText(mUser.getNickName());
        } else {
            rlLoginOrRegister.setVisibility(View.VISIBLE);
            llUserMsg.setVisibility(View.INVISIBLE);
            //todo 处理登录或者注册
            SpannableStringBuilder ssb = new SpannableStringBuilder("登录/注册");
            //修改颜色
            ForegroundColorSpan colorSpan
                    = new ForegroundColorSpan(Color.parseColor("#1A9BFC"));
            ForegroundColorSpan colorSpan2
                    = new ForegroundColorSpan(Color.parseColor("#1A9BFC"));

            ssb.setSpan(colorSpan, 0, 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            ssb.setSpan(colorSpan2, 3, 5, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            ClickableSpan loginClickSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Intent loginIntent = new Intent(mContext, LoginActivity.class);
                    mContext.startActivity(loginIntent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置下划线
                    ds.setUnderlineText(false);
                }
            };
            ClickableSpan registerClickSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    Intent registerIntent = new Intent(mContext, RegisterActivity.class);
                    mContext.startActivity(registerIntent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置下划线
                    ds.setUnderlineText(false);
                }
            };

            ssb.setSpan(loginClickSpan, 0, 2, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
            ssb.setSpan(registerClickSpan, 3, 5, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

            TextView mTvLink = view.findViewById(R.id.tv_loginOrRegister);
            mTvLink.setMovementMethod(LinkMovementMethod.getInstance());
            mTvLink.setHighlightColor(Color.TRANSPARENT);
            mTvLink.setText(ssb);
        }
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent mIntent = null;
        switch (v.getId()) {
//              个人设置
            case R.id.img_mine_head:
                mIntent = new Intent(mContext, PersonalActivity.class);
                mContext.startActivity(mIntent);
                break;
//              我的优惠券
            case R.id.rl_mine_discount:
                mIntent = new Intent(mContext, DiscountCardActivity.class);
                mContext.startActivity(mIntent);
                break;
//              我的加油卡
            case R.id.rl_mine_oil:
                mIntent = new Intent(mContext, MyOilActivity.class);
                mContext.startActivity(mIntent);
                break;
//               我的银行卡
            case R.id.rl_mine_bank:
                mIntent = new Intent(mContext, MyBankActivity.class);
                mContext.startActivity(mIntent);
                break;
//                我的车辆
            case R.id.rl_mine_cars:
                mIntent = new Intent(mContext, MyCarsActivity.class);
                mContext.startActivity(mIntent);
                break;
//                我的消息
            case R.id.rl_mine_news:
                mIntent = new Intent(mContext, MyNewsActivity.class);
                mContext.startActivity(mIntent);
                break;
        }

    }
}
