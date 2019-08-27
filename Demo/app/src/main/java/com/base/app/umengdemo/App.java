package com.base.app.umengdemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.base.app.umeng.push.PushHelper;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.push.demo.BuildConfig;

/**
 * @date on 2019/2/21
 * @describe Application
 */
public class App extends Application {

    public static App appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        initUMConfigure();
        PushHelper.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 初始化友盟推送 配置
     */
    private void initUMConfigure() {
        /*appKey，渠道号，设备类型,*/
        UMConfigure
                .init(this, "UMAppKey", "UMChannel", UMConfigure.DEVICE_TYPE_PHONE, "UMAppPushSecret");
        /*
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
        /*
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);
    }
}
