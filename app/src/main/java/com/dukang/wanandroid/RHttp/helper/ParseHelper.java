package com.dukang.wanandroid.RHttp.helper;

/**
 * @Description :数据解析helper
 * @Author : wdk
 * @CretaTime : 2019/6/30 15:43
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/6/30 15:43
 * @LastCheckBy :wdk
 */
public interface ParseHelper<T> {

    /*数据解析*/
    T parse(String json);
}
