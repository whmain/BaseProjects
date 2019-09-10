package com.wh.baseproject

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.wh.baseproject.login.Login
import com.wh.library.base.activity.BaseVMActivity

@Login(isNeed = true)
class MainActivity : BaseVMActivity<MainViewModel>() {

    override fun initViewModel(): MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initData() {

        mViewModel?.checkVersion(this@MainActivity, Observer{
            Log.d("dfasfasfdasf","122222222222222\n${it.toString()}")
        })
        Test.activity = this
    }


}
