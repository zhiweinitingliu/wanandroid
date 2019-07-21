package com.dukang.wanandroid.mvp.model;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    @FormUrlEncoded
    @POST("/user/login")
    Observable<ResponseBody> login(@Field("username") String username, @Field("password") String password);
}
