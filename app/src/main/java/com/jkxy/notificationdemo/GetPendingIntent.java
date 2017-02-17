package com.jkxy.notificationdemo;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;

/**
 * Created by zh on 2017/2/16.
 */

public class GetPendingIntent {

    static String ACTION = "com.jkxy.notificationdemo.mybro";

    /**
     * 1：PendingIntent.FLAG_UPDATE_CURRENT这个参数一般有四种选择分别是：
     *
     * FLAG_CANCEL_CURRENT：如果构建的PendingIntent已经存在，则取消前一个，重新构建一个。
     * FLAG_NO_CREATE：如果前一个PendingIntent已经不存在了，将不再构建它。
     * FLAG_ONE_SHOT：表明这里构建的PendingIntent只能使用一次。
     * FLAG_UPDATE_CURRENT：如果构建的PendingIntent已经存在，那么系统将不会重复创建，只是把之前不同的传值替换掉。
     *
     * @param context
     * @return
     */
    static PendingIntent getActivity(Context context) {
        Intent intent = new Intent(context, StartedActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    static PendingIntent getService(Context context) {
        Intent intent = new Intent(context, StartedService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    static PendingIntent getBro(Context context, String key ,int requestCode) {

        Intent intent = new Intent(ACTION);
        if (key != null) {
            intent.putExtra("key", key);
        }
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    static PendingIntent getActivityNoTask(Context context) {
        Intent intent = new Intent(context, AloneActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pendingIntent;
    }

    static PendingIntent getActivityTask(Context context) {
        Intent notifyIntent = new Intent(context, BackToMainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(BackToMainActivity.class);
        stackBuilder.addNextIntent(notifyIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0, PendingIntent.FLAG_UPDATE_CURRENT);
        return resultPendingIntent;
    }

}
