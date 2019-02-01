package com.notrika.regular_trafic.Entitie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Req_register {



    @SerializedName("deviceId")
    @Expose
    private String deviceId;
    @SerializedName("notificationToken")
    @Expose
    private String notificationToken;
    @SerializedName("appVersionName")
    @Expose
    private String appVersionName;
    @SerializedName("appVersionNumber")
    @Expose
    private Double appVersionNumber;
    @SerializedName("osVersion")
    @Expose
    private Integer osVersion;
    @SerializedName("deviceType")
    @Expose
    private Integer deviceType;
    @SerializedName("DeviceModel")
    @Expose
    private String deviceModel;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public Double getAppVersionNumber() {
        return appVersionNumber;
    }

    public void setAppVersionNumber(Double appVersionNumber) {
        this.appVersionNumber = appVersionNumber;
    }

    public Integer getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(Integer osVersion) {
        this.osVersion = osVersion;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
}
