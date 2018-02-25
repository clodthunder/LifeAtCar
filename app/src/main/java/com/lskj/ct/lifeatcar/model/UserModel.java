package com.lskj.ct.lifeatcar.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by thunder on 2018/2/18.
 */

public class UserModel implements Parcelable {

    private String name;
    private String pwd;
    private String age;
    private String nickName;
    private String headerIcon;

    public UserModel() {
    }


    protected UserModel(Parcel in) {
        name = in.readString();
        pwd = in.readString();
        age = in.readString();
        nickName = in.readString();
        headerIcon = in.readString();
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(pwd);
        dest.writeString(age);
        dest.writeString(nickName);
        dest.writeString(headerIcon);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeaderIcon() {
        return headerIcon;
    }

    public void setHeaderIcon(String headerIcon) {
        this.headerIcon = headerIcon;
    }
}
