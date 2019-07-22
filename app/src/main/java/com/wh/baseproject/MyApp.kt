package com.wh.baseproject

import com.wh.library.base.BaseApp

/**
 * @author PC-WangHao on 2019/07/22 13:11.
 * E-Mail: wh_main@163.com
 * Description:
 */
class MyApp :BaseApp(){
    override fun onCreate() {
        super.onCreate()

        AppUtils.init(this)
    }
}