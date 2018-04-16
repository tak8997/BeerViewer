package com.example.administrator.beerviewer.di;


import com.example.administrator.beerviewer.view.beerdetail.BeerDetailActivity;
import com.example.administrator.beerviewer.view.beerdetail.BeerDetailModule;
import com.example.administrator.beerviewer.view.beersview.BeersViewModule;
import com.example.administrator.beerviewer.view.splash.SplashModule;
import com.example.administrator.beerviewer.view.beersview.BeersViewActivity;
import com.example.administrator.beerviewer.view.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = SplashModule.class)
    @ActivityScope
    abstract SplashActivity splashActivity();

    @ContributesAndroidInjector(modules = BeersViewModule.class)
    @ActivityScope
    abstract BeersViewActivity beerViewActivity();

    @ContributesAndroidInjector(modules = BeerDetailModule.class)
    @ActivityScope
    abstract BeerDetailActivity beerDetailActivity();
}
