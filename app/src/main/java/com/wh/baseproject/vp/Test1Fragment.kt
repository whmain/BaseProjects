package com.wh.baseproject.vp

import android.content.Intent
import android.util.Log
import com.uuzuche.lib_zxing.activity.CaptureActivity
import com.uuzuche.lib_zxing.activity.CodeUtils
import com.wh.library.base.fragment.BaseLazyFragment
import kotlinx.android.synthetic.main.activity_main.*





/**
 * @author PC-WangHao on 2019/07/22 14:57.
 * E-Mail: wh_main@163.com
 * Description:
 */
class Test1Fragment : BaseLazyFragment() {
    var REQUEST_CODE = 100
    var countShow = 0
    var countLeave = 0
    override fun getLayoutId(): Int {
        return com.wh.baseproject.R.layout.activity_main
    }

    override fun initData() {
        tvss.text = "初始化数据完成"
        tv_scan.setOnClickListener {
            val intent = Intent(activity, CaptureActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
        Log.d("fdasfasfsafs", "第一页：初始化数据完成")
    }

    override fun onUserActive() {
        super.onUserActive()
       Log.d("fdasfasfsafs", "第一页：显示的次数${++countShow}")
    }

    override fun onUserLeave() {
        super.onUserLeave()
        Log.d("fdasfasfsafs", "第一页：隐藏的次数${++countLeave}")
    }


     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (null != data) {
                val bundle = data.extras ?: return

                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    val result = bundle.getString(CodeUtils.RESULT_STRING)
                    Log.d("fdasfasf","解析结果:$result")
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Log.d("fdasfasf","解析失败")
                }
            }
        }
    }
}