package com.example.administrator.beerviewer.view.beersview;


import com.example.administrator.beerviewer.data.model.BeerModel;
import com.example.administrator.beerviewer.data.source.BeerDataSource;
import com.example.administrator.beerviewer.rx.schedulers.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BeersViewPresenter implements BeersViewContract.Presenter {

    private BeerDataSource beerRepository;
    private BaseSchedulerProvider schedulerProvider;

    private BeersViewContract.View view;

    private CompositeDisposable compositeDisposable;

    @Inject
    public BeersViewPresenter(BeerDataSource beerRepository, BaseSchedulerProvider schedulerProvider) {
        this.beerRepository = beerRepository;
        this.schedulerProvider = schedulerProvider;

        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getBeers(int pageStart, int perPage) {
        compositeDisposable.clear();
        Disposable disposable = beerRepository
                .getBeers(pageStart, perPage)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(beers-> view.showItems(beers));

        compositeDisposable.add(disposable);
    }

    @Override
    public void getBeersFromBottom(int pageStart, int perPage, final int position) {
        compositeDisposable.clear();
        Disposable disposable = beerRepository
                .getBeers(pageStart, perPage)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(beers-> view.showItemsFromBottom(beers, position));

        compositeDisposable.add(disposable);
    }

    @Override
    public void subscribe() { }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
    }

    @Override
    public void takeView(BeersViewContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
