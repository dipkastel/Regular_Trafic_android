package com.notrika.regular_trafic.Entitie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperationResult<T> {



    @SerializedName("Data")
    @Expose
    private T data;
    @SerializedName("status")
    @Expose
    private Status status;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
