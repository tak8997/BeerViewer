package com.example.administrator.beerviewer.view.beersview;


import com.example.administrator.beerviewer.data.model.BeerModel;
import com.example.administrator.beerviewer.data.source.BeerDataSource;
import com.example.administrator.beerviewer.rx.rxbus.Events;
import com.example.administrator.beerviewer.rx.rxbus.RxEventBus;
import com.example.administrator.beerviewer.rx.schedulers.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BeersViewPresenter implements BeersViewContract.Presenter {

    private BeerDataSource beerRepository;
    private BaseSchedulerProvider schedulerProvider;

    private BeersViewContract.View view;

    private CompositeDisposable compositeDisposable;
    private Disposable disposable;

    @Inject
    public BeersViewPresenter(BeerDataSource beerRepository, BaseSchedulerProvider schedulerProvider) {
        this.beerRepository = beerRepository;
        this.schedulerProvider = schedulerProvider;

        this.compositeDisposable = new CompositeDisposable();
        onEventBusCalled();
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

    private void onEventBusCalled() {
        disposable = RxEventBus.getInstance().getBusObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    if (event instanceof Events.PageEvent)
                        view.setPageStart();
                });
    }

    @Override
    public void subscribe() { }

    @Override
    public void unsubscribe() {
        compositeDisposable.clear();
        disposable.dispose();
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
