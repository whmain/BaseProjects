package com.wh.baseproject

/**
 * @author PC-WangHao on 2019/07/19 14:11.
 * E-Mail: wh_main@163.com
 * Description:
 */
data class VersionRoot(var ecgVersionOnlineUpdateEntity:VersionInfoBean):BaseMsgResult()

data class VersionInfoBean(var id:Long,var lastVersionCode:Int,var lastVersionName:String)

