package com.base.app.umeng.push;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.android.agoo.huawei.HuaWeiRegister;
import org.android.agoo.xiaomi.MiPushRegistar;

import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;

/**
 * @date on 2019/2/21
 * @describe 推送帮助类
 */
public class PushHelper {

    public static String TAG = PushHelper.class.getSimpleName();

    public static void init(Application application) {
        UMengPushUtil.init(application);
    }

    public static class UMengPushUtil {

        private static boolean sHasInit;

        public static void init(final Application context) {
            if (!sHasInit) {
                sHasInit = true;
                PushAgent mPushAgent = PushAgent.getInstance(context);
                mPushAgent.setPushIntentServiceClass(UMengParseMessageService.class);
                MiPushRegistar.register(context, "MIPushAppId", "MIPushAppKey");
                HuaWeiRegister.register(context);

                //注册推送服务，每次调用register方法都会回调该接口
                try {
                    mPushAgent.register(new IUmengRegisterCallback() {

                        @Override
                        public void onSuccess(String deviceToken) {
                            //注册成功会返回device token
                            Log.d("PushHelper",
                                    "PushAgent register onSuccess, (deviceToken: " + deviceToken + ")");

                        }

                        @Override
                        public void onFailure(String s, String s1) {
                            Log.d("PushHelper", "PushAgent register onFailure, (" + s + ", " + s1 + ")");
                        }
                    });
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //sdk开启通知声音
                mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
            }
        }
    }

    public static void openPushPager(Context context, PushBean pushBean) {
        //点击推送通知跳转到相应的页面
    }

}
