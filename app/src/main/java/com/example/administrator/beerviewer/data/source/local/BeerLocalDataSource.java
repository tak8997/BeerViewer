package com.example.administrator.beerviewer.data.source.local;

import android.util.Log;

import com.example.administrator.beerviewer.data.model.BeerModel;
import com.example.administrator.beerviewer.data.source.BeerDataSource;
import com.example.administrator.beerviewer.rx.rxbus.Events;
import com.example.administrator.beerviewer.rx.rxbus.RxEventBus;
import com.example.administrator.beerviewer.util.IndexUtil;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Maybe;
import io.reactivex.Single;

@Singleton
public class BeerLocalDataSource implements BeerDataSource{

    private BeerDao beerDao;

    @Inject
    public BeerLocalDataSource(BeerDao beerDao) {
        this.beerDao = beerDao;
    }

    private void sendEventBus() {
        RxEventBus.getInstance().post(new Events.PageEvent());
    }

    @Override
    public void saveBeers(List<BeerModel> beers) {
        List<BeerModel> previous = beerDao.getAllBeers();
        List<BeerModel> inserts = beers;

        beerDao.deleteBeers(previous);
        beerDao.insertBeers(inserts);
    }

    @Override
    public Maybe<List<BeerModel>> getBeers() {
        return null;
    }

//    @Override
//    public void getBeers(int pageStart, int perPage, LoadBeersCallback callback) {
//        int indexStart;
//        if (pageStart == 10) {
//            indexStart = IndexUtil.getIndex(pageStart);
//            sendEventBus();
//        } else
//            indexStart = IndexUtil.getIndex(pageStart);
//
//        List<BeerModel> beers = beerDao.getBeers(indexStart, perPage);
//        Log.d("123123s", pageStart + " , " + indexStart);
//        Log.d("123123s", beers.size() + " !!");
//        if (beers.size() != 0)
//            callback.onTaskLoaded(beers);
//        else
//            callback.onDataNotAvailable();
//    }

    @Override
    public Single<List<BeerModel>> getBeers(int pageStart, int perPage) {
        int indexStart;
        if (pageStart == 10) {
            indexStart = IndexUtil.getIndex(pageStart);
            sendEventBus();
        } else
            indexStart = IndexUtil.getIndex(pageStart);

        return beerDao.getBeers(indexStart, perPage);
    }

    @Override
    public void getBeer(int beerId, GetBeerCallback callback) {
        BeerModel beer = beerDao.getBeer(beerId);
        if (beer != null)
            callback.onBeerLoaded(beer);
        else
            callback.onDataNotAvailable();
    }
}













