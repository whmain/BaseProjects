package com.wh.library.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

import static android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS;

/**
 * @author PC-WangHao on 2019/12/26 10:34.
 * E-Mail: wh_main@163.com
 * Description:  参考 https://mp.weixin.qq.com/s/DYxVucxWwTt0D61flsVv5w
 */
public class WhiteListUtil {


    /**
     * 是否在白名单中
     * @return  false：没有加入白名单
     */

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static boolean isIgnoringBatteryOptimizations(Activity context) {
        boolean isIgnoring = false;
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (powerManager != null) {
            isIgnoring = powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
        }
        return isIgnoring;
    }

    /**
     * 请求加入白名单
     */
    @RequiresPermission(REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestIgnoreBatteryOptimizations(Activity context) {
        try {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 加入厂商系统白名单，目前支持 华为、小米、OPPO、VIVO、魅族、三星、乐视、锤子
     * @param context
     */
    public static void setSystemWhiteList(Activity context){
        if (DevicesUtil.isHuawei()){
            goHuaweiSetting(context);
        }else if (DevicesUtil.isXiaomi()){
            goXiaomiSetting(context);
        }else if (DevicesUtil.isOPPO()){
            goOPPOSetting(context);
        }else if (DevicesUtil.isVIVO()){
            goVIVOSetting(context);
        }else if (DevicesUtil.isMeizu()){
            goMeizuSetting(context);
        }else if (DevicesUtil.isSamsung()){
            goSamsungSetting(context);
        }else if (DevicesUtil.isLeTV()){
            goLetvSetting(context);
        }else if (DevicesUtil.isSmartisan()){
            goSmartisanSetting(context);
        }
    }

    /**
     * 跳转到指定应用的首页
     */
    private static void showActivity(Activity context, @NonNull String packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    /**
     * 跳转到指定应用的指定页面
     */
    private static void showActivity(Activity context,@NonNull String packageName, @NonNull String activityDir) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, activityDir));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private static void goHuaweiSetting(Activity context) {
        try {
            showActivity(context,"com.huawei.systemmanager",
                    "com.huawei.systemmanager.startupmgr.ui.StartupNormalAppListActivity");
        } catch (Exception e) {
            showActivity(context,"com.huawei.systemmanager",
                    "com.huawei.systemmanager.optimize.bootstart.BootStartActivity");
        }
    }

    private static void goXiaomiSetting(Activity context) {
        showActivity(context,"com.miui.securitycenter",
                "com.miui.permcenter.autostart.AutoStartManagementActivity");
    }

    private static void goOPPOSetting(Activity context) {
        try {
            showActivity(context,"com.coloros.phonemanager");
        } catch (Exception e1) {
            try {
                showActivity(context,"com.oppo.safe");
            } catch (Exception e2) {
                try {
                    showActivity(context,"com.coloros.oppoguardelf");
                } catch (Exception e3) {
                    showActivity(context,"com.coloros.safecenter");
                }
            }
        }
    }

    private static void goVIVOSetting(Activity context) {
        showActivity(context,"com.iqoo.secure");
    }

    private static void goMeizuSetting(Activity context) {
        showActivity(context,"com.meizu.safe");
    }

    private static void goSamsungSetting(Activity context) {
        try {
            showActivity(context,"com.samsung.android.sm_cn");
        } catch (Exception e) {
            showActivity(context,"com.samsung.android.sm");
        }
    }

    private static void goLetvSetting(Activity context) {
        showActivity(context,"com.letv.android.letvsafe",
                "com.letv.android.letvsafe.AutobootManageActivity");
    }

    private static void goSmartisanSetting(Activity context) {
        showActivity(context,"com.smartisanos.security");
    }


}
