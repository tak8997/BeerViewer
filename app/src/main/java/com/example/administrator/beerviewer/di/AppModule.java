package com.example.administrator.beerviewer.di;

import android.app.Application;
import android.content.Context;

import com.example.administrator.beerviewer.rx.schedulers.BaseSchedulerProvider;
import com.example.administrator.beerviewer.rx.schedulers.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    BaseSchedulerProvider provideSchedulerProvider() {
        return new SchedulerProvider();
    }
}
