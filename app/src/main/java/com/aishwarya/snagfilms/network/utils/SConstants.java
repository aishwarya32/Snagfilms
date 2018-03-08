package com.aishwarya.snagfilms.network.utils;
/**
 * Created by AishwaryaB on 03/7/2018.
 */
/**
 * Constants or end points of 500px
 */
public interface SConstants {
    String MOVIE_SEARCH = "films.json";


    String CONTENT_TYPE = "Content-Type: ";
    String AUTHORIZATION = "Authorization";
    String CM_APPLICATION_JSON = "application/json";
    String CM_H_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";//Content/Media Type,Header
    String H_X_MS_BLOB_KEY = "x-ms-blob-type";
    String H_X_MS_BLOB_VALUE = "BlockBlob";
    String C_H_MULTIPART_FORM_DATA = "multipart/form-data";
    String GSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    String BEARER_HEAD = "Bearer ";
}
