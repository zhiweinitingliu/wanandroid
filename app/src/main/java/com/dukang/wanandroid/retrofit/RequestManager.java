package com.dukang.wanandroid.retrofit;

import android.app.Activity;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/5/30 21:10
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/5/30 21:10
 * @LastCheckBy :wdk
 */
public class RequestManager {
    public static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.wanandroid.com")
                    .build();
        }
        return retrofit;
    }

    public static <T> T creatImpl(Class<T> service) {
        return getRetrofit().create(service);

    }

    public static void requestEnque(Call<ResponseBody> call,HttpCallBack httpCallBack) {
        call.enqueue(new CallBackListener(httpCallBack));
    }


}
