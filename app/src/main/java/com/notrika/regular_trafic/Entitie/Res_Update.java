package com.notrika.regular_trafic.Entitie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Res_Update {


    @SerializedName("LatestVersion")
    @Expose
    private Integer latestVersion;
    @SerializedName("Force")
    @Expose
    private Boolean force;
    @SerializedName("updateNeeded")
    @Expose
    private Boolean updateNeeded;

    public Integer getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(Integer latestVersion) {
        this.latestVersion = latestVersion;
    }

    public Boolean getForce() {
        return force;
    }

    public void setForce(Boolean force) {
        this.force = force;
    }

    public Boolean getUpdateNeeded() {
        return updateNeeded;
    }

    public void setUpdateNeeded(Boolean updateNeeded) {
        this.updateNeeded = updateNeeded;
    }
}
