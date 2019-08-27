package com.base.app.umeng.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * @date on 2019/2/21
 * @describe 通知状态广播接收类
 */
public class NotificationBroadcast extends BroadcastReceiver {
    public static final String TAG = NotificationBroadcast.class.getName();

    public static final String EXTRA_KEY_ACTION = "ACTION";
    public static final String EXTRA_KEY_MSG = "MSG";

    public static final int ACTION_CLICK = 10;
    public static final int ACTION_DISMISS = 11;
    public static final int EXTRA_ACTION_NOT_EXIST = -1;

    @Override
    public void onReceive(Context context, Intent intent) {
        PushBean pushBean = intent.getParcelableExtra(EXTRA_KEY_MSG);
        int action = intent.getIntExtra(EXTRA_KEY_ACTION, EXTRA_ACTION_NOT_EXIST);
        try {
            switch (action) {
                case ACTION_DISMISS:
                    Log.d(TAG, "dismiss notification");
                    break;
                case ACTION_CLICK:
                    Log.d(TAG, "click notification");
                    //推送跳转
                    Log.d(TAG, "click notification" + pushBean.pushType);
                    PushHelper.openPushPager(context, pushBean);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
