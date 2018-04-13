package com.example.administrator.beerviewer;

import android.app.Application;

public class BeerViewerApplication extends Application {

    private static BeerViewerApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BeerViewerApplication getInstance() {
        return instance;
    }
}
