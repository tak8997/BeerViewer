package com.example.administrator.beerviewer.data.source;

import com.example.administrator.beerviewer.data.model.BeerModel;

import java.util.List;

import io.reactivex.Maybe;

public interface BeerDataSource {

    interface LoadBeersCallback {

        void onTaskLoaded(List<BeerModel> beers);

        void onDataNotAvailable();
    }

    interface GetBeerCallback {

        void onBeerLoaded(BeerModel beer);

        void onDataNotAvailable();
    }

    void addBeers(List<BeerModel> beers);

    Maybe<List<BeerModel>> getBeers();

    void getBeers(int pageStart, int perPage, LoadBeersCallback callback);

    void getBeer(int beerId, GetBeerCallback callback);
}
