package com.example.administrator.beerviewer.data.source;

import com.example.administrator.beerviewer.data.BeerModel;

import java.util.List;

import io.reactivex.Single;

public interface SplashDataSource {

    Single<List<BeerModel>> getBeers();

}
