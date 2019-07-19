package com.wh.library.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author PC-WangHao on 2019/07/19 11:18.
 * E-Mail: wh_main@163.com
 * Description:请不要直接使用 子类继承[BaseVMActivity] [BaseNormalActivity]
 */
abstract class RootActivity: AppCompatActivity() {

    abstract fun getLayoutId():Int

    abstract fun initData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

    }

    fun <T> startActivity(clazz: Class<T>){
        startActivity(Intent(this@RootActivity,clazz))

    }
}


