package com.wh.baseproject

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author PC-WangHao on 2019/07/19 14:07.
 * E-Mail: wh_main@163.com
 * Description:
 */
interface ApiService {
    @GET("/cp/pub/android_new_version")
    fun getNewVersionInfo(@Query("versionName") versionName:String): Observable<VersionRoot>
}