package com.dukang.wanandroid.mvp.retrofit;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/7/21 13:55
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/21 13:55
 * @LastCheckBy :wdk
 */
public interface ObserverCallBack {

    void onSuccess(Object o);

    void onFailed(Throwable e);
}
