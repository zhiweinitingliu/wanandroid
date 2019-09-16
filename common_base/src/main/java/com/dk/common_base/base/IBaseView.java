package com.dk.common_base.base;

import android.content.Context;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/9/16 11:24
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/9/16 11:24
 * @LastCheckBy :wdk
 */
public interface IBaseView {

    Context getContext();

    void showLoading();

    void hideLoading();

}
