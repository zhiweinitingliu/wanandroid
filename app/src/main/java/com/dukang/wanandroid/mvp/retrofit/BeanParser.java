package com.dukang.wanandroid.mvp.retrofit;

import com.dukang.wanandroid.mvp.model.BaseBean;

import java.util.List;

/**
 * Created by 王旭 on 2018/1/23 0023.
 * 说明：
 * 注意：
 */

public interface BeanParser {
    /**
     * 解析此类对象
     * @param json
     * @return
     */
    BaseBean getSelf(String json);

    /**
     * 解析此类列表
     * @param json
     * @return
     */
     List getSelfList(String json);

    /**
     * 是否在解析前使用过滤器
     * @return
     */
    boolean isNeedFilter();
}
