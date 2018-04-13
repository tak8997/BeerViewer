package com.example.administrator.beerviewer.data.source;

import com.example.administrator.beerviewer.data.BeerModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class SplashRepository implements SplashDataSource {

    //remote, local을 주입 받는다.
    @Inject
    public SplashRepository(SplashDataSource splashDataSource) {
        this.splashRemoteDataSource = splashDataSource;
    }

    private SplashDataSource splashRemoteDataSource;

    @Override
    public Single<List<BeerModel>> getBeers() {
        return splashRemoteDataSource.getBeers();
    }
}
