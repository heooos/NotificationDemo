package com.jkxy.notificationdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StartedReceiver extends BroadcastReceiver {
    public StartedReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getStringExtra("key") == null) {
            Toast.makeText(context, "已经接收到广播了", Toast.LENGTH_SHORT).show();
        } else {
            switch (intent.getStringExtra("key")) {
                case "收藏":
                    Toast.makeText(context, "收藏", Toast.LENGTH_SHORT).show();
                    break;
                case "上一首":
                    Toast.makeText(context, "上一首", Toast.LENGTH_SHORT).show();
                    break;
                case "下一首":
                    Toast.makeText(context, "下一首", Toast.LENGTH_SHORT).show();
                    break;
                case "播放":
                    Toast.makeText(context, "播放", Toast.LENGTH_SHORT).show();
                    break;
                case "歌词":
                    Toast.makeText(context, "打开歌词", Toast.LENGTH_SHORT).show();
                    break;
            }
        }


    }
}
