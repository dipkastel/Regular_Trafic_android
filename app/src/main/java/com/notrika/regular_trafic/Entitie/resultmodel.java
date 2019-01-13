
package com.notrika.regular_trafic.ntt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class resultmodel {


    @SerializedName("headers")
    @Expose
    private Headers headers;

    public Headers getHeaders() {
        return headers;
    }

    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

}
