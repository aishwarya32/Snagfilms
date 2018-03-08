
package com.aishwarya.snagfilms.dao;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedFilms {

    @SerializedName("relatedFilm")
    @Expose
    private List<RelatedFilm> relatedFilm = null;

    public List<RelatedFilm> getRelatedFilm() {
        return relatedFilm;
    }

    public void setRelatedFilm(List<RelatedFilm> relatedFilm) {
        this.relatedFilm = relatedFilm;
    }

}
