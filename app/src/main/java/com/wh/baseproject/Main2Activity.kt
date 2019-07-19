package com.wh.baseproject

import android.util.Log
import com.wh.library.base.BaseNormalActivity
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : BaseNormalActivity() {


    override fun getLayoutId(): Int = R.layout.activity_main2

    override fun initData() {
        Log.d("dfasfasfdasf","122222222222222\n")
        te.setOnClickListener {
            startActivity(MainActivity::class.java)
        }
    }




}
