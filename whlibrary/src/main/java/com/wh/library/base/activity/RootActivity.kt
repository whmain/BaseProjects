package com.wh.library.base.activity

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity


/**
 * @author PC-WangHao on 2019/07/19 11:18.
 * E-Mail: wh_main@163.com
 * Description:请不要直接使用 子类继承[BaseVMActivity] [BaseNormalActivity]
 */
abstract class RootActivity: RxAppCompatActivity() {

    abstract fun getLayoutId():Int

    abstract fun initData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())

    }



    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            val v = currentFocus
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v!!.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0, 0)
            v!!.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v!!.getHeight()
            val right = left + v!!.getWidth()
            return if (event.x > left && event.x < right && event.y > top && event.y < bottom) false else true
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false
    }

    /**
     * 多种隐藏软件盘方法的其中一种
     *
     * @param token
     */
    private fun hideSoftInput(token: IBinder?) {
        if (token != null) {
            val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(
                token,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}


