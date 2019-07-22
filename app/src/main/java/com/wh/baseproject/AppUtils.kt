package com.wh.baseproject

import android.util.Log
import com.wh.library.base.BaseApp

/**
 * @author PC-WangHao on 2019/07/22 13:04.
 * E-Mail: wh_main@163.com
 * Description:
 */
object AppUtils {
    private var mApplication: BaseApp? = null

    fun init(application: BaseApp) {
        mApplication = application
        Log.d("dfasfasaf",application.toString())
    }

    fun getApp(): BaseApp? {
        return mApplication
    }
}