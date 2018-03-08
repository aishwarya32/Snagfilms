package com.aishwarya.snagfilms.network.io;

import com.aishwarya.snagfilms.dao.SnagFilmsResponse;
import com.aishwarya.snagfilms.network.utils.SConstants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by AishwaryaB on 03/7/2018.
 */
/*This interface contains all network related REST API calls*/
public interface SnagFilmsRestService {
    //    limit,pageIndex
    @Headers({SConstants.CONTENT_TYPE + "" + SConstants.CM_APPLICATION_JSON})
    @GET(SConstants.MOVIE_SEARCH)
    Call<SnagFilmsResponse> doMoviesGridView(@Query("limit") int offsetLimit);

}
