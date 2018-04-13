package com.example.administrator.beerviewer.data.source;

import com.example.administrator.beerviewer.data.BeerModel;

import java.util.List;

import io.reactivex.Single;

public class SplashLocalDataSource implements SplashDataSource {


    @Override
    public Single<List<BeerModel>> getBeers() {
        return null;
    }
}
