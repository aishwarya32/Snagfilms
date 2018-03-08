
package com.aishwarya.snagfilms.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Films {

    @SerializedName("categories")
    @Expose
    private Object categories;
    @SerializedName("film")
    @Expose
    private List<Film> film = null;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("previosOffset")
    @Expose
    private Object previosOffset;
    @SerializedName("nextOffset")
    @Expose
    private Integer nextOffset;
    @SerializedName("pageIndex")
    @Expose
    private Integer pageIndex;
    @SerializedName("pageTotal")
    @Expose
    private Integer pageTotal;
    @SerializedName("dynamicLead")
    @Expose
    private Object dynamicLead;
    @SerializedName("version")
    @Expose
    private Object version;
    @SerializedName("versions")
    @Expose
    private Object versions;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("search_string")
    @Expose
    private Object searchString;

    public Object getCategories() {
        return categories;
    }

    public void setCategories(Object categories) {
        this.categories = categories;
    }

    public List<Film> getFilm() {
        return film;
    }

    public void setFilm(List<Film> film) {
        this.film = film;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getPreviosOffset() {
        return previosOffset;
    }

    public void setPreviosOffset(Object previosOffset) {
        this.previosOffset = previosOffset;
    }

    public Integer getNextOffset() {
        return nextOffset;
    }

    public void setNextOffset(Integer nextOffset) {
        this.nextOffset = nextOffset;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Object getDynamicLead() {
        return dynamicLead;
    }

    public void setDynamicLead(Object dynamicLead) {
        this.dynamicLead = dynamicLead;
    }

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public Object getVersions() {
        return versions;
    }

    public void setVersions(Object versions) {
        this.versions = versions;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public Object getSearchString() {
        return searchString;
    }

    public void setSearchString(Object searchString) {
        this.searchString = searchString;
    }

}
