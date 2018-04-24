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
    BeerDetailContract.View provideBeerDetailActivity() {
        return new BeerDetailActivity();
    }

    @Provides
    @ActivityScope
    static int provideBeerId(BeerDetailActivity activity) {
        return activity.getIntent().getIntExtra(Constant.KEY_BEAR_ID, -1);
    }

    @Provides
    @ActivityScope
    BeerDetailContract.Presenter provideBeerDetailPresenter(BeerRepository beerRepository, BeerDetailContract.View beerDetailActivity, int beerId) {
        return new BeerDetailPresenter(beerRepository, beerDetailActivity, beerId);
    }

}
