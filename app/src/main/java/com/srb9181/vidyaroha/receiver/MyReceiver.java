package com.srb9181.vidyaroha.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.srb9181.vidyaroha.utils.Constants;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        SharedPreferences.Editor preferences = context.getSharedPreferences(Constants.APP, Context.MODE_PRIVATE).edit();
        preferences.putInt(Constants.APP_COUNT,0);
        preferences.commit();
    }
}
