package com.aishwarya.snagfilms;

import android.app.Application;
import android.content.Intent;

import com.aishwarya.snagfilms.dao.SnagFilmsResponse;
import com.aishwarya.snagfilms.network.io.SnagFilmsRestService;
import com.aishwarya.snagfilms.network.utils.SConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by AishwaryaB on 03/7/2018.
 */
/**
 * @Information Application class is responsible to initialise APP (pre required loading objects,views,variables,classes...etc)
 */
public class SnagFilmsApplication extends Application {
    private static SnagFilmsApplication instance;
    private static SnagFilmsRestService snagFilmsRestService;
    private String TAG = "HMApp";
    public JSONObject errorMessage;
    private Intent mIntent;
    private SnagFilmsResponse snagFilmsResponse;

    public SnagFilmsResponse getSnagFilmsResponse() {
        return snagFilmsResponse;
    }

    public void setSnagFilmsResponse(SnagFilmsResponse snagFilmsResponse) {
        this.snagFilmsResponse = snagFilmsResponse;
    }

    public SnagFilmsApplication() {
        instance = this;
    }

    public static SnagFilmsApplication getInstance() {
        if (instance == null) {
            instance = new SnagFilmsApplication();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public SnagFilmsRestService initRetrofit() {
        OkHttpClient client = new OkHttpClient();

        Gson gson = new GsonBuilder()
            .setDateFormat(SConstants.GSON_DATE_FORMAT)
            .create();
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client).build();
        //Log.e(TAG, "client:"+client.dispatcher().getMaxRequests());
        if (snagFilmsRestService == null) {
            snagFilmsRestService = retrofit.create(SnagFilmsRestService.class);
        }
        return snagFilmsRestService;
    }

    public JSONObject setErrorMessage(String errorMessage1) {
        try {
            errorMessage = new JSONObject(errorMessage1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return errorMessage;
    }

    public String getErrorMessage() {
        String error = null;
        try {
            if (errorMessage != null) {
                if (errorMessage.has("message")) {
                    error = errorMessage.getString("message");
                } else if (errorMessage.has("error")) {
                    error = errorMessage.getString("error");
                } else {
                    error = "No Error message...!";
                }
            } else {
                error = "Problem with server,\n Please try after some time...!";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return error;
    }
}
