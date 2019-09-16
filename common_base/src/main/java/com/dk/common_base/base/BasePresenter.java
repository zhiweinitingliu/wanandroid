package com.dk.common_base.base;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/9/16 11:25
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/9/16 11:25
 * @LastCheckBy :wdk
 */
public abstract class BasePresenter<M extends IBaseModel, V extends IBaseView> {

    private V mProxyView;

    private M modle;

    private WeakReference<V> weakReference;

    @SuppressWarnings("unchecked")
    public void attachView(V view) {
        weakReference = new WeakReference<>(view);

        /*不太理解这一步操作*/
        mProxyView = (V) Proxy.newProxyInstance(
                view.getClass().getClassLoader(),
                view.getClass().getInterfaces(),
                new MvpViewHandler(weakReference.get()));

        if (this.modle == null) {
            createModel();
        }
    }

    public V getView() {
        return mProxyView;
    }

    public M getModle() {
        return modle;
    }

    public Context getContext() {
        return getView().getContext();
    }

    /**
     * 创建model
     *
     * @return 实例
     */
    protected abstract M createModel();


    /**
     * 是否与View建立连接
     */
    protected boolean isViewAttached() {
        return weakReference != null && weakReference.get() != null;
    }

    /**
     * View代理类  防止 页面关闭P异步操作调用V 方法 空指针问题
     */
    private class MvpViewHandler implements InvocationHandler {

        private IBaseView mvpView;

        MvpViewHandler(IBaseView mvpView) {
            this.mvpView = mvpView;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果V层没被销毁, 执行V层的方法.
            if (isViewAttached()) {
                return method.invoke(mvpView, args);
            } //P层不需要关注V层的返回值
            return null;
        }
    }


}
