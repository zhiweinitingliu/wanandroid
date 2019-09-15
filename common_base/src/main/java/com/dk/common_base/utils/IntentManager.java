package com.dk.common_base.utils;

import android.content.Context;
import android.content.Intent;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/9/14 17:26
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/9/14 17:26
 * @LastCheckBy :wdk
 */
public class IntentManager {

    public static void startActivity(Context context, Class clazz) {
        Intent intent = new Intent(context, clazz);
        context.startActivity(intent);

    }

}
