package com.notrika.regular_trafic.Entitie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Req_Update {


    @SerializedName("CurrenVersion")
    @Expose
    private String currenVersion;

    public String getCurrenVersion() {
        return currenVersion;
    }

    public void setCurrenVersion(String currenVersion) {
        this.currenVersion = currenVersion;
    }

}
