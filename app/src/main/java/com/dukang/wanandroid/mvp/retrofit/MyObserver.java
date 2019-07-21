package com.dukang.wanandroid.mvp.retrofit;

import android.app.Activity;

import com.dukang.wanandroid.mvp.model.BaseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/7/21 13:51
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/21 13:51
 * @LastCheckBy :wdk
 */
public class MyObserver<T extends BaseBean> implements Observer<ResponseBody> {

    private Activity activity;
    private ObserverCallBack observerCallBack;
    private Class<T> clazz;

    public MyObserver(Activity activity,Class<T> clazz , ObserverCallBack observerCallBack) {
        this.activity = activity;
        this.observerCallBack = observerCallBack;
        this.clazz = clazz;
    }

    private static <T> T newTclass(Class<T> clazz) {
        T a = null;
        try {
            a = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return a;

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseBody responseBody) {

        BaseBean baseBean = newTclass(clazz);

        String json = null;
        try {
            json = responseBody.string();
        } catch (IOException e) {


        }

        try {
            JSONObject jsonObject = new JSONObject(json);

            int errorCode = jsonObject.optInt("errorCode", -1);

            String errorMsg = jsonObject.optString("errorMsg", "");

            String data = jsonObject.optString("data");


            if (errorCode == 0) {
                observerCallBack.onSuccess(baseBean.getSelf(data));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        observerCallBack.onFailed(e);

    }

    @Override
    public void onComplete() {

    }
}
