package com.notrika.regular_trafic.Entitie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqMyReports {

    @SerializedName("licensePlate")
    @Expose
    private String licensePlate;
    @SerializedName("NotifyToMe")
    @Expose
    private Boolean notifyToMe;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Boolean getNotifyToMe() {
        return notifyToMe;
    }

    public void setNotifyToMe(Boolean notifyToMe) {
        this.notifyToMe = notifyToMe;
    }
}
