package com.example.administrator.beerviewer.data.source.remote;

import com.example.administrator.beerviewer.data.source.BeerDataSource;
import com.example.administrator.beerviewer.data.BeerModel;
import com.example.administrator.beerviewer.network.BeerApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class BeerRemoteDataSource implements BeerDataSource {

    @Inject
    public BeerRemoteDataSource(BeerApiService apiService) {
        this.apiService = apiService;
    }

    private final BeerApiService apiService;

    @Override
    public void addBeers(List<BeerModel> beers) {

    }

    @Override
    public Single<List<BeerModel>> getBeers() {
        return apiService.getBeers();
    }

    @Override
    public void getBeers(int pageStart, int pageEnd, LoadBeersCallback callback) {

    }


}
