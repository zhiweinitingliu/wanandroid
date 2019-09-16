package com.dk.module.user.mvp.contract;

import com.dk.common_base.base.IBaseModel;
import com.dk.common_base.base.IBaseView;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/9/16 11:28
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/9/16 11:28
 * @LastCheckBy :wdk
 */
public interface LoginContract {

    interface LoginModel extends IBaseModel {
        void login(String userName, String pwd);
    }

    interface LoginView extends IBaseView {
        void onSuccess(String str);
    }

    interface LoginPresenter {
        void login();
    }
}
