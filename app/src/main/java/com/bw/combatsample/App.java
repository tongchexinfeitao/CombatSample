package com.bw.combatsample;

import android.app.Application;

import com.bw.combatsample.util.MyCaughtExeception;

public class App extends Application {
    public static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Thread.setDefaultUncaughtExceptionHandler(new MyCaughtExeception());
    }
}
