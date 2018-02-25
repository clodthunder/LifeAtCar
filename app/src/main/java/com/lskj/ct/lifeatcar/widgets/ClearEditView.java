package com.lskj.ct.lifeatcar.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.lskj.ct.lifeatcar.R;

/**
 * Created by thunder on 2017/11/27.
 */

public class ClearEditView extends android.support.v7.widget.AppCompatEditText implements View.OnFocusChangeListener, TextWatcher {

    //右侧的删除按钮
    Drawable mClearDrawable;

    public ClearEditView(Context context) {
        this(context, null);
    }

    public ClearEditView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ClearEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClearDrawable = getResources().getDrawable(R.drawable.ic_clear_black_24dp);
        //设置按钮的边界
        mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        setClearIcon(false);
        //添加focus 监听
        setOnFocusChangeListener(this);
        //添加text更改的监听
        addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    /**
     * 文本内容变化时回调
     * 当文本长度大于0时显示删除按钮， 否则隐藏
     *
     * @param s
     * @param start
     * @param before
     * @param count
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        setClearIcon(s.length() > 0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 有焦点，并文本长度大于0则显示删除按钮
     *
     * @param v
     * @param hasFocus
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setClearIcon(getText().length() > 0);
        } else {
            setClearIcon(false);
        }
    }

    /**
     * 设置清空图标是否显示
     *
     * @param isShow
     */
    public void setClearIcon(boolean isShow) {
        Drawable rightDrawable = isShow ? mClearDrawable : null;
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                rightDrawable, getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (getCompoundDrawables()[2] != null) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                boolean mXTouch = (event.getX() > (getWidth() - getPaddingRight() - mClearDrawable.getIntrinsicWidth())
                        && event.getX() < (getWidth() - getPaddingRight()));
                boolean mYTouch = event.getY() > (getHeight() - mClearDrawable.getIntrinsicHeight()) / 2
                        && event.getY() < (getHeight() + mClearDrawable.getIntrinsicHeight()) / 2;
                //清除文本
                if (mXTouch && mYTouch) {
                    getText().clear();
                }
            }
        }
        return super.onTouchEvent(event);
    }
}
