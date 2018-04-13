package com.example.administrator.beerviewer.data.source.splash;

import com.example.administrator.beerviewer.data.BeerModel;
import com.example.administrator.beerviewer.network.BeerApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class SplashRemoteDataSource implements SplashDataSource {

    @Inject
    public SplashRemoteDataSource(BeerApiService apiService) {
        this.apiService = apiService;
//        apiService = BeerViewerClient.createService(BeerApiService.class);
    }

    private final BeerApiService apiService;

    @Override
    public Single<List<BeerModel>> getAllBeers() {
        return apiService.getBeers();
    }
}
