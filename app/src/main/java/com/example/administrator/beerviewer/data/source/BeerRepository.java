package com.example.administrator.beerviewer.data.source;

import com.example.administrator.beerviewer.data.source.BeerDataSource;
import com.example.administrator.beerviewer.data.BeerModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class BeerRepository implements BeerDataSource {

    //remote, local을 주입 받는다.
    @Inject
    public BeerRepository(BeerDataSource splashDataSource) {
        this.splashRemoteDataSource = splashDataSource;
    }

    private BeerDataSource splashRemoteDataSource;

    @Override
    public Single<List<BeerModel>> getAllBeers() {
        return splashRemoteDataSource.getAllBeers();
    }
}
