package com.example.administrator.beerviewer.view.beerdetail;


import com.example.administrator.beerviewer.data.source.BeerRepository;
import com.example.administrator.beerviewer.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class BeerDetailModule {

    @Provides
    @ActivityScope
    BeerDetailContract.Presenter provideBeerDetailPresenter(BeerRepository beerRepository) {
        return new BeerDetailPresenter(beerRepository);
    }

}
