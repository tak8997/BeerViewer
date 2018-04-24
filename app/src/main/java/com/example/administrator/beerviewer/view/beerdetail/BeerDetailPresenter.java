package com.example.administrator.beerviewer.view.beerdetail;

import com.example.administrator.beerviewer.BeerViewerApplication;
import com.example.administrator.beerviewer.R;
import com.example.administrator.beerviewer.data.source.BeerDataSource;
import com.example.administrator.beerviewer.data.model.BeerModel;

import javax.annotation.Nullable;
import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

public class BeerDetailPresenter implements BeerDetailContract.Presenter {

    private BeerDataSource beerRepository;
    private BeerDetailContract.View view;

    @Nullable
    private int beerId;
    private String beerInfo;

    @Inject
    public BeerDetailPresenter(BeerDataSource beerRepository, @Nullable int beerId) {
        this.beerRepository = beerRepository;
        this.beerId = beerId;
    }

    private void getBeer() {
        if (beerId != -1) {
            beerRepository.getBeer(beerId, new BeerDataSource.GetBeerCallback() {
                @Override
                public void onBeerLoaded(BeerModel beer) {
                    view.showDetailBeer(beer);

                    appendBeerContent(beer);
                }

                @Override
                public void onDataNotAvailable() {
                    view.showFailureMessage(BeerViewerApplication.getInstance().getString(R.string.cannot_load_data));
                }
            });
        }
    }

    public void appendBeerContent(BeerModel beer) {
        beerInfo = beer.getName() +"\n" + beer.getTagline() + "\n" + beer.getDescription() + "\n"
                + beer.getBrewersTips() + "\n" + beer.getContributedBy() + "\n" + beer.getFirstBrewed();
    }

    @Override
    public void processBeerContent() {
        view.showShareDialog(beerInfo);
    }

    @Override
    public void subscribe() {
        getBeer();
    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void takeView(BeerDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        this.view = null;
    }
}
