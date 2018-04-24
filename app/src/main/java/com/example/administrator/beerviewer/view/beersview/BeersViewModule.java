package com.example.administrator.beerviewer.view.beersview;


import com.example.administrator.beerviewer.data.source.BeerRepository;
import com.example.administrator.beerviewer.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class BeersViewModule {

    @Provides
    @ActivityScope
    BeersViewContract.View provideBeersViewActivity() {
        return new BeersViewActivity();
    }

    @Provides
    @ActivityScope
    BeersViewContract.Presenter provideBeersViewPresenter(BeerRepository beerRepository, BeersViewContract.View beersViewActivity) {
        return new BeersViewPresenter(beerRepository, beersViewActivity);
    }
}
