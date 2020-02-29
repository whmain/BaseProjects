package com.wh.baseproject.http;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhpan on 2018/3/21.
 */

public class RetrofitManager {
    private static final int DEFAULT_TIMEOUT = 20000;
    private static String BASE_URL = "";

    private static RetrofitManager mInstance;
    private Retrofit mRetrofit;
    private static File mCacheFile;
    public static RetrofitManager getInstance() {
        if (null == mInstance) {
            mInstance = new RetrofitManager();
        }
        return mInstance;
    }

    private RetrofitManager() {
        initRetrofit();
    }

    private void initRetrofit (){
        Cache cache = new Cache(mCacheFile, 1024 * 1024 * 100); //100Mb
        OkHttpClient okHttpClient =  new OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS)
                .cache(cache)
                .build();
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static void init(Context context,String baseUrl){
        mCacheFile = context.getCacheDir();
        BASE_URL = baseUrl;
    }

    public <T> T create(final Class<T> service) {
        return mRetrofit.create(service);
    }
}
