package com.wh.baseproject.vp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @author PC-WangHao on 2019/07/22 14:55.
 * E-Mail: wh_main@163.com
 * Description:
 */
class VPAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return Test1Fragment()
            1 -> return Test2Fragment()
            2 -> return Test3Fragment()
            3 -> return Test4Fragment()
            else -> return Test1Fragment()
        }

    }

    override fun getCount(): Int {
        return 4
    }
}