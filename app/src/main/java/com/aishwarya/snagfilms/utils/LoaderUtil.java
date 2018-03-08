package com.aishwarya.snagfilms.utils;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;
import android.widget.LinearLayout;

import com.aishwarya.snagfilms.R;
import com.aishwarya.snagfilms.network.utils.NetConnectionCheck;

/**
 * Created by Srinivas Rupani on 12/7/2017.
 * Copyright (c) 2017 Promantra Inc, All rights reserved.
 */
public class LoaderUtil {
    private static LoaderUtil loaderUtil;
    private Dialog dialog;
    private Context mContext;
    private boolean isNetAvailable = true;

    public LoaderUtil() {
    }

    public static LoaderUtil getInstance() {
        if (loaderUtil == null) {
            loaderUtil = new LoaderUtil();
        }
        return loaderUtil;
    }


    public void showProgress(Context context, String message) {
        this.mContext = context;
        if (!NetConnectionCheck.getNetStatus(mContext)) {
            DialogUtil.getInstance().showDialog(mContext, "Internet Connection", mContext.getString(R.string
                .message_no_internet_connection), 1, 2, 0);
            isNetAvailable = false;
            return;
        } else {
            isNetAvailable = true;
            displayProgress(message);
        }

    }

    public void displayProgress(String message) {
        dialog = new Dialog(mContext);
        //dialog.setProgressStyle(Dialog.STYLE_SPINNER);
        /*dialog.setTitle("Please wait");
        if (!TextUtils.isEmpty(message) && message.trim().length() > 0) {
            dialog.setMessage(message + "...");
        } else {
            dialog.setMessage(mContext.getString(R.string.please_wait_message) + "...");
        }*/
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.util_custom_loader_view);
        //dialog.getWindow().setLayout(MyUtils.dip2px(mContext, 100), MyUtils.dip2px(mContext, 100));
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        try {
            dialog.show();
        } catch (Exception e) {
            Log.e("LoaderUtil", "Exce" + mContext);
            e.printStackTrace();
        }
    }

    public void hideProgress() {
        try {
            if (isNetAvailable) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
