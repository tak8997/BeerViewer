package com.example.administrator.beerviewer.view.splash;

import com.example.administrator.beerviewer.BeerViewerApplication;
import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.source.BeerRepository;
import com.example.administrator.beerviewer.di.ActivityScope;
import com.example.administrator.beerviewer.rx.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@ActivityScope
public class SplashPresenter implements SplashContract.Presenter {

    private BeerRepository beerRespository;
    private SplashContract.View view;
    private BaseSchedulerProvider schedulerProvider;

    private Disposable disposable;

    @Inject
    public SplashPresenter(BeerRepository beerRespository, BaseSchedulerProvider schedulerProvider) {
        this.beerRespository = beerRespository;
        this.schedulerProvider = schedulerProvider;
    }

    private void getBeers() {
        disposable = beerRespository.getBeers()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(beerModels -> view.startBeerViewActivity(),
                        throwable -> view.showFailureMessage(BeerViewerApplication.getInstance().getString(R.string.no_data)));
    }

    @Override
    public void subscribe() {
        getBeers();
    }

    @Override
    public void unsubscribe() {
        disposable.dispose();
    }

    @Override
    public void takeView(SplashContract.View view) {
        this.view = view;
        this.view.showSplashAnimation();
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
