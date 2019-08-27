package com.base.app.umeng.push;

import java.util.Random;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import com.umeng.push.demo.R;

/**
 * @date on 2019/2/21
 * @describe 通知辅助服务类
 */
public class NotificationService extends Service {
    private static final String TAG = NotificationService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            PushBean pushBean = intent.getParcelableExtra("push_data");
            if (pushBean != null) {
                showNotification(pushBean);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotification(PushBean pushBean) {
        int id = new Random(System.nanoTime()).nextInt();
        NotificationManager manager = (NotificationManager) this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder;
        if (manager != null) {
            String channelID = "theicppcccn";
            String channelName = getString(R.string.app_name);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(channelID, channelName,
                        NotificationManager.IMPORTANCE_HIGH);
                manager.createNotificationChannel(channel);
                builder = new Notification.Builder(this, channelID);
            } else {
                builder = new Notification.Builder(this);
            }
            builder.setContentTitle(pushBean.title)
                    .setContentText(pushBean.message)
                    .setTicker(pushBean.title)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true);
            Notification notification = builder.build();
            notification.deleteIntent = getDismissPendingIntent(this, pushBean);
            notification.contentIntent = getClickPendingIntent(this, pushBean);
            manager.notify(id, notification);
        }
    }

    public PendingIntent getClickPendingIntent(Context context, PushBean pushBean) {
        Intent clickIntent = new Intent();
        clickIntent.setClass(context, NotificationBroadcast.class);
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG, pushBean);
        clickIntent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION, NotificationBroadcast.ACTION_CLICK);

        return PendingIntent.getBroadcast(context, (int) (System.currentTimeMillis()),
                clickIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public PendingIntent getDismissPendingIntent(Context context, PushBean pushBean) {
        Intent deleteIntent = new Intent();
        deleteIntent.setClass(context, NotificationBroadcast.class);
        deleteIntent.putExtra(NotificationBroadcast.EXTRA_KEY_MSG, pushBean);
        deleteIntent.putExtra(NotificationBroadcast.EXTRA_KEY_ACTION, NotificationBroadcast.ACTION_DISMISS);
        return PendingIntent.getBroadcast(context, (int) (System.currentTimeMillis() + 1),
                deleteIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
