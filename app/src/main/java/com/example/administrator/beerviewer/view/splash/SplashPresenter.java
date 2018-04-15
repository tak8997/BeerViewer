package com.example.administrator.beerviewer.view.splash;

import android.util.Log;

import com.example.administrator.beerviewer.BeerViewerApplication;
import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.BeerModel;
import com.example.administrator.beerviewer.data.source.BeerRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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
                .subscribe(new SingleObserver<List<BeerModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onSuccess(List<BeerModel> beerModels) {
                        List<BeerModel> beers = beerModels;
                        Log.d("SplashActivity", "beer_size : " + beers.size() + "");
                        if(beers != null) {
//                            BeerDatabase.getInstance().addBeers(beers);
                            beerRespository.addBeers(beers);
                            view.startBeerViewActivity();
                        } else
                            view.showFailureMessage(BeerViewerApplication.getInstance().getString(R.string.no_data));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });

    }

    @Override
    public void takeView(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
