package com.wh.baseproject.http.utils;

import android.content.Context;
import android.util.Log;


/**
 * Created by zhpan on 2017/5/26.
 * Description:
 */

public class DialogUtils {
    //  加载进度的dialog
//    private CustomProgressDialog mProgressDialog;

    /**
     * 显示ProgressDialog
     */
    public void showProgress(Context context, String msg) {
       /* if (context == null || context.isFinishing()) {
            return;
        }*/
//        if(mProgressDialog==null){
//            mProgressDialog= new CustomProgressDialog.Builder(context)
//                    .setTheme(R.style.ProgressDialogStyle)
//                    .setMessage(msg)
//                    .build();
//        }
//        if(mProgressDialog!=null&&!mProgressDialog.isShowing()) {
//            mProgressDialog.show();
//        }
        Log.d("DialogUtils","showProgress1");
    }

    /**
     * 显示ProgressDialog
     */
    public void showProgress(Context context) {
        /*if (activity == null || activity.isFinishing()) {
            return;
        }*/
//        if(mProgressDialog==null){
//            mProgressDialog= new CustomProgressDialog.Builder(context)
//                    .setTheme(R.style.ProgressDialogStyle)
//                    .build();
//        }
//        if(mProgressDialog!=null&&!mProgressDialog.isShowing()) {
//            mProgressDialog.show();
//        }
        Log.d("DialogUtils","showProgress2");
    }

    /**
     * 取消ProgressDialog
     */
    public void dismissProgress() {
//        if (mProgressDialog != null&&mProgressDialog.isShowing()) {
//            mProgressDialog.dismiss();
//        }
        Log.d("DialogUtils","dismissProgress");
    }
}
