package com.wh.baseproject

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wh.library.base.BaseViewModel

/**
 * @author PC-WangHao on 2019/07/19 13:53.
 * E-Mail: wh_main@163.com
 * Description:
 */
class MainViewModel : BaseViewModel() {

    val mVersionLiveData : MutableLiveData<VersionRoot> by lazy {
        MutableLiveData<VersionRoot>()
    }

    fun checkVersion(lifecycleOwner: LifecycleOwner,observer: Observer<VersionRoot>){
        mVersionLiveData.observe(lifecycleOwner,observer)
        MainReposity.Instance.checkUpdate(mVersionLiveData)

    }
}