package com.dukang.wanandroid.RHttp.cancel;

import io.reactivex.disposables.Disposable;

/**
 * @Description :Http请求管理接口
 * @Author : wdk
 * @CretaTime : 2019/6/30 15:54
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/6/30 15:54
 * @LastCheckBy :wdk
 */
public interface RequestManager<T> {
    /**
     * 添加
     *
     * @param tag
     * @param disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除
     *
     * @param tag
     */
    void remove(T tag);

    /**
     * 取消
     *
     * @param tag
     */
    void cancel(T tag);

    /**
     * 取消全部
     */
    void cancelAll();

}
