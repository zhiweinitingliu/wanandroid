package com.dukang.wanandroid.mvp.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @Description : retrofit 管理类
 * @Author : wdk
 * @CretaTime : 2019/7/1 16:35
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/1 16:35
 * @LastCheckBy :wdk
 */
public class RetrofitManager {

    private Retrofit mRetrofit;

    public static RetrofitManager getInstance() {
        return InstanceHelper.retrofitManager;
    }

    private static class InstanceHelper {
        static RetrofitManager retrofitManager = new RetrofitManager();
    }

    private RetrofitManager() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://wanandroid.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T creatImpl(Class<T> service) {
        return mRetrofit.create(service);

    }

}
