package com.jkxy.notificationdemo;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[] btns = new Button[23];

    private Integer[] IDs = {R.id.btn_normal, R.id.btn_normal1, R.id.btn_update,
            R.id.btn_cancelNormal, R.id.btn_cancelAll,
            R.id.btn_showTime, R.id.btn_noTime,
            R.id.btn_progress, R.id.btn_loading,
            R.id.btn_clickCancel, R.id.btn_clickNoCancel, R.id.btn_cantCancel,
            R.id.btn_clickActivity, R.id.btn_clickService, R.id.btn_clickBro,
            R.id.btn_bigText, R.id.btn_bigPic, R.id.btn_inBox,
            R.id.btn_noTask, R.id.btn_Task,
            R.id.btn_customView,
            R.id.btn_cancelListener,
            R.id.btn_showAll};

    private NotificationManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_normal:
                //展示一个普通的通知栏
                NotificationCompat.Builder builder0 = GetNotificationBuilder.getNormalBuilder(this, "第一个普通的通知栏标题", "第一个普通的通知栏内容");
                manager.notify(R.id.btn_normal, builder0.build());
                Toast.makeText(MainActivity.this, "btn_normal", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_normal1:
                //另外一个普通的通知栏
                NotificationCompat.Builder builder1 = GetNotificationBuilder.getNormalBuilder(this, "第二个普通的通知栏标题", "第二个普通的通知栏内容");
                manager.notify(R.id.btn_normal1, builder1.build());
                Toast.makeText(MainActivity.this, "btn_normal1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_update:
                //更新第一个普通的通知栏
                NotificationCompat.Builder builder2 = GetNotificationBuilder.getNormalBuilder(this, "更新第一个普通的通知栏标题", "更新第一个普通的通知栏内容");
                manager.notify(R.id.btn_normal, builder2.build());
                Toast.makeText(MainActivity.this, "btn_update", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancelNormal:
                //删除第一个普通的通知栏
                manager.cancel(R.id.btn_normal);
                Toast.makeText(MainActivity.this, "btn_cancelNormal", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancelAll:
                //删除所有的通知栏
                manager.cancelAll();
                Toast.makeText(MainActivity.this, "btn_cancelAll", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_showTime:
                //展示有时间的通知栏
                NotificationCompat.Builder builder3 = GetNotificationBuilder.getBuilderIsTime(this, "显示时间的通知栏标题", "显示时间的通知栏内容", true);
                manager.notify(R.id.btn_showTime, builder3.build());
                Toast.makeText(MainActivity.this, "btn_showTime", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_noTime:
                //展示一个没有时间的通知栏
                NotificationCompat.Builder builder4 = GetNotificationBuilder.getBuilderIsTime(this, "不显示时间的通知栏标题", "不显示时间的通知栏内容", false);
                manager.notify(R.id.btn_noTime, builder4.build());
                Toast.makeText(MainActivity.this, "btn_noTime", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_progress:
                //展示一个进度条
                final NotificationCompat.Builder builder5 = GetNotificationBuilder.getBuilderProgress(this, "进度条的通知栏标题", "进度条的通知栏内容");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i;
                        for (i = 0; i <= 100; i += 10) {
                            builder5.setProgress(100, i, false);
//                            builder5.setProgress(0, 0, true);
                            manager.notify(R.id.btn_progress, builder5.build());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                            }
                        }
                        builder5.setContentText("下载完成/获取成功").setProgress(0, 0,
                                false);
                        builder5.setOngoing(false); //用户可以取消
                        manager.notify(R.id.btn_progress, builder5.build());
                    }
                }
                ).start();
                Toast.makeText(MainActivity.this, "btn_progress", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_loading:
                //展示一个loading
                final NotificationCompat.Builder builder6 = GetNotificationBuilder.getBuilderProgress(this, "Loading的通知栏标题", "Loading的通知栏内容");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i;
                        for (i = 0; i <= 100; i += 10) {
                            builder6.setProgress(0, 0, true);
                            manager.notify(R.id.btn_loading, builder6.build());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                            }
                        }
                        builder6.setContentText("缓冲成功").setProgress(0, 0,
                                false);
                        builder6.setOngoing(false);
                        manager.notify(R.id.btn_loading, builder6.build());
                    }
                }
                ).start();
                Toast.makeText(MainActivity.this, "btn_loading", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_clickCancel:
                //点击后取消
                NotificationCompat.Builder builder7 = GetNotificationBuilder.getBuilderActivityIsCancel(this, "启动活动,自动消失(标题)", "启动活动,自动消失(内容)", true);
                manager.notify(R.id.btn_clickCancel, builder7.build());
                Toast.makeText(MainActivity.this, "btn_clickCancel", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_clickNoCancel:
                //点击后不取消
                NotificationCompat.Builder builder8 = GetNotificationBuilder.getBuilderActivityIsCancel(this, "启动活动,不自动消失(标题)", "启动活动,不自动消失(内容)", false);
                manager.notify(R.id.btn_clickNoCancel, builder8.build());
                Toast.makeText(MainActivity.this, "btn_clickNoCancel", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cantCancel:
                //用户无法取消
                NotificationCompat.Builder builder9 = GetNotificationBuilder.getBuilderCantCancel(this, "不能被用户删除(标题)", "不能被用户删除(内容)");
                manager.notify(R.id.btn_cantCancel, builder9.build());
                Toast.makeText(MainActivity.this, "btn_cantCancel", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_clickActivity:
                //点击进入Activity PendingIntent
                NotificationCompat.Builder builder10 = GetNotificationBuilder.getBuilderActivityIsCancel(this, "启动活动,自动消失(标题)", "启动活动,自动消失(内容)", true);
                manager.notify(R.id.btn_clickActivity, builder10.build());
                Toast.makeText(MainActivity.this, "btn_clickActivity", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_clickService:
                //点击进入Service
                NotificationCompat.Builder builder11 = GetNotificationBuilder.getBuilderService(this, "启动一个服务(标题)", "启动一个服务(内容)");
                manager.notify(R.id.btn_clickService, builder11.build());
                Toast.makeText(MainActivity.this, "btn_clickService", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_clickBro:
                //点击启动BroadcastReceiver
                NotificationCompat.Builder builder12 = GetNotificationBuilder.getBuilderBro(this, "发送一条广播(标题)", "发送一条广播(内容)");
                manager.notify(R.id.btn_clickBro, builder12.build());
                Toast.makeText(MainActivity.this, "btn_clickBro", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_bigText:
                //宽视图--多行内容
                NotificationCompat.Builder builder13 = GetNotificationBuilder.getBuilderBigText(this, "多行文字(标题)", "多行文字内容(内容)和哈哈哈哈哈哈哈哈哈哈哈哈谁谁谁水水水水水水水水");
                manager.notify(R.id.btn_bigText, builder13.build());
                Toast.makeText(MainActivity.this, "btn_bigText", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_bigPic:
                //宽视图--大图片
                NotificationCompat.Builder builder14 = GetNotificationBuilder.getBuilderBigPic(this, "大图片模式(标题)", "大图片模式(内容)");
                manager.notify(R.id.btn_bigPic, builder14.build());
                Toast.makeText(MainActivity.this, "btn_bigPic", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_inBox:
                //宽视图--收件箱
                NotificationCompat.Builder builder15 = GetNotificationBuilder.getBuilderInbox(this, "收件箱模式(标题)", "收件箱模式(内容)");
                manager.notify(R.id.btn_inBox, builder15.build());
                Toast.makeText(MainActivity.this, "btn_inBox", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_noTask:
                //启动一个活动--没有回退栈
                NotificationCompat.Builder builder16 = GetNotificationBuilder.getBuilderActivityNoTask(this, "没有回退栈(标题)", "没有回退栈(内容)");
                manager.notify(R.id.btn_noTask, builder16.build());
                Toast.makeText(MainActivity.this, "btn_noTask", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_Task:
                //启动一个活动--带有回退栈
                NotificationCompat.Builder builder17 = GetNotificationBuilder.getBuilderActivityTask(this, "带有回退栈(标题)", "带有回退栈(内容)");
                manager.notify(R.id.btn_Task, builder17.build());
                Toast.makeText(MainActivity.this, "btn_Task", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_customView:
                //自定义一个样式
                NotificationCompat.Builder builder18 = GetNotificationBuilder.getBuilderCustomView(this, "七里香", "周杰伦", getPackageName());
                manager.notify(R.id.btn_customView, builder18.build());
                Toast.makeText(MainActivity.this, "btn_customView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_cancelListener:
                //实现滑动删除监听
                NotificationCompat.Builder builder19 = GetNotificationBuilder.getBuilderCancelListener(this, "删除监听", "删除监听(内容)");
                manager.notify(R.id.btn_cancelListener, builder19.build());
                Toast.makeText(MainActivity.this, "btn_cancelListener", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_showAll:
                //展示所有元素
                NotificationCompat.Builder builder20 = GetNotificationBuilder.getBuilderShowAll(this, "展示所有的元素", "展示所有的元素(内容)");
                manager.notify(R.id.btn_showAll, builder20.build());

                Toast.makeText(MainActivity.this, "btn_showAll", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //初始化视图
    private void initView() {
        for (int i = 0; i < IDs.length; i++) {
            btns[i] = (Button) findViewById(IDs[i]);
            btns[i].setOnClickListener(this);
        }
    }

}
