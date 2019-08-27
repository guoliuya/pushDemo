package com.base.app.umeng.push;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @date on 2019/2/21
 * @describe 封装推送所需实体类（根据自己的需求添加相应字段）
 */
public class PushBean implements Parcelable {

    public String title;
    public String message;
    public String pushType;
    public String contId;
    public String url;
    public String shareImageUrl;
    public String shareType;

    public PushBean() {
    }

    public PushBean(String title, String message, String contId, String pushType, String url,
            String shareImageUrl,String shareType) {
        this.title = title;
        this.message = message;
        this.pushType = pushType;
        this.contId = contId;
        this.url = url;
        this.shareImageUrl = shareImageUrl;
        this.shareType = shareType;
    }

    @Override
    public String toString() {
        return "PushBean{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", pushType='" + pushType + '\'' +
                ", contId='" + contId + '\'' +
                ", contId='" + url + '\'' +
                ", contId='" + shareImageUrl + '\'' +
                ", contId='" + shareType + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.message);
        dest.writeString(this.pushType);
        dest.writeString(this.contId);
        dest.writeString(this.url);
        dest.writeString(this.shareImageUrl);
        dest.writeString(this.shareType);
    }

    protected PushBean(Parcel in) {
        this.title = in.readString();
        this.message = in.readString();
        this.pushType = in.readString();
        this.contId = in.readString();
        this.url = in.readString();
        this.shareImageUrl = in.readString();
        this.shareType = in.readString();
    }

    public static final Creator<PushBean> CREATOR = new Creator<PushBean>() {
        @Override
        public PushBean createFromParcel(Parcel source) {
            return new PushBean(source);
        }

        @Override
        public PushBean[] newArray(int size) {
            return new PushBean[size];
        }
    };
}
