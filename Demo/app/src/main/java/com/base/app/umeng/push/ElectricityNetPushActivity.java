package com.base.app.umeng.push;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;
import com.umeng.message.UmengNotifyClickActivity;

/**
 * @date on 2019/2/21
 * @describe 小米华为系统弹窗处理类
 */
public class ElectricityNetPushActivity extends UmengNotifyClickActivity {

    private static final String TAG = ElectricityNetPushActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onMessage(Intent intent) {
        super.onMessage(intent);  //此方法必须调用，否则无法统计打开数
        Log.d(TAG, "onMessage");
        try {
            //可以通过MESSAGE_BODY取得消息体，并打印相关Log
            String message = intent.getStringExtra(AgooConstants.MESSAGE_BODY);
            JSONObject jsonObject = new JSONObject(message);
            ElectricityNetUMessage msg = new ElectricityNetUMessage(jsonObject);

            Log.d(PushHelper.TAG, message);

            JSONObject extra = jsonObject.getJSONObject(Common.PUSH_EXTRA);
            String contId = extra.optString(Common.PUSH_CONTID);
            String pushType = extra.optString(Common.PUSH_TYPE);
            String url = extra.optString(Common.PUSH_URL);
            String shareImageUrl = extra.optString(Common.PUSH_SHARE_IMAGE_URL);
            String shareType = extra.optString(Common.PUSH_SHARE_TYPE);

            Log.d(PushHelper.TAG, "message=" + message);    //消息体
            Log.d(PushHelper.TAG, "title=" + msg.title);    //通知标题
            Log.d(PushHelper.TAG, "text=" + msg.text);    //通知内容
            Log.d(PushHelper.TAG, "contId=" + contId);

            PushBean pushBean =
                    new PushBean(msg.title, msg.text, contId, pushType, url, shareImageUrl, shareType);

            //推送跳转
            PushHelper.openPushPager(ElectricityNetPushActivity.this, pushBean);
        } catch (Exception e) {
            Log.e(PushHelper.TAG, e.getMessage());
        } finally {
            delayFinish(1);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        delayFinish(1);
    }

    private void delayFinish(int delay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ElectricityNetPushActivity.this.finish();
            }
        }, delay);
    }
}
