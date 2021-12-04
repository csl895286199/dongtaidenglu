package com.example.a99999;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a99999.MainActivity;
import com.example.sht.homework.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class StartActivity extends AppCompatActivity implements TextWatcher, View.OnClickListener{

    TextView tv_regist;
    EditText et_login_user, et_login_password;
    Button bt_login;
    Button bt_mobile_login;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private ImageView leftArm;
    private ImageView rightArm;
    private ImageView leftHand;
    private ImageView rightHand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_start);
        Bmob.initialize(this, "bd4814e57ed9c8f00aa0d119c5676cf9");

        tv_regist = (TextView) findViewById(R.id.tv_regist);
        bt_login = (Button) findViewById(R.id.login);
        bt_mobile_login = (Button) findViewById(R.id.monile_login);
        et_login_user = (EditText) findViewById(R.id.et_login_user);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        tv_regist.setOnClickListener(StartActivity.this);
        bt_login.setOnClickListener(StartActivity.this);
        bt_mobile_login.setOnClickListener(StartActivity.this);
        leftArm = (ImageView) findViewById(R.id.iv_left_arm);
        rightArm = (ImageView) findViewById(R.id.iv_right_arm);
        leftHand = (ImageView) findViewById(R.id.iv_left_hand);
        rightHand = (ImageView) findViewById(R.id.iv_right_hand);
        initViews();

    }

    public void initViews(){


        //监听内容改变 -> 控制按钮的点击状态
        et_login_user.addTextChangedListener(this);
        et_login_password.addTextChangedListener(this);

        //监听EidtText的焦点变化 -> 控制是否需要捂住眼睛
        et_login_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true){
                    //捂住眼睛
                    close();
                }else{
                    //打开
                    open();
                }
            }
        });

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.tv_regist:
                Intent intent_regist = new Intent(StartActivity.this, RegistActivity.class);
                startActivity(intent_regist);
                break;
            case R.id.monile_login:
                Intent intent_mobile = new Intent(StartActivity.this, MobileLoad.class);
                startActivity(intent_mobile);
                break;
            case R.id.login:
                String user_num = et_login_user.getText().toString();
                String user_password = et_login_password.getText().toString().trim();
                // 非空验证
                if (user_num.isEmpty() || user_password.isEmpty()) {
                    Toast.makeText(StartActivity.this, "账号或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                User bu2 = new User();
                bu2.setUsername(user_num);
                bu2.setPassword(user_password);
                // 使用BmobSDK提供的登录功能

                bu2.login(new SaveListener<BmobUser>() {

                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if(e==null){
                            Toast.makeText(StartActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                            Intent intent_main = new Intent(StartActivity.this, MainActivity.class);
                            startActivity(intent_main);
                            //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                        }else{
                            Toast.makeText(StartActivity.this, "登陆失败", Toast.LENGTH_SHORT).show();

                            //loge(e);

                        }
                    }
                });

                break;
        }
    }


    /**
     * 当有控件获得焦点focus 自动弹出键盘
     * 1. 点击软键盘的enter键 自动收回键盘
     * 2. 代码控制 InputMethodManager
     *    requestFocus
     *    showSoftInput:显示键盘 必须先让这个view成为焦点requestFocus
     *
     *    hideSoftInputFromWindow 隐藏键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            //隐藏键盘
            //1.获取系统输入的管理器
            InputMethodManager inputManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

            //2.隐藏键盘
            inputManager.hideSoftInputFromWindow(et_login_user.getWindowToken(),0);

            //3.取消焦点
            View focusView = getCurrentFocus();
            if (focusView != null) {
                focusView.clearFocus(); //取消焦点
            }

            //getCurrentFocus().clearFocus();

            //focusView.requestFocus();//请求焦点
        }
        return true;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //判断两个输入框是否有内容
        if (et_login_user.getText().toString().length() > 0 &&
                et_login_password.getText().toString().length() > 0){
            //按钮可以点击
            bt_login.setEnabled(true);
        }else{
            //按钮不能点击
            bt_login.setEnabled(false);
        }
    }

    public void close(){
        //左边
        RotateAnimation lAnim = new RotateAnimation(0,170,leftArm.getWidth(),0f);
        lAnim.setDuration(500);
        lAnim.setFillAfter(true);

        leftArm.startAnimation(lAnim);

        RotateAnimation rAnim = new RotateAnimation(0, -170,0f,0f);
        rAnim.setDuration(500);
        rAnim.setFillAfter(true);

        rightArm.startAnimation(rAnim);

        TranslateAnimation down = (TranslateAnimation) AnimationUtils.loadAnimation(this,R.anim.hand_down_anim);
        leftHand.startAnimation(down);
        rightHand.startAnimation(down);
    }

    public void open(){
        //左边
        RotateAnimation lAnim = new RotateAnimation(170,0,leftArm.getWidth(),0f);
        lAnim.setDuration(500);
        lAnim.setFillAfter(true);

        leftArm.startAnimation(lAnim);

        RotateAnimation rAnim = new RotateAnimation(-170,0,0f,0f);
        rAnim.setDuration(500);
        rAnim.setFillAfter(true);

        rightArm.startAnimation(rAnim);

        TranslateAnimation up = (TranslateAnimation) AnimationUtils.loadAnimation(this,R.anim.hand_up_anim);
        leftHand.startAnimation(up);
        rightHand.startAnimation(up);
    }

}
