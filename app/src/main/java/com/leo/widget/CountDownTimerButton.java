package com.leo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author leo, ZhangWei
 * @date 2018/5/9
 * @function 一个自动倒计时button
 */
public class CountDownTimerButton extends AppCompatButton {

    private final int DEFAULT_DOWN_TIME = 30;
    private int countDownSecond = 30;
    private boolean selectionChange = false;

    private FinishCallBack finishCallBack;
    private MyCountDownTimer timer;

    private String normalString;
    private String countDownlString;

    public CountDownTimerButton(Context context) {
        super(context);
    }

    public CountDownTimerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCountDownTimerButton(context, attrs);
    }

    public CountDownTimerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCountDownTimerButton(context, attrs);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(new MyClickListener(l));
    }

    private void initCountDownTimerButton(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CountDownTimerButton);
            countDownSecond = typedArray.getInteger(R.styleable.CountDownTimerButton_count_time, DEFAULT_DOWN_TIME);
            selectionChange = typedArray.getBoolean(R.styleable.CountDownTimerButton_change_with_selection, false);
            normalString = typedArray.getString(R.styleable.CountDownTimerButton_normal_string);
            countDownlString = typedArray.getString(R.styleable.CountDownTimerButton_count_down_string);
            typedArray.recycle();
        }
        timer = new MyCountDownTimer(countDownSecond * 1000, 1000);
        CountDownTimerButton.this.setText(normalString);
    }

    public void startTimer() {
        if (timer != null) {
            timer.start();
        }
    }

    private class MyClickListener implements View.OnClickListener {
        View.OnClickListener listener;

        MyClickListener(OnClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View v) {
            if (selectionChange) {
                CountDownTimerButton.this.setSelected(true);
            }
            startTimer();
            listener.onClick(v);
        }
    }

    public interface FinishCallBack {
        /**
         * 当计时器清零时，如果设置过接口回调，会调用该方法
         */
        void onFinish();
    }

    private class MyCountDownTimer extends CountDownTimer {

        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            String string = String.format(countDownlString, String.valueOf(millisUntilFinished / 1000));
            CountDownTimerButton.this.setText(string);
        }

        @Override
        public void onFinish() {
            CountDownTimerButton.this.setText(normalString);
            if (selectionChange) {
                CountDownTimerButton.this.setSelected(false);
            }
            if (finishCallBack != null) {
                finishCallBack.onFinish();
            }
        }
    }

    public int getCountDownSecond() {
        return countDownSecond;
    }

    public void setCountDownSecond(int countDownSecond) {
        this.countDownSecond = countDownSecond;
        timer = new MyCountDownTimer(countDownSecond * 1000, 1000);
    }

    public void setFinishCallBack(FinishCallBack finishCallBack) {
        this.finishCallBack = finishCallBack;
    }
}
