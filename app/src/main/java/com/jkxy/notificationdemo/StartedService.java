package com.jkxy.notificationdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class StartedService extends Service {

    @Override
    public void onCreate() {
        Toast.makeText(this,"我是一个服务,我被通知栏给启动了",Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    public StartedService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
