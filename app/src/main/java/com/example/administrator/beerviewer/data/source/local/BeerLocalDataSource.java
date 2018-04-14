package com.example.administrator.beerviewer.data.source.local;

import com.example.administrator.beerviewer.data.BeerModel;
import com.example.administrator.beerviewer.data.source.BeerDataSource;

import java.util.List;

import io.reactivex.Single;

public class BeerLocalDataSource implements BeerDataSource{

    @Override
    public Single<List<BeerModel>> getAllBeers() {
        return null;
    }

    @Override
    public void getBeers(int pageStart, int pageEnd, LoadBeersCallback callback) {

    }

}
