package com.leo.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CountDownTimerButton button = findViewById(R.id.count_down_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CountDownTimerButton) v).startTimer();
                Toast.makeText(MainActivity.this, "我发出了点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        //如果没用 可以不写finishCallBack
        button.setFinishCallBack(new CountDownTimerButton.FinishCallBack() {
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "倒计时结束", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
