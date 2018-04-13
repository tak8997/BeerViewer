package com.example.administrator.beerviewer.di;


import com.example.administrator.beerviewer.di.module.SplashModule;
import com.example.administrator.beerviewer.view.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = SplashModule.class)
    @ActivityScope
    abstract SplashActivity splashActivity();
}
