package com.dukang.wanandroid;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginService {


    @FormUrlEncoded
    @POST("/user/login")
    Call<ResponseBody> login(@Field("username") String username, @Field("password" ) String password);

}
