package com.dukang.wanandroid.mvp.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dukang.wanandroid.R;
import com.dukang.wanandroid.base.BaseActivity;
import com.dukang.wanandroid.mvp.presenter.LoginContract;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/7/1 14:42
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/1 14:42
 * @LastCheckBy :wdk
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText etUserName;
    private EditText etPassword;
    private Button btnLogin;

    LoginContract.LoginPresenter loginPresenter = new LoginContract.LoginPresenter();

    @Override
    public int getLayout(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //登录
            case R.id.btnLogin:
                login();
                break;
        }
    }

    private void login() {
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(password)) {
            return;
        }

        loginPresenter.doLogin(userName, password);
    }
}
