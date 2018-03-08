package com.aishwarya.snagfilms.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aishwarya.snagfilms.R;

/**
 * Created by Srinivas Rupani on 12/7/2017.
 * Copyright (c) 2017 Promantra Inc, All rights reserved.
 */
public class DialogUtil {
    private String TAG = "Dialog";
    private static DialogUtil dialogUtil;
    private Dialog dialog;
    private TextView mDialogTitle, mDialogInfo;
    private Button mNo, mYes;
    private LinearLayout mNoLL;
    private Intent mIntent;
    private Context mContext;

    //    public PreferencesManager preferencesManager;
    public DialogUtil() {
    }

    public static DialogUtil getInstance() {
        if (dialogUtil == null) {
            dialogUtil = new DialogUtil();
        }
        return dialogUtil;
    }

    /**
     * @param context    reference of requesting class (Activity, Fragment, Adapter)
     * @param title      dialog Title
     * @param message    dialog message (information of dialog)
     * @param buttonFlag whether single/two buttons in a dialog (1/0)
     * @param dialogFlag type of dialog (information,success,error,warning)
     * @param classFlag  represents that screen navigation after performing button action
     */
    public void showDialog(final Context context, String title, String message, final int buttonFlag, int dialogFlag,
                           final int classFlag) {
        this.mContext = context;
        Log.e(TAG, "Context==>" + mContext);
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.util_dialog_view);
        dialog.setCanceledOnTouchOutside(false);
        mDialogTitle = (TextView) dialog.findViewById(R.id.dialogTitle);
        mDialogInfo = (TextView) dialog.findViewById(R.id.dialogInformation);
        mNo = (Button) dialog.findViewById(R.id.dialogNo);
        mYes = (Button) dialog.findViewById(R.id.dialogYes);
        mNoLL = (LinearLayout) dialog.findViewById(R.id.dialogNoLL);
        //Enabling scrolling on TextView.
        mDialogInfo.setMovementMethod(new ScrollingMovementMethod());
        mDialogTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher_background, 0, 0, 0);
        switch (dialogFlag) {
            case 0:
                mDialogTitle.setTextColor(mContext.getResources().getColor(R.color.dialog_information));
                mDialogInfo.setTextColor(mContext.getResources().getColor(R.color.dialog_information));
                break;
            case 1:
                mDialogTitle.setTextColor(mContext.getResources().getColor(R.color.dialog_warning));
                mDialogInfo.setTextColor(mContext.getResources().getColor(R.color.dialog_warning));
                break;
            case 2:
                mDialogTitle.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_launcher_background, 0, 0, 0);
                mDialogTitle.setTextColor(mContext.getResources().getColor(R.color.white));
                mDialogInfo.setTextColor(mContext.getResources().getColor(R.color.dialog_error));
                break;
            case 3:
                mDialogTitle.setTextColor(Color.GREEN);
                mDialogInfo.setTextColor(Color.GREEN);
                break;
        }
        mDialogTitle.setText(title);
        mDialogInfo.setText(message);
        dialog.show();
        if (buttonFlag == 1) {
            handleSingleButton(classFlag);
        } else {
            handleTwoButtons(classFlag);
        }
    }

    private void handleTwoButtons(final int classFlag) {
        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = (Activity) mContext;
                switch (classFlag) {
                    case 100:
                        break;
                }
            }
        });
        mNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void handleSingleButton(final int classFlag) {
        mNoLL.setVisibility(View.GONE);
        mYes.setText("OK");
        mYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (classFlag) {
                    case 100:
                        break;
                    case 200:
                        break;
                }
                dialog.dismiss();
            }
        });
    }
}
