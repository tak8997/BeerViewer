package com.example.administrator.beerviewer.view.splash;


import com.example.administrator.beerviewer.data.source.BeerRepository;
import com.example.administrator.beerviewer.di.ActivityScope;

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
    SplashContract.Presenter provideSplashPresenter(BeerRepository beerRepository) {
        return new SplashPresenter(beerRepository);
    }
}
