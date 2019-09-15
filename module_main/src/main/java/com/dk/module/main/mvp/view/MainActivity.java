package com.dk.module.main.mvp.view;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dk.common_base.base.BaseActivity;
import com.dk.common_base.utils.IntentManager;
import com.dk.module.main.R;

/**
 * @Description :应用首页
 * @Author : wdk
 * @CretaTime : 2019/9/14 15:42
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/9/14 15:42
 * @LastCheckBy :wdk
 */
public class MainActivity extends BaseActivity {

    private TextView tvLogin;

    @Override
    public int getLayout(Bundle savedInstanceState) {
        return R.layout.act_main;
    }

    @Override
    public void initView() {
        tvLogin = findViewById(R.id.tvLogin);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ARouter.getInstance().build("/users/LoginActivity").navigation();
            }
        });
    }
}
