package com.example.administrator.beerviewer.view.splash;


import com.example.administrator.beerviewer.data.source.BeerRepository;
import com.example.administrator.beerviewer.di.ActivityScope;
import com.example.administrator.beerviewer.rx.schedulers.BaseSchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashModule {

    @Provides
    @ActivityScope
    SplashContract.Presenter provideSplashPresenter(BeerRepository beerRepository, BaseSchedulerProvider schedulerProvider) {
        return new SplashPresenter(beerRepository, schedulerProvider);
    }
}
