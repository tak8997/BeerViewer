package com.example.administrator.beerviewer.view.splash;

import com.example.administrator.beerviewer.BeerViewerApplication;
import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.source.BeerRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;
    private BeerRepository beerRespository;

    @Inject
    public SplashPresenter(BeerRepository beerRespository) {
        this.beerRespository = beerRespository;
    }

    @Override
    public void start() {
        getBeers();
    }

    private void getBeers() {
        beerRespository.getBeers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(beerModels -> view.startBeerViewActivity(),
                        throwable -> view.showFailureMessage(BeerViewerApplication.getInstance().getString(R.string.no_data)));
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
