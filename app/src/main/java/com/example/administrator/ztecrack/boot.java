package com.example.administrator.ztecrack;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class boot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("ADB", "开机已启动");
//        Toast.makeText(context,"开机已启动",Toast.LENGTH_SHORT).show();
        try {
            if (Operation.getRemainNum(context) < 10) {
                Operation.putNum(context,50);
            }
        }catch (Exception e){
            Log.d("ADB", e.toString());
//            Toast.makeText(context,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}
