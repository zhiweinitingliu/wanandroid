package com.dukang.wanandroid.mvp.presenter;

import android.app.Activity;

import com.dukang.wanandroid.mvp.model.BaseBean;
import com.dukang.wanandroid.mvp.model.LoginBean;
import com.dukang.wanandroid.mvp.retrofit.MyObserver;
import com.dukang.wanandroid.mvp.retrofit.ObserverCallBack;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/7/21 16:24
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/21 16:24
 * @LastCheckBy :wdk
 */
public class BasePresenter {

    public void toRequest(Activity activity,Observable<ResponseBody> observable, Class<? extends BaseBean> tClass, ObserverCallBack observerCallBack){
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<>(activity, LoginBean.class, observerCallBack));
    }


}
