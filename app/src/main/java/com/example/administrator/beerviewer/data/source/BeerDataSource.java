package com.example.administrator.beerviewer.data.source;

import com.example.administrator.beerviewer.data.BeerModel;

import java.util.List;

import io.reactivex.Single;

public interface BeerDataSource {

    void addBeers(List<BeerModel> beers);

    interface LoadBeersCallback {

        void onTaskLoaded(List<BeerModel> beers);

        void onDataNotAvailable();
    }

    Single<List<BeerModel>> getBeers();

    void getBeers(int pageStart, int pageEnd, LoadBeersCallback callback);
}
