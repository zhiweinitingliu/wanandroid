package com.dukang.wanandroid.mvp.view;

import android.net.Uri;
import android.os.Bundle;

import com.dukang.wanandroid.R;
import com.dukang.wanandroid.base.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/7/23 14:07
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/23 14:07
 * @LastCheckBy :wdk
 */
public class MainActivity extends BaseActivity {

    private SimpleDraweeView dvIcon;

    @Override
    public int getLayout(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        dvIcon = findViewById(R.id.dvIcon);
    }

    @Override
    public void initData() {
        Uri uri = Uri.parse("https:\\/\\/www.jybd.cn\\/data\\/upload\\/shop\\/store\\/goods\\/29459\\/2018\\/12\\/09\\/29459_05976695858146139_360.jpg");
        dvIcon.setImageURI(uri);
    }

    @Override
    public void initListener() {

    }
}
