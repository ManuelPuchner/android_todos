package com.example.hello_world;

import android.app.Application;
import android.util.Log;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class HelloWorldApplication extends Application {
    private static final String TAG = HelloWorldApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Application started");
    }
}
