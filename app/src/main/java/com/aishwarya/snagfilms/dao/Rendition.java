
package com.aishwarya.snagfilms.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rendition {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("bitrate")
    @Expose
    private Object bitrate;
    @SerializedName("key")
    @Expose
    private Object key;
    @SerializedName("resolution")
    @Expose
    private Object resolution;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getBitrate() {
        return bitrate;
    }

    public void setBitrate(Object bitrate) {
        this.bitrate = bitrate;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getResolution() {
        return resolution;
    }

    public void setResolution(Object resolution) {
        this.resolution = resolution;
    }

}
