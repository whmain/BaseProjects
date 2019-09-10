package com.wh.baseproject.base

import android.content.Intent
import android.os.Bundle

import com.wh.baseproject.login.LoginProcessor
import com.wh.library.base.activity.BaseNormalActivity

/**
 * @author PC-WangHao on 2019/09/10 17:23.
 * E-Mail: wh_main@163.com
 * Description:
 */
abstract class BaseAppActivity : BaseNormalActivity() {

    fun startActivity(clazz: Class<*>) {
        if (!LoginProcessor.needLogin(this, clazz)) {
            val intent = Intent(this, clazz)
            startActivity(intent)
        }
    }

    override fun startActivity(intent: Intent) {
        if (!LoginProcessor.needLogin(this, intent)) {
            super.startActivity(intent)
        }

    }

    override fun startActivity(intent: Intent, options: Bundle?) {
        if (!LoginProcessor.needLogin(this, intent)) {
            super.startActivity(intent, options)
        }
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int) {
        if (!LoginProcessor.needLogin(this, intent)) {
            super.startActivityForResult(intent, requestCode)
        }
    }

    override fun startActivityForResult(intent: Intent, requestCode: Int, options: Bundle?) {
        if (!LoginProcessor.needLogin(this, intent)) {
            super.startActivityForResult(intent, requestCode, options)
        }
    }
}
