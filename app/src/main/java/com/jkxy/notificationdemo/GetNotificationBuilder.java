package com.jkxy.notificationdemo;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

/**
 * Created by zh on 2017/2/16.
 */

public class GetNotificationBuilder {


    /**
     * 常见的Notification
     * 包括必须的三个部分:小图标、标题、内容
     *
     * @param context
     * @return
     */
    static NotificationCompat.Builder getNormalBuilder(Context context, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.panda_small); //小图标
        builder.setContentTitle(contentTitle);//标题
        builder.setContentText(contentText);//详细文本
        return builder;
    }

    static NotificationCompat.Builder getBuilderIsTime(Context context, String contentTitle, String contentText, boolean time) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, contentText);
        builder.setShowWhen(time);
        return builder;
    }

    static NotificationCompat.Builder getBuilderProgress(Context context, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, contentText);
        builder.setOngoing(true); //不可被用户取消
        return builder;
    }

    static NotificationCompat.Builder getBuilderActivityIsCancel(Context context, String contentTitle, String contentText, boolean cancel) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, contentText);
        PendingIntent pendingIntent = GetPendingIntent.getActivity(context);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(cancel);
        return builder;
    }

    static NotificationCompat.Builder getBuilderCantCancel(Context context, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, contentText);
        builder.setOngoing(true);
        return builder;
    }

    static NotificationCompat.Builder getBuilderService(Context context, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, contentText);
        PendingIntent pendingIntent = GetPendingIntent.getService(context);
        builder.setContentIntent(pendingIntent);
        return builder;
    }

    static NotificationCompat.Builder getBuilderBro(Context context, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, contentText);
        PendingIntent pendingIntent = GetPendingIntent.getBro(context, null, 0);
        builder.setContentIntent(pendingIntent);
        return builder;
    }

    static NotificationCompat.Builder getBuilderBigText(Context context, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, "内容");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText(contentText));
        builder.setContentIntent(GetPendingIntent.getBro(context, null, 0));
        return builder;
    }

    static NotificationCompat.Builder getBuilderBigPic(Context context, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, contentText);
        builder.setStyle(new NotificationCompat.BigPictureStyle().setBigContentTitle("显示图片后的标题").setSummaryText("summary").bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.bigpic)));
        return builder;
    }

    static NotificationCompat.Builder getBuilderInbox(Context context, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, contentText);
        builder.setStyle(new NotificationCompat.InboxStyle()
                .addLine("第一行")
                .addLine("第二行")
                .addLine("第三行")
                .setBigContentTitle("收件箱")
                .setSummaryText("触摸查看更多"));
        builder.setAutoCancel(true);
        builder.setContentIntent(GetPendingIntent.getActivity(context));
        return builder;
    }

    static NotificationCompat.Builder getBuilderActivityNoTask(Context context, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, contentText);
        PendingIntent pendingIntent = GetPendingIntent.getActivityNoTask(context);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        return builder;

    }

    static NotificationCompat.Builder getBuilderActivityTask(Context context, String contentTitle, String contentText) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, contentText);
        PendingIntent pendingIntent = GetPendingIntent.getActivityTask(context);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        return builder;
    }

    static NotificationCompat.Builder getBuilderCustomView(Context context, String contentTitle, String content, String packageName) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, content);
        RemoteViews views = new RemoteViews(packageName, R.layout.remote_view);
        views.setTextViewText(R.id.tv_title, "七里香");
        views.setTextViewText(R.id.tv_content, "周杰伦");
//        views.setImageViewResource(R.id.image, R.drawable.ic_launcher);

        views.setOnClickPendingIntent(R.id.pic, GetPendingIntent.getActivityTask(context));
        views.setOnClickPendingIntent(R.id.heart, GetPendingIntent.getBro(context, "收藏", 1));
        views.setOnClickPendingIntent(R.id.next, GetPendingIntent.getBro(context, "下一首", 2));
        views.setOnClickPendingIntent(R.id.previous, GetPendingIntent.getBro(context, "上一首", 3));
        views.setOnClickPendingIntent(R.id.play, GetPendingIntent.getBro(context, "播放", 4));
        views.setOnClickPendingIntent(R.id.lrc, GetPendingIntent.getBro(context, "歌词", 5));
        builder.setCustomBigContentView(views);
        builder.setOngoing(true);
        return builder;
    }

    /**
     * @param context
     * @param contentTitle
     * @param content
     * @return
     */
    static NotificationCompat.Builder getBuilderCancelListener(Context context, String contentTitle, String content) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, content);
        builder.setContentIntent(GetPendingIntent.getActivity(context));
        builder.setDeleteIntent(GetPendingIntent.getActivity(context));
        return builder;
    }

    /**
     * api 19 开始支持添加操作按钮，每个展开的通知可包含最多3个操作按钮
     * @param context
     * @param contentTitle
     * @param content
     * @return
     */
    static NotificationCompat.Builder getBuilderShowAll(Context context, String contentTitle, String content) {
        NotificationCompat.Builder builder = getNormalBuilder(context, contentTitle, content);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.zjl));
        builder.addAction(R.drawable.heart, "感谢", GetPendingIntent.getActivity(context));
        builder.setNumber(10);
        return builder;
    }

}
