package com.wh.library.base.activity

import android.os.Bundle

/**
 * @author PC-WangHao on 2019/07/19 16:41.
 * E-Mail: wh_main@163.com
 * Description:普通Activity
 */
abstract class BaseNormalActivity : RootActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initData()
    }
}