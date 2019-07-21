package com.dukang.wanandroid.mvp.presenter;

import android.app.Activity;
import android.util.Log;

import com.dukang.wanandroid.mvp.model.LoginBean;
import com.dukang.wanandroid.mvp.model.LoginService;
import com.dukang.wanandroid.mvp.retrofit.MyObserver;
import com.dukang.wanandroid.mvp.retrofit.ObserverCallBack;
import com.dukang.wanandroid.mvp.retrofit.RetrofitManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

    public static class LoginPresenter extends BasePresenter {

        //        private LoginBean loginBean;
        private LoginService loginService;
        private Activity activity;

        public LoginPresenter(Activity activity) {
            this.activity = activity;
            loginService = RetrofitManager.getInstance().createImpl(LoginService.class);
        }

        public void doLogin(String userName, String password) {
            Observable<ResponseBody> login = loginService.login(userName, password);
            toRequest(activity,login, LoginBean.class, new ObserverCallBack() {
                @Override
                public void onSuccess(Object o) {
                    LoginBean loginBean = (LoginBean) o;
                    Log.e("response", loginBean.toString());
                }

                @Override
                public void onFailed(Throwable e) {
                    Log.e("errorle", "异常了");
                    try {

                        Log.e("error", e.getMessage());
                    } catch (Exception exception) {
                        e.printStackTrace();
                    }
                }
            });
//
//            login.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new MyObserver<>(activity, LoginBean.class, new ObserverCallBack() {
//                        @Override
//                        public void onSuccess(Object o) {
//                            LoginBean loginBean = (LoginBean) o;
//                            Log.e("response", loginBean.toString());
////                            try {
////                                Log.e("response",responseBody.string());
////                            } catch (IOException e) {
////                                e.printStackTrace();
////                            }
//                        }
//
//                        @Override
//                        public void onFailed(Throwable e) {
//                            Log.e("errorle", "异常了");
//                            try {
//
//                                Log.e("error", e.getMessage());
//                            } catch (Exception exception) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }));
//        }
//                    .subscribe(new Observer<ResponseBody>() {
//                        @Override
//                        public void onSubscribe(Disposable d) {
//                            Log.e("onSubscribe","onSubscribe");
//                        }
//
//                        @Override
//                        public void onNext(ResponseBody s) {
//                            try {
//                                Log.e("response",s.string().toString());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.e("errorle","异常了");
//                            try{
//
//                                Log.e("error",e.getMessage());
//                            }catch (Exception exception){
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onComplete() {
//                            Log.e("onComplete","onComplete");
//                        }
//                    });
        }

    }
}
