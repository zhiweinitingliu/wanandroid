package com.dukang.wanandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/7/1 14:43
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/1 14:43
 * @LastCheckBy :wdk
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout(savedInstanceState));
        initView();
        initData();
        initListener();
    }

    public abstract int getLayout(Bundle savedInstanceState);

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();
}
