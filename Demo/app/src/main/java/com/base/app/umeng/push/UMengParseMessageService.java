package com.base.app.umeng.push;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.android.agoo.common.AgooConstants;
import org.json.JSONObject;
import com.umeng.message.UmengMessageService;

/**
 * @date on 2019/2/21
 * @describe 推送接收消息服务类
 */
public class UMengParseMessageService extends UmengMessageService {

    @Override
    public void onMessage(Context context, Intent intent) {
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
            Log.d(PushHelper.TAG, "contId=" + contId);    //自定义消息的内容
            PushBean pushBean =
                    new PushBean(msg.title, msg.text, contId, pushType, url, shareImageUrl, shareType);
            // code  to handle message here
            Intent pushIntent = new Intent();
            pushIntent.setClass(context, NotificationService.class);
            pushIntent.putExtra("push_data", pushBean);
            context.startService(pushIntent);
        } catch (Exception e) {
            Log.e(PushHelper.TAG, e.getMessage());
        }
    }
}
