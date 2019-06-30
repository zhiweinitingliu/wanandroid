package com.dukang.wanandroid.RHttp.callback;

import com.dukang.wanandroid.RHttp.RHttp;
import com.dukang.wanandroid.RHttp.exception.ApiException;
import com.dukang.wanandroid.RHttp.exception.ExceptionEngine;
import com.dukang.wanandroid.RHttp.observer.HttpObserver;
import com.dukang.wanandroid.RHttp.utils.ThreadUtils;

/**
 * @Description : Http请求基础回调接口
 * 备注:处理基本逻辑
 * @Author : wdk
 * @CretaTime : 2019/6/30 15:46
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/6/30 15:46
 * @LastCheckBy :wdk
 */
public abstract class BaseCallBack<T> extends HttpObserver<T> {

    @Override
    public void onNext(T t) {
        super.onNext(t);
        inSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        //异常 Exception
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            inError(apiException.getCode(), apiException.getMsg());
        } else {
            inError(ExceptionEngine.UN_KNOWN_ERROR, "未知错误");
        }
    }

    /**
     * 请求成功
     *
     * @param t
     */
    public abstract void inSuccess(T t);

    /**
     * 请求出错
     *
     * @param code
     * @param desc
     */
    public abstract void inError(int code, String desc);

    /**
     * 请求取消
     */
    public abstract void inCancel();


    /**
     * Http被取消回调处理逻辑
     */
    private void onCanceledLogic() {
        if (!ThreadUtils.isMainThread()) {
            RHttp.Configure.get().getHandler().post(new Runnable() {
                @Override
                public void run() {
                    inCancel();
                }
            });
        } else {
            inCancel();
        }
    }
}
