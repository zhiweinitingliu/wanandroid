package com.dukang.wanandroid.mvp.presenter;

import android.util.Log;

import com.dukang.wanandroid.mvp.model.LoginBean;
import com.dukang.wanandroid.mvp.model.LoginService;
import com.dukang.wanandroid.mvp.retrofit.RetrofitManager;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/7/1 16:49
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/1 16:49
 * @LastCheckBy :wdk
 */
public class LoginContract {

    public static class LoginPresenter {

        private LoginBean loginBean;
        private LoginService loginService;

        public LoginPresenter() {
            loginService = RetrofitManager.getInstance().creatImpl(LoginService.class);
        }

        public void doLogin(String userName, String password) {
            loginService.login(userName, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ResponseBody>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBody s) {
                            try {
                                Log.e("response",s.string().toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }

    }
}
