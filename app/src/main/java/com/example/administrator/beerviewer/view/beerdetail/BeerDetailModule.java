package com.example.administrator.beerviewer.view.beerdetail;


import com.example.administrator.beerviewer.Constant;
import com.example.administrator.beerviewer.data.source.BeerRepository;
import com.example.administrator.beerviewer.di.ActivityScope;
import com.example.administrator.beerviewer.view.beersview.BeersViewActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class BeerDetailModule {

    @Provides
    @ActivityScope
    static int provideBeerId(BeerDetailActivity activity) {
        return activity.getIntent().getIntExtra(Constant.KEY_BEAR_ID, -1);
    }

    @Provides
    @ActivityScope
    BeerDetailContract.Presenter provideBeerDetailPresenter(BeerRepository beerRepository, int beerId) {
        return new BeerDetailPresenter(beerRepository, beerId);
    }

}
