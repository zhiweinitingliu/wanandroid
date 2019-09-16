package com.dk.module.user.mvp.presenter;

import com.dk.common_base.base.BasePresenter;
import com.dk.module.user.mvp.contract.LoginContract;
import com.dk.module.user.mvp.model.LoginModel;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/9/16 15:15
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/9/16 15:15
 * @LastCheckBy :wdk
 */
public class LoginPresenter extends BasePresenter<LoginContract.LoginModel, LoginContract.LoginView> implements LoginContract.LoginPresenter {

    @Override
    public void login() {
        getView().onSuccess("登录成功了");
    }

    @Override
    protected LoginContract.LoginModel createModel() {
        return new LoginModel();
    }
}
