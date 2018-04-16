package com.example.administrator.beerviewer.data.source.remote;

import com.example.administrator.beerviewer.data.source.BeerDataSource;
import com.example.administrator.beerviewer.data.model.BeerModel;
import com.example.administrator.beerviewer.network.BeerApiService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class BeerRemoteDataSource implements BeerDataSource {

    @Inject
    public BeerRemoteDataSource(BeerApiService apiService) {
        this.apiService = apiService;
    }

    private final BeerApiService apiService;

    @Override
    public void addBeers(List<BeerModel> beers) { }

    @Override
    public Single<List<BeerModel>> getBeers() {
        return apiService.getBeers();
    }

    @Override
    public void getBeers(int pageStart, int perPage, final LoadBeersCallback callback) {
        apiService
                .getBeers(pageStart, perPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<BeerModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<BeerModel> beerModels) {
                        if (beerModels.size() == 0)
                            callback.onDataNotAvailable();
                        else
                            callback.onTaskLoaded(beerModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void getBeer(int beerId, GetBeerCallback callback) {

    }

}
