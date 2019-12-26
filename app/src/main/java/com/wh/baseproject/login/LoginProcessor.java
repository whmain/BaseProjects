package com.wh.baseproject.login;

import android.content.Context;
import android.content.Intent;

import com.wh.baseproject.LoginActivity;

import java.lang.annotation.Annotation;

/**
 * @author PC-WangHao on 2019/09/10 18:23.
 * E-Mail: wh_main@163.com
 * Description:登录的监听处理 如需要登录监听在对应的Activity类上增加{@link Login}
 */
public class LoginProcessor {

    public static boolean needLogin(Context context, Intent intent){
        String className = intent.getComponent().getClassName();
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try {
            Class clazz = loader.loadClass(className);
            return needLogin(context,clazz);
        } catch (ClassNotFoundException e) {
            return false;
//            throw new RuntimeException("Class Not Found Exception");
        }
    }

    public static boolean needLogin(Context context,Class clazz){
        Annotation[] annotations = clazz.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof Login){
                Login login = (Login) annotations[i];
                if (login.isNeed() && isLogin()){
                    goLogin(context);
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private static boolean isLogin(){
        return false;
    }

    private static void goLogin(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setClass(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
