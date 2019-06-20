package com.dukang.wanandroid.retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/5/30 21:00
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/5/30 21:00
 * @LastCheckBy :wdk
 */
public interface HttpCallBack {
    void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response);

    void onFailed(Call<ResponseBody> call, Throwable t);
}
