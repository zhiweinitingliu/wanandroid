package com.dukang.wanandroid.RHttp;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.dukang.wanandroid.RHttp.api.Api;
import com.dukang.wanandroid.RHttp.callback.HttpCallback;
import com.dukang.wanandroid.RHttp.observer.HttpObservable;
import com.dukang.wanandroid.RHttp.retrofit.Method;
import com.dukang.wanandroid.RHttp.retrofit.RetrofitUtils;
import com.dukang.wanandroid.RHttp.utils.RequestUtils;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.io.File;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/6/30 15:05
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/6/30 15:05
 * @LastCheckBy :wdk
 */
public class RHttp {

    private Builder builder;
    /*请求方式*/
    private Method method;
    /*请求参数*/
    private Map<String, Object> parameter;
    /*header*/
    private Map<String, Object> header;
    /*LifecycleProvider*/
    private LifecycleProvider lifecycle;
    /*ActivityEvent*/
    private ActivityEvent activityEvent;
    /*FragmentEvent*/
    private FragmentEvent fragmentEvent;
    /*HttpCallback*/
    private HttpCallback httpCallback;
    /*标识请求的TAG*/
    private String tag;
    /*文件map*/
    private Map<String, File> fileMap;
    /*上传文件回调*/
//    private UploadCallback uploadCallback;
    /*基础URL*/
    private String baseUrl;
    /*apiUrl*/
    private String apiUrl;
    /*String参数*/
    String bodyString;
    /*是否强制JSON格式*/
    boolean isJson;
    /*HttpCallback*/

    public RHttp(Builder builder) {
        this.builder = builder;
        this.parameter = builder.parameter;
        this.header = builder.header;
        this.lifecycle = builder.lifecycle;
        this.activityEvent = builder.activityEvent;
        this.fragmentEvent = builder.fragmentEvent;
        this.tag = builder.tag;
        this.fileMap = builder.fileMap;
        this.baseUrl = builder.baseUrl;
        this.apiUrl = builder.apiUrl;
        this.isJson = builder.isJson;
        this.bodyString = builder.bodyString;
        this.method = builder.method;
    }

    public void request(HttpCallback httpCallback) {
        this.httpCallback = httpCallback;
        if (httpCallback == null) {
            throw new NullPointerException("HttpObserver must not null!");
        } else {
            doRequest();
        }
    }

    /*执行请求*/
    private void doRequest() {
        /*设置请求唯一标识*/
        httpCallback.setTag(TextUtils.isEmpty(tag) ? String.valueOf(System.currentTimeMillis()) : tag);

        /*header处理*/
        disposeHeader();

        /*Parameter处理*/
        disposeParameter();

        /*请求方式处理*/
        Observable apiObservable = disposeApiObservable();

        /* 被观察者 httpObservable */
        HttpObservable httpObservable=new HttpObservable.Builder(apiObservable)
                .httpObserver(httpCallback)
                .lifecycleProvider(lifecycle)
                .activityEvent(activityEvent)
                .fragmentEvent(fragmentEvent)
                .build();

        /*设置监听*/
        httpObservable.observe().subscribe(httpCallback);
    }


    /*获取基础URL*/
    private String getBaseUrl() {
        //如果没有重新指定URL则是用默认配置
        return TextUtils.isEmpty(baseUrl) ? Configure.get().getBaseUrl() : baseUrl;
    }

    /*ApiUrl处理*/
    private String disposeApiUrl() {
        return TextUtils.isEmpty(apiUrl) ? "" : apiUrl;
    }

    /*处理Header*/
    private void disposeHeader() {

        /*header空处理*/
        if (header == null) {
            header = new TreeMap<>();
        }

        //添加基础 Header
        Map<String, Object> baseHeader = Configure.get().getBaseHeader();
        if (baseHeader != null && baseHeader.size() > 0) {
            header.putAll(baseHeader);
        }

        if (!header.isEmpty()) {
            //处理header中文或者换行符出错问题
            for (String key : header.keySet()) {
                header.put(key, RequestUtils.getHeaderValueEncoded(header.get(key)));
            }
        }

    }

    /*处理 Parameter*/
    private void disposeParameter() {

        /*空处理*/
        if (parameter == null) {
            parameter = new TreeMap<>();
        }

        //添加基础 Parameter
        Map<String, Object> baseParameter = Configure.get().getBaseParameter();
        if (baseParameter != null && baseParameter.size() > 0) {
            parameter.putAll(baseParameter);
        }
    }

    /*处理ApiObservable*/
    private Observable disposeApiObservable() {

        Observable apiObservable = null;

        /*是否JSON格式提交参数*/
        boolean hasBodyString = !TextUtils.isEmpty(bodyString);
        RequestBody requestBody = null;
        if (hasBodyString) {
            String mediaType = isJson ? "application/json; charset=utf-8" : "text/plain;charset=utf-8";
            requestBody = RequestBody.create(okhttp3.MediaType.parse(mediaType), bodyString);
        }

        /*Api接口*/
        Api apiService = RetrofitUtils.get().getRetrofit(getBaseUrl()).create(Api.class);
        /*未指定默认POST*/
        if (method == null) method = Method.POST;

        switch (method) {
            case GET:
                apiObservable = apiService.get(disposeApiUrl(), parameter, header);
                break;
            case POST:
                if (hasBodyString)
                    apiObservable = apiService.post(disposeApiUrl(), requestBody, header);
                else
                    apiObservable = apiService.post(disposeApiUrl(), parameter, header);
                break;
            case DELETE:
                apiObservable = apiService.delete(disposeApiUrl(), parameter, header);
                break;
            case PUT:
                apiObservable = apiService.put(disposeApiUrl(), parameter, header);
                break;
        }
        return apiObservable;
    }




