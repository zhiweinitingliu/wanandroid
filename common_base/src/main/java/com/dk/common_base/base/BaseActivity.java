package com.dk.common_base.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/7/1 14:43
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/1 14:43
 * @LastCheckBy :wdk
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    public P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout(savedInstanceState));
        iniMvp();
        initView();
        initData();
        initListener();
    }

    protected void iniMvp() {
        presenter = createPresenter();
        if (presenter!=null){
            presenter.attachView(this);
        }
    }

    public abstract int getLayout(Bundle savedInstanceState);

    public abstract P createPresenter();

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
