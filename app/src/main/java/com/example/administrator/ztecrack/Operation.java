package com.example.administrator.ztecrack;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;

import java.util.List;

public class Operation {


    static int getRemainNum(Context context){
        Integer remainNum;
        String adbNum = getNum(context);

        if (TextUtils.isEmpty(adbNum)){
            Log.d("ADB", "当前无剩余可用次数");
            remainNum = 0;
        }
        else{
            remainNum = Integer.parseInt(decrypt(adbNum)) - 99;
        }
        return remainNum;
    }


    private static String getNum(Context context){
        String adbNum = Settings.Global.getString(context.getContentResolver(),"adb_num");
        Log.d("ADB",adbNum);
        return adbNum;
    }

    static void putNum(Context context,int num){
        Settings.Global.putString(context.getContentResolver(),"adb_num",decrypt(Integer.toString(num+99)));
        getNum(context);
    }


    private static String decrypt(String paramString)
    {
        char[] param = paramString.toCharArray();
        for (int i = 0; i < param.length; i++) {
            param[i] = ((char)(char)(param[i] ^ 0x74));
        }
        return new String(param);
    }


    /**
     * [获取应用程序版本名称信息]
     * @return 当前应用的版本名称
     */
     public static int getVersionCode(Context context) {
        List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            Log.v("ADB", packageInfo.packageName);
            if (packageInfo.packageName.equals("com.zte.adb")) {
                Log.d("ADB", "getVersionCode: " + packageInfo.versionCode);
                return packageInfo.versionCode;
            }
        }
        return 0;
    }
}
