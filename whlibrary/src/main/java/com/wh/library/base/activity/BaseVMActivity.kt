package com.wh.library.base.activity

import android.os.Bundle
import com.wh.library.base.BaseViewModel

/**
 * @author PC-WangHao on 2019/07/19 16:39.
 * E-Mail: wh_main@163.com
 * Description:MVVM设计模式的Activity
 */
abstract class BaseVMActivity<E : BaseViewModel>  : RootActivity(){
    var mViewModel : E? = null

    abstract fun initViewModel():E?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mViewModel = initViewModel()
        initData()
    }
}