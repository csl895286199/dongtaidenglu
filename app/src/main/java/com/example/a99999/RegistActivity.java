package com.example.a99999;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sht.homework.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegistActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_regist_user;
    EditText et_regist_password;
    Button bt_regist_save, bt_regist_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_regist);
        Bmob.initialize(this, "bd4814e57ed9c8f00aa0d119c5676cf9");
        et_regist_user = (EditText) findViewById(R.id.et_regist_user);
        et_regist_password = (EditText) findViewById(R.id.et_regist_password);
        bt_regist_save = (Button) findViewById(R.id.bt_regist_save);
        bt_regist_cancel = (Button) findViewById(R.id.bt_regist_cancel);
        bt_regist_save.setOnClickListener(this);
        bt_regist_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_regist_save:
                String user_num=et_regist_user.getText().toString();
                String user_password=et_regist_password.getText().toString();
                //非空验证
                if (user_num.isEmpty()||user_password.isEmpty()){
                    Toast.makeText(this, "账号密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 使用BmobSDK提供的注册功能
                User  myUser=new User();
                myUser.setUsername(user_num);
                myUser.setPassword(user_password);
                myUser.signUp(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e==null){
                            Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                finish();
                  break;
            case R.id.bt_regist_cancel:
                finish();
                break;
            default:
                break;
        }

    }
}
