package com.example.administrator.beerviewer.data.source.local;

import com.example.administrator.beerviewer.data.BeerModel;
import com.example.administrator.beerviewer.data.source.BeerDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class BeerLocalDataSource implements BeerDataSource{

    private BeerDao beerDao;

    @Inject
    public BeerLocalDataSource(BeerDao beerDao) {
        this.beerDao = beerDao;
    }

    @Override
    public void addBeers(List<BeerModel> beers) {
        List<BeerModel> previous = beerDao.getAllBeers();
        List<BeerModel> inserts = beers;

        beerDao.deleteBeers(previous);
        beerDao.insertBeers(inserts);
    }

    @Override
    public Single<List<BeerModel>> getBeers() {
        return null;
    }

    @Override
    public void getBeers(int pageStart, int pageEnd, LoadBeersCallback callback) {
        List<BeerModel> beers = beerDao.getBeers(pageStart, pageEnd);

        if (beers != null)
            callback.onTaskLoaded(beers);
        else
            callback.onDataNotAvailable();
    }

}
