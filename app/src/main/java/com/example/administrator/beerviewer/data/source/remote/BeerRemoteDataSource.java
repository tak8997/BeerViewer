package com.example.administrator.beerviewer.data.source.remote;

import com.example.administrator.beerviewer.data.model.BeerModel;
import com.example.administrator.beerviewer.data.source.BeerDataSource;
import com.example.administrator.beerviewer.network.BeerApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Singleton
public class BeerRemoteDataSource implements BeerDataSource {

    @Inject
    public BeerRemoteDataSource(BeerApiService apiService) {
        this.apiService = apiService;
    }

    private final BeerApiService apiService;

    @Override
    public void saveBeers(List<BeerModel> beers) { }

    @Override
    public Maybe<List<BeerModel>> getBeers() {
        return apiService.getBeers();
    }

    @Override
    public Single<List<BeerModel>> getBeers(int pageStart, int perPage) {
        return apiService.getBeers(pageStart, perPage);
    }

    @Override
    public void getBeer(int beerId, GetBeerCallback callback) {

    }

}
