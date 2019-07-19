package com.wh.library.base

import android.app.ActivityManager
import android.app.Application
import android.content.Context

/**
 * @author PC-WangHao on 2019/07/19 16:57.
 * E-Mail: wh_main@163.com
 * Description:
 */
class BaseApp : Application() {

    /**
    * 是否是主进程
    */
    var isMainProcess = true

    override fun onCreate() {
        super.onCreate()
        isMainProcess = applicationContext.packageName.equals(getCurrentProcessName())
    }


    /**
     * 获取当前进程名
     */
    private fun getCurrentProcessName(): String {
        val pid = android.os.Process.myPid()
        var processName = ""
        val manager = applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (process in manager.runningAppProcesses) {
            if (process.pid == pid) {
                processName = process.processName
            }
        }
        return processName
    }
}