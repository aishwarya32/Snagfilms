
package com.aishwarya.snagfilms.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SnagFilmsResponse {

    @SerializedName("films")
    @Expose
    private Films films;

    public Films getFilms() {
        return films;
    }

    public void setFilms(Films films) {
        this.films = films;
    }

}
