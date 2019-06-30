package com.dukang.wanandroid.RHttp.cancel;

/**
 * @Description :请求取消接口
 * @Author : wdk
 * @CretaTime : 2019/6/30 15:53
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/6/30 15:53
 * @LastCheckBy :wdk
 */
public interface RequestCancel {

    /**
     * 取消请求
     */
    void cancel();

    /**
     * 请求被取消
     */
    void onCanceled();
}
