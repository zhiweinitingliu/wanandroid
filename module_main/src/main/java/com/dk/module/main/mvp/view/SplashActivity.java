package com.dk.module.main.mvp.view;

import android.content.Intent;
import android.os.Bundle;

import com.dk.common_base.base.BaseActivity;
import com.dk.module.main.R;

/**
 * @Description : 闪屏页
 * @Author : wdk
 * @CretaTime : 2019/9/14 15:34
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/9/14 15:34
 * @LastCheckBy :wdk
 */
public class SplashActivity extends BaseActivity {
    @Override
    public int getLayout(Bundle savedInstanceState) {
        return R.layout.act_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        startActivity(new Intent(this, MainActivity.class));
        finish();

    }

    @Override
    public void initListener() {

    }
}
