package com.example.administrator.beerviewer.view.splash;

import com.example.administrator.beerviewer.BeerViewerApplication;
import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.source.BeerRepository;
import com.example.administrator.beerviewer.rx.schedulers.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter implements SplashContract.Presenter {

    private BeerRepository beerRespository;
    private SplashContract.View view;
    private BaseSchedulerProvider schedulerProvider;

    private Disposable disposable;

    @Inject
    public SplashPresenter(BeerRepository beerRespository, SplashContract.View view, BaseSchedulerProvider schedulerProvider) {
        this.beerRespository = beerRespository;
        this.view = view;
        this.schedulerProvider = schedulerProvider;

        this.view.showSplashAnimation();
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
}
