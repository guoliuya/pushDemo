package com.base.app.umeng.push;

import org.json.JSONException;
import org.json.JSONObject;

import com.umeng.message.entity.UMessage;

/**
 * @date on 2019/2/21
 * @describe 友盟推送消息体类
 */
public class ElectricityNetUMessage extends UMessage {

    public ElectricityNetUMessage(JSONObject jsonObject) throws JSONException {
        super(jsonObject);
    }
}
