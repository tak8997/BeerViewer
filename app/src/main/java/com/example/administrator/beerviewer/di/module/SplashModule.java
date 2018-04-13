package com.example.administrator.beerviewer.di.module;


import com.example.administrator.beerviewer.data.source.BeerDataSource;
import com.example.administrator.beerviewer.di.ActivityScope;
import com.example.administrator.beerviewer.view.splash.SplashContract;
import com.example.administrator.beerviewer.view.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

//    @Provides
//    SplashContract.View provideSplashActivity() {
//        return new SplashActivity();
//    }

    @Provides
    @ActivityScope
    SplashContract.Presenter provideSplashPresenter(BeerDataSource splashRepository) {
        return new SplashPresenter(splashRepository);
    }
}
