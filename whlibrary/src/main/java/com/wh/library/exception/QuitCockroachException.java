package com.wh.library.exception;

/**
 * Created by WangHao on 2018/8/17.
 * E-Mail: wh_main@163.com
 * Description:
 */
public class QuitCockroachException extends RuntimeException {
    public QuitCockroachException(String message) {
        super(message);
    }
}
