package com.wh.library.base.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.trello.rxlifecycle2.components.support.RxFragment

/**
 * @author PC-WangHao on 2019/07/22 14:45.
 * E-Mail: wh_main@163.com
 * Description:
 */
open class RootFragment : RxFragment() {

    protected val TAG:String by lazy {
        javaClass.simpleName
    }

    open var mShowLog = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       if (mShowLog) Log.d(TAG, "$TAG-->onCreate()")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (mShowLog) Log.d(TAG, "$TAG-->onCreateView()")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mShowLog) Log.d(TAG, "$TAG-->onViewCreated()")
    }

    override fun onStart() {
        super.onStart()
        if (mShowLog) Log.d(TAG, "$TAG-->onStart()")
    }

    override fun onResume() {
        super.onResume()
        if (mShowLog)Log.d(TAG, "$TAG-->onResume()")
    }

    override fun onPause() {
        super.onPause()
        if (mShowLog) Log.d(TAG, "$TAG-->onPause()")
    }

    override fun onStop() {
        super.onStop()
        if (mShowLog) Log.d(TAG, "$TAG-->onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (mShowLog) Log.d(TAG, "$TAG-->onDestroyView()")
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mShowLog) Log.d(TAG, "$TAG-->onDestroy()")
    }
}