    /**
     * Configure配置
     */
    public static final class Configure {

        /*请求基础路径*/
        String baseUrl;
        /*超时时长*/
        long timeout;
        /*时间单位*/
        TimeUnit timeUnit;
        /*全局上下文*/
        Context context;
        /*全局Handler*/
        Handler handler;
        /*请求参数*/
        Map<String, Object> parameter;
        /*header*/
        Map<String, Object> header;
        /*是否显示Log*/
        boolean showLog;


        public static Configure get() {
            return Configure.Holder.holder;
        }

        private static class Holder {
            private static Configure holder = new Configure();
        }

        private Configure() {
            timeout = 60;//默认60秒
            timeUnit = TimeUnit.SECONDS;//默认秒
            showLog = true;//默认打印LOG
        }

        /*请求基础路径*/
        public RHttp.Configure baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        /*基础参数*/
        public RHttp.Configure baseParameter(Map<String, Object> parameter) {
            this.parameter = parameter;
            return this;
        }

        public Map<String, Object> getBaseParameter() {
            return parameter;
        }

        /*基础Header*/
        public RHttp.Configure baseHeader(Map<String, Object> header) {
            this.header = header;
            return this;
        }

        public Map<String, Object> getBaseHeader() {
            return header;
        }

        /*超时时长*/
        public RHttp.Configure timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public long getTimeout() {
            return timeout;
        }

        /*是否显示LOG*/
        public RHttp.Configure showLog(boolean showLog) {
            this.showLog = showLog;
            return this;
        }

        public boolean isShowLog() {
            return showLog;
        }

        /*时间单位*/
        public RHttp.Configure timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public TimeUnit getTimeUnit() {
            return timeUnit;
        }

        /*Handler*/
        public Handler getHandler() {
            return handler;
        }

        /*Context*/
        public Context getContext() {
            return context;
        }

        /*初始化全局上下文*/
        public RHttp.Configure init(Application app) {
            this.context = app.getApplicationContext();
            this.handler = new Handler(Looper.getMainLooper());
            return this;
        }

    }

    public static final class Builder {

        /*请求方式*/
        Method method;
        /*请求参数*/
        Map<String, Object> parameter;
        /*header*/
        Map<String, Object> header;
        /*LifecycleProvider*/
        LifecycleProvider lifecycle;
        /*ActivityEvent*/
        ActivityEvent activityEvent;
        /*FragmentEvent*/
        FragmentEvent fragmentEvent;
        /*标识请求的TAG*/
        String tag;
        /*文件map*/
        Map<String, File> fileMap;
        /*基础URL*/
        String baseUrl;
        /*apiUrl*/
        String apiUrl;
        /*String参数*/
        String bodyString;
        /*是否强制JSON格式*/
        boolean isJson;


        public Builder() {

        }

        /*GET*/
        public Builder get() {
            method = Method.GET;
            return this;
        }

        /*POST*/
        public Builder post() {
            method = Method.POST;
            return this;
        }

        /*DELETE*/
        public RHttp.Builder delete() {
            this.method = Method.DELETE;
            return this;
        }

        /*PUT*/
        public RHttp.Builder put() {
            this.method = Method.PUT;
            return this;
        }

        /*基础url*/
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        /*设置请求参数*/
        public Builder addParameter(Map<String, Object> parameter) {
            if (this.parameter == null) {
                this.parameter = new TreeMap<>();
            }
            this.parameter.putAll(parameter);

            return this;
        }

        /*设置请求参数*/
        public Builder setParameter(Map<String, Object> parameter) {
            this.parameter = parameter;
            return this;
        }

        /*设置String类型的请求参数*/
        public Builder setBodyString(boolean isJson, String bodyString) {
            this.isJson = isJson;
            this.bodyString = bodyString;
            return this;
        }


        /*添加请求头*/
        public Builder addHeader(Map<String, Object> header) {
            if (this.header == null) {
                this.header = new TreeMap<>();
            }
            this.header.putAll(header);
            return this;
        }

        /*设置请求头*/
        public Builder setHeader(Map<String, Object> header) {
            this.header = header;
            return this;
        }

        /*rxJave 声明周期管理*/
        public Builder lifecycle(LifecycleProvider lifecycle) {
            this.lifecycle = lifecycle;
            return this;
        }

        /*activityEvent*/
        public Builder activityEvent(ActivityEvent activityEvent) {
            this.activityEvent = activityEvent;
            return this;
        }

        /*fragmentEvent*/
        public Builder fragmentEvent(FragmentEvent fragmentEvent) {
            this.fragmentEvent = fragmentEvent;
            return this;
        }

        /*tag*/
        public Builder tag(String tag) {
            this.tag = tag;
            return this;
        }

        /*上传的文件集合*/
        public Builder file(Map<String, File> fileMap) {
            this.fileMap = fileMap;
            return this;
        }

        /*一个key对应多个file*/
        public Builder file(String key, List<File> fileList) {
            if (this.fileMap == null) {
                this.fileMap = new IdentityHashMap<>();
            }

            if (fileList != null && fileList.size() > 0) {
                for (int i = 0; i < fileList.size(); i++) {
                    fileMap.put(new String(key), fileList.get(i));
                }
            }
            return this;
        }


        private RHttp build() {
            return new RHttp(this);
        }

    }
}
