
package com.aishwarya.snagfilms.dao;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Renditions {

    @SerializedName("rendition")
    @Expose
    private List<Rendition> rendition = null;

    public List<Rendition> getRendition() {
        return rendition;
    }

    public void setRendition(List<Rendition> rendition) {
        this.rendition = rendition;
    }

}
