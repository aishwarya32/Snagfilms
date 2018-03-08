package com.aishwarya.snagfilms.network.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.aishwarya.snagfilms.R;

/**
 * Created by AishwaryaB on 03/7/2018.
 */
public class NetConnectionCheck {
    private static int TYPE_WIFI = 1;
    private static int TYPE_MOBILE = 2;
    private static final int TYPE_ETHERNET = 9;
    private static int TYPE_NOT_CONNECTED = 0;
    private static boolean NET_FLAG = true;

    private static int getConnectivityStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
            .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
            if (activeNetwork.getType() == ConnectivityManager.TYPE_ETHERNET)
                return TYPE_ETHERNET;
        }
        return TYPE_NOT_CONNECTED;
    }

    private static void getConnectivityStatusString(Context context) {
        int conn = NetConnectionCheck.getConnectivityStatus(context);
        String status = null;
        if (conn == NetConnectionCheck.TYPE_WIFI) {
            status = "Wifi enabled";
            NET_FLAG = true;
        } else if (conn == NetConnectionCheck.TYPE_MOBILE) {
            status = "Mobile data enabled";
            NET_FLAG = true;
        } else if (conn == NetConnectionCheck.TYPE_ETHERNET) {
            status = "Ethernet enabled";
            NET_FLAG = true;
        } else if (conn == NetConnectionCheck.TYPE_NOT_CONNECTED) {
            status = context.getString(R.string.message_no_internet_connection);
            NET_FLAG = false;
            //Toast.makeText(context, status, Toast.LENGTH_LONG).show();
        }
    }

    public static boolean getNetStatus(Context context) {
        getConnectivityStatusString(context);
        return NET_FLAG;
    }
}
