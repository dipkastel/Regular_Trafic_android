
package com.notrika.regular_trafic.ntt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Headers {

    @SerializedName("x-forwarded-proto")
    @Expose
    private String xForwardedProto;
    @SerializedName("host")
    @Expose
    private String host;
    @SerializedName("accept")
    @Expose
    private String accept;
    @SerializedName("accept-encoding")
    @Expose
    private String acceptEncoding;
    @SerializedName("cache-control")
    @Expose
    private String cacheControl;
    @SerializedName("cookie")
    @Expose
    private String cookie;
    @SerializedName("postman-token")
    @Expose
    private String postmanToken;
    @SerializedName("user-agent")
    @Expose
    private String userAgent;
    @SerializedName("x-forwarded-port")
    @Expose
    private String xForwardedPort;

    public String getXForwardedProto() {
        return xForwardedProto;
    }

    public void setXForwardedProto(String xForwardedProto) {
        this.xForwardedProto = xForwardedProto;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getPostmanToken() {
        return postmanToken;
    }

    public void setPostmanToken(String postmanToken) {
        this.postmanToken = postmanToken;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getXForwardedPort() {
        return xForwardedPort;
    }

    public void setXForwardedPort(String xForwardedPort) {
        this.xForwardedPort = xForwardedPort;
    }

}
