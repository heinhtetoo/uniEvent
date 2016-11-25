package com.sd.heinhtetoo.mytestapp;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;

/**
 * Created by Administrator's user on 25-Nov-16.
 */

public class UniEventApp extends Application {

    public static final String TAG = "UniEventApp";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    public static Context getContext() {
        return context;
    }
}
