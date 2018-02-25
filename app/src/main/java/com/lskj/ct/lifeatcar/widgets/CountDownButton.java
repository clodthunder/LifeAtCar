package com.lskj.ct.lifeatcar.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.lskj.ct.lifeatcar.R;

/**
 * TODO: document your custom view class.
 */
public class CountDownButton
        extends android.support.v7.widget.AppCompatButton
        implements View.OnClickListener {
    private String mDefaultStr;//默认显示文字
    //    private int mDefaultStrColor = Color.RED; // 默认字体颜色
    private float mDefaultStrSize = 12; //默认字体的大小
    public OnCdbBtnClickListener mClickListener;
    private static GTimeDown mGTimerDown;

    public void setmClickListener(OnCdbBtnClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    public CountDownButton(Context context) {
        super(context);
        init(null, 0);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CountDownButton, defStyle, 0);

        mDefaultStr = a.getString(
                R.styleable.CountDownButton_cdb_default_str);
//        mDefaultStrColor = a.getColor(
//                R.styleable.CountDownButton_cdb_str_color,
//                mDefaultStrColor);

        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        mDefaultStrSize = a.getDimension(
                R.styleable.CountDownButton_cdb_str_size,
                mDefaultStrSize);
        a.recycle();
        mGTimerDown = new GTimeDown(59000, 1000);
        setText(mDefaultStr);
//        setTextColor(mDefaultStrColor);
        setTextSize(mDefaultStrSize);
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void onClick(View v) {
        mGTimerDown.start();
        mClickListener.OnClick(v);
    }

    public interface OnCdbBtnClickListener {
        public void OnClick(View view);

        public void OnCancle();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    /**
     * 停止计时 reset button
     */
    public static void cancelTimeCount() {
        if (mGTimerDown != null) {
            mGTimerDown.cancel();
        }
    }

    private class GTimeDown extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public GTimeDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {
            setText(millisUntilFinished / 1000 + " 重发");
            System.out.println(millisUntilFinished / 1000);
            setEnabled(false);
        }

        @Override
        public void onFinish() {
            setText(mDefaultStr);
            setEnabled(true);
            setSelected(false);
        }
    }
}
