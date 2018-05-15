# MyCountDownButton
一个倒计时按钮

通过在xml布局文件内配置自定义参数进行控制

####   change_with_selection 是否自动变更selection状态 默认为false，如果设置为true可以通过配置自动变更背景等组件特性
####   count_down_string 倒计时状态下显示字符 支持字符串模板（只有倒计时一个参数） eg：倒计时%1$s秒
####   count_time 倒计时时间 int型 单位：秒
####   normal_string 正常状态下显示字符 eg：发送验证码

<p>一个xml例子</p>

     <com.leo.widget.CountDownTimerButton
        android:id="@+id/count_down_button"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@drawable/bg_selector_button"
        app:change_with_selection="true"
        app:count_down_string="倒计时%1$s秒"
        app:count_time="5"
        app:normal_string="发送验证码" />


<p>背景文件xml</p>

    <selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@color/amber_500" android:state_selected="true" />
    <item android:drawable="@color/grey_400" android:state_selected="false" />
    </selector>
    
    如果设置了change_with_selection 为true 组件会自动切换状态并变更背景
    
FinishCallBack（非必需）接口通过设置可以在倒计时结束后获得响应

          button.setFinishCallBack(new CountDownTimerButton.FinishCallBack() {
            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "倒计时结束", Toast.LENGTH_SHORT).show();
            }
        });
