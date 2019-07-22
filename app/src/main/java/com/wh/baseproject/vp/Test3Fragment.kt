package com.wh.baseproject.vp

import android.util.Log
import com.wh.baseproject.R
import com.wh.library.base.fragment.BaseLazyFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author PC-WangHao on 2019/07/22 14:57.
 * E-Mail: wh_main@163.com
 * Description:
 */
class Test3Fragment : BaseLazyFragment() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    var countShow = 0
    var countLeave = 0


    override fun initData() {
        tvss.text = "初始化数据完成"
        Log.d("fdasfasfsafs", "第三页：初始化数据完成")
    }

    override fun onUserActive() {
        super.onUserActive()
        Log.d("fdasfasfsafs", "第三页：显示的次数${++countShow}")
    }

    override fun onUserLeave() {
        super.onUserLeave()
        Log.d("fdasfasfsafs", "第三页：隐藏的次数${++countLeave}")
    }
}