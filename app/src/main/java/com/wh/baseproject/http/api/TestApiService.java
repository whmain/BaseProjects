package com.wh.baseproject.http.api;

import com.wh.baseproject.http.model.Artlist;
import com.wh.baseproject.http.model.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author PC-WangHao on 2019/12/30 13:22.
 * E-Mail: wh_main@163.com
 * Description:
 */
public interface TestApiService {

    @GET("/article/list/0/json")
    Observable<BaseResponse<Artlist>> getArticle();
}
