package com.dk.module.user.ui.account;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.dk.common_base.base.BaseActivity;
import com.dk.module.user.R;

/**
 * @Description :登录页面
 * @Author : wdk
 * @CretaTime : 2019/9/14 10:17
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/9/14 10:17
 * @LastCheckBy :wdk
 */
@Route(path = "/users/LoginActivity")
public class LoginActivity extends BaseActivity {
    @Override
    public int getLayout(Bundle savedInstanceState) {
        return R.layout.act_login;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
