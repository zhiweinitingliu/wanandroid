package com.dk.common_base.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.dk.common_base.BuildConfig;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @Description :Application
 * @Author : wdk
 * @CretaTime : 2019/9/14 9:18
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/9/14 9:18
 * @LastCheckBy :wdk
 */
public class BaseApplication extends Application {

    private static BaseApplication application;

    public static BaseApplication getApplication() {
        return application;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        application = this;
        //MultiDex分包方法 必须最先初始化
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();
        init();
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();  // 打印日志
            ARouter.openDebug(); // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application);// 尽可能早，推荐在Application中初始化
    }


    private void init() {
        Fresco.initialize(this);
    }
}
