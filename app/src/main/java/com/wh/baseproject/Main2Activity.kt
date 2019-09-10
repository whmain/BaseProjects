package com.wh.baseproject

import android.content.Intent
import android.util.Log
import android.view.View
import com.wh.baseproject.base.BaseAppActivity
import com.wh.baseproject.vp.VPAdapter
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : BaseAppActivity() {


    override fun getLayoutId(): Int = R.layout.activity_main2

    override fun initData() {
        Log.d("dfasfasfdasf","122222222222222\n")
        te.setOnClickListener {
//            startActivity(MainActivity::class.java)
            var intent = Intent(this@Main2Activity,MainActivity::class.java)
            startActivity(intent)
        }
        vpContainer.offscreenPageLimit = 1
        vpContainer.adapter = VPAdapter(supportFragmentManager)
        btn1.setOnClickListener(mOnClickListener)
        btn2.setOnClickListener(mOnClickListener)
        btn3.setOnClickListener(mOnClickListener)
        btn4.setOnClickListener(mOnClickListener)
    }


    private val mOnClickListener: View.OnClickListener = View.OnClickListener {
        when(it.id){
            R.id.btn1 -> vpContainer.currentItem = 0
            R.id.btn2 -> vpContainer.currentItem = 1
            R.id.btn3 -> vpContainer.currentItem = 2
            R.id.btn4 -> vpContainer.currentItem = 3
        }
        startActivity(Intent(this@Main2Activity,Main3Activity::class.java));
    }

}
