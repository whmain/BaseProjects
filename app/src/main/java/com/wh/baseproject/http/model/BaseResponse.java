package com.wh.baseproject.http.model;

/**
 * @author PC-WangHao on 2019/12/30 13:28.
 * E-Mail: wh_main@163.com
 * Description:
 */
public class BaseResponse<T> {
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
