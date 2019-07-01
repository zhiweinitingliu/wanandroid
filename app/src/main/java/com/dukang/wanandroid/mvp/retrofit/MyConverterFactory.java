package com.dukang.wanandroid.mvp.retrofit;

import com.google.gson.Gson;

import retrofit2.Converter;


/**
 * @Description :
 * @Author : wdk
 * @CretaTime : 2019/7/1 17:53
 * @LastModify(最终修改人) :wdk
 * @LastModifyTime(最终修改时间) : 2019/7/1 17:53
 * @LastCheckBy :wdk
 */
public class MyConverterFactory extends Converter.Factory {

    /**
     * Create an instance using a default {@link Gson} instance for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
//    public static GsonConverterFactory create() {
//        return create(new Gson());
//    }
//
//    /**
//     * Create an instance using {@code gson} for conversion. Encoding to JSON and
//     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
//     */
//    @SuppressWarnings("ConstantConditions") // Guarding public API nullability.
//    public static GsonConverterFactory create(Gson gson) {
//        if (gson == null) throw new NullPointerException("gson == null");
//        return new GsonConverterFactory(gson);
//    }
//
//    private final Gson gson;
//
//    private GsonConverterFactory(Gson gson) {
//        this.gson = gson;
//    }

//    @Override
//    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
//                                                            Retrofit retrofit) {
//        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
//        return new GsonResponseBodyConverter<>(gson, adapter);
//    }
//
//    @Override
//    public Converter<?, RequestBody> requestBodyConverter(Type type,
//                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
//        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
//        return new GsonRequestBodyConverter<>(gson, adapter);
//    }
}
