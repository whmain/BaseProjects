package com.wh.baseproject.http.model;

import java.util.List;

/**
 * @author PC-WangHao on 2019/12/30 14:20.
 * E-Mail: wh_main@163.com
 * Description:
 */
public class BaseResponseList<T> {
    private int errorCode;
    private String errorMsg;
    private List<T> data;

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
