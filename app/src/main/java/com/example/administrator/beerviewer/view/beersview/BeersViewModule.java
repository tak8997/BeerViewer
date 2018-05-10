package com.example.administrator.beerviewer.view.beersview;


import com.example.administrator.beerviewer.data.source.BeerRepository;
import com.example.administrator.beerviewer.di.ActivityScope;
import com.example.administrator.beerviewer.rx.schedulers.BaseSchedulerProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class BeersViewModule {

    @Provides
    @ActivityScope
    BeersViewContract.Presenter provideBeersViewPresenter(BeerRepository beerRepository, BaseSchedulerProvider schedulerProvider) {
        return new BeersViewPresenter(beerRepository, schedulerProvider);
    }
}
