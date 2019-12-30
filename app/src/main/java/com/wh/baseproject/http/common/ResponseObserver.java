package com.wh.baseproject.http.common;

import android.util.Log;

import com.google.gson.JsonParseException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by zhpan on 2017/4/18.
 */

public abstract class ResponseObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        Log.e("Retrofit", e.getMessage());
        if (e instanceof HttpException) {     //   HTTP错误
            onException(ExceptionReason.BAD_NETWORK);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {   //   连接错误
            onException(ExceptionReason.CONNECT_ERROR);
        } else if (e instanceof InterruptedIOException) {   //  连接超时
            onException(ExceptionReason.CONNECT_TIMEOUT);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {   //  解析错误
            onException(ExceptionReason.PARSE_ERROR);
        }
        else {
            onException(ExceptionReason.UNKNOWN_ERROR);
        }
        onFinish();
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param response 服务器返回的数据
     */
     public abstract void onSuccess(T response);


    /**
     * 请求异常
     *
     * @param reason
     */
    public void onException(ExceptionReason reason) {
        Log.d("ResponseObserver","网络出错onException");
//        switch (reason) {
//            case CONNECT_ERROR:
//                ToastUtils.show(R.string.connect_error, Toast.LENGTH_SHORT);
//                break;
//
//            case CONNECT_TIMEOUT:
//                ToastUtils.show(R.string.connect_timeout, Toast.LENGTH_SHORT);
//                break;
//
//            case BAD_NETWORK:
//                ToastUtils.show(R.string.bad_network, Toast.LENGTH_SHORT);
//                break;
//
//            case PARSE_ERROR:
//                ToastUtils.show(R.string.parse_error, Toast.LENGTH_SHORT);
//                break;
//
//            case UNKNOWN_ERROR:
//            default:
//                ToastUtils.show(R.string.unknown_error, Toast.LENGTH_SHORT);
//                break;
//        }
    }

    public void onFinish() {
    }

    /**
     * 请求网络失败原因
     */
    public enum ExceptionReason {
        /**
         * 解析数据失败
         */
        PARSE_ERROR,
        /**
         * 网络问题
         */
        BAD_NETWORK,
        /**
         * 连接错误
         */
        CONNECT_ERROR,
        /**
         * 连接超时
         */
        CONNECT_TIMEOUT,
        /**
         * 未知错误
         */
        UNKNOWN_ERROR,
    }
}
