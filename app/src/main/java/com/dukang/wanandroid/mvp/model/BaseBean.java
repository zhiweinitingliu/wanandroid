package com.dukang.wanandroid.mvp.model;

import com.alibaba.fastjson.JSON;
import com.dukang.wanandroid.mvp.retrofit.BeanParser;

import java.io.Serializable;
import java.util.List;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/7/21 15:14
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/21 15:14
 * @LastCheckBy :wdk
 */
public class BaseBean implements BeanParser, Serializable {

    private int errorCode;
    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public BaseBean getSelf(String json) {
        return JSON.parseObject(json, this.getClass());
    }

    @Override
    public List getSelfList(String json) {
        return JSON.parseArray(json, this.getClass());
    }


    @Override
    public boolean isNeedFilter() {
        return false;
    }
}
