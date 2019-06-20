package com.dukang.wanandroid.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/5/30 21:05
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/5/30 21:05
 * @LastCheckBy :wdk
 */
public class CallBackListener implements Callback<ResponseBody> {

    HttpCallBack httpCallBack;

    public CallBackListener(HttpCallBack httpCallBack) {
        this.httpCallBack = httpCallBack;
    }


    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        httpCallBack.onSuccess(call, response);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        httpCallBack.onFailed(call, t);
    }
}
