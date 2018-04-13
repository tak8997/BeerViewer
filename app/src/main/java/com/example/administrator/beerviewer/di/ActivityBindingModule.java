package com.example.administrator.beerviewer.di;


import com.example.administrator.beerviewer.di.module.BeerViewModule;
import com.example.administrator.beerviewer.di.module.SplashModule;
import com.example.administrator.beerviewer.view.beersview.BeersViewActivity;
import com.example.administrator.beerviewer.view.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = SplashModule.class)
    @ActivityScope
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = BeerViewModule.class)
    @ActivityScope
    abstract BeersViewActivity beerViewActivity();
}
