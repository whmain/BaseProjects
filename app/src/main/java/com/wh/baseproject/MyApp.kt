package com.wh.baseproject

import com.uuzuche.lib_zxing.activity.ZXingLibrary
import com.wh.baseproject.http.RetrofitManager
import com.wh.library.base.BaseApp

/**
 * @author PC-WangHao on 2019/07/22 13:11.
 * E-Mail: wh_main@163.com
 * Description:
 */
 class MyApp :BaseApp(){

    val BASE_URL = "http://api.map.baidu.com";

    override fun onCreate() {
        super.onCreate()

        AppUtils.init(this)

        ZXingLibrary.initDisplayOpinion(this);
        if (isMainProcess){
            RetrofitManager.init(this,BASE_URL)
        }

    }

}