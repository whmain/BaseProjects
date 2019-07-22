package com.wh.library.base.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * @author PC-WangHao on 2019/07/22 13:52.
 * E-Mail: wh_main@163.com
 * Description:BaseLazyFragment只适用于[androidx.viewpager.widget.ViewPager.mOffscreenPageLimit]搭建的多Fragment的懒加载模式
 * ，并且[androidx.viewpager.widget.ViewPager.mOffscreenPageLimit] == 1
 */
abstract class BaseLazyFragment : RootFragment() {


    var mRootView: View? = null
    private var isFirstLoadView = true
    private var isViewCreated = false
    private var mLastVisibleToUser = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false)
            isFirstLoadView = true
            isViewCreated = true
        } else {
            isFirstLoadView = false
            if (mRootView?.parent != null){
                val parent = mRootView?.parent as ViewGroup
                parent.removeView(mRootView)

            }
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isFirstLoadView) {
            initData()
        }
    }


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isViewCreated && mLastVisibleToUser != isVisibleToUser) {
            if (isVisibleToUser) onUserActive() else onUserLeave()
        }
        mLastVisibleToUser = isVisibleToUser
    }

    override fun onResume() {
        super.onResume()
        if (userVisibleHint && isFirstLoadView)onUserActive()
    }


    abstract fun getLayoutId(): Int

    abstract fun initData()

    /**
     * 该页面可见时，代替[onResume]
     * */
    open fun onUserActive() {
        if (mShowLog) Log.d(TAG, "$TAG-->onUserActive()")
    }

    /**
     * 该页面不可见时，代替[onPause]
     * */
    open fun onUserLeave() {
        if (mShowLog) Log.d(TAG, "$TAG-->onUserLeave()")
    }
}