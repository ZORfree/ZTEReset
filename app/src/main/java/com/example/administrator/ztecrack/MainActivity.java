package com.example.administrator.ztecrack;

import android.content.pm.PackageManager;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.view.View;
import android.widget.Button;

import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonInit();
        if ((this.getApplicationInfo().flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
            Log.d("ADB", "这是一个系统APP");

        }
        else {
            Log.d("ADB", "这不是一个系统APP");
            Toast.makeText(MainActivity.this, "没有按照步骤安装，请重试", Toast.LENGTH_SHORT).show();
//            System.exit(0);
        }
        if (getApplicationContext().checkCallingOrSelfPermission("android.permission.WRITE_SECURE_SETTINGS") == PackageManager.PERMISSION_GRANTED) {
            Log.d("ADB", "我有权限");
        }
        else {
            Log.d("ADB", "我没有权限");
            Toast.makeText(MainActivity.this, "没有按照步骤安装，请重试", Toast.LENGTH_SHORT).show();
//            System.exit(0);
        }
    }

    public void buttonInit() {
        Refresh();
        Button button = (Button) findViewById(R.id.Reset);
        button.setOnClickListener(this);

    }

    public void Refresh(){
        try{
            setTitle("ZTECrack  剩余可用次数:"+Integer.toString(Operation.getRemainNum(this)));
        }catch (Exception e){
            Log.d("ADB", e.toString());
        }

    }

    public void reSet(){
        if(Operation.getVersionCode(this) > 178865){
            Operation.putNum(this,100);
        }
        else{
            Operation.putNum(this,50);
        }
    }

    @Override
    public void onClick(View v) {
        reSet();
        Refresh();
    }


}
