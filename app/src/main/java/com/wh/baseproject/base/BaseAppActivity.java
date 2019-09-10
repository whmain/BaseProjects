package com.wh.baseproject.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.wh.baseproject.login.LoginProcessor;
import com.wh.library.base.activity.BaseNormalActivity;

/**
 * @author PC-WangHao on 2019/09/10 17:23.
 * E-Mail: wh_main@163.com
 * Description:
 */
public abstract class BaseAppActivity extends BaseNormalActivity {

    public void startActivity(Class clazz){
        if (!LoginProcessor.needLogin(this,clazz)){
            Intent intent = new Intent(this,clazz);
            startActivity(intent);
        }
    }

    @Override
    public void startActivity(Intent intent) {
        if (!LoginProcessor.needLogin(this,intent)){
            super.startActivity(intent);
        }

    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        if (!LoginProcessor.needLogin(this,intent)){
            super.startActivity(intent, options);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (!LoginProcessor.needLogin(this,intent)){
            super.startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (!LoginProcessor.needLogin(this,intent)){
            super.startActivityForResult(intent, requestCode, options);
        }
    }
}
