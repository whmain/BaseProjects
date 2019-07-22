package com.wh.baseproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.readystatesoftware.chuck.ChuckInterceptor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.Dns
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Inet4Address
import java.net.InetAddress
import java.util.concurrent.TimeUnit
import io.reactivex.Observer as Observer1

/**
 * @author PC-WangHao on 2019/07/19 13:57.
 * E-Mail: wh_main@163.com
 * Description:
 */
class MainReposity private constructor() {
    companion object {
       lateinit var mContext:Application
        val Instance = MainReposity()


    }

    var mRetrofit: Retrofit

    init {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(ChuckInterceptor(AppUtils.getApp()))
            .dns { hostname ->
                val list = Dns.SYSTEM.lookup(hostname)
                val newlist = ArrayList<InetAddress>()
                for (i in list.indices) {
                    if (list[i] is Inet4Address) {
                        newlist.add(list[i])
                    }
                }
                newlist
            }
            .build()
        mRetrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .baseUrl("http://papi.js-cyyl.com:8081")
            .build()
    }



    fun checkUpdate(liveData: MutableLiveData<VersionRoot>) {
        mRetrofit.create(ApiService::class.java)
            .getNewVersionInfo("V1.1.1")
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : io.reactivex.Observer<VersionRoot> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: VersionRoot) {
                    Log.d("dfasfasfdasf", t.toString())
                    liveData.postValue(t)
                }

                override fun onError(e: Throwable) {
                }

            })
    }
}