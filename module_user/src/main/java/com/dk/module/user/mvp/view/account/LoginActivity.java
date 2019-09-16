package com.dk.module.user.mvp.view.account;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dk.common_base.base.BaseActivity;
import com.dk.module.user.R;
import com.dk.module.user.mvp.contract.LoginContract;
import com.dk.module.user.mvp.presenter.LoginPresenter;

/**
 * @Description :登录页面
 * @Author : wdk
 * @CretaTime : 2019/9/14 10:17
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/9/14 10:17
 * @LastCheckBy :wdk
 */
@Route(path = "/users/LoginActivity")
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.LoginView, View.OnClickListener {

    private EditText etUserName;
    private EditText etPwd;
    private Button btnLogin;

    @Override
    public int getLayout(Bundle savedInstanceState) {
        return R.layout.act_login;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void initView() {
        etUserName = findViewById(R.id.etUserName);
        etPwd = findViewById(R.id.etPwd);
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
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            toLogin();
        }
    }

    private void toLogin() {
        presenter.login();
    }

    @Override
    public void onSuccess(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
