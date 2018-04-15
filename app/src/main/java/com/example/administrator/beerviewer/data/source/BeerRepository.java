package com.example.administrator.beerviewer.data.source;

import com.example.administrator.beerviewer.data.BeerModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class BeerRepository implements BeerDataSource {

    private BeerDataSource beerRemoteDataSource;
    private BeerDataSource beerLocalDataSource;
    private boolean isCache = true;

    @Inject
    public BeerRepository(@Remote BeerDataSource beerRemoteDataSource,
                          @Local BeerDataSource beerLocalDataSource) {
        this.beerRemoteDataSource = beerRemoteDataSource;
        this.beerLocalDataSource = beerLocalDataSource;
    }


    @Override
    public void addBeers(List<BeerModel> beers) {
        beerLocalDataSource.addBeers(beers);
    }

    @Override
    public Single<List<BeerModel>> getBeers() {
        return beerRemoteDataSource.getBeers();
    }

    @Override
    public void getBeers(int pageStart, int pageEnd, final LoadBeersCallback callback) {
        if (isCache) {
            beerLocalDataSource.getBeers(pageStart, pageEnd, new LoadBeersCallback() {
                @Override
                public void onTaskLoaded(List<BeerModel> beers) {
                    callback.onTaskLoaded(beers);
                }

                @Override
                public void onDataNotAvailable() {
                    //TODO : call remote
                }
            });
        }
    }

}
